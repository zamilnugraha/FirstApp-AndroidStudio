package com.zamil.appbelajar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zamil.appbelajar.R;
import com.zamil.appbelajar.helper.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPrefManager = new SharedPrefManager(this);

        if(sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(SplashActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        } else {
            // Start home activity
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }

    }
}
