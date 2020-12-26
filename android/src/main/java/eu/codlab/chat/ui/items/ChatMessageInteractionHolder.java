package eu.codlab.chat.ui.items;

import android.util.Base64;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import eu.codlab.chat.R;
import eu.codlab.chat.database.models.ChatMessage;

public class ChatMessageInteractionHolder extends AbstractMessageHolder {


    @Nullable
    ImageView avatar;

    TextView content;

    TextView date;

    public ChatMessageInteractionHolder(int res, ViewGroup parent) {
        super(res, parent);

        avatar = itemView.findViewById(R.id.message_avatar);
        content = itemView.findViewById(R.id.message_content);
        date = itemView.findViewById(R.id.message_date);
    }

    @Override
    public void onBindViewHolder(final ChatMessage message, int position) {
        content.setText(message.getContent());

        date.setText(message.getCreatedAt().toGMTString());

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