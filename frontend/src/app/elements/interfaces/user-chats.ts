import { Message } from "./message";

export interface UserChats
{
    username: string;
    lastMessages: Message[];
}
