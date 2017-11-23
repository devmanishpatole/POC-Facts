package com.manishpatole.pocfacts.network;


import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class to get all the responses at first place to verify response.
 *
 * @author manishpatole.
 */

public class ExecutorCallback<T> implements Callback<T> {

  private OnServerCallResponse onServerCallResponse;

  public ExecutorCallback(OnServerCallResponse onServerCallResponse) {
    this.onServerCallResponse = onServerCallResponse;
  }

  @Override
  public void onResponse(Call<T> call, Response<T> response) {

    if (null != onServerCallResponse) {
      if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
        onServerCallResponse.onSuccess(response.body());
      } else {
        onServerCallResponse.onFailure();
      }
    }
  }

  @Override
  public void onFailure(Call<T> call, Throwable t) {
    if (null != onServerCallResponse) {
      onServerCallResponse.onFailure(t);
    }
  }

  public interface OnServerCallResponse<T> {
    void onSuccess(T response);

    void onFailure(Throwable throwable);

    void onFailure();
  }

}
