package com.zsj.nosql.pojo;

public class Course {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private Integer cid;
    private String name;
    private Integer fcid;
    private Double credit;

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", fcid=" + fcid +
                ", credit=" + credit +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFcid() {
        return fcid;
    }

    public void setFcid(Integer fcid) {
        this.fcid = fcid;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
