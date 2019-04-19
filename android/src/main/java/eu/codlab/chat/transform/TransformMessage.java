package eu.codlab.chat.transform;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.Date;

import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.database.models.ChatMessageType;
import eu.codlab.chat.database.models.User;

public final class TransformMessage {
    private TransformMessage() {

    }

    @NonNull
    public static WritableMap toMap(@NonNull ChatMessage message) {
        WritableMap map = new WritableNativeMap();
        map.putString("uuid", message.getUuid());
        map.putString("content", message.getContent());
        map.putString("image", message.getImage());
        map.putString("state_1", message.getState_1());
        map.putString("state_2", message.getState_2());
        map.putBoolean("state_connectivity_1", message.isState_connectivity_1());
        map.putBoolean("state_connectivity_2", message.isState_connectivity_2());
        map.putString("type", message.getMessageType().getType());
        map.putString("additionnal", message.getAdditionnal());
        if (null != message.getSentAt()) map.putDouble("sent_at", message.getSentAt().getTime());
        if (null != message.getCreatedAt())
            map.putDouble("created_at", message.getCreatedAt().getTime());

        return map;
    }

    @NonNull
    public static WritableArray toMap(@Nullable Iterable<ChatMessage> messages) {
        WritableNativeArray array = new WritableNativeArray();
        if (null != messages) {
            for (ChatMessage message : messages) array.pushMap(toMap(message));
        }
        return array;
    }

    @NonNull
    public static ChatMessage fromMap(@NonNull ReadableMap map) {
        ChatMessage message = new ChatMessage();
        message.setUuid(map.getString("uuid"));
        message.setContent(map.getString("content"));
        message.setImage(map.getString("image"));
        message.setState_1(map.getString("state_1"));
        message.setState_2(map.getString("state_2"));
        message.setState_connectivity_1(map.getBoolean("state_connectivity_1"));
        message.setState_connectivity_2(map.getBoolean("state_connectivity_2"));
        message.setType(ChatMessageType.fromType(map.getString("type")).ordinal());
        message.setAdditionnal(map.getString("additionnal"));
        if (null != map.getString("created_at"))
            message.setCreatedAt(fromDouble(map.getDouble("created_at")));
        if (null != map.getString("sent_at"))
            message.setSentAt(fromDouble(map.getDouble("sent_at")));

        return message;
    }

    private static Date fromDouble(double value) {
        return new Date((long) value);
    }
}
