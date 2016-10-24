package com.octoberm.cloudtestdemo.test.robots;

import android.support.annotation.Nullable;
import android.support.test.espresso.Espresso;

import com.octoberm.cloudtestdemo.test.utils.UtilsScreenshot;

import static com.octoberm.cloudtestdemo.test.utils.UtilsView.waitOnView;

public abstract class AbstractRobot<T extends AbstractRobot, SelfType extends AbstractRobot> {

    protected
    @Nullable
    final T previousRobot;

    protected AbstractRobot(@Nullable T previousRobot) {
        this.previousRobot = previousRobot;
    }

    /**
     * Waits for the specified amount of time by calling Espresso wait functions.
     *
     * @param milliseconds Time to waitFor() in milliseconds
     * @return Stays in the current screen.
     */
    public SelfType waitTime(int milliseconds) {
        waitOnView(milliseconds);
        return (SelfType) this;
    }

    public SelfType takeScreenshot(String fileName) {
        UtilsScreenshot.takeScreenshot(fileName);
        return (SelfType) this;
    }

    /**
     * @return The previous Robot that was used.
     */
    public
    @Nullable
    T goBack() {
        Espresso.pressBack();
        return previousRobot;
    }
}
