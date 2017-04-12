package com.example.chenxianyu.lvyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.chenxianyu.lvyou.R;
import com.example.chenxianyu.lvyou.entity.Jingdian;
import com.example.chenxianyu.lvyou.ui.JingDianViewActivity;


/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.fragment
 * 文件名：MainFragment
 * 创建者：陈贤煜
 * 创建时间：2017/2/18 14:34
 * 描述：主页
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    private Button btn_hotel;
    private Button btn_haibian;
    private Button btn_air;
    private Button btn_shopping;
    private Button btn_gentuan;
    private Button btn_ziyou;
    private Button btn_youlun;
    private Button btn_menpiao;

    private Button btn_guonei;
    private Button btn_jingwai;
    private Button btn_zhoubian;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,null);
        initview(view);
        return view;
    }

    private void initview(View view) {
        btn_hotel = (Button) view.findViewById(R.id.btn_hotel);
        btn_haibian = (Button) view.findViewById(R.id.btn_haibian);
        btn_air = (Button) view.findViewById(R.id.btn_air);
        btn_shopping = (Button) view.findViewById(R.id.btn_shopping);
        btn_gentuan = (Button) view.findViewById(R.id.btn_gentuan);
        btn_ziyou = (Button) view.findViewById(R.id.btn_ziyouxing);
        btn_youlun = (Button) view.findViewById(R.id.btn_youlun);
        btn_menpiao = (Button) view.findViewById(R.id.btn_menpiao);
        btn_guonei = (Button) view.findViewById(R.id.btn_guonei);
        btn_jingwai = (Button) view.findViewById(R.id.btn_jingwai);
        btn_zhoubian = (Button) view.findViewById(R.id.btn_zhoubian);


        btn_hotel.setOnClickListener(this);
        btn_haibian.setOnClickListener(this);
        btn_air.setOnClickListener(this);
        btn_shopping.setOnClickListener(this);
        btn_gentuan.setOnClickListener(this);
        btn_ziyou.setOnClickListener(this);
        btn_youlun.setOnClickListener(this);
        btn_menpiao.setOnClickListener(this);
        btn_guonei.setOnClickListener(this);
        btn_jingwai.setOnClickListener(this);
        btn_zhoubian.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_hotel:
                Toast.makeText(getActivity(),"酒店",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_haibian:
                Toast.makeText(getActivity(),"海边游",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_air:
                Toast.makeText(getActivity(),"飞机",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_shopping:
                Toast.makeText(getActivity(),"购物",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_gentuan:
                Toast.makeText(getActivity(),"跟团游",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_ziyouxing:
                Toast.makeText(getActivity(),"自由行",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), JingDianViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_youlun:
                Toast.makeText(getActivity(),"游轮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_menpiao:
                Toast.makeText(getActivity(),"门票",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_guonei:
                Toast.makeText(getActivity(),"国内游",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_jingwai:
                Toast.makeText(getActivity(),"境外游",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_zhoubian:
                Toast.makeText(getActivity(),"周边游",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
