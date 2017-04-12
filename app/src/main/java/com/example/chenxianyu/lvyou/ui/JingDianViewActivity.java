package com.example.chenxianyu.lvyou.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.example.chenxianyu.lvyou.R;
import com.example.chenxianyu.lvyou.adapter.JingdianListAdapter;
import com.example.chenxianyu.lvyou.entity.Jingdian;
import com.example.chenxianyu.lvyou.entity.JingdianList;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.ui
 * 文件名：JingDianViewActivity
 * 创建者：陈贤煜
 * 创建时间：2017/2/28 23:46
 * 描述：景点详情页
 */

public class JingDianViewActivity extends BaseActivity{
    private GridView mGridView;
    private List<JingdianList> mList = new ArrayList<>();
    private JingdianListAdapter mApater;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jingdianview);
        initView();
    }

    private void initView() {
        mGridView = (GridView) findViewById(R.id.mGridView);
        BmobQuery<Jingdian> query= new BmobQuery<Jingdian>();
        query.findObjects(new FindListener<Jingdian>() {
            @Override
            public void done(List<Jingdian> list, BmobException e) {
                if(e==null){
                    for(int i=0;i<list.size();i++){
                        Jingdian jd = list.get(i);
                        JingdianList jdList = new JingdianList();
                        jdList.setName(jd.getJingdianName());
                        jdList.setUrl(jd.getPic().getUrl());
                        mList.add(jdList);

                    }
                    Toast.makeText(JingDianViewActivity.this,mList.get(0).getUrl(),Toast.LENGTH_SHORT).show();
                    mApater =  new JingdianListAdapter(JingDianViewActivity.this,mList);
                    mGridView.setAdapter(mApater);


                }else{
                    Log.i("bmob","失败:"+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
}
