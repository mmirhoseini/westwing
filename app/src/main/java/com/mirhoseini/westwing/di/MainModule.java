package com.mirhoseini.westwing.di;

import com.mirhoseini.westwing.presentation.MainPresenter;
import com.mirhoseini.westwing.presentation.MainPresenterImpl;
import com.mirhoseini.westwing.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 29/06/16.
 */
@Module
public class MainModule {
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainView provideView() {
        return view;
    }

    @Provides
    public MainPresenter providePresenter(MainPresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }

}
