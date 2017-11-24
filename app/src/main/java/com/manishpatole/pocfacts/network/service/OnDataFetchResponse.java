package com.manishpatole.pocfacts.network.service;

/**
 * Callback to respond back with fact feeds.
 *
 * @author manishpatole
 */

public interface OnDataFetchResponse<T> {

  void onSuccess(T response);

  void onFailure(Throwable throwable);

  void onFailure();
}
