package com.manishpatole.pocfacts;

import android.os.Bundle;
import android.util.Log;

import com.manishpatole.pocfacts.base.BaseActivity;
import com.manishpatole.pocfacts.model.Fact;
import com.manishpatole.pocfacts.presenter.FactFeedsPresenterImpl;
import com.manishpatole.pocfacts.view.FactFeedView;

public class MainActivity extends BaseActivity<FactFeedsPresenterImpl> implements
    FactFeedView {

  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (checkInternet()) {
      mPresenter.getFactFeeds();
    }
  }

  @Override
  protected FactFeedsPresenterImpl getPresenter() {
    return new FactFeedsPresenterImpl(this);
  }

  /**
   * Show feeds.
   *
   * @param fact - to display.
   */
  @Override
  public void showFeeds(Fact fact) {
    Log.e(TAG, fact.getTitle());
  }

  /**
   * Shows error.
   *
   * @param error - to display.
   */
  @Override
  public void showError(String error) {

  }
}
