package eu.codlab.chat.database.controllers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

import java.util.Date;

import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.database.models.ChatMessage_Table;
import eu.codlab.chat.database.models.Conversation;

public class ChatMessageController extends AbstractController<ChatMessage, Long> {
    @Override
    protected Property<Long> getColumnId() {
        return ChatMessage_Table.id;
    }

    @Override
    protected Class<? extends BaseModel> getTableClass() {
        return ChatMessage.class;
    }

    @Override
    protected Long getId(ChatMessage object) {
        return object.getId();
    }

    @Override
    public ChatMessage createObject(@Nullable Long primary) {
        return new ChatMessage();
    }

    @Override
    public boolean equals(@Nullable ChatMessage left, @Nullable ChatMessage right) {
        if (null == left && left == right) return false;
        if (null == left || null == right) return false;
        return left.getId() == right.getId();
    }

    @NonNull
    public FlowCursor fetchFlowCursorForConversation(@NonNull Conversation conversation) {
        Log.d("ChatView", "fetchFlowCursorForConversation: fetching for conversation " + conversation);
        return fetchFlowCursorForConversation(conversation.getId());
    }

    @NonNull
    public FlowCursor fetchFlowCursorForConversation(@NonNull long id) {
        Log.d("ChatView", "fetchFlowCursorForConversation: " + id);
        return new Select()
                .from(getTableClass())
                .where(ChatMessage_Table.conversationId.eq(id))
                .orderBy(ChatMessage_Table.id.asc())
                .query();
    }

    @NonNull
    public ChatMessage createFromCursor(@NonNull FlowCursor cursor) {
        ChatMessage message = new ChatMessage();
        message.setId(cursor.getLongOrDefault("id"));
        message.setContent(cursor.getStringOrDefault("content"));
        message.setType(cursor.getIntOrDefault("type"));
        message.setUuid(cursor.getStringOrDefault("uuid"));
        message.setState_1(cursor.getStringOrDefault("state_1"));
        message.setState_2(cursor.getStringOrDefault("state_2"));
        message.setState_connectivity_1(cursor.getBooleanOrDefault("state_connectivity_1", false));
        message.setState_connectivity_2(cursor.getBooleanOrDefault("state_connectivity_2", true));
        message.setImage(cursor.getStringOrDefault("image"));

        long sentAt = cursor.getLongOrDefault("sentAt");
        long createdAt = cursor.getLongOrDefault("createdAt");
        if (sentAt > 0) message.setSentAt(new Date(sentAt));
        if (createdAt > 0) message.setCreatedAt(new Date(createdAt));

        return message;
    }

    public int getTypeFromCursor(@NonNull FlowCursor cursor) {
        return cursor.getIntOrDefault("type");
    }
}
