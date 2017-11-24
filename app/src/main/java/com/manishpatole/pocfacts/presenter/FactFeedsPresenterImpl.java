package com.manishpatole.pocfacts.presenter;

import com.manishpatole.pocfacts.R;
import com.manishpatole.pocfacts.application.POCFactApplication;
import com.manishpatole.pocfacts.model.Fact;
import com.manishpatole.pocfacts.network.service.FactFeedService;
import com.manishpatole.pocfacts.network.service.FactFeedServiceImpl;
import com.manishpatole.pocfacts.network.service.OnDataFetchResponse;
import com.manishpatole.pocfacts.view.FactFeedView;

/**
 * Implements Fact feeds presenter.
 *
 * @author manishpatole
 */

public class FactFeedsPresenterImpl implements FactFeedsPresenter, OnDataFetchResponse<Fact> {

  private FactFeedView factFeedView;
  private FactFeedService factFeedService;

  public FactFeedsPresenterImpl(FactFeedView factFeedView) {
    this.factFeedView = factFeedView;
    factFeedService = new FactFeedServiceImpl(this);
  }

  /**
   * Fetches the fact feeds.
   */
  @Override
  public void getFactFeeds() {
    factFeedService.fetchFactFeeds();
  }

  @Override
  public void onSuccess(Fact fact) {
    if (null == factFeedView) {
      return;
    }
    factFeedView.showFeeds(fact);
  }

  @Override
  public void onFailure(Throwable throwable) {
    if (null == factFeedView) {
      return;
    }
    factFeedView.showError(null != throwable ? throwable.getMessage() :
        POCFactApplication.getPocFactApplication().getString(R.string.something_went_wrong));
  }

  @Override
  public void onFailure() {
    if (null == factFeedView) {
      return;
    }
    factFeedView.showError(
        POCFactApplication.getPocFactApplication().getString(R.string.something_went_wrong));
  }
}
