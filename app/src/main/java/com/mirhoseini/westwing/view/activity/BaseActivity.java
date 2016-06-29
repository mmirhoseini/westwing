package com.mirhoseini.westwing.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mirhoseini.westwing.WestwingApplication;
import com.mirhoseini.westwing.di.ApplicationComponent;

/**
 * Created by Mohsen on 29/06/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies(WestwingApplication.getComponent());

        // can be used for general purpose in all Activities of Application

    }

    protected abstract void injectDependencies(ApplicationComponent component);

}
