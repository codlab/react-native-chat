package eu.codlab.chat.database.models;

import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

import eu.codlab.chat.database.DatabaseChat;
import eu.codlab.chat.database.controllers.ModelControllerFactory;
import eu.codlab.chat.database.controllers.UserController;

@Table(name = "ChatMessage", database = DatabaseChat.class)
public class ChatMessage extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private int type;

    @Column(defaultValue = "0")
    private String content;

    @Column
    private Date createdAt;

    @Column
    private Date sentAt;

    @Column
    private long conversationId;

    //TODO since senders are not known for now, simply set the information...
    @Column(defaultValue = "")
    private String uuid;

    public ChatMessage() {
    }

    public ChatMessage(Conversation conversation, ChatMessageType type, String content, User sender) {
        this();

        if (null != conversation) this.conversationId = conversation.getId();
        this.type = type.ordinal();
        this.content = content;
        this.uuid = sender.getUuid();
    }

    public String getUuid() {
        return uuid;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSender(User user) {
        this.uuid = user.getUuid();
    }

    public int getType() {
        return type;
    }

    public ChatMessageType getMessageType() {
        return ChatMessageType.fromType(type);
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    @Nullable
    public User getSender() {
        //TODO save current user temporary ?
        return ModelControllerFactory.get(UserController.class).getItemFrom(uuid);
    }

    public long getId() {
        return id;
    }
}
