package com.mirhoseini.westwing.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.mirhoseini.westwing.WestwingApplication;
import com.mirhoseini.westwing.di.ApplicationComponent;

/**
 * Created by Mohsen on 29/06/16.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        injectDependencies(WestwingApplication.getComponent());

        // can be used for general purpose in all Fragments of Application
    }

    protected abstract void injectDependencies(ApplicationComponent component);

}
