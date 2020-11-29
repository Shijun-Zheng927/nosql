package com.zsj.nosql;

import com.zsj.nosql.service.Experiment2Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Experiment2Test {
    @Autowired
    private Experiment2Service experiment2Service;

    @Test
    public void num0Test() {
        experiment2Service.test();
    }

    @Test
    public void num1() {
        experiment2Service.num1();
    }

    @Test
    public void num2() {
        experiment2Service.num2();
    }

    @Test
    public void num3() {
        experiment2Service.num3();
    }

    @Test
    public void num4() {
        experiment2Service.num4();
    }

    @Test
    public void num5() {
        experiment2Service.num5();
    }

    @Test
    public void num6() {
        experiment2Service.num6();
    }

    @Test
    public void num7() {
        experiment2Service.num7();
    }

    @Test
    public void num8() {
        experiment2Service.num8();
    }

    @Test
    public void num9() {
        experiment2Service.num9();
    }

    @Test
    public void num10() {
        experiment2Service.num10();
    }
}
