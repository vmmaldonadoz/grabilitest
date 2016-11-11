package com.thirdmono.grabilitest.presentation.splash.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.thirdmono.grabilitest.AppStoreApplication;
import com.thirdmono.grabilitest.R;
import com.thirdmono.grabilitest.presentation.BaseActivity;
import com.thirdmono.grabilitest.presentation.list.view.AppListActivity;
import com.thirdmono.grabilitest.presentation.splash.SplashContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View for the Splash.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class SplashActivity extends BaseActivity implements SplashContract.View {

    @BindView(R.id.splash_logo)
    ImageView splashLogo;

    @Inject
    SplashContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setupDependencyInjection();

        presenter.setView(this);
        presenter.setupAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void gotoAppListActivity() {
        startActivity(new Intent(SplashActivity.this, AppListActivity.class));
        finish();
    }

    @Override
    public void animateLogo(float mappedValue) {
        splashLogo.setScaleX(mappedValue);
        splashLogo.setScaleY(mappedValue);
    }

    private void setupDependencyInjection() {
        ((AppStoreApplication) getApplication()).getAppComponent().inject(this);
    }
}
