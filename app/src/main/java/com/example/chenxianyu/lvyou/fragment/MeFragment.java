package com.example.chenxianyu.lvyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenxianyu.lvyou.R;
import com.example.chenxianyu.lvyou.entity.MyUser;
import com.example.chenxianyu.lvyou.ui.LoginActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.fragment
 * 文件名：MeFragment
 * 创建者：陈贤煜
 * 创建时间：2017/2/18 14:35
 * 描述：我的
 */

public class MeFragment extends Fragment implements View.OnClickListener {
    private Button btn_exit_user;
    private TextView edit_user;
    private Button btn_update_ok;

    private EditText et_username;
    private EditText et_sex;
    private EditText et_age;
    private EditText et_desc;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,null);
        findView(view);
        return view;
    }
    //初始化view
    private void findView(View view) {
        btn_exit_user = (Button)view.findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);
        edit_user = (TextView)view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);
        et_username=(EditText)view.findViewById(R.id.et_username);
        et_sex=(EditText)view.findViewById(R.id.et_sex);
        et_age=(EditText)view.findViewById(R.id.et_age);
        et_desc=(EditText)view.findViewById(R.id.et_desc);

        btn_update_ok = (Button)view.findViewById(R.id.btn_update_ok);
        btn_update_ok.setVisibility(View.GONE);
        btn_update_ok.setOnClickListener(this);

        //默认不可点击不可修改;
        setEnabled(false);

        //设置具体的值
        MyUser userInfo= BmobUser.getCurrentUser(MyUser.class);


        et_username.setText(userInfo.getUsername());
        et_sex.setText(userInfo.isSex()?"男":"女");
        et_age.setText(userInfo.getAge()+"");
        et_desc.setText(userInfo.getDesc());
    }

    //文本框是否可以点击函数
    private void setEnabled(boolean is){

        et_username.setEnabled(is);
        et_sex.setEnabled(is);
        et_age.setEnabled(is);
        et_desc.setEnabled(is);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_exit_user:
                MyUser.logOut();
                BmobUser current= MyUser.getCurrentUser();//现在的current为null
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.edit_user:
                setEnabled(true);
                btn_update_ok.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_update_ok:
                String username = et_username.getText().toString();
                String age = et_age.getText().toString();
                String sex = et_sex.getText().toString();
                String desc = et_desc.getText().toString();
                //判断是否为空
                if(!TextUtils.isEmpty(username)&!TextUtils.isEmpty(age)&!TextUtils.isEmpty(sex)){
                    //更新属性
                    MyUser user = new MyUser();
                    user.setUsername(username);
                    user.setAge(Integer.parseInt(age));
                    if(sex.equals("男")){
                        user.setSex(true);
                    }else{
                        user.setSex(false);
                    }
                    if(!TextUtils.isEmpty(desc)){
                        user.setDesc(desc);
                    }else{
                        user.setDesc("这个人很懒，什么都没有留下");
                    }
                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    user.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                //修改成功
                                setEnabled(false);
                                btn_update_ok.setVisibility(View.GONE);
                                Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(getActivity(),"修改失败"+e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(getActivity(),"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
