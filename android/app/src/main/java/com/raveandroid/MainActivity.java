package com.raveandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.Callback;
import com.flutterwave.raveandroid.Meta;
import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class MainActivity extends ReactActivity {
  private static MainActivity instance;
  Callback finalResponse;
    String txRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      instance = this;
    }
  public static MainActivity getInstance() {
    return instance;
  }
    public void makePayment(String publicKey, String encryptionKey, int amount, String email, String fName, String lName, String narration, String country, String currency,Meta meta, Callback Response) {
        txRef = email + " " + UUID.randomUUID().toString();
        finalResponse = Response;
        ArrayList<Meta> al=new ArrayList<Meta>();
        al.add(0, meta);
        /*
        Create instance of RavePayManager
         */
        new RavePayManager(this).setAmount(amount)
                .setCountry(country)
                .setCurrency(currency)
                .setEmail(email)
                .setfName(fName)
                .setlName(lName)
                .setNarration(narration)
                .setPublicKey(publicKey)
                .setEncryptionKey(encryptionKey)
                .setTxRef(txRef)
                .setMeta(al)
                .acceptAccountPayments(true)
                .acceptCardPayments(true)
                .acceptMpesaPayments(true)
                .acceptGHMobileMoneyPayments(true)
                .onStagingEnv(false)
                .acceptBankTransferPayments(false)
                .acceptAccountPayments(true)
                .acceptUssdPayments(true)
                .acceptBarterPayments(true)
                .showStagingLabel(true)
                .initialize();
    }
   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                finalResponse.invoke(requestCode, resultCode, message, "SUCCESS");
            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                finalResponse.invoke(requestCode, resultCode, message, "ERROR");
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "Raveandroid";
    }
}
