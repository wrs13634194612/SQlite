package com.example.schoolsqlite.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(@Nullable Context context,
                                @Nullable String name,
                                @Nullable SQLiteDatabase.CursorFactory factory,
                                int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBConstants.CREAR_TABLA_ALUMNO);
        sqLiteDatabase.execSQL(DBConstants.CREAR_TABLA_GRADOS);
        sqLiteDatabase.execSQL(DBConstants.CREAR_TABLA_INSCRIPCION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLA_ALUMNO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLA_GRADO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLA_INSCRIPCION);
    }
}
