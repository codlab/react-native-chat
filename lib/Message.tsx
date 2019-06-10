export interface Message {
    uuid: string;
    content: string;
    additionnal?: string;
    created_at?: number;
    sent_at?: number;
    local?: boolean;
}

export interface MessageImage extends Message {
    image: string;
}

export interface MessageIoT extends Message {
    image?: string;
    state_1: string;
    state_2: string;
    state_connectivity_1: boolean;
    state_connectivity_2: boolean;
}

export interface MessageSystem extends Message {
    image?: string;
    system: boolean;
    error: boolean;
}