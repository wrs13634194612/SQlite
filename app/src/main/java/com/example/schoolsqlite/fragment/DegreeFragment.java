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
import android.widget.TextView;

import com.example.schoolsqlite.R;
import com.example.schoolsqlite.adapter.DegreeAdapter;
import com.example.schoolsqlite.bean.DegreeBean;
import com.example.schoolsqlite.sql.ConexionSQLiteHelper;
import com.example.schoolsqlite.sql.DBConstants;

import java.util.ArrayList;
import java.util.List;

public class DegreeFragment extends Fragment implements View.OnClickListener {
    private ConexionSQLiteHelper dbSQLiteHelper;
    private SQLiteDatabase dbSQLiteDatabase;
    private Button btn_all;
    private EditText add_et_name;
    private Button btn_add;
    private EditText et_search;
    private TextView search_tv_name;
    private Button btn_search;
    private EditText et_update;
    private EditText update_et_name;
    private Button btn_update;
    private EditText et_delete;
    private Button btn_delete;
    private Button btn_delete_all;
    private List<DegreeBean> lists;
    private DegreeBean degreeBean;
    private GridView mGridView;
    private DegreeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_degree, container, false);


        Log.e("TAG", "DegreeFragment onCreateView：");

        btn_all = view.findViewById(R.id.btn_all_degree);
        add_et_name = view.findViewById(R.id.add_et_name_degree);
        btn_add = view.findViewById(R.id.btn_add_degree);
        et_search = view.findViewById(R.id.et_search_degree);
        search_tv_name = view.findViewById(R.id.search_tv_name_degree);
        btn_search = view.findViewById(R.id.btn_search_degree);
        btn_delete_all = view.findViewById(R.id.degree_delete_all);
        et_update = view.findViewById(R.id.et_update_degree);
        update_et_name = view.findViewById(R.id.update_et_name_degree);
        btn_update = view.findViewById(R.id.btn_update_degree);
        et_delete = view.findViewById(R.id.et_delete_degree);
        btn_delete = view.findViewById(R.id.btn_delete_degree);
        mGridView = view.findViewById(R.id.grid_degree);

        btn_add.setOnClickListener(this);
        btn_all.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_delete_all.setOnClickListener(this);

        dbSQLiteHelper = new ConexionSQLiteHelper(getActivity(), "bd_alumnos", null, 1);
        lists = new ArrayList<>();

        searchAll();
        adapter = new DegreeAdapter(lists, getActivity());
        mGridView.setAdapter(adapter);
        return view;
    }

    //新增
    private void addData(String name) {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();  //数据库写的权限
        ContentValues values = new ContentValues();
        values.put(DBConstants.CGRADO_DESCRIPCION, name);
        Long idResultant = dbSQLiteDatabase.insert(DBConstants.TABLA_GRADO, DBConstants.CGRADO_ID, values);
        Log.e("TAG", "新增：" + idResultant);
    }

    //编辑
    private void updateData(String[] ids, String name) {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.CGRADO_DESCRIPCION, name);
        dbSQLiteDatabase.update(DBConstants.TABLA_GRADO, values, DBConstants.CGRADO_ID + "=?", ids);
        dbSQLiteDatabase.close();
    }

    //删除
    private void deleteData(String[] ids) {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();
        dbSQLiteDatabase.delete(DBConstants.TABLA_GRADO, DBConstants.CGRADO_ID + "=?", ids);
        dbSQLiteDatabase.close();
    }

    //删除所有数据
    private void deleteAll() {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();
        dbSQLiteDatabase.delete(DBConstants.TABLA_GRADO, null, null);
        dbSQLiteDatabase.close();
    }


    //查询
    private void searchData(String[] ids) {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();
        try {
            Cursor cursor = dbSQLiteDatabase.rawQuery("SELECT " + DBConstants.CGRADO_DESCRIPCION
                    + " FROM " + DBConstants.TABLA_GRADO
                    + " WHERE " + DBConstants.CGRADO_ID + "=? ", ids);
            cursor.moveToFirst();
            search_tv_name.setText(cursor.getString(0));
        } catch (Exception e) {
            Log.e("TAG", "学位尚未注册");
        }
    }

    //查询所有
    private void searchAll() {
        dbSQLiteDatabase = dbSQLiteHelper.getReadableDatabase();
        Cursor cursor = dbSQLiteDatabase.rawQuery("SELECT * FROM " + DBConstants.TABLA_GRADO, null);
        if (lists != null) {
            lists.clear();
        }
        while (cursor.moveToNext()) {
            degreeBean = new DegreeBean();
            degreeBean.setId(cursor.getString(0));
            degreeBean.setDegreeName(cursor.getString(1));
            lists.add((degreeBean));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_all_degree:
                searchAll();
                adapter.setData(lists);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_add_degree:
                addData(add_et_name.getText().toString());
                searchAll();
                adapter.setData(lists);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_search_degree:
                String[] searchId = {et_search.getText().toString()};
                searchData(searchId);
                break;
            case R.id.btn_update_degree:
                String[] updateId = {et_update.getText().toString()};
                updateData(updateId, update_et_name.getText().toString());
                searchAll();
                adapter.setData(lists);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_delete_degree:
                String[] deleteId = {et_delete.getText().toString()};
                deleteData(deleteId);
                searchAll();
                adapter.setData(lists);
                adapter.notifyDataSetChanged();
                break;
            case R.id.degree_delete_all:
                deleteAll();
                searchAll();
                adapter.setData(lists);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}