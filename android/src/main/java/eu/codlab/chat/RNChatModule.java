
package eu.codlab.chat;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import eu.codlab.chat.utils.FixCursorWindow;

public class RNChatModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNChatModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;

    FixCursorWindow.fix();
  }

  @Override
  public String getName() {
    return "RNChat";
  }
}