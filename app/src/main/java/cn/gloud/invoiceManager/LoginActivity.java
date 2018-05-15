package cn.gloud.invoiceManager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.gloud.invoiceManager.Entity.LoginRespon;
import cn.gloud.invoiceManager.Entity.UserInfo;
import cn.gloud.invoiceManager.Utils.ConstanValues;
import cn.gloud.invoiceManager.Utils.GenerialUtils;
import cn.gloud.invoiceManager.Utils.Md5;
import cn.gloud.invoiceManager.Utils.UserInfoUtils;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // UI references.
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mPasswordView;
    private View mLoginFormView;
    private com.dd.processbutton.iml.ActionProcessButton mEmailSignInButton;
    private ProgressGenerator mProgressGenerator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (AutoCompleteTextView) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mProgressGenerator= new ProgressGenerator();
        mEmailSignInButton = (ActionProcessButton) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        mEmailSignInButton.setEnabled(false);
        mPasswordView.setEnabled(false);
        mEmailView.setEnabled(false);

        mProgressGenerator.start(mEmailSignInButton);
       // mEmailSignInButton.setMode(ActionProcessButton.Mode.PROGRESS);
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;



        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.account_empty_tips));
            focusView = mEmailView;
            cancel = true;
        }else if (!(!TextUtils.isEmpty(password)&&isPasswordValid(password))) {
                mPasswordView.setError(getString(R.string.pwd_empty_tips));
                focusView = mPasswordView;
                cancel = true;
            }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            Login(email,password);
        }
    }

    private void Login(String account,String pwd){
        HashMap<String,String> params= GenerialUtils.GetBaseMap(this);
        params.put(ConstanValues.T,ConstanValues.LOGIN);
        params.put(ConstanValues.ACCOUNT,account);
        params.put(ConstanValues.PWD,Md5.getMessageDigest(pwd.getBytes()));
        Log.i("ZQ","uRL=="+GenerialUtils.GetUrlParams(ConstanValues.BASEURL+"/User",params));
        new MyOkHttp().get().url(ConstanValues.BASEURL+"/User").params(params).enqueue(new GsonResponseHandler<LoginRespon>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                mPasswordView.setEnabled(true);
                mEmailSignInButton.setEnabled(true);
                mEmailView.setEnabled(true);
               // mEmailSignInButton.setMode(ActionProcessButton.Mode.ENDLESS);
                mProgressGenerator.stop();
            }

            @Override
            public void onSuccess(int statusCode, LoginRespon response) {
                mPasswordView.setEnabled(true);
                mEmailSignInButton.setEnabled(true);
                mEmailView.setEnabled(true);
                mProgressGenerator.stop();
                if (statusCode==200&&response.getRet()==0){
                    //登录成功
                    UserInfoUtils.getInstances(LoginActivity.this).SaveUserInfo(response.getData());
                    Intent tIntent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(tIntent);
                    finish();
                }else{
                    //错误
                    if (null!=response){
                        mPasswordView.setError(response.getMsg());
                        mPasswordView.requestFocus();
                    }
                }
            }
        });
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }



}

