package com.manishpatole.pocfacts.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Network status receiver.
 *
 * @author manishpatole
 */
public class NetworkStateReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    Utility.isConnectedToInternet = Utility.isConnectedToInternet(context);
  }
}
