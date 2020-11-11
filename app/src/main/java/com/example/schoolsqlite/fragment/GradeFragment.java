package com.example.schoolsqlite.fragment;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.schoolsqlite.R;
import com.example.schoolsqlite.adapter.GradeAdapter;
import com.example.schoolsqlite.bean.GradeBean;
import com.example.schoolsqlite.sql.ConexionSQLiteHelper;
import com.example.schoolsqlite.sql.DBConstants;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GradeFragment extends Fragment implements View.OnClickListener {
    private Button btn_all_grade;
    private Button grade_delete_all;
    private ImageView iv_grade_image;
    private Button btn_grade_camera;
    private EditText add_et_number_grade;
    private EditText add_et_grade;
    private EditText add_et_score_grade;
    private EditText add_et_price_grade;
    private Button btn_add_grade;
    private EditText et_search_grade;
    private TextView search_tv_number_grade;
    private TextView search_tv_grade;
    private TextView search_tv_score_grade;
    private TextView search_tv_price_grade;
    private Button btn_search_grade;
    private ImageView iv_update_image;
    private ImageView iv_grade_search;
    private Button btn_update_camera;
    private EditText et_update_grade;
    private EditText update_et_number_grade;
    private EditText update_et_grade;
    private EditText update_et_score_grade;
    private EditText update_et_price_grade;
    private Button btn_update;
    private EditText et_delete_grade;
    private Button btn_delete_grade;
    private GridView grid_view_grade;
    private static final int CAMERA_REQUEST = 200;
    private static final int CAMERA_REQUEST_UPDATE = 201;
    private ConexionSQLiteHelper dbSQLiteHelper;
    private SQLiteDatabase dbSQLiteDatabase;
    private GradeBean gradeBean;
    private List<GradeBean> lists;
    private GradeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grade, container, false);

        btn_all_grade = view.findViewById(R.id.btn_all_grade);
        grade_delete_all = view.findViewById(R.id.grade_delete_all);
        iv_grade_image = view.findViewById(R.id.iv_grade_image);
        btn_grade_camera = view.findViewById(R.id.btn_grade_camera);
        add_et_number_grade = view.findViewById(R.id.add_et_number_grade);
        add_et_grade = view.findViewById(R.id.add_et_grade);
        add_et_score_grade = view.findViewById(R.id.add_et_score_grade);
        add_et_price_grade = view.findViewById(R.id.add_et_price_grade);
        btn_add_grade = view.findViewById(R.id.btn_add_grade);
        et_search_grade = view.findViewById(R.id.et_search_grade);
        search_tv_number_grade = view.findViewById(R.id.search_tv_number_grade);
        search_tv_grade = view.findViewById(R.id.search_tv_grade);
        search_tv_score_grade = view.findViewById(R.id.search_tv_score_grade);
        search_tv_price_grade = view.findViewById(R.id.search_tv_price_grade);
        btn_search_grade = view.findViewById(R.id.btn_search_grade);
        iv_update_image = view.findViewById(R.id.iv_update_image);
        iv_grade_search = view.findViewById(R.id.iv_grade_search);
        btn_update_camera = view.findViewById(R.id.btn_update_camera);
        et_update_grade = view.findViewById(R.id.et_update_grade);
        update_et_number_grade = view.findViewById(R.id.update_et_number_grade);
        update_et_grade = view.findViewById(R.id.update_et_grade);
        update_et_score_grade = view.findViewById(R.id.update_et_score_grade);
        update_et_price_grade = view.findViewById(R.id.update_et_price_grade);
        btn_update = view.findViewById(R.id.btn_update_grade);
        et_delete_grade = view.findViewById(R.id.et_delete_grade);
        btn_delete_grade = view.findViewById(R.id.btn_delete_grade);
        grid_view_grade = view.findViewById(R.id.grid_view_grade);

        dbSQLiteHelper = new ConexionSQLiteHelper(getActivity(), "bd_alumnos", null, 1);
        lists = new ArrayList<>();

        btn_all_grade.setOnClickListener(this);
        grade_delete_all.setOnClickListener(this);
        btn_grade_camera.setOnClickListener(this);
        btn_add_grade.setOnClickListener(this);
        btn_search_grade.setOnClickListener(this);
        btn_update_camera.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete_grade.setOnClickListener(this);

        searchAll();
        adapter = new GradeAdapter(lists, getActivity());
        grid_view_grade.setAdapter(adapter);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_all_grade:
                searchAll();
                adapter.setData(lists);
                adapter.notifyDataSetChanged();
                break;
            case R.id.grade_delete_all:
                deleteAll();
                break;
            case R.id.btn_grade_camera:
                takePhoto();
                break;
            case R.id.btn_add_grade:
                addData(add_et_number_grade.getText().toString(),
                        add_et_grade.getText().toString(),
                        add_et_score_grade.getText().toString(),
                        add_et_price_grade.getText().toString()
                );
                searchAll();
                adapter.setData(lists);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_search_grade:
                String[] searchId = {et_search_grade.getText().toString()};
                searchData(searchId);
                searchAll();
                adapter.setData(lists);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_update_camera:
                takePhotoUpdate();
                break;
            case R.id.btn_update_grade:
                String[] updateId = {et_update_grade.getText().toString()};
                updateData(update_et_number_grade.getText().toString(),
                        update_et_grade.getText().toString(),
                        update_et_score_grade.getText().toString(),
                        update_et_price_grade.getText().toString(), updateId);
                searchAll();
                adapter.setData(lists);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_delete_grade:
                String[] deleteId = {et_delete_grade.getText().toString()};
                deleteData(deleteId);
                searchAll();
                adapter.setData(lists);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }

    private void takePhotoUpdate() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_REQUEST_UPDATE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap) extras.get("data");
            iv_grade_image.setImageBitmap(image);
        } else if (requestCode == CAMERA_REQUEST_UPDATE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap) extras.get("data");
            iv_update_image.setImageBitmap(image);
        }
    }

    //bitmap转byte数组
    private static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    //新增
    private void addData(String number, String grade, String score, String price) {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();//写权限
        //把图片的内容 转换为bitmap
        Bitmap bitmap = ((BitmapDrawable) iv_grade_image.getDrawable()).getBitmap();
        byte[] data = getBitmapAsByteArray(bitmap);
        ContentValues values = new ContentValues();
        values.put(DBConstants.INS_ALU, number);
        values.put(DBConstants.INS_GRA, grade);
        values.put(DBConstants.INS_SEC, score);
        values.put(DBConstants.INS_FECHA, price);
        values.put(DBConstants.INS_IMG, data);
        Long idResultant = dbSQLiteDatabase.insert(DBConstants.TABLA_INSCRIPCION, DBConstants.INS_ID, values);
        Log.e("TAG", "数据库长度:" + idResultant);
    }


    //编辑更新
    private void updateData(String number, String grade, String score, String price, String[] ids) {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();
        Bitmap bitmap = ((BitmapDrawable) iv_update_image.getDrawable()).getBitmap();
        byte[] data = getBitmapAsByteArray(bitmap);
        ContentValues values = new ContentValues();
        values.put(DBConstants.INS_ALU, number);
        values.put(DBConstants.INS_GRA, grade);
        values.put(DBConstants.INS_SEC, score);
        values.put(DBConstants.INS_FECHA, price);
        values.put(DBConstants.INS_IMG, data);
        dbSQLiteDatabase.update(DBConstants.TABLA_INSCRIPCION, values, DBConstants.INS_ID + "=?", ids);
        dbSQLiteDatabase.close();
    }

    //删除
    private void deleteData(String[] ids) {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();
        dbSQLiteDatabase.delete(DBConstants.TABLA_INSCRIPCION, DBConstants.INS_ID + "=?", ids);
        dbSQLiteDatabase.close();
    }

    //删除所有
    private void deleteAll() {
        dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();
        dbSQLiteDatabase.delete(DBConstants.TABLA_INSCRIPCION, null, null);
        dbSQLiteDatabase.close();
    }

    //查询所有
    private void searchAll() {
        dbSQLiteDatabase = dbSQLiteHelper.getReadableDatabase(); //只读权限
        Cursor cursor = dbSQLiteDatabase.rawQuery("SELECT * FROM " + DBConstants.TABLA_INSCRIPCION, null);
        if (lists != null) {
            lists.clear();
        }
        while (cursor.moveToNext()) {
            gradeBean = new GradeBean();
            gradeBean.setId(cursor.getString(0));
            gradeBean.setNombrealu(cursor.getString(1));
            gradeBean.setGradoalu(cursor.getString(2));
            gradeBean.setSecalu(cursor.getString(3));
            gradeBean.setFehca_ins(cursor.getString(4));
            byte[] imgBytes = gradeBean.setInsimg(cursor.getBlob(5));
            BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
            lists.add(gradeBean);
        }
    }

    //查询
    private void searchData(String[] searchId) {
        //分为两个  一个是查询图片  一个是查询字段  但是都存在数据库里面
        try {
            dbSQLiteDatabase = dbSQLiteHelper.getWritableDatabase();
            Cursor cursor = dbSQLiteDatabase.rawQuery("SELECT " + DBConstants.INS_ALU
                    + "," + DBConstants.INS_GRA
                    + "," + DBConstants.INS_SEC
                    + "," + DBConstants.INS_FECHA
                    + "," + DBConstants.INS_IMG + " FROM " + DBConstants.TABLA_INSCRIPCION
                    + " WHERE " + DBConstants.INS_ID + "=? ", searchId);
            cursor.moveToFirst();
            search_tv_number_grade.setText(cursor.getString(0));
            search_tv_grade.setText(cursor.getString(1));
            search_tv_score_grade.setText(cursor.getString(2));
            search_tv_price_grade.setText(cursor.getString(3));
            Glide.with(iv_grade_image.getContext()).load(cursor.getBlob(4)).into(iv_grade_search);
        } catch (Exception e) {
            Log.e("TAG", "未注册成绩");
        }
    }
}