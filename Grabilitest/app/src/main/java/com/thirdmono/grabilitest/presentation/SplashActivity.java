package com.thirdmono.grabilitest.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.thirdmono.grabilitest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static double TENSION = 800;
    private static double FRICTION = 10;
    @BindView(R.id.splash_logo)
    ImageView splashLogo;
    SpringListener springListener;
    Spring spring;
    SpringSystem springSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setupSplashAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        spring.addListener(springListener);
        startMainActivity();
    }

    @Override
    protected void onPause() {
        super.onPause();
        spring.removeAllListeners();
    }

    private void setupSplashAnimation() {
        springSystem = SpringSystem.create();
        spring = springSystem.createSpring();
        SpringConfig springConfig = new SpringConfig(TENSION, FRICTION);
        spring.setSpringConfig(springConfig);
        springListener = new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 2);
                splashLogo.setScaleX(mappedValue);
                splashLogo.setScaleY(mappedValue);
            }
        };
        spring.addListener(springListener);
        spring.setEndValue(1.5);
    }

    private void startMainActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 1000);
    }
}
