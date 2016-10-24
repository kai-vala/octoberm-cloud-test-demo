package com.octoberm.cloudtestdemo.test.utils;

import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;

import com.octoberm.cloudtestdemo.test.logger.Logger;

import java.io.Closeable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String getString(@StringRes int resourceId) {
        return InstrumentationRegistry.getTargetContext().getResources().getString(resourceId);
    }

    static String generateTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US).format(new Date());
    }

    static void executeShellCommand(String command) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ParcelFileDescriptor closeable = InstrumentationRegistry.getInstrumentation()
                        .getUiAutomation()
                        .executeShellCommand(command);
                closeSafely(closeable);
            }
        } catch (Exception exception) {
            Logger.logException("Failed to execute shell command", exception);
        }
    }

    static void closeSafely(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception exception) {
            Logger.logException("Exception while closing", exception);
        }
    }

    static String addSuffix(String line, String suffix) {
        if (!line.endsWith(suffix)) {
            line += suffix;
        }
        return line;
    }
}
