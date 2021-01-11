package com.zsj.nosql.mapper;

import com.zsj.nosql.pojo.NewScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Experiment6Mapper {
    @Select("select distinct cname from new_score")
    public List<String> query1();

    @Select("select id,avg(score) from new_score group by id order by avg(score) desc")
    public List<NewScore> query2(Integer sid);
}
