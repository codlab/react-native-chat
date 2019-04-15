package eu.codlab.chat.database.controllers;

import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;

import eu.codlab.chat.database.models.Conversation;
import eu.codlab.chat.database.models.Conversation_Table;

public class ConversationController extends AbstractController<Conversation, Long> {
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
}
