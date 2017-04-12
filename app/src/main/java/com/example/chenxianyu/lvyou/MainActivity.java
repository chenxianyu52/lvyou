package com.example.chenxianyu.lvyou;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.chenxianyu.lvyou.fragment.AskFragment;
import com.example.chenxianyu.lvyou.fragment.MainFragment;
import com.example.chenxianyu.lvyou.fragment.MeFragment;
import com.example.chenxianyu.lvyou.fragment.NearFragment;
import com.example.chenxianyu.lvyou.ui.CameraActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //TabLayout
    private TabLayout mTabLayout;
    //ViewLayout
    private ViewPager mViewPager;
    //Title
    private List<String> mTitle;
    //Fragment
    private List<Fragment> mFragment;
    //懸浮按鈕
    private FloatingActionButton fab_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去掉阴影
        getSupportActionBar().setElevation(0);
        initData();
        initView();
    }
    //初始化view
    private void initView() {
        fab_camera=(FloatingActionButton)findViewById(R.id.fab_camera);
        fab_camera.setOnClickListener(this);
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }
            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }
    //初始化数据
    private void initData() {
        mTitle= new ArrayList<>();
        mTitle.add(this.getString(R.string.main));
        mTitle.add(this.getString(R.string.near));
        mTitle.add(this.getString(R.string.ask));
        mTitle.add(this.getString(R.string.me));

        mFragment =new ArrayList<>();
        mFragment.add(new MainFragment());
        mFragment.add(new NearFragment());
        mFragment.add(new AskFragment());
        mFragment.add(new MeFragment());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_camera:
                startActivity(new Intent(this,CameraActivity.class));
                break;
        }
    }
}
