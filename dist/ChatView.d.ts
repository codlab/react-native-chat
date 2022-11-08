import { Component } from 'react';
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
interface HolderDeletable {
    [key: number]: Holder | undefined;
}
/**
 * Composes `View`.
 */
export default class ChatView extends Component<ChatViewProps, ChatViewState> {
    static defaultProps: {};
    _UiManager: any;
    _view: any | null;
    _handler: null | number;
    _nextRequestId: number;
    _requestMap: HolderDeletable;
    constructor(props: ChatViewProps);
    componentDidMount(): void;
    _sendCallReturn(command: any): Promise<unknown>;
    _onCallReturn: (event: any) => void;
    render(): JSX.Element;
}
export {};
