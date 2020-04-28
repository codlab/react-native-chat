import { NativeModules } from 'react-native';
import { User } from '.';
import Conversation from './Conversation';
import { Message } from './Message';
import ConversationManagerDummy from './ConversationManagerDummy';
const { RNChat } = NativeModules;

const need_dummy = !RNChat || !RNChat.getUsers;

export default class ConversationManager {
    constructor() {

    }

    getUsers(): Promise<User[]> {
        if(need_dummy) return ConversationManagerDummy.instance.getUsers();
        return RNChat.getUsers();
    }

    createUser(user: User): Promise<User> {
        if(need_dummy) return ConversationManagerDummy.instance.createUser(user);
        return RNChat.createUser(user);
    }

    getConversations(): Promise<Conversation[]> {
        if(need_dummy) return ConversationManagerDummy.instance.getConversations();
        return RNChat.getConversations();
    }

    createConversation(uuid: string, name: string): Promise<Conversation> {
        if(need_dummy) return ConversationManagerDummy.instance.createConversation(uuid, name);
        return RNChat.createConversation(uuid, name);
    }

    addUserToConversation(user: User, conversation: Conversation): Promise<boolean> {
        if(need_dummy) return ConversationManagerDummy.instance.addUserToConversation(user, conversation);
        return RNChat.addUserToConversation(user, conversation);
    }

    saveMessage(user: User, conversation: Conversation, message: Message) {
        if(need_dummy) return ConversationManagerDummy.instance.saveMessage(user, conversation, message);
        return RNChat.saveMessage(user, conversation, message);
    }

    requery(): Promise<boolean> {
        if(need_dummy) return ConversationManagerDummy.instance.requery();
        return RNChat.requery();
    }

}