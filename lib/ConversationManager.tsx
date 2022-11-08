import { NativeModules } from 'react-native';
import { User } from '.';
import Conversation from './Conversation';
import { Message } from './Message';
import ConversationManagerDummy from './ConversationManagerDummy';
const { RNChat } = NativeModules;

const need_dummy = !RNChat || !RNChat.getUsers;

export interface Holder {
    [key: string]: string
}

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

    setSent(uuid: string): Promise<boolean> {
        if(need_dummy) return ConversationManagerDummy.instance.setSent(uuid);
        return RNChat.setSent(uuid);
    }

    setTranslation(key: string, content: string): Promise<boolean> {
        if(need_dummy) return ConversationManagerDummy.instance.setTranslation(key, content);
        return RNChat.setTranslation(key, content);
    }

    setTranslations(keyValues: Holder): Promise<boolean> {
        if(need_dummy) return ConversationManagerDummy.instance.setTranslations(keyValues);
        return RNChat.setTranslations(keyValues);
    }

    saveMessage(user: User, conversation: Conversation, message: Message): Promise<boolean> {
        if(need_dummy) return ConversationManagerDummy.instance.saveMessage(user, conversation, message);
        return RNChat.saveMessage(user, conversation, message);
    }

    requery(): Promise<boolean> {
        if(need_dummy) return ConversationManagerDummy.instance.requery();
        return RNChat.requery();
    }

}