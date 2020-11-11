package com.example.schoolsqlite.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.schoolsqlite.R;
import com.example.schoolsqlite.bean.GradeBean;

import java.util.List;

public class GradeAdapter extends BaseAdapter {
    private List<GradeBean> studentList;
    private Context mContext;

    public GradeAdapter(List<GradeBean> studentList, Context mContext) {
        this.studentList = studentList;
        this.mContext = mContext;
    }

    public void setData(List<GradeBean> languageList2) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grade, null);
            holder.tv_number = convertView.findViewById(R.id.item_number_grade);
            holder.tv_grade = convertView.findViewById(R.id.item_grade);
            holder.tv_score = convertView.findViewById(R.id.item_score_grade);
            holder.item_id = convertView.findViewById(R.id.item_id_grade);
            holder.tv_price = convertView.findViewById(R.id.item_price_grade);
            holder.iv_grade_item = convertView.findViewById(R.id.iv_grade_item);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.item_id.setText(studentList.get(i).getId());
        holder.tv_number.setText(studentList.get(i).getNombrealu());
        holder.tv_grade.setText(studentList.get(i).getGradoalu());
        holder.tv_score.setText(studentList.get(i).getSecalu());
        holder.tv_price.setText(studentList.get(i).getFehca_ins());
        Glide.with(holder.iv_grade_item.getContext()).load(studentList.get(i).getInsimg()).into(holder.iv_grade_item);
        return convertView;
    }

    private class MyViewHolder {
        private TextView item_id;
        private TextView tv_number;
        private TextView tv_grade;
        private TextView tv_score;
        private TextView tv_price;
        private ImageView iv_grade_item;
    }

}
