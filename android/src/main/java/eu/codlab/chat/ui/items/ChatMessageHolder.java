package eu.codlab.chat.ui.items;

import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import eu.codlab.chat.R;
import eu.codlab.chat.database.models.ChatMessage;

public class ChatMessageHolder extends AbstractMessageHolder {


    @Nullable
    ImageView avatar;

    TextView content;

    public ChatMessageHolder(int res, ViewGroup parent) {
        super(res, parent);

        avatar = itemView.findViewById(R.id.message_avatar);
        content = itemView.findViewById(R.id.message_content);
    }

    @Override
    public void onBindViewHolder(final ChatMessage message, int position) {
        content.setText(message.getContent());

        if (null != avatar) {
            String hash = Base64.encodeToString(("" + message.getId()).getBytes(),
                    Base64.NO_WRAP | Base64.URL_SAFE);
            Picasso.with(itemView.getContext())
                    .load("https://www.gravatar.com/avatar/" + hash + "?s=32&r=PG")
                    .resize(100, 100)
                    .into(avatar);
        }
    }
}