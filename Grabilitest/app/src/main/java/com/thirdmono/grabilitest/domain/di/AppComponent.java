package com.thirdmono.grabilitest.domain.di;

import com.thirdmono.grabilitest.presentation.list.view.AppListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(AppListActivity target);
}
