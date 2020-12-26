package eu.codlab.chat.ui.recycler;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raizlabs.android.dbflow.structure.database.FlowCursor;

import eu.codlab.chat.database.controllers.ChatMessageController;
import eu.codlab.chat.database.controllers.ModelControllerFactory;
import eu.codlab.chat.database.models.ChatMessageType;
import eu.codlab.chat.ui.items.AbstractMessageHolder;
import eu.codlab.chat.ui.views.ChatMessageFactory;

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<AbstractMessageHolder> {

    private static final String TAG = ChatRecyclerViewAdapter.class.getSimpleName();
    private final ChatMessageController controller;
    private final FlowCursor cursor;

    public ChatRecyclerViewAdapter(@NonNull FlowCursor cursor) {
        //super("id", cursor);

        this.cursor = cursor;
        setHasStableIds(true);

        this.controller = ModelControllerFactory.get(ChatMessageController.class);
    }

    public void setCursor(@NonNull FlowCursor cursor) {
        requery();
    }

    @Override
    public int getItemViewType(int position) {
        moveToPosition(position);
        return controller.getTypeFromCursor(getCursor());
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    @NonNull
    @Override
    public AbstractMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatMessageType type = ChatMessageType.fromType(viewType);
        return ChatMessageFactory.createViewHolder(parent, type);
    }

    public FlowCursor getCursor() {
        return cursor;
    }

    @Override
    public void onBindViewHolder(@NonNull final AbstractMessageHolder holder, int position) {
        moveToPosition(position);
        holder.onBindViewHolder(controller.createFromCursor(cursor), cursor.getPosition());
    }

    @Override
    public long getItemId(int position) {
        moveToPosition(position);
        return cursor.getIntOrDefault("id");
    }

    public void requery() {
        int count = getItemCount();
        getCursor().requery();

        notifyDataSetChanged();
    }

    private void moveToPosition(int position) {
        cursor.moveToPosition(position);
    }
}
