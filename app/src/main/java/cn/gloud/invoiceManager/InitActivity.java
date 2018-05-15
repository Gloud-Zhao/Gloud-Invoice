package cn.gloud.invoiceManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;

import cn.gloud.invoiceManager.Entity.LoginRespon;
import cn.gloud.invoiceManager.Entity.UserInfo;
import cn.gloud.invoiceManager.Utils.ConstanValues;
import cn.gloud.invoiceManager.Utils.GenerialUtils;
import cn.gloud.invoiceManager.Utils.UserInfoUtils;

public class InitActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserInfo tUserinfo=UserInfoUtils.getInstances(this).GetUserinfo();
        if (null==tUserinfo||TextUtils.isEmpty(tUserinfo.getLogintoken())){
            //未登录过，直接进入登录页
            Intent tIntent=new Intent(this,LoginActivity.class);
            startActivity(tIntent);
            finish();
        }else{
            //使用token进行登录
            HashMap<String,String> params=GenerialUtils.GetBaseMap(this);
            params.put(ConstanValues.T,ConstanValues.LOGIN);

            Log.i("ZQ","uRL=="+GenerialUtils.GetUrlParams(ConstanValues.BASEURL+"/User",params));

            new MyOkHttp().get().url(ConstanValues.BASEURL+"/User").params(params).enqueue(new GsonResponseHandler<LoginRespon>() {
                @Override
                public void onFailure(int statusCode, String error_msg) {
                    //进到登录页
                    Intent tIntent=new Intent(InitActivity.this,LoginActivity.class);
                    startActivity(tIntent);
                    finish();
                }

                @Override
                public void onSuccess(int statusCode, LoginRespon response) {
                    if (statusCode==200&&response.getRet()==0){
                        //登录成功
                        UserInfoUtils.getInstances(InitActivity.this).SaveUserInfo(response.getData());
                        Intent tIntent=new Intent(InitActivity.this,Main2Activity.class);
                        startActivity(tIntent);
                        finish();
                    }else{
                        //进到登录页
                        Intent tIntent=new Intent(InitActivity.this,LoginActivity.class);
                        startActivity(tIntent);
                        finish();
                    }
                }
            });
        }

    }
}
