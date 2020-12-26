import { NativeModules } from 'react-native';
import ConversationManagerDummy from './ConversationManagerDummy';
var RNChat = NativeModules.RNChat;
var need_dummy = !RNChat || !RNChat.getUsers;
var ConversationManager = /** @class */ (function () {
    function ConversationManager() {
    }
    ConversationManager.prototype.getUsers = function () {
        if (need_dummy)
            return ConversationManagerDummy.instance.getUsers();
        return RNChat.getUsers();
    };
    ConversationManager.prototype.createUser = function (user) {
        if (need_dummy)
            return ConversationManagerDummy.instance.createUser(user);
        return RNChat.createUser(user);
    };
    ConversationManager.prototype.getConversations = function () {
        if (need_dummy)
            return ConversationManagerDummy.instance.getConversations();
        return RNChat.getConversations();
    };
    ConversationManager.prototype.createConversation = function (uuid, name) {
        if (need_dummy)
            return ConversationManagerDummy.instance.createConversation(uuid, name);
        return RNChat.createConversation(uuid, name);
    };
    ConversationManager.prototype.addUserToConversation = function (user, conversation) {
        if (need_dummy)
            return ConversationManagerDummy.instance.addUserToConversation(user, conversation);
        return RNChat.addUserToConversation(user, conversation);
    };
    ConversationManager.prototype.setSent = function (uuid) {
        if (need_dummy)
            return ConversationManagerDummy.instance.setSent(uuid);
        return RNChat.setSent(uuid);
    };
    ConversationManager.prototype.setTranslation = function (key, content) {
        if (need_dummy)
            return ConversationManagerDummy.instance.setTranslation(key, content);
        return RNChat.setTranslation(key, content);
    };
    ConversationManager.prototype.saveMessage = function (user, conversation, message) {
        if (need_dummy)
            return ConversationManagerDummy.instance.saveMessage(user, conversation, message);
        return RNChat.saveMessage(user, conversation, message);
    };
    ConversationManager.prototype.requery = function () {
        if (need_dummy)
            return ConversationManagerDummy.instance.requery();
        return RNChat.requery();
    };
    return ConversationManager;
}());
export default ConversationManager;
//# sourceMappingURL=ConversationManager.js.map