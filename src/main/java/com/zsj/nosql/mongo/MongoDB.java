package com.zsj.nosql.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class MongoDB {
    /**
     * @方法简介：声明静态配置，链接MongoDB数据库
     */
    static MongoClient mongoClient = null;
    static MongoDatabase mgdb = null;
    static {
        try {
            // 链接到MongoDB服务器
            mongoClient = new MongoClient("localhost", 27017);
            // 链接MongoDB数据库
            mgdb = mongoClient.getDatabase("test");
            System.out.println("----数据库连接成功----");
            System.out.println("当前的数据是 : " + mgdb.getName());
            // 如果MongoDB运行在安全模式需要添加如下认证.
            // boolean auth = db.authenticate(myUserName, myPassword);
            // System.out.println("Authentication: "+auth);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * @方法简介 创建集合，列出所有的集合名称
     * @collectionName 集合名称
     */
    public static int CreateCollection(String collectionName) {
        for (String name : mgdb.listCollectionNames()) {
            if (name.equals(collectionName)) {
                System.out.println("需要创建的集合已经存在");
                return 0;
            }
        }
        // 创建集合
        mgdb.createCollection(collectionName, new CreateCollectionOptions().capped(false).sizeInBytes(0x100000));
        System.out.println("----集合创建成功----");
        System.out.println("当前数据库中的所有集合是：");
        for (String name : mgdb.listCollectionNames()) {
            System.out.print(name+" ");
        }
        return 1;
    }

    /**
     * 移除集合
     *
     * @param collectionName
     */
    public static void RemoveCollection(String collectionName) {
        for (String name : mgdb.listCollectionNames()) {
            if (name.equals(collectionName)){
                mgdb.getCollection(collectionName).drop();
                System.out.println("----移除集合" + collectionName+"----");
            }
        }
    }

    /**
     * @方法简介 插入一个文档
     * @param collectionName
     */
    public static void InsertDocument(String collectionName, Map<String, Object> map) {
        try {
            MongoCollection<Document> collection = mgdb.getCollection(collectionName);
            collection.insertOne(new Document(map));
            System.out.println("\n"+"----插入一个文档----");
            findAllDoc(collectionName);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * @方法简介 插入多个文档
     * @param collectionName
     *            是一个key-value集合
     * @throws Exception
     */
    public static void insertMany(String collectionName, ArrayList<Map<String, Object>> mapList) throws Exception {
        List<Document> documents = new ArrayList<Document>();
        MongoCollection<Document> collection = mgdb.getCollection(collectionName);
        for (Map<String, Object> map : mapList) {
            Document doc = new Document(map);
            documents.add(doc);
        }
        collection.insertMany(documents);
        System.out.println("----插入多个文档----");
        findAllDoc(collectionName);
    }

    /**
     * @方法简介 更新查到的第一个文档
     * @query 更新前的值
     * @update 更新后的值
     */
    public static void updateDocument(String collectionName, BasicDBObject query, BasicDBObject update) {
        try {
            MongoCollection<Document> collection = mgdb.getCollection(collectionName);
            UpdateResult result = collection.updateOne(query, new Document("$set",update));
            System.out.println("----更新查到的第一个文档-----");
            findAllDoc(collectionName);
            System.out.println("更新的统计结果是："+result.toString());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * @方法简介 更新所有的文档
     * @query 更新前的值
     * @update 更新后的值
     */
    public static void updateManyDocument(String collectionName, BasicDBObject query, BasicDBObject update) {
        try {
            MongoCollection<Document> collection = mgdb.getCollection(collectionName);
            UpdateResult result = collection.updateMany(query, new Document("$set",update));
            System.out.println("----更新所有的文档----");
            findAllDoc(collectionName);
            System.out.println("更新的统计结果是："+result.toString());

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * @方法简介 移除第一个符合条件的文档
     */
    public static void removeFirstDoc(String collectionName, String key, Object value) {
        try {
            MongoCollection<Document> collection = mgdb.getCollection(collectionName);
            System.out.println("----移除第一个符合条件的文档前----");
            collection.find().forEach(printBlock);
            // 删除文档集合
            DeleteResult result = collection.deleteOne(Filters.eq(key, value));
            System.out.println("----移除第一个符合条件的文档后----");
            findAllDoc(collectionName);
            System.out.println("移除的统计结果是："+result.toString());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * @方法简介 移除所有符合条件的文档
     */
    public static void removeAllDoc(String collectionName, String key, Object value) {
        try {
            MongoCollection<Document> collection = mgdb.getCollection(collectionName);
            System.out.println("----移除所有符合条件的文档前----");
            collection.find().forEach(printBlock);
            // 删除文档集合
            DeleteResult result = collection.deleteMany(Filters.eq(key, value));
            System.out.println("----移除所有符合条件的文档后----");
            findAllDoc(collectionName);
            System.out.println("移除的统计结果是："+result.toString());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    static Block<Document> printBlock = new Block<Document>() {
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };

    /**
     * @方法简介 检索指定集合中所有的文档
     * @param collectionName
     *            集合名称
     */
    public static void findAllDoc(String collectionName) throws Exception {

        MongoCollection<Document> collection = mgdb.getCollection(collectionName);
        System.out.println(collectionName + "集合选择成功,文档集合如下：");
        /**
         * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
         * 通过游标遍历检索出的文档集合
         **/
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next());
        }
    }

    /**
     * 根据条件查询
     *
     * @param filter
     * @查询条件 注意Bson的几个实现类，BasicDBObject, BsonDocument, BsonDocumentWrapper,
     *       CommandResult, Document, RawBsonDocument
     * @return 返回集合列表
     */
    public static void findBy(Bson filter, String collectionName) {
        System.out.println("----查询指定条件的文档----");
        MongoCollection<Document> collection = mgdb.getCollection(collectionName);
        FindIterable<Document> iterables = collection.find(filter);
        MongoCursor<Document> cursor = iterables.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    public static void main(String[] args) throws Exception {
        String collectionName = "student";// j集合名称
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("sid", "2023222");
        map1.put("name", "wangfang");
        map1.put("age", 12);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("sid", "2023224");
        map2.put("name", "lihong");
        map2.put("age", 13);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("sid", "2023220");
        map3.put("name", "jianghong");
        map3.put("age", 13);
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("sid", "2023221");
        map4.put("name", "liqian");
        map4.put("age", 16);
        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
        BasicDBObject query1 = new BasicDBObject("name", "lihong");
        BasicDBObject update1 = new BasicDBObject("name", "xumao");
        BasicDBObject query2 = new BasicDBObject("age", 13);
        BasicDBObject update2 = new BasicDBObject("age", 17);
        // 移除集合
        RemoveCollection(collectionName);
        // 创建集合
        CreateCollection(collectionName);
        // 插入一个文档
        InsertDocument(collectionName, map1);
        // 插入多个文档
        insertMany(collectionName,mapList);
        // 更新第一个文档
        updateDocument(collectionName, query1, update1);
        // 更新所有的文档
        updateManyDocument(collectionName, query2, update2);
        // 删除第一个文档
        removeFirstDoc(collectionName,"sid","2023224");
        // 删除所有满足条件的文档
        removeAllDoc(collectionName,"age",17);
        //查询所有的文档
        findAllDoc(collectionName);
        //查询指定条件的文档:Document,BasicDBOject用法基本一致
        System.out.println("小于等于12的输出集合为：");
        findBy(new Document("age", new BasicDBObject("$lte", 12)),collectionName);
        System.out.println("大于12小于20的输出集合为：");
        findBy(new Document("age", new BasicDBObject("$gt", 12).append("$lte",20)),collectionName);
    }

}
