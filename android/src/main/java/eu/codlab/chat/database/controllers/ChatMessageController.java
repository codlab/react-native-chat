package eu.codlab.chat.database.controllers;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;
import java.util.List;

import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.database.models.ChatMessageType;
import eu.codlab.chat.database.models.ChatMessage_Table;
import eu.codlab.chat.database.models.Conversation;
import eu.codlab.chat.utils.DateUtils;

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
                .orderBy(ChatMessage_Table.createdAt.asc())
                .query();
    }

    public void checkForDateHeader(@NonNull Conversation conversation, @NonNull long millis) {
        DateTime dateTime = new DateTime(millis, DateTimeZone.UTC);
        Date date = dateTime.toDate();
        checkForDateHeader(conversation, date);
    }

    public void checkForDateHeader(@NonNull Conversation conversation, @NonNull Date date) {
        int yyyymmdd = DateUtils.getYYYYMMDD(date);
        List messages = new Select()
                .from(getTableClass())
                .where(ChatMessage_Table.conversationId.eq(conversation.getId()))
                .and(ChatMessage_Table.yyyymmdd.eq(yyyymmdd))
                .limit(1)
                .queryList();

        Log.d("ChatMessageController", "checkForDateHeader: creating a message " + yyyymmdd);

        if (messages.size() == 0) {
            ChatMessage header = new ChatMessage();
            header.setType(ChatMessageType.CHAT_DATE.ordinal());
            DateTime dateTime = new DateTime(date);
            try {
                dateTime = dateTime.withSecondOfMinute(0).withMinuteOfHour(0).withHourOfDay(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            header.setCreatedAt(dateTime.toDate());
            header.setYyyymmdd(yyyymmdd);
            header.setConversationId(conversation.getId());
            header.save();
            saveItem(header.getId(), header);
        } else {
            Log.d("ChatMessageController", "checkForDateHeader: no need to create a message");
        }
    }

    public boolean exists(@NonNull String uuid) {
        return null != message(uuid);
    }

    @Nullable
    public ChatMessage message(@NonNull String uuid) {
        List<ChatMessage> messages = (List<ChatMessage>) new Select()
                .from(getTableClass())
                .where(ChatMessage_Table.uuid.eq(uuid))
                .queryList();

        ChatMessage chatMessage = messages.size() > 0 ? messages.get(0) : null;
        if (null == chatMessage) return null;

        ChatMessage cache = getItemFromCache(chatMessage.getId());
        if (null == cache) putItemInCache(chatMessage.getId(), chatMessage);

        return chatMessage;
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
        message.setTranslation_key(cursor.getStringOrDefault("translation_key"));

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
