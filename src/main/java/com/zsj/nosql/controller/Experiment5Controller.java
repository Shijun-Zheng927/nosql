package com.zsj.nosql.controller;

import com.zsj.nosql.pojo.CourseDetails;
import com.zsj.nosql.pojo.SelectCourse;
import com.zsj.nosql.pojo.Student;
import com.zsj.nosql.service.Experiment5Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class Experiment5Controller {
    @Autowired
    private Experiment5Service experiment5Service;

    @RequestMapping("/selectCourse")
    public Integer selectCourse(@RequestBody SelectCourse selectCourse) {
        return experiment5Service.selectCourse(selectCourse);
    }

    @RequestMapping("/allSelect")
    public List<CourseDetails> allSelect(@RequestBody Student student) {
        return experiment5Service.getCourse(student.getSid());
    }
}
