package eu.codlab.chat.transform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import eu.codlab.chat.database.models.Conversation;

public final class TransformConversations {
    private TransformConversations() {

    }

    @NonNull
    public static WritableMap toMap(@NonNull Conversation conversation) {
        WritableMap map = new WritableNativeMap();
        map.putInt("id", (int) conversation.getId());
        map.putString("uuid", conversation.getUuid());
        map.putString("name", conversation.getName());
        map.putArray("users", TransformUser.toMap(conversation.getUsers()));

        return map;
    }

    @NonNull
    public static WritableArray toMap(@Nullable Iterable<Conversation> conversations) {
        WritableNativeArray array = new WritableNativeArray();
        if (null != conversations) {
            for (Conversation conversation : conversations) array.pushMap(toMap(conversation));
        }
        return array;
    }

    @NonNull
    public static Conversation fromMap(@NonNull ReadableMap map) {
        Conversation conversation = new Conversation();
        if (map.hasKey("uuid")) conversation.setUuid(map.getString("uuid"));
        if (map.hasKey("name")) conversation.setName(map.getString("name"));

        return conversation;
    }
}
