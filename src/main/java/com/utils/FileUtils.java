package com.utils;

import java.io.File;

public class FileUtils {
    public static void create_dir(String dir_name) {
        File f = new File(dir_name);
        if (!f.exists()) {
            f.mkdirs();
        }
    }
}
