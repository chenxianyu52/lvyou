package com.example.chenxianyu.lvyou.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenxianyu.lvyou.R;
import com.example.chenxianyu.lvyou.adapter.AwserListAdapter;
import com.example.chenxianyu.lvyou.entity.Awser;
import com.example.chenxianyu.lvyou.entity.AwserList;
import com.example.chenxianyu.lvyou.entity.MyUser;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.ui
 * 文件名：AwserViewActivity
 * 创建者：陈贤煜
 * 创建时间：2017/2/25 23:37
 * 描述：回答视图activity
 */

public class AwserViewActivity extends BaseActivity implements View.OnClickListener {
    private ListView mAwserListView;
    private List<AwserList> mList = new ArrayList<>();
    private EditText etw_question;
    private Button btnw_send;
    private AwserListAdapter adapter;

    private TextView qw_name;
    private TextView qw_content;

    private String id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awserview);
        initView();
    }

    private void initView() {
        qw_name = (TextView) findViewById(R.id.qw_name);
        qw_content = (TextView) findViewById(R.id.qw_content);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        id = intent.getStringExtra("questionId");
        String content = intent.getStringExtra("content");
        qw_name.setText(username);
        qw_content.setText(content);
        mAwserListView = (ListView) findViewById(R.id.mwList);
        etw_question = (EditText) findViewById(R.id.mw_question);
        InputMethodManager imm = (InputMethodManager) AwserViewActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etw_question.getWindowToken(),0);
        btnw_send = (Button) findViewById(R.id.mwbtn_send);
        btnw_send.setOnClickListener(this);
        BmobQuery<Awser> query= new BmobQuery<Awser>();
        query.addWhereEqualTo("questionId",id);
        query.findObjects(new FindListener<Awser>() {
            @Override
            public void done(List<Awser> list, BmobException e) {
                if(e==null){
                    for(int i=0;i<list.size();i++){
                        Awser as = list.get(i);
                        AwserList aswer = new AwserList();
                        aswer.setUser_name(as.getUsername());
                        aswer.setContent(as.getContent());
                        mList.add(aswer);

                    }
//                    Toast.makeText(getActivity(),mList.get(0).getContent(),Toast.LENGTH_SHORT).show();
                    adapter =  new AwserListAdapter(AwserViewActivity.this,mList);
                    mAwserListView.setAdapter(adapter);


                }else{
                    Log.i("bmob","失败:"+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mwbtn_send:
                String text = etw_question.getText().toString();
                if(!TextUtils.isEmpty(text)){
                    etw_question.setText("");
                    addQuestion(text);
                    InputMethodManager imm = (InputMethodManager) AwserViewActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etw_question.getWindowToken(),0);
                }else{
                    Toast.makeText(AwserViewActivity.this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void addQuestion(final String text) {
        MyUser userInfo= BmobUser.getCurrentUser(MyUser.class);
        Awser asw = new Awser();

        final String username = userInfo.getUsername();
        asw.setUsername(username);
        asw.setQuestionId(id);
        asw.setContent(text);
        asw.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    AwserList aslist= new AwserList();
                    aslist.setUser_name(username);
                    aslist.setContent(text);
                    mList.add(aslist);
                    adapter.notifyDataSetChanged();
                    mAwserListView.setSelection(mList.size());
                }else{
                    Toast.makeText(AwserViewActivity.this,"添加失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
