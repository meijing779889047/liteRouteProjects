package com.mj.module1library;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.mj.commonlibrary.BaseApp;
import com.mj.literouterlibrary.activitymanager.MainToModule1IntentService;
import com.mj.literouterlibrary.activitymanager.Module1ToModule2IntentService;
import com.mj.literouterlibrary.activitymanager.bean.MainToModule1Bean;
import com.mj.literouterlibrary.liteRoute.LiteRouter;

public class Module1Activity extends AppCompatActivity   implements View.OnClickListener {

    private TextView  tvGoMain;
    private TextView  tvGoModule2;
    private TextView  tvData;
    private LiteRouter mLiteRouter;
    private MainToModule1IntentService mMainToModule1IntentService;
    private Module1ToModule2IntentService mModule1ToModule2IntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module1);
        //初始化组件
        initView();
        //初始化数据
        initData();

    }

    /**
     * 初始化组件
     */
    private void initView() {
        tvGoMain= (TextView) findViewById(R.id.tv_go_main);
        tvGoMain.setOnClickListener(this);
        tvGoModule2= (TextView) findViewById(R.id.tv_go_module2);
        tvGoModule2.setOnClickListener(this);
        tvData= (TextView) findViewById(R.id.tv_data);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        mLiteRouter= BaseApp.getLiteRoute();
        mMainToModule1IntentService = mLiteRouter.create(MainToModule1IntentService.class, this);
        mModule1ToModule2IntentService = mLiteRouter.create(Module1ToModule2IntentService.class, this);
        Bundle  bunlder=getIntent().getExtras();
        if(bunlder!=null){
            String platform=bunlder.getString("platform");
            StringBuilder  sb=new StringBuilder();
            sb.append("从上一个界面传递过来的数据："+"\n");
            sb.append("platform:"+platform+"\n");
            if("从主项目跳转到依赖库1".equals(platform)) {
                MainToModule1Bean bean = bunlder.getParcelable("data");
                sb.append("data:" + bean.toString() + "\n");
            }else{
                long longData=bunlder.getLong("data");
                sb.append("data:" +longData + "\n");
            }
            tvData.setText(sb.toString());
        }

    }

    @Override
    public void onClick(View v) {
       int id=v.getId();
        if(id==R.id.tv_go_main){
            mMainToModule1IntentService.module1ToMainIntent("从依赖库1跳转到主项目",true);
        }else if(id==R.id.tv_go_module2){
            mModule1ToModule2IntentService.module1ToModule2Intent("从依赖库1跳转到依赖库2",3.141592);
        }
    }


    @Override
    public void finish() {
        Intent  intent=new Intent();
        Bundle  bundle=new Bundle();
        bundle.putString("data","从依赖库1返回的数据");
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        super.finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==10002){
                Bundle  bundle=data.getExtras();
                if(bundle!=null){
                    String result=bundle.getString("data");
                    tvData.setText("从跳转的界面返回的数据："+result);
                }

            }else if(requestCode==30001){
                Bundle  bundle=data.getExtras();
                if(bundle!=null){
                    String result=bundle.getString("data");
                    tvData.setText("从跳转的界面返回的数据："+result);
                }
            }

        }
    }
}
