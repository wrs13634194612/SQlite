package com.example.schoolsqlite.bean;


public class GradeBean {
    public String id;
    public String nombrealu;
    public String gradoalu;
    public String secalu;
    public String fehca_ins;
    public byte [] insimg;

    public GradeBean() {
        this.id = id;
        this.nombrealu = nombrealu;
        this.gradoalu = gradoalu;
        this.secalu = secalu;
        this.fehca_ins = fehca_ins;
        this.insimg = insimg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrealu() {
        return nombrealu;
    }

    public void setNombrealu(String nombrealu) {
        this.nombrealu = nombrealu;
    }

    public String getGradoalu() {
        return gradoalu;
    }

    public void setGradoalu(String gradoalu) {
        this.gradoalu = gradoalu;
    }

    public String getSecalu() {
        return secalu;
    }

    public void setSecalu(String secalu) {
        this.secalu = secalu;
    }

    public String getFehca_ins() {
        return fehca_ins;
    }

    public void setFehca_ins(String fehca_ins) {
        this.fehca_ins = fehca_ins;
    }

    public byte[] getInsimg() {
        return insimg;
    }

    public byte[] setInsimg(byte[] insimg) {
        this.insimg = insimg;
        return insimg;
    }
}
