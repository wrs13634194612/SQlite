package com.example.schoolsqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.schoolsqlite.R;
import com.example.schoolsqlite.bean.DegreeBean;

import java.util.List;

public class DegreeAdapter extends BaseAdapter {
    private List<DegreeBean> degreeList;
    private Context mContext;

    public DegreeAdapter(List<DegreeBean> degreeList, Context mContext) {
        this.degreeList = degreeList;
        this.mContext = mContext;
    }

    public void setData(List<DegreeBean> languageList2) {
        this.degreeList = languageList2;
    }

    @Override
    public int getCount() {
        return degreeList.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_degree, null);
            holder.item_id = (TextView) convertView.findViewById(R.id.item_id_degree);
            holder.tv_name_degree = (TextView) convertView.findViewById(R.id.item_name_degree);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.item_id.setText(degreeList.get(i).getId());
        holder.tv_name_degree.setText(degreeList.get(i).getDegreeName());
        return convertView;
    }

    private class MyViewHolder {
        private TextView item_id;
        private TextView tv_name_degree;
    }

}
