package com.octoberm.cloudtestdemo.test.testcases;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;

import com.octoberm.cloudtestdemo.test.handler.CustomFailureHandler;
import com.octoberm.cloudtestdemo.test.logger.Logger;

import org.junit.BeforeClass;


public class BaseTest {

    /**
     * Note that {@link BeforeClass} methods need to be public and static.
     */
    @BeforeClass
    public static void setFailureHandler() {
        Logger.log("Setting custom failure handler");
        Espresso.setFailureHandler(
                new CustomFailureHandler(InstrumentationRegistry.getInstrumentation().getTargetContext()));
    }
}
