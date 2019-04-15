package eu.codlab.chat.utils;

import android.database.CursorWindow;

import java.lang.reflect.Field;

public class FixCursorWindow {
    private final static String FIELD_NAME = "sCursorWindowSize";
    private final static int WINDOW_CURSOR_MB = 102400 * 1024;

    public static void fix() {
        try {
            Field field = CursorWindow.class.getDeclaredField(FIELD_NAME);
            field.setAccessible(true);
            field.set(null, WINDOW_CURSOR_MB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
