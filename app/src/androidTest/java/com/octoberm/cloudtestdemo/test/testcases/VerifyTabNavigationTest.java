package com.octoberm.cloudtestdemo.test.testcases;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.octoberm.cloudtestdemo.MainActivity;
import com.octoberm.cloudtestdemo.test.logger.Logger;
import com.octoberm.cloudtestdemo.test.robots.TabRobot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Verifies the tab navigation by opening each tab in a separate function as well as opening them all in a single function.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class VerifyTabNavigationTest extends BaseTest {

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void verifyTabNavigation() {
        Logger.log("Executing verifyTabNavigation");
        new TabRobot()
                .verifyTab1()
                .openTab2()
                .verifyTab2()
                .openTab3()
                .verifyTab3()
                .openTab1()
                .verifyTab1()
                .clickClearAction()
                .verifyNoText(1);
    }

    @Test
    public void verifyTab1() {
        Logger.log("Executing verifyTab1");
        new TabRobot()
                .openTab1()
                .verifyTab1()
                .clickClearAction()
                .verifyNoText(1);
    }

    @Test
    public void verifyTab2() {
        Logger.log("Executing verifyTab2");
        new TabRobot()
                .openTab2()
                .verifyTab2()
                .clickClearAction()
                .verifyNoText(2);
    }

    @Test
    public void verifyTab3() {
        Logger.log("Executing verifyTab3");
        new TabRobot()
                .openTab3()
                .verifyTab3()
                .clickClearAction()
                .verifyNoText(3);
    }

    @Test
    public void failOnPurpose() {
        Logger.log("Executing failOnPurpose");
        new TabRobot()
                .openTab3()
                .verifyTab3()
                .verifyNoText(3);
    }
}
