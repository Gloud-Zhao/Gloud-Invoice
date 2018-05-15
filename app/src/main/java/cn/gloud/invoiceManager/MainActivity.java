package cn.gloud.invoiceManager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.greendao.query.QueryBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import cn.gloud.invoiceManager.Entity.InvoiceInfoEntity;
import cn.gloud.invoiceManager.greendao.gen.DaoMaster;
import cn.gloud.invoiceManager.greendao.gen.DaoSession;
import cn.gloud.invoiceManager.greendao.gen.InvoiceInfoEntityDao;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView priceTv;
    private TextView pushDateTv;
    private TextView checkNumTv;
    private TextView lefNumTv;
    private TextView numTv;
    private TextView statusTv;
    private EditText memberEdx;
    private Button mSaveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        priceTv = (TextView) findViewById(R.id.price_tv);
        pushDateTv = (TextView) findViewById(R.id.push_date_tv);
        checkNumTv = (TextView) findViewById(R.id.check_num_tv);
        lefNumTv = (TextView) findViewById(R.id.lef_num_tv);
        numTv = (TextView) findViewById(R.id.num_tv);
        memberEdx= getEditText();
        statusTv= (TextView) findViewById(R.id.status_tv);
        findViewById(R.id.scan_qrcode_btn).setOnClickListener(this);
        mSaveButton= (Button) findViewById(R.id.save_info_btn);
        mSaveButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scan_qrcode_btn:
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    Intent intent=new Intent(this,ScanQrCodeActivity.class);
                    startActivityForResult(intent,9527);
                }
                break;
            case R.id.save_info_btn:
                SaveInfo();
                break;
        }
    }

    private void SaveInfo() {
        String memberStr=memberEdx.getText().toString();
        if (memberStr.isEmpty()){
            Toast.makeText(this,"报销人不能为空",Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(MainActivity.this, "插入数据成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode==9527){
            if (resultCode== Activity.RESULT_OK){
                String resutlStr=data.getStringExtra("DATA");
                DisplayInfo(resutlStr);
            }

       }
    }

    private void DisplayInfo(String str){
        Log.i("ZQ","ResultStr==="+str);
        //未知,未知,发票左上角的号，发票号，金额，开票日期，校验码，未知
        if (str.contains(",")){
            

        }else{
            Toast.makeText(this,"非有效发票二维码",Toast.LENGTH_LONG).show();
        }

    }




    public String makeMD5(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    private EditText getEditText(){
        return (EditText) findViewById(R.id.editText);
    }
}
