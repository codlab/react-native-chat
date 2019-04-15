package eu.codlab.chat.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Simple database describing the preferences
 */

@Database(name = DatabaseChat.NAME, version = DatabaseChat.VERSION)
public class DatabaseChat {

    public final static String NAME = "DatabaseChat";

    public final static int VERSION = 1;
}
