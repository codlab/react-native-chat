package eu.codlab.chat.database.controllers;

import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;

import eu.codlab.chat.database.models.User;
import eu.codlab.chat.database.models.User_Table;

public class UserController extends AbstractController<User, String> {
    @Override
    protected Property<String> getColumnId() {
        return User_Table.uuid;
    }

    @Override
    protected Class<? extends BaseModel> getTableClass() {
        return User.class;
    }

    @Override
    protected String getId(User object) {
        return object.getUuid();
    }

    @Override
    public User createObject(@Nullable String primary) {
        return new User(primary, "", "", "");
    }

    @Override
    public boolean equals(@Nullable User left, @Nullable User right) {
        if (null == left && left == right) return false;
        if (null == left || null == right) return false;
        return left.getUuid().equals(right.getUuid());
    }
}
