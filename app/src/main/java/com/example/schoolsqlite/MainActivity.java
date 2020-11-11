package com.example.schoolsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.example.schoolsqlite.adapter.ViewPagerAdapter;
import com.example.schoolsqlite.fragment.DegreeFragment;
import com.example.schoolsqlite.fragment.GradeFragment;
import com.example.schoolsqlite.fragment.StudentFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.add(new StudentFragment(), "学生信息表");
        viewPagerAdapter.add(new DegreeFragment(), "学位注册表");
        viewPagerAdapter.add(new GradeFragment(), "成绩录入表");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}