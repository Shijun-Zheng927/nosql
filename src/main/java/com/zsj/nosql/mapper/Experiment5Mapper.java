package com.zsj.nosql.mapper;

import com.zsj.nosql.pojo.Course;
import com.zsj.nosql.pojo.SelectCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface Experiment5Mapper {
    @Select("select * from course")
    public List<Course> getCourse();

    @Select("select * from student_course where sid=#{sid}")
    public List<SelectCourse> getSelectCourse(Integer sid);

    @Update("insert into student_course(sid, cid) values (#{sid}, #{cid}")
    public Integer addCourse(Integer sid, Integer cid);

    @Select("select sid from student_course where sid=#{sid} and cid=#{fcid}")
    public Integer isSelected(Integer sid, Integer fcid);
}
