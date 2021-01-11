package com.zsj.nosql.service.impl;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.zsj.nosql.mapper.Experiment2Mapper;
import com.zsj.nosql.mapper.Experiment3Mapper;
import com.zsj.nosql.mongo.MongoDB;
import com.zsj.nosql.mongo.MongoDBUtils;
import com.zsj.nosql.pojo.*;
import com.zsj.nosql.service.Experiment3Service;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Experiment3ServiceImpl implements Experiment3Service {
    @Autowired
    private Experiment3Mapper experiment3Mapper;
    @Autowired
    private Experiment2Mapper experiment2Mapper;


    @Override
    public void test() {
        List<Student> students = experiment2Mapper.search1();
        for (Student s : students) {
            System.out.println(s);
        }
    }

    @Override
    public void insertXls(String path) {
        File excel = new File(path);
        try {
            FileInputStream fis = new FileInputStream(excel);
            Workbook wb = new HSSFWorkbook(fis);
            Sheet sheet = wb.getSheet("成绩单");
            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();
            String[] name = {"id", "cid", "cname", "credit", "hours", "attribute", "score"};
//            for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
//                Cell cell = row.getCell(cIndex);
//                if (cell != null) {
//                    name[cIndex] = cell.toString();
////                    System.out.println(name[cIndex]);
//                }
//            }
//            System.out.println("firstRowIndex: "+firstRowIndex);
//            System.out.println("lastRowIndex: "+lastRowIndex);
            for(int rIndex = firstRowIndex + 1; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                System.out.println("成绩单rIndex: " + rIndex);
                Row row = sheet.getRow(rIndex);

                if (row != null) {
                    Map<String, Object> map = new HashMap<>();
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();

                    NewScore newScore = new NewScore();
                    newScore.setId(row.getCell(0).toString());
                    newScore.setCid(row.getCell(1).toString());
                    newScore.setCname(row.getCell(2).toString());
                    newScore.setCredit(Double.parseDouble(row.getCell(3).toString()));
                    newScore.setHours(row.getCell(4).toString());
                    newScore.setAttribute(row.getCell(5).toString());
                    newScore.setScore(row.getCell(6).toString());
                    experiment3Mapper.insert1(newScore);
//                    System.out.println(newScore);
                }
            }

            sheet = wb.getSheet("课程表");
            firstRowIndex = sheet.getFirstRowNum();
            lastRowIndex = sheet.getLastRowNum();
            String[] name2 = {"cid", "cname", "credit", "hours", "attribute"};
            for(int rIndex = firstRowIndex + 1; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                System.out.println("课程表rIndex: " + rIndex);
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    NewCourse newCourse = new NewCourse();
                    newCourse.setCid(row.getCell(0).toString());
                    newCourse.setCname(row.getCell(1).toString());
                    newCourse.setCredit(Double.parseDouble(row.getCell(2).toString()));
                    newCourse.setHours(Integer.parseInt(row.getCell(3).toString()
                            .substring(0, row.getCell(3).toString().indexOf("."))));
                    newCourse.setAttribute(row.getCell(4).toString());
                    experiment3Mapper.insert2(newCourse);
                }
            }

            sheet = wb.getSheet("学生表");
            firstRowIndex = sheet.getFirstRowNum();
//            lastRowIndex = sheet.getLastRowNum();
            lastRowIndex = 658;
            String[] name3 = {"sid", "name", "gender"};
            for(int rIndex = firstRowIndex + 1; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                System.out.println("学生表rIndex: " + rIndex);
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    NewStudent newStudent = new NewStudent();
                    newStudent.setSid(Integer.parseInt(row.getCell(0).toString()
                            .substring(0, row.getCell(0).toString().indexOf("."))));
                    newStudent.setName(row.getCell(1).toString());
                    newStudent.setGender(row.getCell(2).toString());
                    experiment3Mapper.insert3(newStudent);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        File excel = new File(path);
//        try {
//            FileInputStream fis = new FileInputStream(excel);
//            Workbook wb = new HSSFWorkbook(fis);
//            Sheet sheet = wb.getSheet("成绩单");
//            int firstRowIndex = sheet.getFirstRowNum();
//            int lastRowIndex = sheet.getLastRowNum();
//            String[] name = {"id", "cid", "cname", "credit", "hours", "attribute", "score"};
////            for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
////                Cell cell = row.getCell(cIndex);
////                if (cell != null) {
////                    name[cIndex] = cell.toString();
//////                    System.out.println(name[cIndex]);
////                }
////            }
////            System.out.println("firstRowIndex: "+firstRowIndex);
////            System.out.println("lastRowIndex: "+lastRowIndex);
//            for(int rIndex = firstRowIndex + 1; rIndex <= lastRowIndex; rIndex++) {   //遍历行
//                System.out.println("成绩单rIndex: " + rIndex);
//                Row row = sheet.getRow(rIndex);
//
//                if (row != null) {
//                    Map<String, Object> map = new HashMap<>();
//                    int firstCellIndex = row.getFirstCellNum();
//                    int lastCellIndex = row.getLastCellNum();
//
//                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
//                        Cell cell = row.getCell(cIndex);
//                        if (cell != null) {
////                            System.out.println(cell.toString());
//                            if (!cell.toString().equals("")) {
//                                if (cIndex == 0 || cIndex == 3 || cIndex == 4 || cIndex == 6) {
//                                    map.put(name[cIndex], Double.parseDouble(cell.toString()));
//                                } else {
//                                    map.put(name[cIndex], cell.toString());
//                                }
//                            }
//                        }
////                        System.out.println(map);
//                    }
//                    if (map.size() == 0) {
//                        break;
//                    }
//                    MongoDB.InsertDocument("new_score", map);
//                }
////                System.out.println(list.size());
////                MongoDB.insertMany("new_score", list);
//            }
//
//            sheet = wb.getSheet("课程表");
//            firstRowIndex = sheet.getFirstRowNum();
//            lastRowIndex = sheet.getLastRowNum();
//            String[] name2 = {"cid", "cname", "credit", "hours", "attribute"};
//            for(int rIndex = firstRowIndex + 1; rIndex <= lastRowIndex; rIndex++) {   //遍历行
//                System.out.println("课程表rIndex: " + rIndex);
//                Row row = sheet.getRow(rIndex);
//                if (row != null) {
//                    Map<String, Object> map = new HashMap<>();
//                    int firstCellIndex = row.getFirstCellNum();
//                    int lastCellIndex = row.getLastCellNum();
//                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
//                        Cell cell = row.getCell(cIndex);
//                        if (cell != null) {
//                            if (!cell.toString().equals("")) {
//                                if (cIndex == 2 || cIndex == 3) {
//                                    map.put(name2[cIndex], Double.parseDouble(cell.toString()));
//                                } else {
//                                    map.put(name2[cIndex], cell.toString());
//                                }
//                            }
//                        }
//                    }
//                    if (map.size() == 0) {
//                        break;
//                    }
//                    MongoDB.InsertDocument("new_course", map);
//                }
//            }
//
//            sheet = wb.getSheet("学生表");
//            firstRowIndex = sheet.getFirstRowNum();
//            lastRowIndex = sheet.getLastRowNum();
//            String[] name3 = {"sid", "name", "gender"};
//            for(int rIndex = firstRowIndex + 1; rIndex <= lastRowIndex; rIndex++) {   //遍历行
//                System.out.println("学生表rIndex: " + rIndex);
//                Row row = sheet.getRow(rIndex);
//                if (row != null) {
//                    Map<String, Object> map = new HashMap<>();
//                    int firstCellIndex = row.getFirstCellNum();
//                    int lastCellIndex = row.getLastCellNum();
//                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
//                        Cell cell = row.getCell(cIndex);
//                        if (cell != null) {
//                            if (!cell.toString().equals("")) {
//                                if (cIndex == 0) {
//                                    map.put(name3[cIndex], Double.parseDouble(cell.toString()));
//                                } else {
//                                    map.put(name3[cIndex], cell.toString());
//                                }
//                            }
//                        }
//                    }
//                    if (map.size() == 0) {
//                        break;
//                    }
//                    MongoDB.InsertDocument("new_student", map);
//                }
//            }
////            MongoDatabase mongoDatabase = MongoDBUtils.getConnection();
////            MongoCollection<Document> collection = mongoDatabase.getCollection("new_score");
////            FindIterable<Document> findIterable = collection.find();
////            MongoCursor<Document> cursor = findIterable.iterator();
////            List<NewScore> result = new ArrayList<>();
////            Gson gson = new Gson();
////            while (cursor.hasNext()) {
////                String s = cursor.next().toJson();
//////                System.out.println(s);
////                NewScore newScore = gson.fromJson(s, NewScore.class);
////                System.out.println(newScore.toString());
////                result.add(newScore);
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void insertTeacher(Teacher teacher) {
        Map<String, Object> map = new HashMap<>();
        map.put("tid", Double.parseDouble(teacher.getTid().toString()));
        map.put("name", teacher.getName());
        map.put("sex", teacher.getSex());
        map.put("dname", teacher.getDname());
        map.put("age", Double.parseDouble(teacher.getAge().toString()));
        MongoDB.InsertDocument("teacher", map);
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", score.getId());
//        map.put("cid", score.getCid());
//        map.put("cname", score.getCname());
//        map.put("credit", score.getCredit());
//        map.put("hours", score.getHours());
//        map.put("attribute", score.getAttribute());
//        map.put("score", score.getScore());
//        MongoDB.InsertDocument("new_score", map);
    }

    @Override
    public void insetCourse(Course course) {
        Map<String, Object> map = new HashMap<>();
        map.put("cid", Double.parseDouble(course.getCid().toString()));
        map.put("name", course.getName());
        map.put("fcid", Double.parseDouble(course.getFcid().toString()));
        map.put("credit", course.getCredit());
        MongoDB.InsertDocument("course", map);
    }

    @Override
    public void insertStudent(Student student) {
        Map<String, Object> map = new HashMap<>();
        map.put("sid", Double.parseDouble(student.getSid().toString()));
        map.put("name", student.getName());
        map.put("sex", student.getSex());
        map.put("age", Double.parseDouble(student.getAge().toString()));
        map.put("birthday", student.getBirthday());
        map.put("dname", student.getDname());
        map.put("class", Double.parseDouble(student.getClassname().toString()));
        MongoDB.InsertDocument("student", map);
    }
}
