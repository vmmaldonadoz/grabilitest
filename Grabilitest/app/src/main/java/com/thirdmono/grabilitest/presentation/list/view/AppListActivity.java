package com.thirdmono.grabilitest.presentation.list.view;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thirdmono.grabilitest.AppStoreApplication;
import com.thirdmono.grabilitest.R;
import com.thirdmono.grabilitest.data.api.FreeAppsService;
import com.thirdmono.grabilitest.data.entity.Entry;
import com.thirdmono.grabilitest.presentation.BaseActivity;
import com.thirdmono.grabilitest.presentation.list.AppListContract;
import com.thirdmono.grabilitest.presentation.list.presenter.AppListPresenter;
import com.thirdmono.grabilitest.presentation.list.view.adapter.ItemAppAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppListActivity extends BaseActivity implements AppListContract.View, ItemAppAdapter.OnItemClickListener {

    @Inject
    FreeAppsService freeAppsService;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.offline_message)
    TextView offlineMessage;

    @BindView(R.id.recycler_with_apps)
    RecyclerView recyclerViewWithApps;

    ItemAppAdapter appAdapter;
    AppListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);
        setupDependencyInjection();

        setupToolbar();
        setupRecyclerViewWithApps();

        presenter = new AppListPresenter();
        presenter.setView(this);
        presenter.getApplications(freeAppsService);
        presenter.setupConnectionBroadcastReceiver();
    }

    private void setupRecyclerViewWithApps() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewWithApps.setLayoutManager(linearLayoutManager);
        appAdapter = new ItemAppAdapter(new ArrayList<Entry>(), this, this);
        recyclerViewWithApps.setAdapter(appAdapter);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
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


    private void setupDependencyInjection() {
        ((AppStoreApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void onClick(View view, Entry entry) {
        presenter.onItemClicked(view, entry);
    }

    @Override
    public void openDetail() {
        Toast.makeText(this, "Opening details.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateListOfApplications(List<Entry> listOfApplications) {
        appAdapter.setItems(listOfApplications);
    }

    @Override
    public void hideNoConnectionMessage() {
        offlineMessage.setVisibility(View.GONE);
    }

    @Override
    public void showNoConnectionMessage() {
        offlineMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void registerConnectionBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void unRegisterConnectionBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        unregisterReceiver(broadcastReceiver);
    }
}
