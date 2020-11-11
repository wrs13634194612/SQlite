package com.example.schoolsqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.schoolsqlite.R;
import com.example.schoolsqlite.bean.StudentBean;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private List<StudentBean> studentList;
    private Context mContext;

    public StudentAdapter(List<StudentBean> studentList, Context mContext) {
        this.studentList = studentList;
        this.mContext = mContext;
    }

    public void setData(List<StudentBean> languageList2) {
        this.studentList = languageList2;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        if (convertView == null) {
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_student, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.item_name);
            holder.tv_sex = (TextView) convertView.findViewById(R.id.item_sex);
            holder.tv_tel = (TextView) convertView.findViewById(R.id.item_tel);
            holder.item_id = (TextView) convertView.findViewById(R.id.item_id);
            holder.tv_profession = (TextView) convertView.findViewById(R.id.item_profession);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.item_id.setText(studentList.get(i).getId());
        holder.tv_name.setText(studentList.get(i).getNombre());
        holder.tv_sex.setText(studentList.get(i).getApellido());
        holder.tv_tel.setText(studentList.get(i).getTelefono());
        holder.tv_profession.setText(studentList.get(i).getDireccion());
        return convertView;
    }

    private class MyViewHolder {
        private TextView item_id;
        private TextView tv_name;
        private TextView tv_sex;
        private TextView tv_tel;
        private TextView tv_profession;
    }

}
