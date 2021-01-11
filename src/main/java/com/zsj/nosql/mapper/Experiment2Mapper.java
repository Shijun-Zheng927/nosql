package com.zsj.nosql.mapper;

import com.zsj.nosql.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Experiment2Mapper {
    @Select("select * from student where age < 20;")
    public List<Student> search1();

    @Select("select * from student where age < 20 and dname=\"SC\";")
    public List<Student> search2();
}
