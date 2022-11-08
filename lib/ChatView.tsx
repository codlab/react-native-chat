// https://pspdfkit.com/blog/2018/advanced-techniques-for-react-native-ui-components/
import React, {Component} from 'react';
import { requireNativeComponent, findNodeHandle, UIManager } from 'react-native';

const RNChatView = requireNativeComponent('RNChatView');

export interface ChatViewState {

}

export interface ChatViewProps {
  conversationUUID: string
}

export interface Resolve {
  (result: any): any|undefined;
}

export interface Reject {
  (error: Error): any|undefined;
}

export interface Holder {
  resolve: Resolve;
  reject: Reject;
}

interface HolderDeletable {
  [key: number]: Holder|undefined
}

/**
 * Composes `View`.
 */
export default class ChatView extends Component<ChatViewProps, ChatViewState> {
  static defaultProps = {
  }

  _UiManager:any = UIManager;

  _view: any|null;
  _handler: null | number;
  _nextRequestId = 1;
  _requestMap: HolderDeletable = {};

  constructor(props: ChatViewProps) {
    super(props);
    this._view = null;
    this._handler = null;
  }

  componentDidMount() {
    this._handler = findNodeHandle(this._view);
  }

  _sendCallReturn(command: any) {
    const requestId: number = this._nextRequestId++;
    const requestMap: HolderDeletable = this._requestMap;

    const promise = new Promise((resolve, reject) => {
      requestMap[requestId] = { resolve, reject };
    });

    this._UiManager.dispatchViewManagerCommand(
      this._handler,
      command,
      [ requestId ]
    );

    return promise;
  }

  _onCallReturn = (event: any) => {
    const { requestId, result, error } = event.nativeEvent;
    const promise: Holder|undefined = this._requestMap[requestId];

    this._requestMap[requestId] = undefined;
    delete this._requestMap[requestId];

    if(!promise) return;

    if (result) {
      promise.resolve(result);
    } else {
      promise.reject(error);
    }
  }

  render() {
    return (
      <RNChatView
        {...this.props}
        ref={(v: any) => this._view = v}
        { ...{onCallReturn: (event :any) => this._onCallReturn(event)} }
      />
    );
  }
}