package com.example.chenxianyu.lvyou.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.utils
 * 文件名：PicassoUtils
 * 创建者：陈贤煜
 * 创建时间：2017/2/28 23:31
 * 描述：picasso封装
 */

public class PicassoUtils {
    //默认加载图片
    public static void loadImageView(Context mContext,String url,ImageView imageView){
        Picasso.with(mContext).load(url).into(imageView);
    }
    //默认加载图片大小
    public static void loadImageViewSize(Context mContext,String url,int width,int height,ImageView imageView){
        Picasso.with(mContext).load(url).resize(width,height).centerCrop().into(imageView);
    }
    //加载图片有默认图片
    public static void loadImageViewHolder(Context mContext,String url,int loadImag,int errorImg,ImageView imageView){
        Picasso.with(mContext).load(url).placeholder(loadImag).error(errorImg).into(imageView);
    }
    //裁剪图片
    public static void loadImageViewCrop(Context mContext,String url,ImageView imageView){
        Picasso.with(mContext).load(url).transform(new CropSquareTransformation()).into(imageView);
    }
    //按比例裁剪矩形
    public static class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() { return "lgl"; }
    }
}
