package eu.codlab.chat.utils;

import android.support.annotation.NonNull;

import java.io.File;

public class FileUtil {
    @NonNull
    public static File reactNativePath(@NonNull String path) {
        if(path.startsWith("file://")) path = path.substring(7);
        return new File(path);
    }
}
