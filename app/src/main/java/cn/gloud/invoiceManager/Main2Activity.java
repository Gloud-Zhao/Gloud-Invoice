package cn.gloud.invoiceManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;

import cn.gloud.invoiceManager.Entity.InvoiceRespon;
import cn.gloud.invoiceManager.Entity.LoginRespon;
import cn.gloud.invoiceManager.Entity.UserInfo;
import cn.gloud.invoiceManager.Utils.ConstanValues;
import cn.gloud.invoiceManager.Utils.GenerialUtils;
import cn.gloud.invoiceManager.Utils.UserInfoUtils;
import cn.gloud.invoiceManager.fragment.ReSetPwdFragment;
import cn.gloud.invoiceManager.fragment.ScanQrCodeFragment;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mAccountNameTv,mAccountIdTv;
    private UserInfo mUserinfo;

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAccountIdTv=navigationView.getHeaderView(0).findViewById(R.id.account_id);
        mAccountNameTv=navigationView.getHeaderView(0).findViewById(R.id.account_name);
        mUserinfo= UserInfoUtils.getInstances(this).GetUserinfo();

        mAccountIdTv.setText(mUserinfo.getAccount_name());
        mAccountNameTv.setText(mUserinfo.getIs_admin()==1?"管理员":"普通用户");

        mCurrentFragment=new ScanQrCodeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_root_layout,mCurrentFragment,"").commit();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("ZQ","onActivityResult..."+requestCode);
        if (resultCode== Activity.RESULT_OK){
            final String resutlStr=data.getStringExtra("DATA");
            Log.i("ZQ","resutlStr=="+resutlStr);
            if (mCurrentFragment instanceof ScanQrCodeFragment){
                Log.i("ZQ","zzzzzzzzzzzzzzresutlStr=="+resutlStr);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((ScanQrCodeFragment)mCurrentFragment).SnedInvoice(resutlStr);
                    }
                },260);
            }
        }
    }

Handler handler=new Handler();


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.setting_item) {
            mCurrentFragment=new ReSetPwdFragment();
         getSupportFragmentManager().beginTransaction().replace(R.id.main_root_layout,mCurrentFragment,"").commit();
        }else if (id==R.id.scan_qrcode_item){
            mCurrentFragment=new ScanQrCodeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_root_layout,mCurrentFragment,"").commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
