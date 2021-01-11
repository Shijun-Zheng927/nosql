package com.zsj.nosql;

import com.zsj.nosql.pojo.Student;
import com.zsj.nosql.service.Experiment3Service;
import com.zsj.nosql.service.Experiment4Service;
import com.zsj.nosql.service.Experiment6Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test6 {
    @Autowired
    private Experiment6Service experiment6Service;

    @Test
    public void test() {
        experiment6Service.allStudent();
    }

    @Test
    public void test2() {
        experiment6Service.query2();
    }

    @Test
    public void test3() {
        experiment6Service.query3();
    }

    @Test
    public void test4() {
        experiment6Service.query4();
    }

    @Test
    public void test5() {
        experiment6Service.query5();
    }

    @Test
    public void test6() {
        experiment6Service.query6();
    }

    @Test
    public void test8() {
        experiment6Service.query8();
    }

    @Test
    public void test9() {
        experiment6Service.query9();
    }

    @Autowired
    private Experiment3Service experiment3Service;

    @Test
    public void test10() {
        experiment3Service.test();
    }
}
