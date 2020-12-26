package eu.codlab.chat.ui.items;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import eu.codlab.chat.database.models.ChatMessage;

public abstract class AbstractMessageHolder extends RecyclerView.ViewHolder {
    public AbstractMessageHolder(@LayoutRes int res, ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
    }

    public abstract void onBindViewHolder(final ChatMessage message, int position);
}
