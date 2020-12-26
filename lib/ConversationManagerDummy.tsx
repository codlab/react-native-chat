import { NativeModules } from 'react-native';
import { User } from '.';
import Conversation from './Conversation';
import { Message } from './Message';
const { RNChat } = NativeModules;

export default class ConversationManagerDummy {
    public static instance = new ConversationManagerDummy();

    private constructor() {

    }

    getUsers(): Promise<User[]> {
        return RNChat.getUsers();
    }

    createUser(user: User): Promise<User> {
        return Promise.resolve(user);
    }

    getConversations(): Promise<Conversation[]> {
        return Promise.resolve([]);
    }

    createConversation(uuid: string, name: string): Promise<Conversation> {
        return Promise.resolve({id: 0, uuid, name, users: []});
    }

    addUserToConversation(user: User, conversation: Conversation): Promise<boolean> {
        return Promise.resolve(false);
    }

    saveMessage(user: User, conversation: Conversation, message: Message) {
        return Promise.reject("can send message");
    }

    setSent(uuid: string) {
        return Promise.reject("can't set sent");
    }

    setTranslation(key: string, content: string): Promise<boolean> {
        return Promise.reject("can't set translation " + key+" "+content);
    }

    requery(): Promise<boolean> {
        return Promise.resolve(false);
    }

}