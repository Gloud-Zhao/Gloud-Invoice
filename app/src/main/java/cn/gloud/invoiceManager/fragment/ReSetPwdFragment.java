package cn.gloud.invoiceManager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.roger.catloadinglibrary.CatLoadingView;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;

import cn.gloud.invoiceManager.Entity.LoginRespon;

import cn.gloud.invoiceManager.Entity.UserInfo;
import cn.gloud.invoiceManager.R;
import cn.gloud.invoiceManager.Utils.ConstanValues;
import cn.gloud.invoiceManager.Utils.GenerialUtils;
import cn.gloud.invoiceManager.Utils.Md5;
import cn.gloud.invoiceManager.Utils.UserInfoUtils;

public class ReSetPwdFragment extends Fragment implements View.OnClickListener{
    private AutoCompleteTextView mOldPwdEtx;
    private AutoCompleteTextView mNewPwdEtx;
    private Button mButton;
    private View mView=null;
    private CatLoadingView mCatLoadView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null==mView){
            mView=View.inflate(getActivity(), R.layout.fragment_resetpwd,null);
            mOldPwdEtx=mView.findViewById(R.id.old_pwd_etx);
            mNewPwdEtx=mView.findViewById(R.id.new_pwd_etx);
            mButton=mView.findViewById(R.id.re_set_pwd_btn);
            mButton.setOnClickListener(this);
            mCatLoadView=new CatLoadingView();
        }
        return mView;
    }

    @Override
    public void onClick(View view) {
        final String oldPwd=mOldPwdEtx.getText().toString();
        final String newPwd=mNewPwdEtx.getText().toString();
        if (TextUtils.isEmpty(oldPwd)){
            mOldPwdEtx.setError("旧密码不能为空");
            mOldPwdEtx.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(newPwd)){
            mNewPwdEtx.setError("新密码不能为空");
            mNewPwdEtx.requestFocus();
            return;
        }

        mCatLoadView.show(getFragmentManager(),"");

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> params= GenerialUtils.GetBaseMap(getActivity());
                params.put(ConstanValues.T,ConstanValues.RESETPWD);
                params.put(ConstanValues.OLD_PWD, Md5.getMessageDigest(oldPwd.getBytes()));
                params.put(ConstanValues.NEW_PWD, Md5.getMessageDigest(newPwd.getBytes()));
                Log.i("ZQ","uRL=="+GenerialUtils.GetUrlParams(ConstanValues.BASEURL+"/User",params));
                new MyOkHttp().get().url(ConstanValues.BASEURL+"/User").params(params).enqueue(new GsonResponseHandler<LoginRespon>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        mCatLoadView.dismiss();
                    }

                    @Override
                    public void onSuccess(int statusCode, LoginRespon response) {
                        mCatLoadView.dismiss();
                        if (null!=response&&response.getRet()==0){
                            UserInfoUtils.getInstances(getActivity()).SaveUserInfo(response.getData());
                        }else{
                            mOldPwdEtx.setError(response.getMsg());
                            mOldPwdEtx.requestFocus();
                        }
                        Toast.makeText(getActivity(),response.getMsg(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        },2500);


    }
}
