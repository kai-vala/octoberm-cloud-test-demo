package com.octoberm.cloudtestdemo.test.logger;

import android.util.Log;

import java.util.ArrayList;

public class Logger {

    private static final String LOG_DEBUG = "ANDROID_TEST_DEBUG";
    private static final String LOG_ERROR = "ANDROID_TEST_ERROR";
    private static final String LOG_EXCEPTION = "ANDROID_TEST_EXCEPTION";


    public static void log(String message) {
        Log.d(LOG_DEBUG, message);
    }

    public static void log(String message, Object... params) {
        logWithFormat(LOG_DEBUG, message, convertParams(params));
    }

    private static void logTag(String tag, String message) {
        Log.d(tag, message);
    }

    public static void logError(String message) {
        Log.d(LOG_ERROR, message);
    }

    public static void logException(String message, Exception exception) {
        Log.d(LOG_EXCEPTION, message + ":\n" + Log.getStackTraceString(exception));
    }

    public static void logException(String message, Exception exception, Object... params) {
        logWithFormat(LOG_EXCEPTION, message + ":\n" + Log.getStackTraceString(exception),
            convertParams(params));
    }

    public static void logException(String message, String exception) {
        Log.d(LOG_EXCEPTION, message + ":\n" + exception);
    }

    private static void logWithFormat(String logTag, String message, String... params) {
        // Cast to suppress warning
        Log.d(logTag, String.format(message, (Object[]) params));
    }

    private static void logError(String message, String... params) {
        logWithFormat(LOG_ERROR, message, params);
    }

    /**
     * Depending on the object instance, a conversion will take place with default calling
     * toString() and null objects inserting 'null'.
     *
     * @param params Objects to convert to String
     * @return String array
     */
    private static String[] convertParams(Object... params) {
        ArrayList<String> list = new ArrayList<>();
        for (Object object : params) {
            if (object != null) {
                if (object instanceof String) {
                    list.add((String) object);
                } else if (object instanceof Integer) {
                    list.add(Integer.toString((Integer) object));
                } else if (object instanceof Double) {
                    list.add(Double.toString((Double) object));
                } else if (object instanceof Long) {
                    list.add(Long.toString((Long) object));
                } else {
                    list.add(object.toString());
                }
            } else {
                list.add("null");
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
