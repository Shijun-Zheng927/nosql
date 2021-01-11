package com.zsj.nosql.controller;

import com.zsj.nosql.pojo.NewScore;
import com.zsj.nosql.pojo.SelectCourse;
import com.zsj.nosql.service.Experiment6Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class Experiment6Controller {
    @Autowired
    private Experiment6Service experiment6Service;

    @RequestMapping("/e6_1")
    public List<NewScore> query1() {
        return experiment6Service.allStudent();
    }

    @RequestMapping("/e6_2")
    public List<NewScore> query2() {
        return experiment6Service.query2();
    }

    @RequestMapping("/e6_3")
    public List<NewScore> query3() {
        return experiment6Service.query3();
    }

    @RequestMapping("/e6_4")
    public List<NewScore> query4() {
        return experiment6Service.query4();
    }

    @RequestMapping("/e6_5")
    public List<NewScore> query5() {
        return experiment6Service.query5();
    }

    @RequestMapping("/e6_6")
    public List<NewScore> query6() {
        return experiment6Service.query6();
    }

    @RequestMapping("/e6_8")
    public List<NewScore> query8() {
        return experiment6Service.query8();
    }

    @RequestMapping("/e6_9")
    public List<NewScore> query9() {
        return experiment6Service.query9();
    }
}
