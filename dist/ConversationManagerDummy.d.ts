import { User } from '.';
import Conversation from './Conversation';
import { Holder } from './ConversationManager';
import { Message } from './Message';
export default class ConversationManagerDummy {
    static instance: ConversationManagerDummy;
    private constructor();
    getUsers(): Promise<User[]>;
    createUser(user: User): Promise<User>;
    getConversations(): Promise<Conversation[]>;
    createConversation(uuid: string, name: string): Promise<Conversation>;
    addUserToConversation(user: User, conversation: Conversation): Promise<boolean>;
    saveMessage(user: User, conversation: Conversation, message: Message): Promise<never>;
    setSent(uuid: string): Promise<never>;
    setTranslation(key: string, content: string): Promise<boolean>;
    setTranslations(holder: Holder): Promise<boolean>;
    requery(): Promise<boolean>;
}
