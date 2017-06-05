package com.mj.literouterlibrary.activitymanager;

import com.mj.literouterlibrary.activitymanager.bean.MainToModule1Bean;
import com.mj.literouterlibrary.liteRoute.annotations.ClassName;
import com.mj.literouterlibrary.liteRoute.annotations.Key;
import com.mj.literouterlibrary.liteRoute.annotations.RequestCode;

/**
 * Created by lenovo on 2017/6/4.
 */

public interface MainToModule1IntentService {

    @ClassName("com.mj.module1library.Module1Activity")
    @RequestCode(10001)
    void mainToModule1Intent(@Key("platform") String platform, @Key("data") MainToModule1Bean data);


    @ClassName("com.mj.literouter.MainActivity")
    @RequestCode(10002)
    void module1ToMainIntent(@Key("platform") String platform, @Key("data") boolean data);
}
