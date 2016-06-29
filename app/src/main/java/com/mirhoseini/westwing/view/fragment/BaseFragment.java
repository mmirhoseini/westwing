package com.mirhoseini.westwing.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mirhoseini.westwing.WestwingApplication;
import com.mirhoseini.westwing.di.ApplicationComponent;

/**
 * Created by Mohsen on 29/06/16.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies(WestwingApplication.getComponent());

        // can be used for general purpose in all Fragments of Application
    }

    protected abstract void injectDependencies(ApplicationComponent component);

}
