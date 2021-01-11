package com.zsj.nosql.mapper;

import com.zsj.nosql.pojo.Course;
import com.zsj.nosql.pojo.Student;
import com.zsj.nosql.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Experiment4Mapper {
    @Update("update student where sid=#{sid} set name=#{name}," +
            "sex=#{sex},age=#{age},birthday=#{birthday},dname=#{dname},classname=#{classname}")
    public void updateStudent(Student student);

    @Update("update course where cid=#{cid} set name=#{name}," +
            "fcid=#{fcid},credit=#{credit}")
    public void updateCourse(Course course);

    @Update("update teacher where tid=#{tid} set name=#{name}" +
            "sex=#{sex}, age=#{age}, dname=#{dname}")
    public void updateTeacher(Teacher teacher);
}
