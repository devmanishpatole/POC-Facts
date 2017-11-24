package com.manishpatole.pocfacts.network;

import com.manishpatole.pocfacts.model.Fact;
import com.manishpatole.pocfacts.utility.Constant;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Web API interface.
 *
 * @author manishpatole
 */

public interface FactFeeds {

  /**
   * Searches the facts.
   *
   * @return list of facts.
   */
  @GET(Constant.SEARCH_FEEDS)
  Call<Fact> getFactFeeds();
}
