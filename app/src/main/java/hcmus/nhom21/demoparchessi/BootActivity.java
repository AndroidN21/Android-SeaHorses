package hcmus.nhom21.demoparchessi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BootActivity extends Activity {
    private ProgressBar proBar;
    private TextView txtPercent;
    private ImageView img;
    int progressStep = 1;
    int value = 0;
    int accum = 0;
    int waitingTime = 10;
    Handler myHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        proBar = (ProgressBar) findViewById(R.id.proBar);
        txtPercent = (TextView) findViewById(R.id.txtPercent);
        img = findViewById(R.id.imageView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        value = 100;
        accum = 0;
        proBar.setMax(value);
        proBar.setProgress(0);
        proBar.setVisibility(View.VISIBLE);

        Thread myBackgroundThread = new Thread(backgroundTask, "backAlias1");
        myBackgroundThread.start();
    }

    // FOREGROUND: this foreground Runnable works on behave of the background thread, its mission is to update the main UI which is unreachable to back worker
    private Runnable foregroundRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                accum += progressStep;
                proBar.setProgress(accum);
                txtPercent.setText((int)accum * 100 / value + "%");

                if (accum >= value) {
                    //Khởi tạo Intent
                    Intent intentMain = new Intent();
                    intentMain.setClass(findViewById(android.R.id.content).getRootView().getContext(), MainActivity.class);
                    //Gửi Intent cho hệ thống Android để kích hoạt Activity
                    startActivity(intentMain);
                }
            }
            catch (Exception e) { Log.e("<<foregroundTask>>", e.getMessage()); }
        }
    }; // foregroundTask

    private Runnable backgroundTask = new Runnable() {
        @Override
        public void run() { // busy work goes here...
            try {
                for (int n = 0; n < value; n++) {
                    Thread.sleep(waitingTime);
                    myHandler.post(foregroundRunnable);
                }
            }
            catch (InterruptedException e) { Log.e("<<foregroundTask>>", e.getMessage()); }
        }// run
    };// backgroundTask
}
