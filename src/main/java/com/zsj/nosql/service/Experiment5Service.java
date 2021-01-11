package com.zsj.nosql.service;

import com.zsj.nosql.pojo.CourseDetails;
import com.zsj.nosql.pojo.SelectCourse;

import java.util.List;

public interface Experiment5Service {
    public Integer selectCourse(SelectCourse selectCourse);

    public List<CourseDetails> getCourse(Long sid);
}
