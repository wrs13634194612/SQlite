package com.example.schoolsqlite.bean;

public class DegreeBean {

    private String id;
    private String degreeName;

    @Override
    public String toString() {
        return "DegreeBean{" +
                "id='" + id + '\'' +
                ", degreeName='" + degreeName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }
}
