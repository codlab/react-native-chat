import React, { Component } from 'react';
export interface ChatViewState {
}
export interface ChatViewProps {
    conversationUUID: string;
}
export interface Resolve {
    (result: any): any | undefined;
}
export interface Reject {
    (error: Error): any | undefined;
}
export interface Holder {
    resolve: Resolve;
    reject: Reject;
}
/**
 * Composes `View`.
 */
export default class ChatView extends Component<ChatViewProps, ChatViewState> {
    static defaultProps: {};
    _UiManager: any;
    _view: React.Component | null;
    _handler: null | number;
    _nextRequestId: number;
    _requestMap: Map<number, Holder>;
    constructor(props: ChatViewProps);
    componentDidMount(): void;
    _sendCallReturn(command: any): Promise<unknown>;
    _onCallReturn: (event: any) => void;
    render(): any;
}
