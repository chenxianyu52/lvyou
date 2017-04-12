package com.example.chenxianyu.lvyou.entity;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.entity
 * 文件名：Awser
 * 创建者：陈贤煜
 * 创建时间：2017/2/26 23:30
 * 描述：回答数据库类
 */

public class Awser  extends BmobObject{
    //问题
    private String content;
    //姓名
    private String username;
    //问题id
    private String questionId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
