package com.manishpatole.pocfacts.network.service;

import com.manishpatole.pocfacts.model.Fact;

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
