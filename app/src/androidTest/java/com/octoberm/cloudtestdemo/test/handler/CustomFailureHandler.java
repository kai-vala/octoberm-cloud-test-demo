package com.octoberm.cloudtestdemo.test.handler;

import android.content.Context;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.base.DefaultFailureHandler;
import android.view.View;

import com.octoberm.cloudtestdemo.test.utils.UtilsScreenshot;

import org.hamcrest.Matcher;

public class CustomFailureHandler implements FailureHandler {

    private final FailureHandler delegate;

    public CustomFailureHandler(Context targetContext) {
        delegate = new DefaultFailureHandler(targetContext);
    }

    @Override
    public void handle(Throwable error, Matcher<View> viewMatcher) {
        try {
            handleFailure();
            delegate.handle(error, viewMatcher);
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

    public static void handleFailure() {
        UtilsScreenshot.failureScreenshot();
    }
}