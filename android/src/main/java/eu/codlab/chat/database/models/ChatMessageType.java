package eu.codlab.chat.database.models;

import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.Nullable;

public enum ChatMessageType {
    CHAT_MESSAGE_TYPE_SENT("text"),
    CHAT_MESSAGE_TYPE_RECEIVED("text"),
    CHAT_IMAGE_TYPE_SENT("image"),
    CHAT_IMAGE_TYPE_RECEIVED("image"),
    CHAT_VIDEO_TYPE_SENT("video"),
    CHAT_VIDEO_TYPE_RECEIVED("video"),
    CHAT_CALL_ENDED("call_ended"),
    CHAT_CALL_MISSED("call_missed"),
    CHAT_CALL_NO_ANSWER("call_no_answer"),
    CHAT_INTERACTION("chat_interation"),
    CHAT_IOT("chat_iot"),
    CHAT_DATE("chat_date");

    private final static String TAG = ChatMessageType.class.getSimpleName();
    private final static SparseArray<ChatMessageType> CACHE = new SparseArray<>();
    private final static ChatMessageType[] INFOS = new ChatMessageType[]{
            CHAT_MESSAGE_TYPE_SENT,
            CHAT_MESSAGE_TYPE_RECEIVED,
            CHAT_IMAGE_TYPE_SENT,
            CHAT_IMAGE_TYPE_RECEIVED,
            CHAT_VIDEO_TYPE_SENT,
            CHAT_VIDEO_TYPE_RECEIVED,
            CHAT_CALL_ENDED,
            CHAT_CALL_MISSED,
            CHAT_CALL_NO_ANSWER,
            CHAT_INTERACTION,
            CHAT_IOT,
            CHAT_DATE
    };

    public static void init() {
        for (ChatMessageType info : INFOS) {
            CACHE.put(info.ordinal(), info);
        }
    }


    private String type;

    ChatMessageType() {
    }

    ChatMessageType(String type) {
        this();
        this.type = type;
    }

    public static ChatMessageType fromType(int viewType) {
        ChatMessageType type = CACHE.get(viewType);
        if (null == type) {
            Log.d(TAG, "fromType: THE TYPE IS INVALID " + viewType);
            type = CHAT_MESSAGE_TYPE_SENT;
        }
        return type;
    }

    public static ChatMessageType fromType(@Nullable String type) {
        if (null != type) {
            for (ChatMessageType in_cache : INFOS) {
                if (in_cache.getType().equalsIgnoreCase(type)) {
                    return in_cache;
                }
            }
        }
        return CHAT_MESSAGE_TYPE_SENT;
    }

    public String getType() {
        return type;
    }
}
