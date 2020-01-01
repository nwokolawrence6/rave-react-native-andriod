//  Created by react-native-create-bridge

package com.ravepayment.ravepay;


import android.widget.Toast;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.*;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.flutterwave.raveandroid.Meta;
import com.raveandroid.MainActivity;

import java.util.HashMap;
import java.util.Map;

public class RavePayModule extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "RavePay";
    private static ReactApplicationContext reactContext = null;

    public RavePayModule(ReactApplicationContext context) {
        // Pass in the context to the constructor and save it so you can emit events
        // https://facebook.github.io/react-native/docs/native-modules-android.html#the-toast-module
        super(context);

        reactContext = context;
    }

    @Override
    public String getName() {
        // Tell React the name of the module
        // https://facebook.github.io/react-native/docs/native-modules-android.html#the-toast-module
        return REACT_CLASS;
    }

    @Override
    public Map<String, Object> getConstants() {
        // Export any constants to be used in your native module
        // https://facebook.github.io/react-native/docs/native-modules-android.html#the-toast-module
        final Map<String, Object> constants = new HashMap<>();
        constants.put("EXAMPLE_CONSTANT", "example");

        return constants;
    }

    @ReactMethod
    public void startPaymentProcess (String publicKey, String encryptionKey, int amount, String email, String fName, String lName, String narration, String country, String currency, Meta meta, Callback Response) {

//        Toast.makeText(reactContext, "SUCCESS " , Toast.LENGTH_SHORT).show();
//        MainActivity mActivity= new MainActivity();
          MainActivity.getInstance().makePayment( publicKey, encryptionKey,  amount,  email, fName,  lName,  narration, country, currency, meta, Response);
//        // An example native method that you will expose to React
        // https://facebook.github.io/react-native/docs/native-modules-android.html#the-toast-module
    }

    private static void emitDeviceEvent(String eventName, @Nullable WritableMap eventData) {
        // A method for emitting from the native side to JS
        // https://facebook.github.io/react-native/docs/native-modules-android.html#sending-events-to-javascript
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, eventData);
    }
}
