package com.example.chenxianyu.lvyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenxianyu.lvyou.R;
import com.example.chenxianyu.lvyou.entity.JingdianList;
import com.example.chenxianyu.lvyou.ui.JingDianViewActivity;
import com.example.chenxianyu.lvyou.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.adapter
 * 文件名：JingdianListAdapter
 * 创建者：陈贤煜
 * 创建时间：2017/3/1 0:04
 * 描述：景点适配器
 */

public class JingdianListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater inflater;
    private JingdianList jdList;
    private List<JingdianList> mList;

    private WindowManager wm;
    private int width;

    public JingdianListAdapter(Context mContext, List<JingdianList> mList) {
        this.mContext= mContext;
        this.mList = mList;
        //获取系统服务
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();

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
            convertView = inflater.inflate(R.layout.jingdian_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tx_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder)convertView.getTag();
        }
        jdList=mList.get(position);
        String url = jdList.getUrl();

        PicassoUtils.loadImageViewSize(mContext,url,width/2,250,viewHolder.imageView);
        viewHolder.name.setText(jdList.getName());
        return convertView;
    }
    class ViewHolder {
        private ImageView imageView;
        private TextView name;
    }
}
