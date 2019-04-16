package eu.codlab.chat;

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
}
