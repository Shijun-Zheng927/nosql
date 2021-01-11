package com.zsj.nosql.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBUtils {
    static MongoClient mongoClient = null;
    static MongoDatabase mongoDatabase = null;
    public static MongoDatabase getConnection() {
        if (mongoDatabase != null) {
            return mongoDatabase;
        }
        mongoClient = new MongoClient("localhost", 27017);
        mongoDatabase = mongoClient.getDatabase("user201800301014");
        System.out.println("connect MongoDB success");
        return mongoDatabase;
    }

    public static MongoCursor<Document> getAll(String name) {
        mongoDatabase = MongoDBUtils.getConnection();
        MongoCollection<Document> collection = mongoDatabase.getCollection(name);
        BasicDBObject condition = new BasicDBObject();
        FindIterable<Document> findIterable = collection.find(condition);
        return findIterable.iterator();
    }
}
