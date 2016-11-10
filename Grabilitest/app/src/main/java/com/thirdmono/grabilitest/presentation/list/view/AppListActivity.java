package com.thirdmono.grabilitest.presentation.list.view;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thirdmono.grabilitest.AppStoreApplication;
import com.thirdmono.grabilitest.R;
import com.thirdmono.grabilitest.data.entity.CategoryFilter;
import com.thirdmono.grabilitest.data.entity.Entry;
import com.thirdmono.grabilitest.presentation.BaseActivity;
import com.thirdmono.grabilitest.presentation.list.AppListContract;
import com.thirdmono.grabilitest.presentation.list.view.adapter.ItemAppAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Presenter for the list of applications.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class AppListActivity extends BaseActivity implements AppListContract.View, ItemAppAdapter.OnItemClickListener {

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.offline_message)
    TextView offlineMessage;

    @BindView(R.id.recycler_with_apps)
    RecyclerView recyclerViewWithApps;

    @Inject
    AppListContract.Presenter presenter;
    private ItemAppAdapter appAdapter;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);
        setupDependencyInjection();

        setupToolbar();
        setupRecyclerViewWithApps();

        presenter.setView(this);
        presenter.getApplicationsByCategory(new CategoryFilter());
        presenter.setupConnectionBroadcastReceiver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.filter_categories:
                showCategories();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showCategories() {
        new AlertDialog.Builder(this, R.style.CategoriesDialogStyle)
                .setTitle(R.string.choose_category)
                .setSingleChoiceItems(getResources().getStringArray(R.array.genre_name), 0, null)
                .setPositiveButton(R.string.ok_button_label, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        String[] names = getResources().getStringArray(R.array.genre_name);
                        String[] ids = getResources().getStringArray(R.array.genre_id);
                        CategoryFilter category = new CategoryFilter(ids[selectedPosition], names[selectedPosition]);
                        presenter.getApplicationsByCategory(category);
                    }
                })
                .show();
    }

    private void setupRecyclerViewWithApps() {
        if (getResources().getBoolean(R.bool.tablet)) {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerViewWithApps.setLayoutManager(layoutManager);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerViewWithApps.setLayoutManager(linearLayoutManager);
        }
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
        snackbar = null;
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
    public void updateListOfApplications(List<Entry> listOfApplications, CategoryFilter categoryFilter) {
        appAdapter.setItems(listOfApplications);
        updateTitle(categoryFilter);
        hideConnectionMessage();
    }

    private void hideConnectionMessage() {
        if (snackbar != null && snackbar.isShownOrQueued()) {
            snackbar.dismiss();
        }
    }

    private void updateTitle(CategoryFilter categoryFilter) {
        if (categoryFilter != null && !categoryFilter.getId().isEmpty()) {
            toolbar.setTitle(categoryFilter.getName());
        } else {
            toolbar.setTitle(getTitle());
        }

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

    @Override
    public void showErrorDuringRequestMessage() {
        showRetrySnackbar(R.string.error_loading);
    }

    @Override
    public void showEmptyResponseMessage(CategoryFilter categoryFilter) {
        showRetrySnackbar(R.string.empty_response);
        updateTitle(categoryFilter);
    }

    private void showRetrySnackbar(@StringRes int message) {
        snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.getApplicationsByCategory(null);
                    }
                });
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
}
