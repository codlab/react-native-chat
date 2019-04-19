package eu.codlab.chat.database.controllers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Where;
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

    public long deleteLink(@NonNull Conversation conversation, @NonNull User user) {
        return from(delete(), conversation, user).executeUpdateDelete();
    }

    public void createLink(@NonNull Conversation conversation, @NonNull User user) {
        List<ConversationUser> links = from(select(), conversation, user).queryList();

        if(links.size() == 0) {
            ConversationUser conversationUser = new ConversationUser();
            conversationUser.setConversationId(conversation.getId());
            conversationUser.setUserId(user.getUuid());

            conversationUser.save();
        }
    }

    private From<ConversationUser> select() {
        return new Select().from(ConversationUser.class);
    }
    private From<ConversationUser> delete() {
        return new Select().from(ConversationUser.class);
    }

    private Where<ConversationUser> from(From<ConversationUser> query, @NonNull Conversation conversation, @NonNull User user) {
        return query
                .where(ConversationUser_Table.conversationId.eq(conversation.getId()))
                .and(ConversationUser_Table.userId.eq(user.getUuid()));
    }
}
