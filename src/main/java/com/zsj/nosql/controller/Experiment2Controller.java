package com.zsj.nosql.controller;

import com.zsj.nosql.pojo.Course;
import com.zsj.nosql.pojo.Student;
import com.zsj.nosql.pojo.Teacher;
import com.zsj.nosql.service.Experiment2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
public class Experiment2Controller {
    @Autowired
    private Experiment2Service experiment2Service;

    @RequestMapping("/e2_1")
    @ResponseBody
    public List<Student> e2_1() {
        return experiment2Service.num1();
    }

    @RequestMapping("/e2_2")
    @ResponseBody
    public List<Student> e2_2() {
        return experiment2Service.num2();
    }

    @RequestMapping("/e2_3")
    @ResponseBody
    public List<Student> e2_3() {
        return experiment2Service.num3();
    }

    @RequestMapping("/e2_4")
    @ResponseBody
    public List<Student> e2_4() {
        return experiment2Service.num4();
    }

    @RequestMapping("/e2_5")
    @ResponseBody
    public List<Student> e2_5() {
        return experiment2Service.num5();
    }

    @RequestMapping("/e2_6")
    @ResponseBody
    public List<Course> e2_6() {
        return experiment2Service.num6();
    }

    @RequestMapping("/e2_7")
    @ResponseBody
    public List<Course> e2_7() {
        return experiment2Service.num7();
    }

    @RequestMapping("/e2_8")
    @ResponseBody
    public List<Teacher> e2_8() {
        return experiment2Service.num8();
    }

    @RequestMapping("/e2_9")
    @ResponseBody
    public List<Teacher> e2_9() {
        return experiment2Service.num9();
    }

    @RequestMapping("/e2_10")
    @ResponseBody
    public List<Teacher> e2_10() {
        return experiment2Service.num10();
    }
}
