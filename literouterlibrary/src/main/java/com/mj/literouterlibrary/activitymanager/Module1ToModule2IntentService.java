package com.mj.literouterlibrary.activitymanager;


import com.mj.literouterlibrary.liteRoute.annotations.ClassName;
import com.mj.literouterlibrary.liteRoute.annotations.Key;
import com.mj.literouterlibrary.liteRoute.annotations.RequestCode;

/**
 * Created by lenovo on 2017/6/4.
 */

public interface Module1ToModule2IntentService {

    @ClassName("com.mj.module2library.Module2Activity")
    @RequestCode(30001)
    void module1ToModule2Intent(@Key("platform") String platform, @Key("data") Double data);

    @ClassName("com.mj.module1library.Module1Activity")
    @RequestCode(30002)
    void module2ToModule1Intent(@Key("platform") String platform, @Key("data") long data);

}
