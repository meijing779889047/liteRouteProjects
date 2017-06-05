package com.mj.literouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mj.commonlibrary.BaseApp;
import com.mj.literouterlibrary.activitymanager.MainToModule1IntentService;
import com.mj.literouterlibrary.activitymanager.MainToModule2IntentService;
import com.mj.literouterlibrary.activitymanager.bean.MainToModule1Bean;
import com.mj.literouterlibrary.liteRoute.IntentWrapper;
import com.mj.literouterlibrary.liteRoute.LiteRouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_go_module1)
    TextView tvGoModule1;
    @BindView(R.id.tv_main_go_module2)
    TextView tvGoModule2;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private LiteRouter mLiteRouter;
    private MainToModule1IntentService mMainToModule1IntentService;
    private MainToModule2IntentService mMainToModule2IntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLiteRouter= BaseApp.getLiteRoute();
        mMainToModule1IntentService = mLiteRouter.create(MainToModule1IntentService.class, this);
        mMainToModule2IntentService = mLiteRouter.create(MainToModule2IntentService.class, this);
        Bundle  bunlder=getIntent().getExtras();
        if(bunlder!=null){
            String platform=bunlder.getString("platform");
            StringBuilder  sb=new StringBuilder();
            sb.append("从上一个界面传递过来的数据："+"\n");
            sb.append("platform:"+platform+"\n");
            if("从依赖库1跳转到主项目".equals(platform)) {
                boolean bolleanData=bunlder.getBoolean("data");
                sb.append("data:" +bolleanData + "\n");
            }else{
                float floatData=bunlder.getFloat("data");
                sb.append("data:" +floatData + "\n");
            }
            tvData.setText(sb.toString());
        }
    }

    @OnClick({R.id.tv_go_module1, R.id.tv_main_go_module2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_module1://从主项目跳转到依赖库1的activity
                MainToModule1Bean  bean=new MainToModule1Bean();
                bean.setId("1");
                bean.setName("mj");
                bean.setAge("20");
                mMainToModule1IntentService.mainToModule1Intent("从主项目跳转到依赖库1",bean);
                break;
            case R.id.tv_main_go_module2://从主项目跳转到依赖库2的activity
                IntentWrapper intentWrapper = mMainToModule2IntentService.mainToModule2Intent("从主项目跳转到依赖库2", 2017);
                // intent
                Intent intent = intentWrapper.getIntent();
                // add your flags
                intentWrapper.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                // start
                intentWrapper.start();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==10001){
                Bundle  bundle=data.getExtras();
                if(bundle!=null){
                    String result=bundle.getString("data");
                    tvData.setText("从跳转的界面返回的数据："+result);
                }

            }else if(requestCode==20001){
                Bundle  bundle=data.getExtras();
                if(bundle!=null){
                    String result=bundle.getString("data");
                    tvData.setText("从跳转的界面返回的数据："+result);
                }
            }

        }
    }

    @Override
    public void finish() {
        Intent  intent=new Intent();
        Bundle  bundle=new Bundle();
        bundle.putString("data","从主项目返回的数据");
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        super.finish();
    }
}
