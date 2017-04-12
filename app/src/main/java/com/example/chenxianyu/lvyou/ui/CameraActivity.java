package com.example.chenxianyu.lvyou.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.chenxianyu.lvyou.R;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import com.unity3d.player.UnityPlayerNativeActivity;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.ui
 * 文件名：CameraActivity
 * 创建者：陈贤煜
 * 创建时间：2017/2/18 15:29
 * 描述：相機
 */

public class CameraActivity extends UnityPlayerNativeActivity{
    private UnityPlayer mUnityPlayer;
    private FrameLayout layout;
//    static {
//        try {
//            System.load("/system/lib/libmain.so");
//        }catch(UnsatisfiedLinkError e){
//            Log.w("my app","Native code library failed to load from /System/lib/libmain.so.\n"+e);
//        }
//        try{
//            System.load("main");
//        }catch (UnsatisfiedLinkError e){
//            Log.w("My app","Native code libary failed to load from \"main\".\n"+e);
//        }
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mUnityPlayer = new UnityPlayer(this);
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode",1);
        boolean trueColor8888=false;
        mUnityPlayer.init(glesMode,trueColor8888);

        setContentView(R.layout.activity_camera);
        layout= (FrameLayout) findViewById(R.id.frameLayout2);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        layout.addView(mUnityPlayer.getView(),0,lp);


        Log.d("OverrideActivity","onCreate called");
//        RelativeLayout layout = (RelativeLayout) findViewById(R.id.parent_layout);
//        layout.addView(mUnityPlayer.getView());
    }

    @Override
    public void onBackPressed() {runOnUiThread(new Runnable() {
        @Override
        public void run() {
            mUnityPlayer.quit();
        }
    });
        super.onBackPressed();
    }
}
