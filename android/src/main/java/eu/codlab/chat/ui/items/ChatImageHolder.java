package eu.codlab.chat.ui.items;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import javax.annotation.Nullable;

import eu.codlab.chat.R;
import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.utils.FileUtil;

public class ChatImageHolder extends AbstractMessageHolder {

    @Nullable
    ImageView photo;

    public ChatImageHolder(@LayoutRes int res, ViewGroup parent) {
        super(res, parent);

        photo = itemView.findViewById(R.id.photo);
    }

    @Override
    public void onBindViewHolder(final ChatMessage message, int position) {
        String image = message.getImage();
        String content = message.getContent();

        if (null != image && image.length() > 0) {
            photo.setVisibility(View.VISIBLE);
            RequestCreator requestCreator = null;
            if (image.startsWith("http")) {
                requestCreator = Picasso.with(itemView.getContext())
                        .load(image);
            } else {
                requestCreator = Picasso.with(itemView.getContext())
                        .load(FileUtil.reactNativePath(image));
            }

            requestCreator
                    .resize(720, 600)
                    .onlyScaleDown()
                    .centerCrop()
                    .into(photo);
        } else if (null != content && content.length() > 0) {
            photo.setVisibility(View.VISIBLE);
            Picasso.with(itemView.getContext())
                    .load(FileUtil.reactNativePath(image))
                    .resize(720, 600)
                    .onlyScaleDown()
                    .centerCrop()
                    .into(photo);
        } else {
            photo.setVisibility(View.GONE);
        }
    }
}