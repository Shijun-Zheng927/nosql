package com.zsj.nosql;

import com.zsj.nosql.pojo.Student;
import com.zsj.nosql.service.Experiment4Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test4 {
    @Autowired
    private Experiment4Service experiment4Service;

    @Test
    public void test() {
        Student student = new Student();
        student.setId("5fcf331bcaa3fc6bcaf7c8d2");
        experiment4Service.updateStudent(student);
}

    @Test
    public void test1() {
        experiment4Service.allStudent();
    }
}
