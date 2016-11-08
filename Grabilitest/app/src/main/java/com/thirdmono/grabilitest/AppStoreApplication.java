package com.thirdmono.grabilitest;

import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.thirdmono.grabilitest.data.logger.LoggerTree;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Custom {@link MultiDexApplication}.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class AppStoreApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setupTimber();
    }

    private void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Fabric.with(this, new Crashlytics());
            Timber.plant(new LoggerTree());
        }
    }
}
