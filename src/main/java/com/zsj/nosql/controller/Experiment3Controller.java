package com.zsj.nosql.controller;

import com.zsj.nosql.pojo.*;
import com.zsj.nosql.service.Experiment3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@Controller
public class Experiment3Controller {
    @Autowired
    private Experiment3Service experiment3Service;

    @RequestMapping("/upload")
    @ResponseBody
    public Integer upload(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = "D:/IDEA/nosql/src/main/resources/static/";
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + file.getOriginalFilename();
            String path = filePath + fileName;
            File f = new File(path);
            System.out.println(path);
            file.transferTo(f);
            experiment3Service.insertXls(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @RequestMapping("/insertScore")
    @ResponseBody
    public Integer insertTeacher(@RequestBody Teacher teacher) {
        experiment3Service.insertTeacher(teacher);
        return 1;
    }

    @RequestMapping("/insertCourse")
    @ResponseBody
    public Integer insetCourse(@RequestBody Course course) {
        experiment3Service.insetCourse(course);
        return 1;
    }

    @RequestMapping("/insertStudent")
    @ResponseBody
    public Integer insertStudent(@RequestBody Student student) {
        experiment3Service.insertStudent(student);
        return 1;
    }
}
