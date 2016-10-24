package com.octoberm.cloudtestdemo.test.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.view.View;

import com.octoberm.cloudtestdemo.test.constants.TestConstants;
import com.octoberm.cloudtestdemo.test.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Original source:
 * http://testdroid.com/tech/tips-and-tricks-taking-screenshots-with-espresso-or-espresso-v2-0
 */
public class UtilsScreenshot {

    public static void failureScreenshot() {
        UtilsScreenshot.takeScreenshot(TestConstants.SCREENSHOT_FAILURE_FILENAME);
    }

    public static void takeScreenshot(String fileName) {
        fileName += "_" + Utils.generateTimeStamp();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Logger.log("Version higher than %s detected. Taking screenshot via shell command.",
                Build.VERSION_CODES.LOLLIPOP);
            takeScreenshotShell(fileName);
        } else {
            takeScreenshotActivity(fileName);
            Logger.log("Taking screenshot by drawing the activity.");
        }
    }

    private static void takeScreenshotActivity(String fileName) {
        try {
            Activity activity = UtilsActivity.getCurrentActivity();
            takeScreenshot(activity, fileName);
        } catch (Exception exception) {
            Logger.logException("Failed to capture screenshot", exception);
        }
    }

    private static void takeScreenshotShell(String fileName) {
        try {
            String path = createScreenshotDirectoryPath();
            UtilsFile.createDirectory(path);
            path = createScreenshotFilePath(path, fileName);
            Utils.executeShellCommand("screencap -p " + path);
            // Sleep in case the screenshot takes a while.
            Thread.sleep(2000);
        } catch (Exception exception) {
            Logger.logException("Failed to capture screenshot", exception);
        }
    }

    private static void takeScreenshot(Activity activity, String name) {
        // In TestDroid Cloud, taken screenshots are always stored
        // under /test-screenshots/ folder and this ensures those screenshots
        // be shown under Test Results
        String path = createScreenshotDirectoryPath();
        UtilsFile.createDirectory(path);
        path = createScreenshotFilePath(path, name);

        View scrView = activity.getWindow().getDecorView().getRootView();
        scrView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(scrView.getDrawingCache());
        scrView.setDrawingCacheEnabled(false);

        OutputStream out = null;
        File imageFile = new File(path);

        try {
            out = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
        } catch (Exception e) {
            Logger.logException("Failed to write screenshot", e);
        } finally {
            Utils.closeSafely(out);
        }
        if (imageFile.exists()) {
            Logger.log("Captured screenshot: " + path);
        } else {
            Logger.logError("Failed to capture screenshot: " + path);
        }
    }

    private static String createScreenshotDirectoryPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
            + TestConstants.SCREENSHOT_DIR;
    }

    private static String createScreenshotFilePath(String directory, String fileName) {
        String path = Utils.addSuffix(directory + fileName, TestConstants.SCREENSHOT_SUFFIX);
        Logger.log("Screenshot: " + path);
        return path;
    }
}
