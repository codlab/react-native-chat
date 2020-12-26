package eu.codlab.chat.database.models;

import androidx.annotation.Nullable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import eu.codlab.chat.database.DatabaseChat;

@Table(name = "KeyValueModel", database = DatabaseChat.class)
public class KeyValueModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    public long id;

    @Column
    public String key;

    @Column
    public String value;

    public KeyValueModel() {

    }

    public KeyValueModel(String key, String value) {
        this();

        this.key = key;
        this.value = value;
    }

    public void setValue(@Nullable String value) {
        this.value = value;
    }

    public void commit() {

        save();
    }

    @Override
    public String toString() {
        return "id/value := " + key + "/" + value;
    }

}
