package eu.codlab.chat.ui.items;

import android.util.Base64;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import eu.codlab.chat.R;
import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.translation.TranslationController;

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
        String message_content = message.getContent();

        if (null != message.getTranslation_key()) {
            String translation_content = TranslationController.instance.get(message.getTranslation_key());
            if (null != translation_content && translation_content.length() > 0)
                message_content = translation_content;
        }

        content.setText(message_content);

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