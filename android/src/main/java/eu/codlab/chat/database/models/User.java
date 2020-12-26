package eu.codlab.chat.database.models;

import androidx.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import eu.codlab.chat.database.DatabaseChat;
import eu.codlab.chat.database.controllers.ConversationUserController;
import eu.codlab.chat.database.controllers.ModelControllerFactory;

@Table(name = "User", database = DatabaseChat.class)
public class User extends BaseModel {
    @NonNull
    @PrimaryKey
    private String uuid;

    @NonNull
    @Column
    private String name;

    @NonNull
    @Column
    private String avatar;

    @Nullable
    @Column
    private String additionnal;

    public User() {
    }

    public User(@NonNull String uuid, @NonNull String name, @NonNull String avatar, @NonNull String additionnal) {
        this();
        this.uuid = uuid;
        this.name = name;
        this.avatar = avatar;
        this.additionnal = additionnal;
    }

    public void setUuid(@NonNull String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setAdditionnal(@NonNull String additionnal) {
        this.additionnal = additionnal;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getAvatar() {
        return avatar;
    }

    @NonNull
    public String getAdditionnal() {
        return additionnal;
    }

    @NonNull
    public List<Conversation> getConversations() {
        List<Conversation> result = new ArrayList<>();
        List<ConversationUser> list = ModelControllerFactory.get(ConversationUserController.class)
                .getConversationForUser(this);

        for (ConversationUser holder : list) {
            result.add(holder.getConversation());
        }
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", additionnal='" + additionnal + '\'' +
                '}';
    }
}
