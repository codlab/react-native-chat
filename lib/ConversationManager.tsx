import { NativeModules } from 'react-native';
import { User } from '.';
import Conversation from './Conversation';
import { Message } from './Message';
const { RNChat } = NativeModules;

export default class ConversationManager {
    constructor() {

    }

    getUsers(): Promise<User[]> {
        return RNChat.getUsers();
    }

    createUser(user: User): Promise<User> {
        return RNChat.createUser(user);
    }

    getConversations(): Promise<Conversation[]> {
        return RNChat.getConversations();
    }

    createConversation(uuid: string, name: string): Promise<Conversation> {
        return RNChat.createConversation(uuid, name);
    }

    addUserToConversation(user: User, conversation: Conversation): Promise<boolean> {
        return RNChat.addUserToConversation(user, conversation);
    }

    saveMessage(user: User, conversation: Conversation, message: Message) {
        return RNChat.saveMessage(user, conversation, message);
    }

    requery(): Promise<boolean> {
        return RNChat.requery();
    }

}