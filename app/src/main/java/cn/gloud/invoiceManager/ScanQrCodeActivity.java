package cn.gloud.invoiceManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;
import com.vondear.rxtools.activity.ActivityScanerCode;
import com.vondear.rxtools.interfaces.OnRxScanerListener;

public class ScanQrCodeActivity extends ActivityScanerCode implements OnRxScanerListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScanerListener(this);
    }

    @Override
    public void onSuccess(String s, Result result) {
       Intent tIntent=getIntent();
        tIntent.putExtra("DATA",result.getText());
        setResult(Activity.RESULT_OK,tIntent);
        this.finish();
    }

    @Override
    public void onFail(String s, String s1) {

    }
}
