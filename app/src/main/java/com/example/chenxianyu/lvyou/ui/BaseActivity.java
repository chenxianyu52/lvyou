package com.example.chenxianyu.lvyou.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.ui
 * 文件名：BaseActivity
 * 创建者：陈贤煜
 * 创建时间：2017/2/18 10:59
 * 描述：activity的基类
 */

/**
 * 做的事情：
 * 1、统一的属性
 * 2、统一的接口
 * 3、统一的方法
 */

public class BaseActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //菜单栏操作

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
