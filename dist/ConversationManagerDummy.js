import { NativeModules } from 'react-native';
var RNChat = NativeModules.RNChat;
var ConversationManagerDummy = /** @class */ (function () {
    function ConversationManagerDummy() {
    }
    ConversationManagerDummy.prototype.getUsers = function () {
        return RNChat.getUsers();
    };
    ConversationManagerDummy.prototype.createUser = function (user) {
        return Promise.resolve(user);
    };
    ConversationManagerDummy.prototype.getConversations = function () {
        return Promise.resolve([]);
    };
    ConversationManagerDummy.prototype.createConversation = function (uuid, name) {
        return Promise.resolve({ id: 0, uuid: uuid, name: name, users: [] });
    };
    ConversationManagerDummy.prototype.addUserToConversation = function (user, conversation) {
        return Promise.resolve(false);
    };
    ConversationManagerDummy.prototype.saveMessage = function (user, conversation, message) {
        return Promise.reject("can send message");
    };
    ConversationManagerDummy.prototype.requery = function () {
        return Promise.resolve(false);
    };
    ConversationManagerDummy.instance = new ConversationManagerDummy();
    return ConversationManagerDummy;
}());
export default ConversationManagerDummy;
//# sourceMappingURL=ConversationManagerDummy.js.map