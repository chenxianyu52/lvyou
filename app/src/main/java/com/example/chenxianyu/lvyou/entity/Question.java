package com.example.chenxianyu.lvyou.entity;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.entity
 * 文件名：Question
 * 创建者：陈贤煜
 * 创建时间：2017/2/23 20:54
 * 描述：问题实体类
 */

public class Question extends BmobObject{
    //问题
    private String q_content;
    //姓名
    private String user_name;

    //用户id
    private String user_Id;




    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getQ_content() {
        return q_content;
    }

    public void setQ_content(String q_content) {
        this.q_content = q_content;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

}
