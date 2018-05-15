package cn.gloud.invoiceManager;

import com.dd.processbutton.ProcessButton;
import com.dd.processbutton.iml.ActionProcessButton;

import android.os.Handler;

import java.util.Random;
public class ProgressGenerator {


    private int mProgress;
    private ActionProcessButton mButton;

    public ProgressGenerator() {

    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            mProgress += 10;
            if (mProgress>=100){
                mProgress=10;
            }
            mButton.setProgress(mProgress);
            if (mProgress < 100) {
                handler.postDelayed(this, generateDelay());
            } else {
                mProgress=10;
                handler.postDelayed(this, generateDelay());
            }
        }
    };
    final Handler handler = new Handler();
    public void start(final ActionProcessButton button) {
        mButton=button;
        handler.postDelayed(runnable, generateDelay());
    }

    public void stop(){
        mButton.setMode(ActionProcessButton.Mode.ENDLESS);
        handler.removeCallbacks(runnable);
    }

    private Random random = new Random();

    private int generateDelay() {
        return random.nextInt(1000);
    }
}