package eu.codlab.chat.ui.items;

import android.view.ViewGroup;
import android.widget.TextView;

import eu.codlab.chat.R;
import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.utils.DateUtils;

public class ChatMessageDateHolder extends AbstractMessageHolder {

    TextView content;

    public ChatMessageDateHolder(int res, ViewGroup parent) {
        super(res, parent);

        content = itemView.findViewById(R.id.message_content);
    }

    @Override
    public void onBindViewHolder(final ChatMessage message, int position) {

        int yyyymmdd = message.getYyyymmdd();
        if (yyyymmdd == 0) yyyymmdd = DateUtils.getYYYYMMDD(message.getCreatedAt());

        int yyyy = yyyymmdd / 10000;
        int mm = (yyyymmdd / 100) % 100;
        int dd = yyyymmdd % 100;

        String day = ""+dd;
        if(day.length() < 2) day = "0" + day;

        String month = ""+mm;
        if(month.length() < 2) month = "0" + month;

        String year = ""+yyyy;
        while(year.length() < 4) year = "0" + year;

        if (null != content) {
            content.setText(day + " " + month + " " + year);
        }
    }
}