//  Created by react-native-create-bridge

package com.ravepayment.ravepay;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RavePayPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
      // Register your native module
      // https://facebook.github.io/react-native/docs/native-modules-android.html#register-the-module
      return Arrays.<NativeModule>asList(
          new RavePayModule(reactContext)
      );
    }


    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        // Register your native component's view manager
        // https://facebook.github.io/react-native/docs/native-components-android.html#4-register-the-viewmanager
        return Arrays.<ViewManager>asList(
            new com.ravepayment.ravepay.RavePayManager()
        );
    }
}
