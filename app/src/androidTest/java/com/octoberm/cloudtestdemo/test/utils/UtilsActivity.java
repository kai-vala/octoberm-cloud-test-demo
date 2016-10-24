package com.octoberm.cloudtestdemo.test.utils;

import android.app.Activity;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.octoberm.cloudtestdemo.test.logger.Logger;

import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.runner.lifecycle.Stage.RESUMED;

public class UtilsActivity {

    public static Activity getCurrentActivity() throws Exception {

        Activity activity = getActivityInstance();

        if (activity != null) {
            Logger.log("Current activity is: " + activity.getClass().getName());
        } else {
            throw new Exception("Cannot return activity. The current activity was null.");
        }

        return activity;
    }

    private static Activity getActivityInstance() {
        // http://qathread.blogspot.se/2014/09/discovering-espresso-for-android-how-to.html
        // http://stackoverflow.com/questions/32705993/cant-get-the-activity-under-test-in-an
        // -android-espresso-test
        RunnableGetActivity runnable = new RunnableGetActivity();
        getInstrumentation().runOnMainSync(runnable);
        return runnable.getCurrentActivity();
    }

    public static class RunnableGetActivity implements Runnable {
        Activity currentActivity;

        public void run() {
            Collection resumedActivities =
                ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
            if (resumedActivities.iterator().hasNext()) {
                currentActivity = (Activity) resumedActivities.iterator().next();
            }
        }

        public Activity getCurrentActivity() {
            return currentActivity;
        }
    }
}
