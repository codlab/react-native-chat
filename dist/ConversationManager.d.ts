import { User } from '.';
import Conversation from './Conversation';
import { Message } from './Message';
export default class ConversationManager {
    constructor();
    getUsers(): Promise<User[]>;
    createUser(user: User): Promise<User>;
    getConversations(): Promise<Conversation[]>;
    createConversation(uuid: string, name: string): Promise<Conversation>;
    addUserToConversation(user: User, conversation: Conversation): Promise<boolean>;
    setSent(uuid: string): Promise<boolean>;
    setTranslation(key: string, content: string): Promise<boolean>;
    saveMessage(user: User, conversation: Conversation, message: Message): Promise<boolean>;
    requery(): Promise<boolean>;
}
