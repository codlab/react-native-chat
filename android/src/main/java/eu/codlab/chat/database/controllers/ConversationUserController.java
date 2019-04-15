package eu.codlab.chat.database.controllers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import eu.codlab.chat.database.models.Conversation;
import eu.codlab.chat.database.models.ConversationUser;
import eu.codlab.chat.database.models.ConversationUser_Table;
import eu.codlab.chat.database.models.User;

public class ConversationUserController extends AbstractController<ConversationUser, Long> {
    @Override
    protected Property<Long> getColumnId() {
        return ConversationUser_Table.id;
    }

    @Override
    protected Class<? extends BaseModel> getTableClass() {
        return ConversationUser.class;
    }

    @Override
    protected Long getId(ConversationUser object) {
        return object.getId();
    }

    @Override
    public ConversationUser createObject(@Nullable Long primary) {
        ConversationUser item = new ConversationUser();

        return item;
    }

    public List<ConversationUser> getUsersForConversation(@NonNull Conversation conversation) {
        List<ConversationUser> users = new Select()
                .from(ConversationUser.class)
                .where(ConversationUser_Table.conversationId.eq(conversation.getId()))
                .queryList();

        return listFromDatabaseThroughCache(users);
    }

    public List<ConversationUser> getConversationForUser(@NonNull User user) {
        List<ConversationUser> conversations = new Select()
                .from(ConversationUser.class)
                .where(ConversationUser_Table.userId.eq(user.getUuid()))
                .queryList();

        return listFromDatabaseThroughCache(conversations);
    }

    @Override
    public boolean equals(@Nullable ConversationUser left, @Nullable ConversationUser right) {
        if (null == left && null == right) return false;
        if (null == left || null == right) return false;
        return left.getId() == right.getId();
    }
}
