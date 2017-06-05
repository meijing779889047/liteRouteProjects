package com.mj.literouterlibrary.activitymanager;

import com.mj.literouterlibrary.liteRoute.IntentWrapper;
import com.mj.literouterlibrary.liteRoute.annotations.ClassName;
import com.mj.literouterlibrary.liteRoute.annotations.Key;
import com.mj.literouterlibrary.liteRoute.annotations.RequestCode;

/**
 * Created by lenovo on 2017/6/4.
 */

public interface MainToModule2IntentService {

    @ClassName("com.mj.module2library.Module2Activity")
    @RequestCode(20001)
    IntentWrapper mainToModule2Intent(@Key("platform") String platform, @Key("data") int data);

    @ClassName("com.mj.literouter.MainActivity")
    @RequestCode(20002)
    void module2ToMainIntent(@Key("platform") String platform, @Key("data") float data);
}
