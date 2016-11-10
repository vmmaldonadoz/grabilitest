package com.thirdmono.grabilitest.presentation.list.presenter;


import android.view.View;

import com.thirdmono.grabilitest.data.api.FreeAppsService;
import com.thirdmono.grabilitest.data.entity.Entry;
import com.thirdmono.grabilitest.data.entity.Feed;
import com.thirdmono.grabilitest.data.entity.FeedWrapper;
import com.thirdmono.grabilitest.presentation.BaseView;
import com.thirdmono.grabilitest.presentation.list.AppListContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Presenter for the App list.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class AppListPresenter implements AppListContract.Presenter {


    private AppListContract.View view;

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void setView(BaseView view) {
        this.view = (AppListContract.View) view;
    }

    @Override
    public void onItemClicked(View view, Entry entry) {
        this.view.openDetail();
    }

    @Override
    public void getApplications(FreeAppsService freeAppsService) {
        freeAppsService.getFreeApplicationsByCategory("").enqueue(new Callback<FeedWrapper>() {
            @Override
            public void onResponse(Call<FeedWrapper> call, Response<FeedWrapper> response) {
                Timber.d("Got some feed back!");
                if (response.isSuccessful()) {
                    view.updateListOfApplications(getListOfApplications(response.body()));
                    Timber.i("Books data was loaded from API.");
                }
            }

            @Override
            public void onFailure(Call<FeedWrapper> call, Throwable t) {
                Timber.e(t, "Failed to get feed!");
            }

        });
    }

    private List<Entry> getListOfApplications(FeedWrapper feedWrapper) {
        Feed feed = null;
        List<Entry> applications = new ArrayList<>();
        if (feedWrapper != null) {
            feed = feedWrapper.getFeed();
        }
        if (feed != null) {
            if (feed.getEntry() != null && !feed.getEntry().isEmpty()) {
                Timber.i("getListOfApplications(): size: %s", feed.getEntry().size());
                applications = feed.getEntry();
            } else {
                Timber.w("getListOfApplications(): Empty entries.");
            }
        } else {
            Timber.w("getListOfApplications(): Empty feed!");
        }
        return applications;
    }
}
