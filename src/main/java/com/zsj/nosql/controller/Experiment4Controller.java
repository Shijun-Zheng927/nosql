package com.zsj.nosql.controller;

import com.zsj.nosql.pojo.Course;
import com.zsj.nosql.pojo.Student;
import com.zsj.nosql.pojo.Teacher;
import com.zsj.nosql.service.Experiment4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class Experiment4Controller {
    @Autowired
    private Experiment4Service experiment4Service;

    @RequestMapping("/allStudent")
    public List<Student> allStudent() {
        System.out.println("allStudent");
        return experiment4Service.allStudent();
    }

    @RequestMapping("/allCourse")
    public List<Course> allCourse() {
        System.out.println("allCourse");
        return experiment4Service.allCourse();
    }

    @RequestMapping("/allTeacher")
    public List<Teacher> allTeacher() {
        System.out.println("allTeacher");
        return experiment4Service.allTeacher();
    }

    @RequestMapping("/updateStudent")
    public Integer updateStudent(@RequestBody Student student) {
        System.out.println("updateStudent");
//        System.out.println(student.toString());
        experiment4Service.updateStudent(student);
        return 1;
    }

    @RequestMapping("/updateCourse")
    public Integer updateCourse(@RequestBody Course course) {
        System.out.println("updateCourse");
        experiment4Service.updateCourse(course);
        return 1;
    }

    @RequestMapping("/updateTeacher")
    public Integer updateTeacher(@RequestBody Teacher teacher) {
        System.out.println("updateTeacher");
        experiment4Service.updateTeacher(teacher);
        return 1;
    }
}
