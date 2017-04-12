package com.example.chenxianyu.lvyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chenxianyu.lvyou.R;
import com.example.chenxianyu.lvyou.entity.AwserList;

import java.util.List;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.adapter
 * 文件名：AwserListAdapter
 * 创建者：陈贤煜
 * 创建时间：2017/2/26 22:55
 * 描述：回答adapter
 */

public class AwserListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    private AwserList asdata;
    private List<AwserList> mList;

    public AwserListAdapter(Context mContext, List<AwserList> mList) {
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
            convertView = inflater.inflate(R.layout.answer_item, null);
            viewHolder.qw_name = (TextView) convertView.findViewById(R.id.qwt_name);
            viewHolder.qw_content = (TextView) convertView.findViewById(R.id.qwt_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder)convertView.getTag();
        }
        asdata=mList.get(position);

        viewHolder.qw_content.setText(asdata.getContent());
        viewHolder.qw_name.setText(asdata.getUser_name());
        return convertView;
    }
    class ViewHolder {
        private TextView qw_name;
        private TextView qw_content;
    }
}
