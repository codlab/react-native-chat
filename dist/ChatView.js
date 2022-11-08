var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
// https://pspdfkit.com/blog/2018/advanced-techniques-for-react-native-ui-components/
import React, { Component } from 'react';
import { requireNativeComponent, findNodeHandle, UIManager } from 'react-native';
var RNChatView = requireNativeComponent('RNChatView');
/**
 * Composes `View`.
 */
var ChatView = /** @class */ (function (_super) {
    __extends(ChatView, _super);
    function ChatView(props) {
        var _this = _super.call(this, props) || this;
        _this._UiManager = UIManager;
        _this._nextRequestId = 1;
        _this._requestMap = {};
        _this._onCallReturn = function (event) {
            var _a = event.nativeEvent, requestId = _a.requestId, result = _a.result, error = _a.error;
            var promise = _this._requestMap[requestId];
            _this._requestMap[requestId] = undefined;
            delete _this._requestMap[requestId];
            if (!promise)
                return;
            if (result) {
                promise.resolve(result);
            }
            else {
                promise.reject(error);
            }
        };
        _this._view = null;
        _this._handler = null;
        return _this;
    }
    ChatView.prototype.componentDidMount = function () {
        this._handler = findNodeHandle(this._view);
    };
    ChatView.prototype._sendCallReturn = function (command) {
        var requestId = this._nextRequestId++;
        var requestMap = this._requestMap;
        var promise = new Promise(function (resolve, reject) {
            requestMap[requestId] = { resolve: resolve, reject: reject };
        });
        this._UiManager.dispatchViewManagerCommand(this._handler, command, [requestId]);
        return promise;
    };
    ChatView.prototype.render = function () {
        var _this = this;
        return (<RNChatView {...this.props} ref={function (v) { return _this._view = v; }} {...{ onCallReturn: function (event) { return _this._onCallReturn(event); } }}/>);
    };
    ChatView.defaultProps = {};
    return ChatView;
}(Component));
export default ChatView;
//# sourceMappingURL=ChatView.js.map