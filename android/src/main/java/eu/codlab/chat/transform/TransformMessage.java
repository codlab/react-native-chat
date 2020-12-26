package eu.codlab.chat.transform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.Date;

import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.database.models.ChatMessageType;

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
        map.putBoolean("error", message.isError());
        map.putBoolean("system", message.isSystem());
        map.putBoolean("local", message.isLocal());
        map.putString("type", message.getMessageType().getType());
        map.putString("additionnal", message.getAdditionnal());
        map.putString("translation_key", message.getTranslation_key());
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
        if (map.hasKey("uuid")) message.setUuid(map.getString("uuid"));
        if (map.hasKey("content")) message.setContent(map.getString("content"));
        if (map.hasKey("image")) message.setImage(map.getString("image"));
        if (map.hasKey("state_1")) message.setState_1(map.getString("state_1"));
        if (map.hasKey("state_2")) message.setState_2(map.getString("state_2"));
        if (map.hasKey("translation_key")) message.setTranslation_key(map.getString("translation_key"));
        if (map.hasKey("additionnal_translation_key")) message.setAdditionnal_translation_key(map.getString("additionnal_translation_key"));
        if (map.hasKey("state_connectivity_1"))
            message.setState_connectivity_1(map.getBoolean("state_connectivity_1"));
        if (map.hasKey("state_connectivity_2"))
            message.setState_connectivity_2(map.getBoolean("state_connectivity_2"));
        if (map.hasKey("local")) message.setLocal(map.getBoolean("local"));
        /*if (map.hasKey("type")) {
            message.setType(ChatMessageType.fromType(map.getString("type")).ordinal());
        } else {
            message.setType(calculateType(map).ordinal());
        }*/
        message.setType(calculateType(map).ordinal());
        if (map.hasKey("additionnal")) message.setAdditionnal(map.getString("additionnal"));
        if (map.hasKey("error")) message.setError(map.getBoolean("error"));
        if (map.hasKey("system")) message.setSystem(map.getBoolean("system"));
        if (map.hasKey("created_at") && !isNull(map, "created_at"))
            message.setCreatedAt(fromDouble(map.getDouble("created_at")));
        if (map.hasKey("sent_at") && !isNull(map, "sent_at"))
            message.setSentAt(fromDouble(map.getDouble("sent_at")));

        return message;
    }

    private static ChatMessageType calculateType(@NonNull ReadableMap map) {
        if (hasKeys(map, "system", "error") && (!hasKeys(map, "local") || !map.getBoolean("local"))) {
            return ChatMessageType.CHAT_INTERACTION;
        } else if (hasKeys(map, "state_1", "state_2", "state_connectivity_1", "state_connectivity_2")) {
            return ChatMessageType.CHAT_IOT;
        } else if (hasKeys(map, "image", "local") && map.getBoolean("local")) {
            return ChatMessageType.CHAT_IMAGE_TYPE_SENT;
        } else if (hasKeys(map, "image", "sent_at") || (hasKeys(map, "image", "local") && !map.getBoolean("local"))) { //not local but has a sent info
            return ChatMessageType.CHAT_IMAGE_TYPE_RECEIVED;
        } else if (hasKeys(map, "image", "local")) {
            return ChatMessageType.CHAT_MESSAGE_TYPE_SENT;
        } else if (hasKeys(map, "sent_at")) {
            return ChatMessageType.CHAT_MESSAGE_TYPE_RECEIVED;
        } else {
            //default
            return ChatMessageType.CHAT_MESSAGE_TYPE_SENT;
        }
    }

    private static Date fromDouble(double value) {
        return new Date((long) value);
    }

    private static boolean hasKeys(@NonNull ReadableMap map, String... keys) {
        for (String key : keys) {
            if (null == key || !map.hasKey(key)) return false;
        }
        return keys.length > 0;
    }

    private static boolean isNull(@NonNull ReadableMap map, @NonNull String key) {
        try {
            return map.isNull(key);
        } catch (Exception e) {

        }
        return false;
    }
}
