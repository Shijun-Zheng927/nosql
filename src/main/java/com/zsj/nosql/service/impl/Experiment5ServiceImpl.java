package com.zsj.nosql.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.zsj.nosql.mongo.MongoDB;
import com.zsj.nosql.mongo.MongoDBUtils;
import com.zsj.nosql.pojo.CourseDetails;
import com.zsj.nosql.pojo.SelectCourse;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Experiment5ServiceImpl implements com.zsj.nosql.service.Experiment5Service {
    @Override
    public Integer selectCourse(SelectCourse selectCourse) {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");
        BasicDBObject condition = new BasicDBObject();
        condition.append("sid", selectCourse.getSid());
//        condition.append("cid", selectCourse.getCid());
        FindIterable<Document> findIterable = collection.find(condition);
        MongoCursor<Document> cursor = findIterable.iterator();
        if (!cursor.hasNext()) {
            return 0;
        }
        mongoDatabase = MongoDBUtils.getConnection();
        collection = mongoDatabase.getCollection("student_course");
        condition = new BasicDBObject();
        condition.append("sid", selectCourse.getSid());
        condition.append("cid", selectCourse.getCid());
        findIterable = collection.find(condition);
        cursor = findIterable.iterator();
        if (cursor.hasNext()) {
            return 0;
        }
        mongoDatabase = MongoDBUtils.getConnection();
        collection = mongoDatabase.getCollection("student_course");
        condition = new BasicDBObject();
        condition.append("sid", selectCourse.getSid());
        condition.append("cid", selectCourse.getFcid());
        findIterable = collection.find(condition);
        cursor = findIterable.iterator();
        if (!cursor.hasNext()) {
            return 0;
        }
        collection = mongoDatabase.getCollection("teacher_course");
        condition = new BasicDBObject();
        condition.append("cid", selectCourse.getCid());
        findIterable = collection.find(condition);
        cursor = findIterable.iterator();
        Integer tid = null;
        if (cursor.hasNext()) {
            Document d = cursor.next();
            String s = d.get("tid").toString();
            tid = Integer.parseInt(s.substring(0, s.indexOf(".")));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("sid", (double)selectCourse.getSid());
        map.put("cid", (double)selectCourse.getCid());
        map.put("tid", (double)tid);
        MongoDB.InsertDocument("student_course", map);
        return 1;
    }

    @Override
    public List<CourseDetails> getCourse(Long sid) {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("student_course");
        BasicDBObject condition = new BasicDBObject();
        condition.append("sid", sid);
        FindIterable<Document> findIterable = collection.find(condition);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<CourseDetails> result = new ArrayList<>();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            CourseDetails courseDetails = new CourseDetails();
            courseDetails.setSid(sid);
            String cidStr = d.get("cid").toString();
            courseDetails.setCid(Integer.parseInt(cidStr.substring(0, cidStr.indexOf("."))));
            if (d.get("score") != null) {
                courseDetails.setScore(Double.parseDouble(d.get("score").toString()));
            }
            String tidStr = d.get("tid").toString();
            Integer tid = Integer.parseInt(tidStr.substring(0, tidStr.indexOf(".")));
            MongoCollection<Document> collection1 = mongoDatabase.getCollection("teacher");
            BasicDBObject condition1 = new BasicDBObject();
            condition1.append("tid", tid);
            FindIterable<Document> findIterable1 = collection1.find(condition1);
            MongoCursor<Document> cursor1 = findIterable1.iterator();
            if (cursor1.hasNext()) {
                Document d1 = cursor1.next();
                String name = d1.get("name").toString();
                courseDetails.setTeacher(name);
            }
            result.add(courseDetails);
        }
        return result;
    }
}
