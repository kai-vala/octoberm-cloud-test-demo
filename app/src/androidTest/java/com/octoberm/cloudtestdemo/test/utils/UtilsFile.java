package com.octoberm.cloudtestdemo.test.utils;

import java.io.File;

public class UtilsFile {

    public static void createDirectory(String path) throws IllegalStateException {
        File directory = new File(path);
        if (!directory.isDirectory()) {
            if (!directory.mkdirs()) {
                throw new IllegalStateException("Could not create directory");
            }
        }
    }
}
