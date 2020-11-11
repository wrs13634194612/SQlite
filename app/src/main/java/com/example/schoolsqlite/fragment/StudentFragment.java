package com.example.schoolsqlite.fragment;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.schoolsqlite.R;
import com.example.schoolsqlite.adapter.StudentAdapter;
import com.example.schoolsqlite.bean.StudentBean;
import com.example.schoolsqlite.sql.ConexionSQLiteHelper;
import com.example.schoolsqlite.sql.DBConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentFragment extends Fragment implements View.OnClickListener {
    private Button btn_add, btn_search, btn_update, btn_delete, btn_all;
    private EditText add_et_name, add_et_sex, add_et_tel, add_et_profession;  //新增
    private EditText update_et_name, update_et_sex, update_et_tel, update_et_profession;   //编辑
    private EditText et_search, et_update, et_delete;
    private TextView search_tv_name, search_tv_sex, search_tv_tel, search_tv_profession;
    private ConexionSQLiteHelper dbSQLiteHelper;
    private Button student_delete_all;
    private SQLiteDatabase dbSQLiteDatabase;
    private List<StudentBean> studentList;
    private StudentBean studentBean;
    private GridView mGridView;
    private StudentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        btn_add = view.findViewById(R.id.btn_add);
        btn_search = view.findViewById(R.id.btn_search);
        btn_update = view.findViewById(R.id.btn_update);
        btn_delete = view.findViewById(R.id.btn_delete);
        student_delete_all = view.findViewById(R.id.btn_delete);
        btn_all = view.findViewById(R.id.btn_all);
        et_search = view.findViewById(R.id.et_search);
        et_update = view.findViewById(R.id.et_update);
        et_delete = view.findViewById(R.id.et_delete);

        add_et_name = view.findViewById(R.id.add_et_name);
        add_et_sex = view.findViewById(R.id.add_et_sex);
        add_et_tel = view.findViewById(R.id.add_et_tel);
        add_et_profession = view.findViewById(R.id.add_et_profession);
        update_et_name = view.findViewById(R.id.update_et_name);
        update_et_sex = view.findViewById(R.id.update_et_sex);
        update_et_tel = view.findViewById(R.id.update_et_tel);
        update_et_profession = view.findViewById(R.id.update_et_profession);
        mGridView = view.findViewById(R.id.grid_student);

        search_tv_name = view.findViewById(R.id.search_tv_name);
        search_tv_tel = view.findViewById(R.id.search_tv_tel);
        search_tv_sex = view.findViewById(R.id.search_tv_sex);
        search_tv_profession = view.findViewById(R.id.search_tv_profession);

        dbSQLiteHelper = new ConexionSQLiteHelper(getActivity(), "bd_alumnos", null, 1);

        studentList = new ArrayList<>();

        btn_add.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_all.setOnClickListener(this);
        student_delete_all.setOnClickListener(this);

        searchAll();  //查询所有数据
        adapter = new StudentAdapter(studentList, getActivity());
        mGridView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                addData(add_et_name.getText().toString(),
                        add_et_sex.getText().toString(),
                        add_et_tel.getText().toString(),
                        add_et_profession.getText().toString());
                searchAll();
                adapter.setData(studentList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_search:
                String[] searchId = {et_search.getText().toString()};
                searchData(searchId);
                break;
            case R.id.btn_update:
                String[] updateId = {et_update.getText().toString()};
                updateData(update_et_name.getText().toString(),
                        update_et_sex.getText().toString(),
                        update_et_tel.getText().toString(),
                        update_et_profession.getText().toString(), updateId);
                searchAll();
                adapter.setData(studentList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_delete:
                String[] deleteId = {et_delete.getText().toString()};
                deleteData(deleteId);
                searchAll();
                adapter.setData(studentList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_all:
                searchAll();
                adapter.setData(studentList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.student_delete_all:
                deleteAll();
                searchAll();
                adapter.setData(studentList);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    //查询数据库表里面的所有数据
    private void searchAll() {
        dbSQLiteDatabase = dbSQLiteHelper.getReadableDatabase();  //只读权限
        Cursor cursor = dbSQLiteDatabase.rawQuery("SELECT * FROM " + DBConstants.TABLA_ALUMNO, null);
        if (studentList != null) {
            studentList.clear();
        }
        while (cursor.moveToNext()) {
            studentBean = new StudentBean();
            studentBean.setId(cursor.getString(0));
            studentBean.setNombre(cursor.getString(1));
            studentBean.setApellido(cursor.getString(2));
            studentBean.setTelefono(cursor.getString(3));
            studentBean.setDireccion(cursor.getString(4));
            studentList.add((studentBean));
        }
    }

    //新增
    private void addData(String name, String sex, String tel, String profession) {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();  //写的权限
        ContentValues values = new ContentValues();
        values.put(DBConstants.CAMPO_NOMBRE, name);
        values.put(DBConstants.CAMPO_APELLIDO, sex);
        values.put(DBConstants.CAMPO_TELFONO, tel);
        values.put(DBConstants.CAMPO_DIRECCION, profession);
        Long idResultant = dbSQLiteDatabase.insert(DBConstants.TABLA_ALUMNO, DBConstants.CAMPO_ID, values);
        Log.e("TAG", "学生注册：" + idResultant);
    }

    //查找
    private void searchData(String[] searchId) {
        try {
            dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();  //写的权限
            Cursor cursor = dbSQLiteDatabase.rawQuery("SELECT " + DBConstants.CAMPO_NOMBRE
                    + "," + DBConstants.CAMPO_APELLIDO + "," + DBConstants.CAMPO_TELFONO
                    + "," + DBConstants.CAMPO_DIRECCION + " FROM " + DBConstants.TABLA_ALUMNO
                    + " WHERE " + DBConstants.CAMPO_ID + "=? ", searchId);
            cursor.moveToFirst();
            search_tv_name.setText(cursor.getString(0));
            search_tv_sex.setText(cursor.getString(1));
            search_tv_tel.setText(cursor.getString(2));
            search_tv_profession.setText(cursor.getString(3));
        } catch (Exception e) {
            Log.e("TAG", "未注册学生");
        }
    }

    //编辑  名字 性别 电话号码  专业
    private void updateData(String name, String sex, String tel, String profession, String[] updateId) {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();  //写的权限
        ContentValues values = new ContentValues();
        values.put(DBConstants.CAMPO_NOMBRE, name);
        values.put(DBConstants.CAMPO_APELLIDO, sex);
        values.put(DBConstants.CAMPO_TELFONO, tel);
        values.put(DBConstants.CAMPO_DIRECCION, profession);
        dbSQLiteDatabase.update(DBConstants.TABLA_ALUMNO, values, DBConstants.CAMPO_ID + "=?", updateId);
        dbSQLiteDatabase.close();
        Log.e("TAG", "更新成功");
    }

    //删除所有数据
    private void deleteAll() {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();
        dbSQLiteDatabase.delete(DBConstants.TABLA_ALUMNO, null, null);
        dbSQLiteDatabase.close();
        Log.e("TAG", "删除成功");
    }

    //删除
    private void deleteData(String[] deleteId) {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();  //写的权限
        dbSQLiteDatabase.delete(DBConstants.TABLA_ALUMNO, DBConstants.CAMPO_ID + "=?", deleteId);
        dbSQLiteDatabase.close();
        Log.e("TAG", "删除成功");
    }
}