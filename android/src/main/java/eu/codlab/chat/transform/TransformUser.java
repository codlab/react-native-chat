package eu.codlab.chat.transform;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import eu.codlab.chat.database.models.User;

public final class TransformUser {
    private TransformUser() {

    }

    @NonNull
    public static WritableMap toMap(@NonNull User user) {
        WritableMap map = new WritableNativeMap();
        map.putString("uuid", user.getUuid());
        map.putString("avatar", user.getAvatar());
        map.putString("name", user.getName());
        map.putString("additionnal", user.getAdditionnal());

        return map;
    }

    @NonNull
    public static WritableArray toMap(@Nullable Iterable<User> users) {
        WritableNativeArray array = new WritableNativeArray();
        if (null != users) {
            for (User user : users) array.pushMap(toMap(user));
        }
        return array;
    }

    @NonNull
    public static User fromMap(@NonNull ReadableMap map) {
        User user = new User();
        user.setUuid(map.getString("uuid"));
        user.setName(map.getString("name"));
        user.setAvatar(map.getString("avatar"));
        user.setAdditionnal(map.getString("additionnal"));

        return user;
    }
}
