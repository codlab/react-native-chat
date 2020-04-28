import { NativeModules } from 'react-native';
var RNChat = NativeModules.RNChat;
var ConversationManager = /** @class */ (function () {
    function ConversationManager() {
    }
    ConversationManager.prototype.getUsers = function () {
        return RNChat.getUsers();
    };
    ConversationManager.prototype.createUser = function (user) {
        return RNChat.createUser(user);
    };
    ConversationManager.prototype.getConversations = function () {
        return RNChat.getConversations();
    };
    ConversationManager.prototype.createConversation = function (uuid, name) {
        return RNChat.createConversation(uuid, name);
    };
    ConversationManager.prototype.addUserToConversation = function (user, conversation) {
        return RNChat.addUserToConversation(user, conversation);
    };
    ConversationManager.prototype.saveMessage = function (user, conversation, message) {
        return RNChat.saveMessage(user, conversation, message);
    };
    ConversationManager.prototype.requery = function () {
        return RNChat.requery();
    };
    return ConversationManager;
}());
export default ConversationManager;
//# sourceMappingURL=ConversationManager.js.map