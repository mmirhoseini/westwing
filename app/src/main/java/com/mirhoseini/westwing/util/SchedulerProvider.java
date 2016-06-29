package com.mirhoseini.westwing.util;

import rx.Scheduler;

/**
 * Created by Mohsen on 29/06/16.
 */
public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();

}
