package com.manishpatole.pocfacts.presenter;

import com.manishpatole.pocfacts.base.IPresenter;

/**
 * Fact feeds presenter contract.
 *
 * @author manishpatole
 */

public interface FactFeedsPresenter extends IPresenter {

  /**
   * Fetches the fact feeds.
   */
  void getFactFeeds();

}
