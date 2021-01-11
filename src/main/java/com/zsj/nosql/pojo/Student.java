package com.zsj.nosql.pojo;

public class Student {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private Long sid;
    private String name;
    private String sex;
    private Integer age;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", sid=" + sid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", dname='" + dname + '\'' +
                ", classname=" + classname +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getClassname() {
        return classname;
    }

    public void setClassname(Integer classname) {
        this.classname = classname;
    }

    private String birthday;
    private String dname;
    private Integer classname;
}
