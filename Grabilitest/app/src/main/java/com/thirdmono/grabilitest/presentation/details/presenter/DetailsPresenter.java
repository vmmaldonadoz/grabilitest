package com.thirdmono.grabilitest.presentation.details.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.thirdmono.grabilitest.data.entity.Entry;
import com.thirdmono.grabilitest.data.entity.ImImage;
import com.thirdmono.grabilitest.domain.utils.Utils;
import com.thirdmono.grabilitest.presentation.BaseView;
import com.thirdmono.grabilitest.presentation.details.DetailsContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Presenter for the application details.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;
    private BroadcastReceiver connectionBroadcastReceiver;
    private Entry entry;

    @Inject
    public DetailsPresenter() {
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
    }

    @Override
    public void setView(BaseView view) {
        this.view = (DetailsContract.View) view;
    }

    @Override
    public void setupConnectionBroadcastReceiver() {
        connectionBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (Utils.hasNetwork(context)) {
                    view.hideNoConnectionMessage();
                } else {
                    view.showNoConnectionMessage();
                }
            }
        };
    }

    @Override
    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    @Override
    public void init() {
        if (entry == null) return;

        final List<ImImage> images = entry.getImImage();
        if (images.size() > 0) {
            view.setIcon(images.get(images.size() - 1).getLabel());
        }

        view.setTitle(entry.getTitle().getLabel());

        view.setArtist(entry.getImArtist().getLabel());

        view.setCategory(entry.getCategory().getAttributes().getLabel());

        view.setDescription(entry.getSummary().getLabel());

    }
}
