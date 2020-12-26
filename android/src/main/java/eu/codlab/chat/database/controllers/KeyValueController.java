package eu.codlab.chat.database.controllers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;

import eu.codlab.chat.database.models.KeyValueModel;
import eu.codlab.chat.database.models.KeyValueModel_Table;

public class KeyValueController extends AbstractController<KeyValueModel, String> {

    private static KeyValueController _instance = new KeyValueController();

    private KeyValueController() {

    }

    public static KeyValueController getInstance() {
        return _instance;
    }

    @Override
    protected Property<String> getColumnId() {
        return KeyValueModel_Table.key;
    }

    @Override
    protected Class<? extends BaseModel> getTableClass() {
        return KeyValueModel.class;
    }

    @Override
    protected String getId(KeyValueModel object) {
        return object.key;
    }

    @Override
    public KeyValueModel createObject(@Nullable String primary) {
        return new KeyValueModel(primary, "");
    }

    @Override
    public boolean equals(@Nullable KeyValueModel left, @Nullable KeyValueModel right) {
        if (null == left && null == right) return false;
        if (null == left || null == right) return false;
        return left.key.equals(right.key);
    }

    @NonNull
    public KeyValueModel getOrCreate(@NonNull String key, String default_value) {
        KeyValueModel instance = getItemFrom(key);
        if (null == instance) {
            instance = new KeyValueModel(key, default_value);
            saveItem(key, instance);
        }

        return instance;
    }
}
