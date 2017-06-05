package com.mj.literouter;

import android.util.Log;

import com.mj.commonlibrary.BaseApp;

/**
 * Created by lenovo on 2017/6/4.
 */

public class MyApp extends BaseApp {

    private  String TAG="BaseApp";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG,"初始化主项目的application");
    }


}
