package com.zsj.nosql.service;

import com.zsj.nosql.pojo.Course;
import com.zsj.nosql.pojo.Student;
import com.zsj.nosql.pojo.Teacher;

import java.util.List;

public interface Experiment4Service {
    public void updateStudent(Student student);

    public void updateCourse(Course course);

    public void updateTeacher(Teacher teacher);

    public List<Student> allStudent();

    public List<Course> allCourse();

    public List<Teacher> allTeacher();
}
