package eu.codlab.chat.ui.views;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.raizlabs.android.dbflow.structure.database.FlowCursor;

import eu.codlab.chat.R;
import eu.codlab.chat.database.controllers.ChatMessageController;
import eu.codlab.chat.database.controllers.ModelControllerFactory;
import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.database.models.ChatMessageType;
import eu.codlab.chat.database.models.User;
import eu.codlab.chat.ui.recycler.ChatRecyclerViewAdapter;

public class ChatView extends FrameLayout {

    private ChatMessageController controller;

    RecyclerView chat_list;

    private Handler handler = new Handler(Looper.getMainLooper());
    private ChatRecyclerViewAdapter adapter;
    private boolean mKeepScroll = true;

    public ChatView(@NonNull Context context) {
        super(context);

        init();
    }

    public ChatView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ChatView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ChatView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init() {
        controller = ModelControllerFactory.get(ChatMessageController.class);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_list, this, false);

        addView(view);

        chat_list = view.findViewById(R.id.chat_list);

        FlowCursor cursor = controller.fetchFlowCursorForConversation();
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        //manager.setReverseLayout(true);
        manager.setStackFromEnd(true);

        chat_list.setLayoutManager(manager);
        adapter = new ChatRecyclerViewAdapter(cursor);
        chat_list.setAdapter(adapter);

        chat_list.setOverScrollMode(View.OVER_SCROLL_NEVER);
        chat_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                mKeepScroll = !recyclerView.canScrollVertically(1);
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                ChatMessageType type = ChatMessageType.CHAT_MESSAGE_TYPE_RECEIVED;
                int value = (int) (System.currentTimeMillis() % 25);

                if(value < 5) {
                    type = ChatMessageType.CHAT_MESSAGE_TYPE_SENT;
                } else if(value < 10) {
                    type = ChatMessageType.CHAT_CALL_ENDED;
                } else if(value < 15) {
                    type = ChatMessageType.CHAT_CALL_MISSED;
                } else if(value < 20) {
                    type = ChatMessageType.CHAT_CALL_NO_ANSWER;
                }

                new ChatMessage(null, type, "test", new User()).save();
                requery();
                handler.postDelayed(this, 600);
            }
        });
    }

    private void requery() {
        adapter.requery();
        if (mKeepScroll) {
            chat_list.smoothScrollToPosition(adapter.getItemCount());
        }
    }
}