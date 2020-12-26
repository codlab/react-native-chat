package eu.codlab.chat.database.models;

import androidx.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.Column;
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

    @NonNull
    @Column
    private String uuid;

    @Column
    private String name;

    private List<User> users = null;

    public Conversation() {
        uuid = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public void setUuid(@NonNull String uuid) {
        this.uuid = uuid;
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
            User tmp = user.getUser();
            result.add(tmp);
        }

        if (null == this.users) this.users = result;

        return result;
    }

    public void setUsers(@NonNull List<User> users) {
        List<User> still_in = new ArrayList<>();
        List<User> to_remove = new ArrayList<>();

        if (null != users) {
            for (User user : this.users) {
                if (!users.contains(user)) to_remove.add(user);
            }
        }

        for (User user : to_remove) {
            ModelControllerFactory.get(ConversationUserController.class)
                    .deleteLink(this, user);
        }

        this.users = users;
    }

    public boolean hasUser(@NonNull User user) {
        return getUsers().contains(user);
    }

    public boolean addUser(@NonNull User user) {
        if (!hasUser(user)) {
            ModelControllerFactory.get(ConversationUserController.class)
                    .createLink(this, user);
            users.add(user);

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
