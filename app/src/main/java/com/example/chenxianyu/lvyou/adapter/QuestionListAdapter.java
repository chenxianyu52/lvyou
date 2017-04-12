package com.example.chenxianyu.lvyou.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenxianyu.lvyou.MainActivity;
import com.example.chenxianyu.lvyou.R;
import com.example.chenxianyu.lvyou.entity.QuestList;
import com.example.chenxianyu.lvyou.entity.Question;

import java.util.List;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.adapter
 * 文件名：QuestionListAdapter
 * 创建者：陈贤煜
 * 创建时间：2017/2/23 20:51
 * 描述：问题adapter
 */

public class QuestionListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private QuestList question_data;
    private List<QuestList> mList;

    public QuestionListAdapter(Context mContext, List<QuestList> mList) {
        this.mContext= mContext;
        this.mList = mList;
        //获取系统服务
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.question_item, null);
            viewHolder.q_name = (TextView) convertView.findViewById(R.id.q_name);
            viewHolder.q_content = (TextView) convertView.findViewById(R.id.q_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        question_data=mList.get(position);
        viewHolder.q_content.setText(question_data.getContent());
        viewHolder.q_name.setText(question_data.getUser_name());
        return convertView;
    }


    class ViewHolder {
        private TextView q_name;
        private TextView q_content;
    }
}
