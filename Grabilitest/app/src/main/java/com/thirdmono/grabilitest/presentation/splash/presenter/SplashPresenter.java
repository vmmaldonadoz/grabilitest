package com.thirdmono.grabilitest.presentation.splash.presenter;

import android.os.Handler;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.thirdmono.grabilitest.presentation.BaseView;
import com.thirdmono.grabilitest.presentation.splash.SplashContract;

/**
 * Presenter for the Splash.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class SplashPresenter implements SplashContract.Presenter {

    private SpringListener springListener;
    private Spring spring;
    private SpringSystem springSystem;
    private SplashContract.View view;

    private void addDelay() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.gotoMainActivity();
            }
        }, 1000);
    }

    @Override
    public void resume() {
        spring.addListener(springListener);
    }

    @Override
    public void pause() {
        spring.removeAllListeners();
    }

    @Override
    public void destroy() {
        this.view = null;
        if (spring != null) {
            spring.removeAllListeners();
        }
        spring = null;
        springListener = null;
        springSystem = null;
    }

    @Override
    public void setView(BaseView view) {
        this.view = (SplashContract.View) view;
    }

    @Override
    public void setupAnimation() {
        springSystem = SpringSystem.create();
        spring = springSystem.createSpring();
        double TENSION = 800;
        double FRICTION = 10;
        SpringConfig springConfig = new SpringConfig(TENSION, FRICTION);
        spring.setSpringConfig(springConfig);
        springListener = new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 1.5);
                view.animateLogo(mappedValue);
            }
        };
        spring.addListener(springListener);
        spring.setEndValue(1.5);
        addDelay();
    }

}
