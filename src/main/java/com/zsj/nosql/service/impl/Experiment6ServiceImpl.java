package com.zsj.nosql.service.impl;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.zsj.nosql.mongo.MongoDBUtils;
import com.zsj.nosql.pojo.NewScore;
import com.zsj.nosql.pojo.Student;
import com.zsj.nosql.service.Experiment6Service;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Experiment6ServiceImpl implements Experiment6Service {
    @Override
    public List<NewScore> allStudent() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("new_score");
        Document sub_group = new Document();
        sub_group.put("_id", "$cname");
        Document group = new Document("$group", sub_group);
        List<Document> aggregateList = new ArrayList<Document>();
        aggregateList.add(group);
        AggregateIterable<Document> resultset = collection.aggregate(aggregateList);
        MongoCursor<Document> cursor = resultset.iterator();
        List<NewScore> result = new ArrayList<>();
        while(cursor.hasNext()) {
            Document d = cursor.next();
//            System.out.println(d.get("_id").toString());
            NewScore newScore = new NewScore();
            newScore.setId(d.get("_id").toString());
            result.add(newScore);
        }
        return result;
    }

    @Override
    public List<NewScore> query2() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("new_score");

//        Document sub_match = new Document();
//        sub_match.put("appId", app_id);
//        sub_match.put("leaveTime", new Document("$gt", beginDate).append("$lt", endDate));

        Document sub_group = new Document();
        sub_group.put("_id", "$id");
        sub_group.put("score", new Document("$avg", "$score"));

//        Document match = new Document("$match", sub_match);
        Document group = new Document("$group", sub_group);
        Document sort = new Document("$sort", new Document("score", -1));

        List<Document> aggregateList = new ArrayList<Document>();
//        aggregateList.add(match);
        aggregateList.add(group);
        aggregateList.add(sort);

//        JSONObject ret_obj = new JSONObject();
        AggregateIterable<Document> resultset = collection.aggregate(aggregateList);
        MongoCursor<Document> cursor = resultset.iterator();
        int num = 0;
        List<NewScore> result = new ArrayList<>();
        while(cursor.hasNext() && num < 10) {
            num++;
            Document d = cursor.next();
//            System.out.println(d.toJson());
            NewScore newScore = new NewScore();
            String id = d.get("_id").toString();
            newScore.setId(id.substring(0, id.indexOf(".")));
            newScore.setScore(d.get("score").toString());
            result.add(newScore);
            MongoCollection<Document> collection1 = mongoDatabase.getCollection("new_student");
            BasicDBObject condition1 = new BasicDBObject();
            condition1.append("sid", Double.parseDouble(id.substring(0, id.indexOf("."))));
            FindIterable<Document> findIterable1 = collection1.find(condition1);
            MongoCursor<Document> cursor1 = findIterable1.iterator();
            while (cursor1.hasNext()) {
                Document d1 = cursor1.next();
//                System.out.println(d1.get("name"));
                newScore.setAttribute(d1.get("name").toString());
            }
            System.out.println(newScore);
        }
        System.out.println(result.size());
        return result;
    }

    @Override
    public List<NewScore> query3() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("new_score");
        Document sub_group = new Document();
        sub_group.put("_id", "$id");
        sub_group.put("count", new Document("$sum", 1));
        Document group = new Document("$group", sub_group);
        Document sort = new Document("$sort", new Document("count", -1));

        List<Document> aggregateList = new ArrayList<Document>();
        aggregateList.add(group);
        aggregateList.add(sort);

        AggregateIterable<Document> resultset = collection.aggregate(aggregateList);
        MongoCursor<Document> cursor = resultset.iterator();
        int num = 0;
        List<NewScore> result = new ArrayList<>();
        while(cursor.hasNext() && num < 10) {
            num++;
            Document d = cursor.next();
            System.out.println(d.toJson());
            NewScore newScore = new NewScore();
            String id = d.get("_id").toString();
            newScore.setId(id.substring(0, id.indexOf(".")));
            newScore.setScore(d.get("count").toString());
            result.add(newScore);
            MongoCollection<Document> collection1 = mongoDatabase.getCollection("new_student");
            BasicDBObject condition1 = new BasicDBObject();
            condition1.append("sid", Double.parseDouble(id.substring(0, id.indexOf("."))));
            FindIterable<Document> findIterable1 = collection1.find(condition1);
            MongoCursor<Document> cursor1 = findIterable1.iterator();
            while (cursor1.hasNext()) {
                Document d1 = cursor1.next();
//                System.out.println(d1.get("name"));
                newScore.setAttribute(d1.get("name").toString());
            }
            System.out.println(newScore);
        }
        System.out.println(result.size());
        return result;
    }

    @Override
    public List<NewScore> query4() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("new_score");
        BasicDBObject sub_group = new BasicDBObject();
        BasicDBObject detailes = new BasicDBObject();
        detailes.append("id", "$id");
