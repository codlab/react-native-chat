package eu.codlab.chat.ui.items;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import eu.codlab.chat.database.models.ChatMessage;

public abstract class AbstractMessageHolder extends RecyclerView.ViewHolder {
    public AbstractMessageHolder(@LayoutRes int res, ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
    }

    public abstract void onBindViewHolder(final ChatMessage message, int position);
}
