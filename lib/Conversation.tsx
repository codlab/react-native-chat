import { User } from "..";

export default interface Conversation {
    id: number;
    uuid: string;
    name: string;
    users: User[];
}