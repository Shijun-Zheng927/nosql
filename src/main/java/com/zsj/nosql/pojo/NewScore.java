package com.zsj.nosql.pojo;

public class NewScore {
    private String id;
    private String cid;
    private String cname;
    private Double credit;
    private String hours;
    private String attribute;
    private String score;

    @Override
    public String toString() {
        return "NewScore{" +
                "id='" + id + '\'' +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", credit=" + credit +
                ", hours='" + hours + '\'' +
                ", attribute='" + attribute + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
