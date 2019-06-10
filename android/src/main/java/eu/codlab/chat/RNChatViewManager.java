package eu.codlab.chat;

import android.widget.VideoView;

import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import eu.codlab.chat.ui.views.ChatView;

public class RNChatViewManager extends SimpleViewManager<ChatView> {
    @Override
    public String getName() {
        return "RNChatView";
    }

    @Override
    protected ChatView createViewInstance(ThemedReactContext reactContext) {
        return new ChatView(reactContext);
    }

    @ReactProp(name="conversationUUID")
    public void setConversation(ChatView chatView, String conversationUUID) {
        chatView.setConversation(conversationUUID);
    }
}
