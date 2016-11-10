package com.thirdmono.grabilitest.presentation.details.presenter;

import com.thirdmono.grabilitest.presentation.BaseView;
import com.thirdmono.grabilitest.presentation.details.AppDetailsContract;

import javax.inject.Inject;

/**
 * Presenter for the application details.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class DetailsPresenter implements AppDetailsContract.Presenter {

    private AppDetailsContract.View view;

    @Inject
    public DetailsPresenter() {
    }


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
        this.view = (AppDetailsContract.View) view;
    }
}
