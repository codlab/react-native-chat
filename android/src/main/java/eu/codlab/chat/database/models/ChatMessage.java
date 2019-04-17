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

    @Nullable
    @Column
    private String additionnal;

    @Nullable
    @Column
    private String state_1;

    @Nullable
    @Column
    private String state_2;

    @Nullable
    @Column
    private boolean state_connectivity_1;

    @Nullable
    @Column
    private boolean state_connectivity_2;

    @Nullable
    @Column
    private String image;

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
        createdAt = new Date();
        sentAt = null;
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

    @Nullable
    public String getAdditionnal() {
        return additionnal;
    }

    public void setAdditionnal(@Nullable String additionnal) {
        this.additionnal = additionnal;
    }

    @Nullable
    public String getState_1() {
        return state_1;
    }

    public void setState_1(@Nullable String state_1) {
        this.state_1 = state_1;
    }

    @Nullable
    public String getState_2() {
        return state_2;
    }

    public void setState_2(@Nullable String state_2) {
        this.state_2 = state_2;
    }

    public boolean isState_connectivity_1() {
        return state_connectivity_1;
    }

    public void setState_connectivity_1(boolean state_connectivity_1) {
        this.state_connectivity_1 = state_connectivity_1;
    }

    public boolean isState_connectivity_2() {
        return state_connectivity_2;
    }

    public void setState_connectivity_2(boolean state_connectivity_2) {
        this.state_connectivity_2 = state_connectivity_2;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(@Nullable String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }
}
