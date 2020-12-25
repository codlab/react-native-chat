package eu.codlab.chat.database;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

import eu.codlab.chat.database.models.ChatMessage;

/**
 * Simple database describing the preferences
 */

@Database(name = DatabaseChat.NAME, version = DatabaseChat.VERSION)
public class DatabaseChat {

    public final static String NAME = "DatabaseChat";

    public final static int VERSION = 4;

    @Migration(version = 3, database = DatabaseChat.class)
    public static class Migration2 extends AlterTableMigration<ChatMessage> {

        public Migration2(Class<ChatMessage> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "local");
        }
    }

    @Migration(version = 4, database = DatabaseChat.class)
    public static class Migration3 extends AlterTableMigration<ChatMessage> {

        public Migration3(Class<ChatMessage> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "yyyymmdd");
        }
    }

}
