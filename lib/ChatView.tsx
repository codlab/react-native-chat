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

/**
 * Composes `View`.
 */
export default class ChatView extends Component<ChatViewProps, ChatViewState> {
  static defaultProps = {
  }

  _UiManager:any = UIManager;

  _view: React.Component|null;
  _handler: null | number;
  _nextRequestId = 1;
  _requestMap: Map<number, Holder> = new Map();

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
    const requestMap: Map<number, Holder> = this._requestMap;

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
    const promise: Holder = this._requestMap[requestId];
    if (result) {
      promise.resolve(result);
    } else {
      promise.reject(error);
    }
    this._requestMap.delete(requestId);
  }

  render() {
    return (
      <RNChatView
        {...this.props}
        ref={(v: React.Component) => this._view = v}
        { ...{onCallReturn: (event :any) => this._onCallReturn(event)} }
      />
    );
  }
}