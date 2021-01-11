package com.zsj.nosql.pojo;

public class NewStudent {
    private Integer sid;
    private String name;
    private String gender;

    @Override
    public String toString() {
        return "NewStudent{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
