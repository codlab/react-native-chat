package eu.codlab.chat.ui.items;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import eu.codlab.chat.R;
import eu.codlab.chat.database.models.ChatMessage;

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
        date = itemView.findViewById(R.id.message_date);
    }

    @Override
    public void onBindViewHolder(final ChatMessage message, int position) {
        content.setText(message.getContent());

        date.setText(message.getCreatedAt().toGMTString());

        additionnal.setText(message.getAdditionnal());

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