package com.example.schoolsqlite.sql;


public class DBConstants {

    public static final String TABLA_ALUMNO = " alumno";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_APELLIDO = "apellido";
    public static final String CAMPO_TELFONO = "telefono";
    public static final String CAMPO_DIRECCION = "direccion";

    //原来在这里决定代码长度的
    public static final String CREAR_TABLA_ALUMNO = " CREATE TABLE " + TABLA_ALUMNO + " ( " + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_NOMBRE + " TEXT, " + CAMPO_APELLIDO + " TEXT, " + CAMPO_TELFONO + " TEXT, " + CAMPO_DIRECCION + " TEXT ) ";

    public static final String TABLA_GRADO="grado";
    public static final String CGRADO_ID="id";
    public static final String CGRADO_DESCRIPCION="descripcion";

    public static final String CREAR_TABLA_GRADOS =" CREATE TABLE " + TABLA_GRADO + " (" + CGRADO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CGRADO_DESCRIPCION + " TEXT)";

    public static final String TABLA_INSCRIPCION="inscripcion";
    public static final String INS_ID="id";
    public static final String INS_ALU="nombrealu";
    public static final String INS_GRA="gradoalu";
    public static final String INS_SEC="secalu";
    public static final String INS_FECHA = "fecha_ins";
    public static final String INS_IMG = "insimg";

    public static final String CREAR_TABLA_INSCRIPCION =" CREATE TABLE " + TABLA_INSCRIPCION + " (" + INS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + INS_ALU + " TEXT," + INS_GRA + " TEXT,"  + INS_SEC + " TEXT," + INS_FECHA + " TEXT, " + INS_IMG +" BLOB)";



}
