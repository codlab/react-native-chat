package eu.codlab.chat.ui.items;

import android.content.res.Resources;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import eu.codlab.chat.R;
import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.translation.TranslationController;

public class ChatMessageIoTHolder extends AbstractMessageHolder {


    @Nullable
    ImageView avatar;

    TextView content;

    TextView date;

    TextView additionnal;

    TextView state_1;

    View state_connectivity_1;

    TextView state_2;

    View state_connectivity_2;

    public ChatMessageIoTHolder(int res, ViewGroup parent) {
        super(res, parent);

        avatar = itemView.findViewById(R.id.message_avatar);
        content = itemView.findViewById(R.id.message_content);

        additionnal = itemView.findViewById(R.id.message_additionnal);
        state_1 = itemView.findViewById(R.id.state_1);
        state_2 = itemView.findViewById(R.id.state_2);
        state_connectivity_1 = itemView.findViewById(R.id.state_connectivity_1);
        state_connectivity_2 = itemView.findViewById(R.id.state_connectivity_2);
    }

    @Override
    public void onBindViewHolder(final ChatMessage message, int position) {
        String message_content = message.getContent();
        String message_additionnal = message.getAdditionnal();

        if (null != message.getTranslation_key()) {
            String translation_content = TranslationController.instance.get(message.getTranslation_key());
            if (null != translation_content && translation_content.length() > 0)
                message_content = translation_content;
        }

        if (null != message.getAdditionnal_translation_key()) {
            String translation_additionnal = TranslationController.instance.get(message.getAdditionnal_translation_key());
            if (null != translation_additionnal && translation_additionnal.length() > 0)
                message_additionnal = translation_additionnal;
        }

        content.setText(message_content);
        additionnal.setText(message_additionnal);

        state_1.setText(message.getState_1());
        state_2.setText(message.getState_2());

        Resources resources = itemView.getContext().getResources();

        state_connectivity_1.setBackgroundColor(resources.getColor(message.isState_connectivity_1() ? R.color.state_green : R.color.state_red));
        state_connectivity_2.setBackgroundColor(resources.getColor(message.isState_connectivity_2() ? R.color.state_green : R.color.state_red));

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