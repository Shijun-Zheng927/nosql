package com.zsj.nosql.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

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
}
