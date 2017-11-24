package com.manishpatole.pocfacts.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * Utility class.
 *
 * @author manishpatole
 */

public class Utility {

  private static final String TAG = Utility.class.getSimpleName();
  public static boolean isConnectedToInternet;


  /**
   * Checks internet connectivity.
   *
   * @param context - live context.
   * @return - status of internet.
   */
  public static boolean isConnectedToInternet(Context context) {

    if (null == context) {
      return false;
    }

    final ConnectivityManager connectivityManager =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      final Network[] networks = connectivityManager.getAllNetworks();
      NetworkInfo networkInfo;
      for (Network mNetwork : networks) {
        networkInfo = connectivityManager.getNetworkInfo(mNetwork);

        if (networkInfo != null && networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
          return true;
        }
      }
    } else {
      if (connectivityManager != null) {
        final NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
        if (info != null) {
          for (NetworkInfo networkInfo : info) {
            if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
