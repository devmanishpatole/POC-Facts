package com.manishpatole.pocfacts.network.service;

import com.manishpatole.pocfacts.model.Fact;
import com.manishpatole.pocfacts.network.ExecutorCallback;
import com.manishpatole.pocfacts.network.FactFeeds;
import com.manishpatole.pocfacts.network.NetworkUtil;

import retrofit2.Call;

/**
 * Fetches fact feeds from server.
 *
 * @author manishpatole
 */

public class FactFeedServiceImpl implements FactFeedService, OnDataFetchResponse<Fact> {

  private OnDataFetchResponse<Fact> onDataFetchResponse;

  public FactFeedServiceImpl(OnDataFetchResponse<Fact> onDataFetchResponse) {
    this.onDataFetchResponse = onDataFetchResponse;
  }

  /**
   * Fetches the fact feeds from appropriate location.
   */
  @Override
  public void fetchFactFeeds() {
    final FactFeeds facts = NetworkUtil.getRetrofitClient().create(FactFeeds.class);
    Call<Fact> call = facts.getFactFeeds();
    call.enqueue(new ExecutorCallback<Fact>(this));
  }

  @Override
  public void onSuccess(Fact response) {
    onDataFetchResponse.onSuccess(response);
  }

  @Override
  public void onFailure(Throwable throwable) {
    onDataFetchResponse.onFailure(throwable);
  }

  @Override
  public void onFailure() {
    onDataFetchResponse.onFailure();
  }

}
