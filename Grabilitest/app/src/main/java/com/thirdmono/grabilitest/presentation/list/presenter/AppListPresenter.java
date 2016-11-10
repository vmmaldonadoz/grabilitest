package com.thirdmono.grabilitest.presentation.list.presenter;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.thirdmono.grabilitest.data.api.FreeAppsService;
import com.thirdmono.grabilitest.data.entity.CategoryFilter;
import com.thirdmono.grabilitest.data.entity.Entry;
import com.thirdmono.grabilitest.data.entity.Feed;
import com.thirdmono.grabilitest.data.entity.FeedWrapper;
import com.thirdmono.grabilitest.domain.utils.NetworkUtils;
import com.thirdmono.grabilitest.presentation.BaseView;
import com.thirdmono.grabilitest.presentation.list.AppListContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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

    private final FreeAppsService freeAppsService;
    private AppListContract.View view;
    private BroadcastReceiver connectionBroadcastReceiver;
    private CategoryFilter categoryFilter;

    @Inject
    public AppListPresenter(FreeAppsService appsService) {
        this.freeAppsService = appsService;
    }

    @Override
    public void resume() {
        view.registerConnectionBroadcastReceiver(connectionBroadcastReceiver);
    }

    @Override
    public void pause() {
        view.unRegisterConnectionBroadcastReceiver(connectionBroadcastReceiver);
    }

    @Override
    public void destroy() {
        connectionBroadcastReceiver = null;
        categoryFilter = null;
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
    public void getApplicationsByCategory(CategoryFilter category) {
        String id = "";
        if (category == null) {
            if (categoryFilter != null) {
                id = categoryFilter.getId();
            }
        } else {
            categoryFilter = category;
            id = category.getId();
        }
        freeAppsService.getFreeApplicationsByCategory(id).enqueue(new Callback<FeedWrapper>() {
            @Override
            public void onResponse(Call<FeedWrapper> call, Response<FeedWrapper> response) {
                Timber.d("Got some feed back!");
                if (response.isSuccessful()) {

                    List<Entry> entries = getListOfApplications(response.body());
                    if (entries.isEmpty()) {
                        view.showEmptyResponseMessage(categoryFilter);
                        Timber.i("Empty response from API.");
                    } else {
                        view.updateListOfApplications(entries, categoryFilter);
                        Timber.i("Apps data was loaded from API.");
                    }
                }
            }

            @Override
            public void onFailure(Call<FeedWrapper> call, Throwable t) {
                Timber.e(t, "Failed to get feed!");
                view.showErrorDuringRequestMessage();
            }

        });
    }

    @Override
    public void setupConnectionBroadcastReceiver() {
        connectionBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (NetworkUtils.hasNetwork(context)) {
                    view.hideNoConnectionMessage();
                } else {
                    view.showNoConnectionMessage();
                }
            }
        };
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
