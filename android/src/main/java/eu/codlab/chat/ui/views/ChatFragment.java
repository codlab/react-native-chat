package eu.codlab.chat.ui.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.structure.database.FlowCursor;

import eu.codlab.chat.R;
import eu.codlab.chat.database.controllers.ChatMessageController;
import eu.codlab.chat.database.controllers.ModelControllerFactory;
import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.database.models.ChatMessageType;
import eu.codlab.chat.database.models.User;
import eu.codlab.chat.ui.recycler.ChatRecyclerViewAdapter;

public class ChatFragment extends Fragment {

    private final ChatMessageController controller;

    RecyclerView chat_list;

    private Handler handler = new Handler(Looper.getMainLooper());
    private ChatRecyclerViewAdapter adapter;
    private boolean mKeepScroll = true;

    public ChatFragment() {
        controller = ModelControllerFactory.get(ChatMessageController.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
