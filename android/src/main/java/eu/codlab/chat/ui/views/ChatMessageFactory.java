package eu.codlab.chat.ui.views;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import eu.codlab.chat.R;
import eu.codlab.chat.database.models.ChatMessageType;
import eu.codlab.chat.ui.items.AbstractMessageHolder;
import eu.codlab.chat.ui.items.ChatImageHolder;
import eu.codlab.chat.ui.items.ChatMessageCallInfoHolder;
import eu.codlab.chat.ui.items.ChatMessageHolder;
import eu.codlab.chat.ui.items.ChatMessageInteractionHolder;
import eu.codlab.chat.ui.items.ChatMessageIoTHolder;
import eu.codlab.chat.ui.items.ChatVideoHolder;

public class ChatMessageFactory {
    private ChatMessageFactory() {

    }

    public static int getResourceId(ChatMessageType type) {
        switch (type) {
            case CHAT_IOT:
                return R.layout.fragment_chat_message_iot;
            case CHAT_INTERACTION:
                return R.layout.fragment_chat_message_interaction;
            case CHAT_CALL_NO_ANSWER:
                return R.layout.fragment_chat_message_no_answer_call;
            case CHAT_CALL_MISSED:
                return R.layout.fragment_chat_message_missed_call;
            case CHAT_CALL_ENDED:
                return R.layout.fragment_chat_message_ended_call;
            case CHAT_IMAGE_TYPE_SENT:
            case CHAT_IMAGE_TYPE_RECEIVED:
            case CHAT_VIDEO_TYPE_SENT:
            case CHAT_VIDEO_TYPE_RECEIVED:
            case CHAT_MESSAGE_TYPE_SENT:
                return R.layout.fragment_chat_message_sent;
            case CHAT_MESSAGE_TYPE_RECEIVED:
            default:
                return R.layout.fragment_chat_message_received;
        }
    }

    public static AbstractMessageHolder createViewHolder(@NonNull ViewGroup parent, @NonNull ChatMessageType type) {
        int resource = getResourceId(type);

        switch (type) {
            case CHAT_IOT:
                return new ChatMessageIoTHolder(resource, parent);
            case CHAT_INTERACTION:
                return new ChatMessageInteractionHolder(resource, parent);
            case CHAT_CALL_ENDED:
            case CHAT_CALL_MISSED:
            case CHAT_CALL_NO_ANSWER:
                return new ChatMessageCallInfoHolder(resource, parent);
            case CHAT_IMAGE_TYPE_RECEIVED:
            case CHAT_IMAGE_TYPE_SENT:
                return new ChatImageHolder(resource, parent);
            case CHAT_VIDEO_TYPE_RECEIVED:
            case CHAT_VIDEO_TYPE_SENT:
                return new ChatVideoHolder(resource, parent);
            case CHAT_MESSAGE_TYPE_RECEIVED:
            case CHAT_MESSAGE_TYPE_SENT:
            default:
                return new ChatMessageHolder(resource, parent);
        }
    }
}
