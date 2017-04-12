package com.example.chenxianyu.lvyou.entity;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.entity
 * 文件名：Jingdian
 * 创建者：陈贤煜
 * 创建时间：2017/2/28 23:55
 * 描述：景点数据类
 */

public class Jingdian  extends BmobObject{
    BmobFile pic;
    String jingdianName;

    public BmobFile getPic() {
        return pic;
    }

    public void setPic(BmobFile pic) {
        this.pic = pic;
    }

    public String getJingdianName() {
        return jingdianName;
    }

    public void setJingdianName(String jingdianName) {
        this.jingdianName = jingdianName;
    }
}
