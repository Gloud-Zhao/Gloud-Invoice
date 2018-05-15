package cn.gloud.invoiceManager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;

import cn.gloud.invoiceManager.Entity.InvoiceRespon;
import cn.gloud.invoiceManager.R;
import cn.gloud.invoiceManager.ScanQrCodeActivity;
import cn.gloud.invoiceManager.Utils.ConstanValues;
import cn.gloud.invoiceManager.Utils.GenerialUtils;
import cn.gloud.invoiceManager.Utils.UserInfoUtils;

public class ScanQrCodeFragment extends Fragment implements View.OnClickListener{

    private TextView mPriceTv;
    private TextView pushDateTv;
    private TextView checkNumTv;
    private TextView lefNumTv;
    private TextView numTv;
    private TextView statusTv;

    private AutoCompleteTextView memberEdx;
    private Button mSaveButton;
    private Button mDelBtn;
    private Button mClaimBtn;
    private String mMemberStr;

    private View mView=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null==mView){
            mView=View.inflate(getContext(), R.layout.fragment_qrresult,null);
            mPriceTv = mView.findViewById(R.id.invoice_price_tv);
            pushDateTv = mView.findViewById(R.id.invoice_push_date_tv);
            checkNumTv = mView.findViewById(R.id.invoice_check_num_tv);
            lefNumTv = mView.findViewById(R.id.invoice_lef_num_tv);
            numTv =  mView.findViewById(R.id.invoice_num_tv);
            memberEdx= mView.findViewById(R.id.invoice_master_etx);
            mClaimBtn=mView.findViewById(R.id.claim_btn);

            statusTv=  mView.findViewById(R.id.invoice_status_tv);
            mSaveButton= mView.findViewById(R.id.scan_qrcode_btn);
            mSaveButton.setOnClickListener(this);
            mDelBtn=mView.findViewById(R.id.del_btn);
            mDelBtn.setOnClickListener(this);
        }
       Log.i("ZQ","---1---"+this.toString());
        return mView;
    }

    public void SnedInvoice(String str){
        Log.i("ZQ","---1---"+this.toString());
        HashMap<String,String> params= GenerialUtils.GetBaseMap(getActivity());
        params.put(ConstanValues.T,ConstanValues.INVOICE_INSERT);
        params.put(ConstanValues.INVOICE_DATA,str);
        params.put(ConstanValues.UPDATER, UserInfoUtils.getInstances(getActivity()).GetUserinfo().getAccount_name());
        params.put(ConstanValues.ACCOUNT,mMemberStr);
        Log.i("ZQ","uRL=="+GenerialUtils.GetUrlParams(ConstanValues.BASEURL+"/Invoice",params));

        new MyOkHttp().get().url(ConstanValues.BASEURL+"/Invoice").params(params).enqueue(new GsonResponseHandler<InvoiceRespon>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Toast.makeText(getActivity(),error_msg,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode,final InvoiceRespon response) {
                if (statusCode==200&&response.getRet()==0){
                    DisplayResult(response);
                }else{
                    Toast.makeText(getActivity(),response.getMsg(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    private TextView priceTv;
//    private TextView pushDateTv;
//    private TextView checkNumTv;
//    private TextView lefNumTv;
//    private TextView numTv;
//    private TextView statusTv;

    private InvoiceRespon mInvoiceRespon;
    private void DisplayResult(InvoiceRespon response) {
        if (null!=response){
            mInvoiceRespon=response;
            InvoiceRespon.InvoiceBean tInvoice=response.getData();
            Log.i("ZQ","tInvoice=="+tInvoice.toString());
            mPriceTv.setText("不含税价格:"+tInvoice.getPrice());
            pushDateTv.setText("日期:"+tInvoice.getDate());
            checkNumTv.setText("校验码:"+tInvoice.getCheckCode());
            lefNumTv.setText("机器编码:"+tInvoice.getLeftCode());
            numTv.setText("发票号:"+tInvoice.getInvoiceCode());
           statusTv.setText(tInvoice.getMaster()+(tInvoice.getClaim()==1?("已报销"):"未报销"));

           if (tInvoice.getIsDelete()==1){
               mDelBtn.setText("恢复");
           }else{
               mDelBtn.setText("删除");
           }

            mDelBtn.setVisibility(UserInfoUtils.getInstances(getActivity()).GetUserinfo().getIs_admin()==1?View.VISIBLE:View.GONE);

            mClaimBtn.setVisibility(UserInfoUtils.getInstances(getActivity()).GetUserinfo().getIs_admin()==1?View.VISIBLE:View.GONE);

            mClaimBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String,String> params= GenerialUtils.GetBaseMap(getActivity());
                    params.put(ConstanValues.T,ConstanValues.INVOICE_UPDATE);
                    params.put(ConstanValues.INVOICE_ID, mInvoiceRespon.getData().getInvoiceid()+"");
                    params.put(ConstanValues.ACCOUNT,UserInfoUtils.getInstances(getActivity()).GetUserinfo().getAccount_name());
                    params.put(ConstanValues.INVOICE_CLAIM,(mInvoiceRespon.getData().getIsDelete()==1)?"0":"1");
                    Log.i("ZQ","uRL=="+GenerialUtils.GetUrlParams(ConstanValues.BASEURL+"/Invoice",params));

                    new MyOkHttp().get().url(ConstanValues.BASEURL+"/Invoice").params(params).enqueue(new GsonResponseHandler<InvoiceRespon>() {
                        @Override
                        public void onFailure(int statusCode, String error_msg) {
                            Toast.makeText(getActivity(),error_msg,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onSuccess(int statusCode,final InvoiceRespon response) {
                            if (statusCode==200&&response.getRet()==0){
                                mClaimBtn.setVisibility(View.GONE);
                            }else{
                                Toast.makeText(getActivity(),response.getMsg(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });

            mDelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String,String> params= GenerialUtils.GetBaseMap(getActivity());
                    params.put(ConstanValues.T,ConstanValues.INVOICE_DEL);
                    params.put(ConstanValues.INVOICE_ID, mInvoiceRespon.getData().getInvoiceid()+"");
                    params.put(ConstanValues.ACCOUNT,UserInfoUtils.getInstances(getActivity()).GetUserinfo().getAccount_name());
                    params.put(ConstanValues.INVOICE_IS_DEL,(mInvoiceRespon.getData().getIsDelete()==1)?"0":"1");
                    Log.i("ZQ","uRL=="+GenerialUtils.GetUrlParams(ConstanValues.BASEURL+"/Invoice",params));

                    new MyOkHttp().get().url(ConstanValues.BASEURL+"/Invoice").params(params).enqueue(new GsonResponseHandler<InvoiceRespon>() {
                        @Override
                        public void onFailure(int statusCode, String error_msg) {
                            Toast.makeText(getActivity(),error_msg,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onSuccess(int statusCode,final InvoiceRespon response) {
                            if (statusCode==200&&response.getRet()==0){
                                InvoiceRespon.InvoiceBean tData=mInvoiceRespon.getData();
                                tData.setIsDelete((mInvoiceRespon.getData().getIsDelete()==1)?0:1);
                                mInvoiceRespon.setData(tData);
                                if (tData.getIsDelete()==1){
                                    mDelBtn.setText("恢复");
                                }else{
                                    mDelBtn.setText("删除");
                                }
                               Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getActivity(),response.getMsg(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });

        }


    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.scan_qrcode_btn){
            mMemberStr=memberEdx.getText().toString();
            if (TextUtils.isEmpty(mMemberStr)){
                memberEdx.setError("报销人不能为空");
                return;
            }
            Intent intent=new Intent(getActivity(),ScanQrCodeActivity.class);
            startActivityForResult(intent,9527);

        }else if (view.getId()==R.id.del_btn){
            //删除
        }

    }
}
