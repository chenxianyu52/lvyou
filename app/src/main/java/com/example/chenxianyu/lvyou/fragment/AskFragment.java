package com.example.chenxianyu.lvyou.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenxianyu.lvyou.MainActivity;
import com.example.chenxianyu.lvyou.R;
import com.example.chenxianyu.lvyou.adapter.QuestionListAdapter;
import com.example.chenxianyu.lvyou.entity.MyUser;
import com.example.chenxianyu.lvyou.entity.QuestList;
import com.example.chenxianyu.lvyou.entity.Question;
import com.example.chenxianyu.lvyou.ui.AwserViewActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.fragment
 * 文件名：AskFragment
 * 创建者：陈贤煜
 * 创建时间：2017/2/18 14:35
 * 描述：问大家
 */

public class AskFragment extends Fragment implements View.OnClickListener {
    private ListView mListView;
    private List<QuestList> mList = new ArrayList<>();
    private EditText et_question;
    private Button btn_send;
    private QuestionListAdapter adapter;

    private List<String> idList = new ArrayList<>();
    private List<String> usernameList = new ArrayList<>();
    private List<String> contentList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ask,null);
        findView(view);
        return view;
    }
    //初始化view
    private void findView(View view) {

        mListView = (ListView) view.findViewById(R.id.mQuestionList);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), AwserViewActivity.class);
                intent.putExtra("username",usernameList.get(position));
                intent.putExtra("questionId",idList.get(position));
                intent.putExtra("content",contentList.get(position));
                startActivity(intent);
//                Toast.makeText(getActivity(),idList.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        et_question = (EditText) view.findViewById(R.id.et_question);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_question.getWindowToken(),0);
        btn_send = (Button) view.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        //显示列表
        BmobQuery<Question> query = new BmobQuery<Question>();
        query.findObjects(new FindListener<Question>() {
            @Override
            public void done(List<Question> list, BmobException e) {

                if(e==null){
                    for(int i=0;i<list.size();i++){
                        Question que = list.get(i);
                        QuestList question = new QuestList();
                        question.setUser_name(que.getUser_name());
                        question.setContent(que.getQ_content());
                        mList.add(question);

                        usernameList.add(que.getUser_name());
                        contentList.add(que.getQ_content());
                        idList.add(que.getObjectId());

                    }
//                    Toast.makeText(getActivity(),mList.get(0).getContent(),Toast.LENGTH_SHORT).show();
                    adapter =  new QuestionListAdapter(getActivity(),mList);
                    mListView.setAdapter(adapter);


                }else{
                    Log.i("bmob","失败:"+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                String text = et_question.getText().toString();
                if(!TextUtils.isEmpty(text)){
                    et_question.setText("");
                    addQuestion(text);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_question.getWindowToken(),0);
                }else{
                    Toast.makeText(getActivity(),"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void addQuestion(final String text) {
        MyUser userInfo= BmobUser.getCurrentUser(MyUser.class);
        Question question = new Question();

        String id = userInfo.getObjectId();
        final String username = userInfo.getUsername();
        question.setQ_content(text);
        question.setUser_Id(id);
        question.setUser_name(username);
        question.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    QuestList quest= new QuestList();
                    quest.setUser_name(username);
                    quest.setContent(text);
                    mList.add(quest);
                    adapter.notifyDataSetChanged();
                    mListView.setSelection(mList.size()-1);
                }else{
                    Toast.makeText(getActivity(),"添加失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
