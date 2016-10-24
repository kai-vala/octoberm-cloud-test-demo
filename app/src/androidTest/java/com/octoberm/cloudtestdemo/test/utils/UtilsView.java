package com.octoberm.cloudtestdemo.test.utils;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.octoberm.cloudtestdemo.test.actions.WaitAction;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

public class UtilsView {

    public static void waitOnView(int milliseconds) {
        onView(ViewMatchers.isRoot()).perform(WaitAction.waitFor(milliseconds));
    }

    public static Matcher<View> getDisplayMatcher(boolean completely) {
        if (completely) {
            return isCompletelyDisplayed();
        } else {
            return isDisplayed();
        }
    }

    /**
     * Requires that the resource is 'in the view hierarchy'.
     * @param resourceId Example, R.id.fab_button_clear
     */
    public static void clickResourceOnView(@IdRes int resourceId) {
        onView(withId(resourceId)).perform(click());
    }

    /**
     * Requires that the text is displayed.
     *
     * @param textValue The text visible on the screen
     */
    public static void clickTextOnView(String textValue) {
        onView(allOf(withText(textValue), isDisplayed())).perform(click());
    }

    public static void clickTextOnView(@StringRes int resourceId) {
        onView(allOf(withText(resourceId), isDisplayed())).perform(click());
    }

    public static void matchTextOnView(String textValue) {
        onView(withText(textValue)).check(matches(isDisplayed()));
    }

    public static void matchTextOnView(@StringRes int resourceId) {
        onView(withText(resourceId)).check(matches(isDisplayed()));
    }

    public static void matchTextNotOnView(@IdRes int resourceId, String textValue) {
        onView(allOf(withText(textValue), withId(resourceId))).check(doesNotExist());
    }

    public static void matchTextOnView(@IdRes int resourceId, String textValue) {
        onView(allOf(withText(textValue), withId(resourceId))).check(matches(isDisplayed()));
    }

    public static void matchTextOnView(@IdRes int resourceId, @StringRes int textResourceId) {
        onView(allOf(withText(textResourceId), withId(resourceId))).check(matches(isDisplayed()));
    }


    /**
     * Validates that the resource is displayed on the view.
     *
     * @param resourceId Example, R.id.
     * @param completely Determine if {@link ViewMatchers#isCompletelyDisplayed()} is used.
     */
    public static void matchResourceOnView(@IdRes int resourceId, boolean completely) {
        onView(withId(resourceId)).check(matches(getDisplayMatcher(completely)));
    }

    public static void matchResourceOnView(@IdRes int resourceId) {
        matchResourceOnView(resourceId, false);
    }

    public static void matchResourceNotOnView(@IdRes int resourceId, boolean completely) {
        onView(withId(resourceId)).check(matches(not(getDisplayMatcher(completely))));
    }

    /**
     * Validates that the resource text is displayed on the view.
     *
     * @param resourceId Example, R.id.
     * @param completely Determine if {@link ViewMatchers#isCompletelyDisplayed()} is used.
     */
    public static void matchResourceTextOnView(@StringRes int resourceId, boolean completely) {
        onView(withText(resourceId)).check(matches(getDisplayMatcher(completely)));
    }

}
