package eu.codlab.chat.database.models;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.List;

import eu.codlab.chat.database.DatabaseChat;
import eu.codlab.chat.database.controllers.ConversationUserController;
import eu.codlab.chat.database.controllers.ModelControllerFactory;

@Table(name = "Conversation", database = DatabaseChat.class)
public class Conversation extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private long id;

    public Conversation() {
    }

    public Conversation(List<User> users) {
        this();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        List<ConversationUser> users = ModelControllerFactory.get(ConversationUserController.class)
                .getUsersForConversation(this);

        for (ConversationUser user : users) {
            result.add(user.getUser());
        }
        return result;
    }
}
