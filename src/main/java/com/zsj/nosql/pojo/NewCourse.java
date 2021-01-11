package com.zsj.nosql.pojo;

public class NewCourse {
    private String cid;
    private String cname;
    private Double credit;
    private Integer hours;
    private String attribute;

    @Override
    public String toString() {
        return "NewCourse{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", credit=" + credit +
                ", hours=" + hours +
                ", attribute='" + attribute + '\'' +
                '}';
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
