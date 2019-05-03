package eu.codlab.chat.ui.items;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.TextView;

import eu.codlab.chat.R;
import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.database.models.User;

public class ChatMessageCallInfoHolder extends AbstractMessageHolder {

    @Nullable
    private static String MISSED_CALL = null;

    @Nullable
    private static String ENDED_CALL = null;

    @Nullable
    private static String NO_ANSWER = null;

    TextView content;

    public ChatMessageCallInfoHolder(int res, ViewGroup parent) {
        super(res, parent);

        if (null == MISSED_CALL) MISSED_CALL = parent.getContext().getString(R.string.missed_call);
        if (null == ENDED_CALL) ENDED_CALL = parent.getContext().getString(R.string.ended_call);
        if (null == NO_ANSWER) NO_ANSWER = parent.getContext().getString(R.string.no_answer);

        content = itemView.findViewById(R.id.message_content);
    }

    @Override
    public void onBindViewHolder(final ChatMessage message, int position) {
        User user = message.getSenderUser();
        String str = "%s";

        switch (message.getMessageType()) {
            case CHAT_CALL_ENDED:
                str = ENDED_CALL;
                break;
            case CHAT_CALL_MISSED:
                str = MISSED_CALL;
                break;
            case CHAT_CALL_NO_ANSWER:
            default:
                str = NO_ANSWER;
                break;

        }

        if(null != user) {
            content.setText(String.format(str, user.getName()));
        } else {
            content.setText(String.format(str, "no user"));
        }
    }
}