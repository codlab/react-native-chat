
package eu.codlab.chat;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;

import eu.codlab.chat.database.controllers.ChatMessageController;
import eu.codlab.chat.database.controllers.ConversationController;
import eu.codlab.chat.database.controllers.ModelControllerFactory;
import eu.codlab.chat.database.controllers.UserController;
import eu.codlab.chat.database.models.ChatMessage;
import eu.codlab.chat.database.models.Conversation;
import eu.codlab.chat.database.models.User;
import eu.codlab.chat.transform.TransformConversations;
import eu.codlab.chat.transform.TransformMessage;
import eu.codlab.chat.transform.TransformUser;
import eu.codlab.chat.utils.FixCursorWindow;

public class RNChatModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    private ChatMessageController chatMessageController;
    private UserController userController;
    private ConversationController conversationController;

    public RNChatModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;


        userController = ModelControllerFactory.get(UserController.class);
        chatMessageController = ModelControllerFactory.get(ChatMessageController.class);
        conversationController = ModelControllerFactory.get(ConversationController.class);

        if (null == userController) {
            ModelControllerFactory.init(reactContext);
            userController = ModelControllerFactory.get(UserController.class);
            chatMessageController = ModelControllerFactory.get(ChatMessageController.class);
            conversationController = ModelControllerFactory.get(ConversationController.class);
        }

        FixCursorWindow.fix();
    }

    @Override
    public String getName() {
        return "RNChat";
    }


    @ReactMethod
    public void getUsers(Promise promise) {
        promise.resolve(TransformUser.toMap(userController.list()));
    }

    @ReactMethod
    public void createUser(ReadableMap map, Promise promise) {
        User user = TransformUser.fromMap(map);
        promise.resolve(TransformUser.toMap(userController.getOrCreate(user)));
    }

    @ReactMethod
    public void getConversations(Promise promise) {
        promise.resolve(TransformConversations.toMap(conversationController.list()));
    }

    @ReactMethod
    public void createConversation(String uuid, String name, Promise promise) {
        promise.resolve(TransformConversations.toMap(conversationController.create(uuid, name)));
    }

    @ReactMethod
    public void saveMessage(ReadableMap userJS, ReadableMap conversationJS, ReadableMap messageJS, Promise promise) {
        if(null != userJS && null != conversationJS && null != messageJS) {
            User user = userController.getOrCreate(TransformUser.fromMap(userJS));
            Conversation conversation = conversationController.getOrCreate(TransformConversations.fromMap(conversationJS));
            ChatMessage message = TransformMessage.fromMap(messageJS);

            message.setConversationId(conversation.getId());
            message.setSender(user);

            message.save();
            chatMessageController.saveItem(message.getId(), message);

            promise.resolve(TransformMessage.toMap(message));
        } else {
            promise.resolve(false);
        }
    }


}