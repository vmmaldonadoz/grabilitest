package com.thirdmono.grabilitest.presentation.splash;

import com.thirdmono.grabilitest.presentation.BasePresenter;
import com.thirdmono.grabilitest.presentation.BaseView;

/**
 * Contract between view and presenter for the Splash in the MVP pattern.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public interface SplashContract {

    interface Presenter extends BasePresenter {

        void setupAnimation();
    }

    interface View extends BaseView {

        void animateLogo(float mappedValue);

        void gotoMainActivity();
    }
}
