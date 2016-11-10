package com.thirdmono.grabilitest.presentation;

import android.os.Bundle;

import com.thirdmono.grabilitest.AppStoreApplication;
import com.thirdmono.grabilitest.R;
import com.thirdmono.grabilitest.data.api.FreeAppsService;
import com.thirdmono.grabilitest.data.entity.Entry;
import com.thirdmono.grabilitest.data.entity.Feed;
import com.thirdmono.grabilitest.data.entity.FeedWrapper;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Inject
    FreeAppsService freeAppsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDependencyInjection();
        makeAPICall();
    }

    private void makeAPICall() {
        freeAppsService.getFreeApplicationsByCategory("").enqueue(new Callback<FeedWrapper>() {
            @Override
            public void onResponse(Call<FeedWrapper> call, Response<FeedWrapper> response) {
                Timber.d("Got some feed back!");
                if (response.isSuccessful()) {
                    showFreeApps(response.body());
                    Timber.i("Books data was loaded from API.");
                }
            }

            @Override
            public void onFailure(Call<FeedWrapper> call, Throwable t) {
                Timber.e(t, "Failed to get feed!");
            }

        });
    }

    private void showFreeApps(FeedWrapper feedWrapper) {
        Feed feed = null;
        if (feedWrapper != null) {
            feed = feedWrapper.getFeed();
        }
        if (feed != null) {
            if (feed.getEntry() != null && !feed.getEntry().isEmpty()) {
                Timber.i("showFreeApps(): size: %s", feed.getEntry().size());
                for (Entry entry : feed.getEntry()) {
                    Timber.i("showFreeApps(): entry: {%s}", entry.getImName().getLabel());
                }
            } else {
                Timber.w("showFreeApps(): Empty entries.");
            }
        } else {
            Timber.w("showFreeApps(): Empty feed!");
        }
    }

    private void setupDependencyInjection() {
        ((AppStoreApplication) getApplication()).getAppComponent().inject(this);
    }
}
