package com.thirdmono.grabilitest.presentation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * {@link BroadcastReceiver} for checking the internet connection.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class CheckConnectivity extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {

        boolean isConnected = arg1.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
        if (isConnected) {
            Toast.makeText(context, "Internet Connection Lost", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show();
        }
    }
}
