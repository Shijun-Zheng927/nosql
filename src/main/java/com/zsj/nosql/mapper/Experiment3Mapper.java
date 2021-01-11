package com.zsj.nosql.mapper;

import com.zsj.nosql.pojo.NewCourse;
import com.zsj.nosql.pojo.NewScore;
import com.zsj.nosql.pojo.NewStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Experiment3Mapper {
    @Update("insert ignore into new_score values(#{id}, #{cid}, " +
            "#{cname}, #{credit}, #{hours}, #{attribute}, #{score})")
    public void insert1(NewScore newScore);

    @Update("insert ignore into new_course values(#{cid}, #{cname}, " +
            "#{credit}, #{hours}, #{attribute})")
    public void insert2(NewCourse newCourse);

    @Update("insert ignore into new_student values(#{sid}, #{name}, " +
            "#{gender})")
    public void insert3(NewStudent newStudent);
}
