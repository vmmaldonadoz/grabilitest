package com.thirdmono.grabilitest.presentation.list;

import com.thirdmono.grabilitest.data.api.FreeAppsService;
import com.thirdmono.grabilitest.data.entity.Entry;
import com.thirdmono.grabilitest.presentation.BasePresenter;
import com.thirdmono.grabilitest.presentation.BaseView;

import java.util.List;

/**
 * Contract between view and presenter for the App list in the MVP pattern.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public interface AppListContract {

    interface Presenter extends BasePresenter {

        void onItemClicked(android.view.View view, Entry entry);


        void getApplications(FreeAppsService freeAppsService);
    }

    interface View extends BaseView {

        void openDetail();

        void updateListOfApplications(List<Entry> listOfApplications);
    }
}