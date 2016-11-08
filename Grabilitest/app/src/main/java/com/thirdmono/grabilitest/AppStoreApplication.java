package com.thirdmono.grabilitest;

import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
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
        setupLeakCanary();
    }

    private void setupTimber() {
        Fabric.with(this, new Crashlytics());
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new LoggerTree());
        }
    }

    private void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
