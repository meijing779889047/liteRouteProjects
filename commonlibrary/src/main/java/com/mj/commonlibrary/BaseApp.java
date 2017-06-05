package com.mj.commonlibrary;

import android.app.Application;
import android.util.Log;

import com.mj.literouterlibrary.liteRoute.IntentWrapper;
import com.mj.literouterlibrary.liteRoute.Interceptor;
import com.mj.literouterlibrary.liteRoute.LiteRouter;

/**
 * Created by lenovo on 2017/6/4.
 */

public class BaseApp extends Application {

    private  String TAG="BaseApp";
    private  static  LiteRouter  mLiteRouter;
    @Override
    public void onCreate() {
        super.onCreate();
        initLiteRouter();
        Log.i(TAG,"初始化基类的application");
    }

    /**
     * 初始化liteRoute
     */
    private void initLiteRouter() {
        mLiteRouter=new LiteRouter.Builder().interceptor(new Interceptor() {
            @Override
            public boolean intercept(IntentWrapper intentWrapper) {//拦截器
                Log.i(TAG,"拦截器");
                return false;
            }
        }).build();
        Log.i(TAG,"初始化LiteRouter");
    }


    public static LiteRouter  getLiteRoute(){
        return  mLiteRouter;
    }
}
