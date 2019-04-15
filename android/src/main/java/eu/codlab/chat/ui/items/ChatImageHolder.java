package eu.codlab.chat.ui.items;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import eu.codlab.chat.database.models.ChatMessage;

public class ChatImageHolder extends AbstractMessageHolder {

    public ChatImageHolder(@LayoutRes int res, ViewGroup parent) {
        super(res, parent);
    }

    @Override
    public void onBindViewHolder(final ChatMessage message, int position) {

    }
}