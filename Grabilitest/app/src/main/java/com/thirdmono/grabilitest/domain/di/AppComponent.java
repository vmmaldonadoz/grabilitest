package com.thirdmono.grabilitest.domain.di;

import com.thirdmono.grabilitest.presentation.details.view.DetailsActivity;
import com.thirdmono.grabilitest.presentation.details.view.DetailsFragment;
import com.thirdmono.grabilitest.presentation.list.view.AppListActivity;
import com.thirdmono.grabilitest.presentation.splash.view.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(AppListActivity target);

    void inject(SplashActivity target);

    void inject(DetailsFragment target);

    void inject(DetailsActivity target);
}
