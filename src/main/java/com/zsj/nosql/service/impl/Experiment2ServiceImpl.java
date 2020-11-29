package com.zsj.nosql.service.impl;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.zsj.nosql.mongo.MongoDBUtils;
import com.zsj.nosql.pojo.Course;
import com.zsj.nosql.pojo.Student;
import com.zsj.nosql.pojo.Teacher;
import com.zsj.nosql.service.Experiment2Service;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Experiment2ServiceImpl implements Experiment2Service {
    @Override
    public void test() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");

        BasicDBObject condition = new BasicDBObject();
        condition.append("age", new BasicDBObject("$gte", 20));
//        BasicDBObject key = new BasicDBObject("name", 1);
        BasicDBObject keys = new BasicDBObject();
        keys.put("name", 1);
        FindIterable<Document> findIterable = collection.find(condition);
//        FindIterable<Document> findIterable = collection.find(condition).projection(keys);
        MongoCursor<Document> cursor = findIterable.iterator();

//        FindIterable<Document> findIterable = collection.find();
//        MongoCursor<Document> cursor = findIterable.iterator();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            Document d = cursor.next();
//            System.out.println(d.get("class"));
//            System.out.println(cursor.next().get("class"));
            String s = d.toJson();
//            System.out.println(s);
            Student student = gson.fromJson(s, Student.class);
            String classname = d.get("class").toString();
            student.setClassname(Integer.parseInt(classname.substring(0, classname.indexOf("."))));
            System.out.println(student.toString());
        }
    }

    @Override
    public List<Student> num1() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");
        BasicDBObject condition = new BasicDBObject();
        condition.append("age", new BasicDBObject("$lt", 20));
        FindIterable<Document> findIterable = collection.find(condition);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Student> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            String s = d.toJson();
            Student student = gson.fromJson(s, Student.class);
            String classname = d.get("class").toString();
            student.setClassname(Integer.parseInt(classname.substring(0, classname.indexOf("."))));
            System.out.println(student.toString());
            result.add(student);
        }
        return result;
    }

    @Override
    public List<Student> num2() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");
        BasicDBObject condition = new BasicDBObject();
        condition.append("age", new BasicDBObject("$lt", 20));
        condition.append("dname", "SC");
        FindIterable<Document> findIterable = collection.find(condition);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Student> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            String s = d.toJson();
            System.out.println(s);
            Student student = gson.fromJson(s, Student.class);
            String classname = d.get("class").toString();
            student.setClassname(Integer.parseInt(classname.substring(0, classname.indexOf("."))));
            System.out.println(student.toString());
            result.add(student);
        }
        return result;
    }

    @Override
    public List<Student> num3() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");
        BasicDBObject condition = new BasicDBObject();
        FindIterable<Document> findIterable = collection.find(condition);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Student> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            String s = d.toJson();
            System.out.println(s);
            Student student = gson.fromJson(s, Student.class);
            String classname = d.get("class").toString();
            student.setClassname(Integer.parseInt(classname.substring(0, classname.indexOf("."))));
            System.out.println(student.toString());
            result.add(student);
        }
        return result;
    }

    @Override
    public List<Student> num4() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");
        BasicDBObject condition = new BasicDBObject();
        BasicDBObject keys = new BasicDBObject();
        keys.put("name", 1);
        keys.put("age", 1);
        FindIterable<Document> findIterable = collection.find(condition).projection(keys);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Student> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            String s = d.toJson();
            System.out.println(s);
            Student student = gson.fromJson(s, Student.class);
            System.out.println(student.toString());
            result.add(student);
        }
        return result;
    }

    @Override
    public List<Student> num5() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");
        BasicDBObject condition = new BasicDBObject();
        condition.append("age", new BasicDBObject("$lt", 20));
        BasicDBObject keys = new BasicDBObject();
        keys.put("name", 1);
        keys.put("sex", 1);
        FindIterable<Document> findIterable = collection.find(condition).projection(keys);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Student> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            Document d = cursor.next();
            String s = d.toJson();
            System.out.println(s);
            Student student = gson.fromJson(s, Student.class);
            System.out.println(student.toString());
            result.add(student);
        }
        return result;
    }

    @Override
    public List<Course> num6() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("course");
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Course> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            String s = cursor.next().toJson();
            System.out.println(s);
            Course course = gson.fromJson(s, Course.class);
            System.out.println(course.toString());
            result.add(course);
        }
        return result;
    }

    @Override
    public List<Course> num7() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("course");
        BasicDBObject condition = new BasicDBObject();
        condition.append("fcid", 300001);
        BasicDBObject keys = new BasicDBObject();
        keys.put("name", 1);
        FindIterable<Document> findIterable = collection.find(condition).projection(keys);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Course> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            String s = cursor.next().toJson();
            System.out.println(s);
            Course course = gson.fromJson(s, Course.class);
            System.out.println(course.toString());
            result.add(course);
        }
        return result;
    }

    @Override
    public List<Teacher> num8() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("teacher");
        BasicDBObject condition = new BasicDBObject();
        condition.append("age", new BasicDBObject("$gt", 50));
        FindIterable<Document> findIterable = collection.find(condition);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Teacher> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            String s = cursor.next().toJson();
            System.out.println(s);
            Teacher teacher = gson.fromJson(s, Teacher.class);
            System.out.println(teacher.toString());
            result.add(teacher);
        }
        return result;
    }

    @Override
    public List<Teacher> num9() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("teacher");
        BasicDBObject condition = new BasicDBObject();
        condition.append("sex", "M");
        FindIterable<Document> findIterable = collection.find(condition);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Teacher> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            String s = cursor.next().toJson();
            System.out.println(s);
            Teacher teacher = gson.fromJson(s, Teacher.class);
            System.out.println(teacher.toString());
            result.add(teacher);
        }
        return result;
    }

    @Override
    public List<Teacher> num10() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("teacher");
        BasicDBObject condition = new BasicDBObject();
        condition.append("dname", "CS");
        FindIterable<Document> findIterable = collection.find(condition);
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Teacher> result = new ArrayList<>();
        Gson gson = new Gson();
        while (cursor.hasNext()) {
            String s = cursor.next().toJson();
            System.out.println(s);
            Teacher teacher = gson.fromJson(s, Teacher.class);
            System.out.println(teacher.toString());
            result.add(teacher);
        }
        return result;
    }
}
