package com.Pedrum.StoryFinderFinale.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.Pedrum.StoryFinderFinale.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SplashActivity.this.finish();
                SplashActivity.this.overridePendingTransition(0,android.R.anim.fade_out);
                startActivity(new Intent(SplashActivity.this, StoryListActivity.class));
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }
}
