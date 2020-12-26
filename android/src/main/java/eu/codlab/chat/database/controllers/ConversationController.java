package eu.codlab.chat.database.controllers;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import eu.codlab.chat.database.models.Conversation;
import eu.codlab.chat.database.models.Conversation_Table;

public class ConversationController extends AbstractController<Conversation, Long> {
    private static final String TAG = ConversationController.class.getSimpleName();

    @Override
    protected Property<Long> getColumnId() {
        return Conversation_Table.id;
    }

    @Override
    protected Class<? extends BaseModel> getTableClass() {
        return Conversation.class;
    }

    @Override
    protected Long getId(Conversation object) {
        return object.getId();
    }

    @Override
    public Conversation createObject(@Nullable Long primary) {
        Conversation conversation = new Conversation();
        conversation.setId(primary);
        return conversation;
    }

    @Override
    public boolean equals(@Nullable Conversation left, @Nullable Conversation right) {
        if (null == left && null == right) return false;
        if (null == left || null == right) return false;
        return left.getId() == right.getId();
    }

    @NonNull
    public Conversation getOrCreate(@NonNull Conversation conversation) {
        List<Conversation> list = list();

        for (Conversation conv : list) {
            if (conv.getUuid().equalsIgnoreCase(conversation.getUuid())) {
                return conv;
            }
        }
        try {
            conversation.save();
            saveItem(conversation.getId(), conversation);
        } catch (Exception e) {
            Log.d("ChatView", "getOrCreate: exception " + e.getMessage());
            e.printStackTrace();
        }
        conversation.setUsers(conversation.getUsers());
        return conversation;
    }

    public Conversation create(String uuid, String name) {
        List<Conversation> list = new Select()
                .from(Conversation.class)
                .where(Conversation_Table.uuid.eq(uuid))
                .queryList();

        if (list.size() == 0) {
            Conversation conversation = new Conversation();
            conversation.setUuid(uuid);
            conversation.setName(name);
            conversation.save();
            saveItem(conversation.getId(), conversation);

            return conversation;
        }

        return list.get(0);
    }
}
