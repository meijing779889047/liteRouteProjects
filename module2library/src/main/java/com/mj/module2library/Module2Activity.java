package com.mj.module2library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mj.commonlibrary.BaseApp;
import com.mj.literouterlibrary.activitymanager.MainToModule2IntentService;
import com.mj.literouterlibrary.activitymanager.Module1ToModule2IntentService;
import com.mj.literouterlibrary.liteRoute.LiteRouter;

public class Module2Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView   tvGoMain;
    private TextView  tvGoModule1;
    private TextView  tvData;
    private LiteRouter mLiteRouter;
    private Module1ToModule2IntentService mModule1ToModule2IntentService;
    private MainToModule2IntentService mMainToModule2IntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module2);
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
        tvGoModule1= (TextView) findViewById(R.id.tv_go_module1);
        tvGoModule1.setOnClickListener(this);
        tvData= (TextView) findViewById(R.id.tv_data);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        mLiteRouter= BaseApp.getLiteRoute();
        mMainToModule2IntentService = mLiteRouter.create(MainToModule2IntentService.class, this);
        mModule1ToModule2IntentService = mLiteRouter.create(Module1ToModule2IntentService.class, this);
        Bundle  bunlder=getIntent().getExtras();
        if(bunlder!=null){
            StringBuilder  sb=new StringBuilder();
            String platform=bunlder.getString("platform");
            sb.append("从上一个界面传递过来的数据："+"\n");
            sb.append("platform:"+platform+"\n");
            if("从主项目跳转到依赖库2".equals(platform)) {
                int year = bunlder.getInt("data");
                sb.append("data:"+year+"\n");
            }else {
                Double doubleData=bunlder.getDouble("data");
                sb.append("data:"+doubleData+"\n");
            }
            tvData.setText(sb.toString());
        }

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.tv_go_main){
           mMainToModule2IntentService.module2ToMainIntent("从依赖库2跳转到主项目",3.14f);
        }else if(id==R.id.tv_go_module1){
           mModule1ToModule2IntentService.module2ToModule1Intent("从依赖库2跳转到依赖库2",2017);
        }
    }


    @Override
    public void finish() {
        Intent intent=new Intent();
        Bundle  bundle=new Bundle();
        bundle.putString("data","从依赖库2返回的数据");
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        super.finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==20002){
                Bundle  bundle=data.getExtras();
                if(bundle!=null){
                    String result=bundle.getString("data");
                    tvData.setText("从跳转的界面返回的数据："+result);
                }

            }else if(requestCode==30002){
                Bundle  bundle=data.getExtras();
                if(bundle!=null){
                    String result=bundle.getString("data");
                    tvData.setText("从跳转的界面返回的数据："+result);
                }
            }

        }
    }
}
