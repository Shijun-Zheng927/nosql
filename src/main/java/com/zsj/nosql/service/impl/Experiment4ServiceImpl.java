package com.zsj.nosql.service.impl;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.zsj.nosql.mongo.MongoDB;
import com.zsj.nosql.mongo.MongoDBUtils;
import com.zsj.nosql.pojo.Course;
import com.zsj.nosql.pojo.Student;
import com.zsj.nosql.pojo.Teacher;
import com.zsj.nosql.service.Experiment4Service;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Experiment4ServiceImpl implements Experiment4Service {
    private MongoDatabase mongoDatabase;

    @Override
    public void updateStudent(Student student) {
        BasicDBObject query = new BasicDBObject();
        query.append("_id", new ObjectId(student.getId()));
        BasicDBObject update = new BasicDBObject();
        update.append("sid", Double.parseDouble(student.getSid().toString()));
        update.append("name", student.getName());
        update.append("sex", student.getSex());
        update.append("age", Double.parseDouble(student.getAge().toString()));
        update.append("birthday", student.getBirthday());
        update.append("dname", student.getDname());
        update.append("class", Double.parseDouble(student.getClassname().toString()));
        MongoDB.updateDocument("student", query, update);
    }

    @Override
    public void updateCourse(Course course) {
        BasicDBObject query = new BasicDBObject();
        query.append("_id", new ObjectId(course.getId()));
        BasicDBObject update = new BasicDBObject();
        update.append("cid", Double.parseDouble(course.getCid().toString()));
        update.append("name", course.getName());
        update.append("fcid", Double.parseDouble(course.getFcid().toString()));
        update.append("credit", course.getCredit());
        MongoDB.updateDocument("course", query, update);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        BasicDBObject query = new BasicDBObject();
        query.append("_id", new ObjectId(teacher.getId()));
        BasicDBObject update = new BasicDBObject();
        update.append("tid", Double.parseDouble(teacher.getTid().toString()));
        update.append("name", teacher.getName());
        update.append("sex", teacher.getSex());
        update.append("age", Double.parseDouble(teacher.getAge().toString()));
        update.append("dname", teacher.getDname());
        MongoDB.updateDocument("teacher", query, update);

    }

    public List<Student> allStudent() {
        MongoCursor<Document> cursor = MongoDBUtils.getAll("student");
        List<Student> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            String s = d.toJson();
            Student student = gson.fromJson(s, Student.class);
            student.setId(d.get("_id").toString());
            String classname = d.get("class").toString();
            student.setClassname(Integer.parseInt(classname.substring(0, classname.indexOf("."))));
//            System.out.println(student.toString());
            result.add(student);
        }
        return result;
    }

    public List<Course> allCourse() {
        MongoCursor<Document> cursor = MongoDBUtils.getAll("course");
        List<Course> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            String s = d.toJson();
//            System.out.println(s);
            Course course = gson.fromJson(s, Course.class);
            course.setId(d.get("_id").toString());
//            System.out.println(course.toString());
            result.add(course);
        }
        return result;
    }

    public List<Teacher> allTeacher() {
        MongoCursor<Document> cursor = MongoDBUtils.getAll("teacher");
        List<Teacher> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            String s = d.toJson();
//            System.out.println(s);
            Teacher teacher = gson.fromJson(s, Teacher.class);
            teacher.setId(d.get("_id").toString());
//            System.out.println(teacher.toString());
            result.add(teacher);
        }
        return result;
    }
}
