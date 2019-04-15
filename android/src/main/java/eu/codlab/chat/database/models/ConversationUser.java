package eu.codlab.chat.database.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import eu.codlab.chat.database.DatabaseChat;
import eu.codlab.chat.database.controllers.ConversationController;
import eu.codlab.chat.database.controllers.ModelControllerFactory;
import eu.codlab.chat.database.controllers.UserController;

@Table(name = "ConversationUser", database = DatabaseChat.class)
public class ConversationUser extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String userId;

    @Column
    private long conversationId;

    public ConversationUser() {
    }

    public ConversationUser(String userId, long conversationId) {
        this();

        this.userId = userId;
        this.conversationId = conversationId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    public User getUser() {
        return ModelControllerFactory.get(UserController.class)
                .getItemFrom(getUserId());
    }

    public Conversation getConversation() {
        return ModelControllerFactory.get(ConversationController.class)
                .getItemFrom(getConversationId());
    }
}
