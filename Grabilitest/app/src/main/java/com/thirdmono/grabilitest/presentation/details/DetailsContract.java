package com.thirdmono.grabilitest.presentation.details;

import android.content.BroadcastReceiver;

import com.thirdmono.grabilitest.data.entity.Entry;
import com.thirdmono.grabilitest.presentation.BasePresenter;
import com.thirdmono.grabilitest.presentation.BaseView;

/**
 * Contract between view and presenter for the App details in the MVP pattern.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public interface DetailsContract {

    interface Presenter extends BasePresenter {

        void setupConnectionBroadcastReceiver();

        void setEntry(Entry entry);

        void init();
    }

    interface View extends BaseView {

        void registerConnectionBroadcastReceiver(BroadcastReceiver broadcastReceiver);

        void unRegisterConnectionBroadcastReceiver(BroadcastReceiver broadcastReceiver);

        void hideNoConnectionMessage();

        void showNoConnectionMessage();

        void setTitle(String label);

        void setArtist(String label);

        void setCategory(String label);

        void setDescription(String label);

        void setIcon(String label);
    }
}
