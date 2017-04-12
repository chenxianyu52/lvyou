package com.example.chenxianyu.lvyou.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chenxianyu.lvyou.MainActivity;
import com.example.chenxianyu.lvyou.R;
import com.example.chenxianyu.lvyou.entity.MyUser;
import com.example.chenxianyu.lvyou.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.ui
 * 文件名：LoginActivity
 * 创建者：陈贤煜
 * 创建时间：2017/2/20 23:29
 * 描述：登录界面
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //注册按钮
    private Button btn_register;

    private EditText tt_user;
    private EditText tt_password;

    private Button btn_login;

    private CustomDialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        btn_register =(Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);

        tt_user=(EditText)findViewById(R.id.tt_user);
        tt_password=(EditText)findViewById(R.id.tt_password);

        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        dialog=new CustomDialog(this,100,100,R.layout.dialog_loading,R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        //屏幕外点击无效
        dialog.setCancelable(false);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.btn_login:
                String name = tt_user.getText().toString().trim();
                String password=tt_password.getText().toString();
                if(!TextUtils.isEmpty(name)&!TextUtils.isEmpty(password)){
                    dialog.show();
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            dialog.dismiss();
                            if(e==null){
                                //判断邮箱是否验证
                                if(user.getEmailVerified()){
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(LoginActivity.this,"请前往邮箱验证",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(LoginActivity.this,"登录失败"+e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
