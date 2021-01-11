package com.zsj.nosql;

import com.zsj.nosql.service.Experiment3Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test3 {
    @Autowired
    private Experiment3Service experiment3Service;

    @Test
    public void test() {
        experiment3Service.insertXls("");
    }
}
