package com.byr.assistant.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.byr.assistant.R;
import com.byr.assistant.ui.DialogFragmentActivity;
import com.byr.assistant.ui.home.HomeActivity;


/**
 * Created by orange on 13-11-26.
 */
public class SplashActivity extends DialogFragmentActivity implements Runnable {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }

    @Override
    public void run() {
        finish();
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
