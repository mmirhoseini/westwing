package com.mirhoseini.westwing;

import timber.log.Timber;

/**
 * Created by Mohsen on 29/06/16.
 */
public class WestwingApplicationImpl extends WestwingApplication {

    @Override
    void initApplication() {
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(StackTraceElement element) {
                //adding line number to logs
                return super.createStackElementTag(element) + ":" + element.getLineNumber();
            }
        });
    }
}