//        detailes.append("cname", "$cname");
        sub_group.append("_id", detailes);
        sub_group.append("score", new BasicDBObject("$max", "$score"));
        BasicDBObject group = new BasicDBObject("$group", sub_group);

        BasicDBObject sub_project = new BasicDBObject();
        sub_project.append("_id", 1);
        sub_project.append("id", "$_id.id");
        sub_project.append("cname", 1);
        sub_project.append("score", 1);
        BasicDBObject project = new BasicDBObject("$project", sub_project);

        List<BasicDBObject> aggregateList = new ArrayList<>();
//        aggregateList.add(project);
        aggregateList.add(group);
        aggregateList.add(project);

        AggregateIterable<Document> resultset = collection.aggregate(aggregateList);
        MongoCursor<Document> cursor = resultset.iterator();
        List<NewScore> result = new ArrayList<>();
        int num = 0;
        while(cursor.hasNext()) {
            num++;
            Document d = cursor.next();
//            System.out.println(d.toJson());
            NewScore newScore = new NewScore();
            String id = d.get("id").toString();
            newScore.setId(id.substring(0, id.indexOf(".")));
            newScore.setScore(d.get("score").toString());
            result.add(newScore);
            MongoCollection<Document> collection1 = mongoDatabase.getCollection("new_student");
            BasicDBObject condition1 = new BasicDBObject();
            condition1.append("sid", Double.parseDouble(id.substring(0, id.indexOf("."))));
            FindIterable<Document> findIterable1 = collection1.find(condition1);
            MongoCursor<Document> cursor1 = findIterable1.iterator();
            while (cursor1.hasNext()) {
                Document d1 = cursor1.next();
//                System.out.println(d1.get("name"));
                newScore.setAttribute(d1.get("name").toString());
            }
//            System.out.println(newScore);
        }
        System.out.println(num);
        for (NewScore score : result) {
            MongoDatabase mongoDatabase1 = MongoDBUtils.getConnection();
            MongoCollection<Document> collection1 = mongoDatabase1.getCollection("new_score");
            BasicDBObject condition = new BasicDBObject();
            condition.append("id", Double.parseDouble(score.getId()));
            condition.append("score", Double.parseDouble(score.getScore()));
            FindIterable<Document> findIterable1 = collection1.find(condition);
            MongoCursor<Document> cursor1 = findIterable1.iterator();
            while (cursor1.hasNext()) {
                Document d = cursor1.next();
//                String s = d.toJson();
//                System.out.println(s);
//                System.out.println(d.get("cname"));
                score.setCname(d.get("cname").toString());
            }
            System.out.println(score.toString());
        }
        return result;
    }

    @Override
    public List<NewScore> query5() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("new_score");

        Document sub_match = new Document();
        sub_match.put("score", new Document("$gte", 90));

        Document sub_group = new Document();
        sub_group.put("_id", "$id");
        sub_group.put("count", new Document("$sum", 1));

        Document match = new Document("$match", sub_match);
        Document group = new Document("$group", sub_group);

        List<Document> aggregateList = new ArrayList<>();
        aggregateList.add(match);
        aggregateList.add(group);

        AggregateIterable<Document> resultset = collection.aggregate(aggregateList);
        MongoCursor<Document> cursor = resultset.iterator();
        List<NewScore> result = new ArrayList<>();
        int num = 0;
        while(cursor.hasNext()) {
            num++;
            Document d = cursor.next();
            NewScore newScore = new NewScore();
            String id = d.get("_id").toString();
            MongoCollection<Document> collection1 = mongoDatabase.getCollection("new_student");
            BasicDBObject condition1 = new BasicDBObject();
            condition1.append("sid", Double.parseDouble(id.substring(0, id.indexOf("."))));
            FindIterable<Document> findIterable1 = collection1.find(condition1);
            MongoCursor<Document> cursor1 = findIterable1.iterator();
            while (cursor1.hasNext()) {
                Document d1 = cursor1.next();
//                System.out.println(d1.get("name"));
                newScore.setCid(d1.get("name").toString());
            }
            newScore.setId(id.substring(0, id.indexOf(".")));
            newScore.setScore(d.get("count").toString());
            result.add(newScore);
        }
        sub_match = new Document();
        sub_match.put("score", new Document("$gte", 75).append("$lt", 90));
        match = new Document("$match", sub_match);

        aggregateList = new ArrayList<>();
        aggregateList.add(match);
        aggregateList.add(group);

        resultset = collection.aggregate(aggregateList);
        cursor = resultset.iterator();
        while(cursor.hasNext()) {
            Document d = cursor.next();
            String id = d.get("_id").toString();
            id = id.substring(0, id.indexOf("."));
            for (NewScore score : result) {
                if (id.equals(score.getId())) {
                    score.setAttribute(d.get("count").toString());
                }
            }
        }

        sub_match = new Document();
        sub_match.put("score", new Document("$gte", 60).append("$lt", 75));
        match = new Document("$match", sub_match);

        aggregateList = new ArrayList<>();
        aggregateList.add(match);
        aggregateList.add(group);

        resultset = collection.aggregate(aggregateList);
        cursor = resultset.iterator();
        while(cursor.hasNext()) {
            Document d = cursor.next();
//            System.out.println(d.toJson());
            String id = d.get("_id").toString();
            id = id.substring(0, id.indexOf("."));
            for (NewScore score : result) {
                if (id.equals(score.getId())) {
                    score.setHours(d.get("count").toString());
                }
            }
        }

        sub_match = new Document();
        sub_match.put("score", new Document("$lt", 75));
        match = new Document("$match", sub_match);

        aggregateList = new ArrayList<>();
        aggregateList.add(match);
        aggregateList.add(group);

        resultset = collection.aggregate(aggregateList);
        cursor = resultset.iterator();
        while(cursor.hasNext()) {
            Document d = cursor.next();
//            System.out.println(d.toJson());
            String id = d.get("_id").toString();
            id = id.substring(0, id.indexOf("."));
            for (NewScore score : result) {
                if (id.equals(score.getId())) {
                    score.setCname(d.get("count").toString());
                    System.out.println(score);
                }
            }
        }
        return result;
    }

    @Override
    public List<NewScore> query6() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("new_score");
        Document sub_group = new Document();
        sub_group.put("_id", "$cname");
        sub_group.put("count", new Document("$sum", 1));
        Document group = new Document("$group", sub_group);

        List<Document> aggregateList = new ArrayList<Document>();
        aggregateList.add(group);

        AggregateIterable<Document> resultset = collection.aggregate(aggregateList);
        MongoCursor<Document> cursor = resultset.iterator();
        List<NewScore> result = new ArrayList<>();
        while(cursor.hasNext()) {
            Document d = cursor.next();
//            System.out.println(d.toJson());
            NewScore newScore = new NewScore();
            String id = d.get("_id").toString();
            newScore.setCname(id);
            newScore.setScore(d.get("count").toString());
            result.add(newScore);
//            System.out.println(newScore);
        }

        sub_group = new Document();
        sub_group.put("_id", "$cname");
        sub_group.put("count", new Document("$avg", "$score"));
        group = new Document("$group", sub_group);

        aggregateList = new ArrayList<Document>();
        aggregateList.add(group);

        resultset = collection.aggregate(aggregateList);
        cursor = resultset.iterator();
        while(cursor.hasNext()) {
            Document d = cursor.next();
            String id = d.get("_id").toString();
            for (NewScore score : result) {
                if (id.equals(score.getCname())) {
                    score.setHours(d.get("count").toString());
                }
            }
        }
        for (NewScore score : result) {
            System.out.println(score.toString());
        }

        return result;
    }

    @Override
    public List<NewScore> query8() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("new_score");
        Document sub_group = new Document();
        sub_group.put("_id", "$cname");
        sub_group.put("count", new Document("$avg", "$score"));
        Document group = new Document("$group", sub_group);
        Document sort = new Document("$sort", new Document("count", -1));

        List<Document> aggregateList = new ArrayList<Document>();
        aggregateList.add(group);
        aggregateList.add(sort);

        AggregateIterable<Document> resultset = collection.aggregate(aggregateList);
        MongoCursor<Document> cursor = resultset.iterator();
        int num = 0;
        List<NewScore> result = new ArrayList<>();
        while(cursor.hasNext() && num < 10) {
            num++;
            Document d = cursor.next();
            System.out.println(d.toJson());
            NewScore newScore = new NewScore();
            String id = d.get("_id").toString();
            newScore.setCname(id);
            newScore.setScore(d.get("count").toString());
            result.add(newScore);
            System.out.println(newScore);
        }
        System.out.println(result.size());
        return result;
    }

    @Override
    public List<NewScore> query9() {
        MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection("new_score");
        Document sub_group = new Document();
        sub_group.put("_id", "$cname");
        sub_group.put("count", new Document("$sum", 1));
        Document group = new Document("$group", sub_group);
        Document sort = new Document("$sort", new Document("count", -1));

        List<Document> aggregateList = new ArrayList<Document>();
        aggregateList.add(group);
        aggregateList.add(sort);

        AggregateIterable<Document> resultset = collection.aggregate(aggregateList);
        MongoCursor<Document> cursor = resultset.iterator();
        int num = 0;
        List<NewScore> result = new ArrayList<>();
        while(cursor.hasNext() && num < 10) {
            num++;
            Document d = cursor.next();
            System.out.println(d.toJson());
            NewScore newScore = new NewScore();
            String id = d.get("_id").toString();
            newScore.setCname(id);
            newScore.setScore(d.get("count").toString());
            result.add(newScore);
            System.out.println(newScore);
        }
        System.out.println(result.size());
        return result;
    }

}
