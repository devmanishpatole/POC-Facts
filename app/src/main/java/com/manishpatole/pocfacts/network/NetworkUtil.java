package com.manishpatole.pocfacts.network;

import com.manishpatole.pocfacts.utility.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Returns the retrofit instance.
 *
 * @author manishpatole
 */

public class NetworkUtil {

  private static Retrofit retrofit;

  /**
   * @return - retrofit client.
   */
  public static Retrofit getRetrofitClient() {

    if (null == retrofit) {
      retrofit = new Retrofit.Builder()
          .baseUrl(Constant.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return retrofit;
  }
}
