package com.proj.advoteck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    private static final long SPLASH_DELAY = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Using Handler to delay the intent for 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start MainActivity
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                // Close SplashActivity
                finish();
            }
        }, SPLASH_DELAY);
    }
}

