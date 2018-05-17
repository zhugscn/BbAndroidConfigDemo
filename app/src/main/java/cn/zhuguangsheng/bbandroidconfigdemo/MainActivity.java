package cn.zhuguangsheng.bbandroidconfigdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.zhuguangsheng.bbandroidconfigdemo.util.MetaDataUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        Context appContext = getApplicationContext();
        MetaDataUtil.init(appContext);

        findViewById(R.id.btn_from_build_config).setOnClickListener(this);
        findViewById(R.id.btn_from_metadata).setOnClickListener(this);
    }

    private void onClickBuildConfig(){
        //从gradle的BuildConfig里取，常用于debug模式和release模式使用不同的参数

        String serverIp = BuildConfig.SERVER_IP;

        Toast.makeText(this, "BuildConfig里设置的服务器地址是" + serverIp, Toast.LENGTH_SHORT).show();
    }

    private void onClickMetaData(){
        //从metadata里取，而metadata里又用占位符()取了gradle里的值。常用于不同的版本分支

        String payType = "";

        try {
            payType = MetaDataUtil.getPayType();
        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, "MetaData取到的支付方式是" + payType, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_from_build_config:
                onClickBuildConfig();
                break;
            case R.id.btn_from_metadata:
                onClickMetaData();
                break;
            default:
                break;
        }
    }
}
