package com.thirdmono.grabilitest.presentation.details.view;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.thirdmono.grabilitest.AppStoreApplication;
import com.thirdmono.grabilitest.R;
import com.thirdmono.grabilitest.domain.utils.Constants;
import com.thirdmono.grabilitest.presentation.BaseActivity;
import com.thirdmono.grabilitest.presentation.details.DetailsContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity implements DetailsContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.offline_message)
    TextView offlineMessage;

    @Inject
    DetailsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setupDependencyInjection();

        setupToolbar();
        setupDetailFragment(savedInstanceState);

        presenter.setView(this);
        presenter.setupConnectionBroadcastReceiver();
    }

    private void setupDependencyInjection() {
        ((AppStoreApplication) getApplication()).getAppComponent().inject(this);
    }

    private void setupDetailFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(Constants.APP_SELECTED_KEY, getIntent().getStringExtra(Constants.APP_SELECTED_KEY));
            DetailsFragment fragment = new DetailsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit();
        }
    }

    private void setupToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void registerConnectionBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void unRegisterConnectionBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        unregisterReceiver(broadcastReceiver);
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
    public void setTitle(String label) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setArtist(String label) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCategory(String label) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDescription(String label) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setIcon(String label) {
        throw new UnsupportedOperationException();
    }

}
