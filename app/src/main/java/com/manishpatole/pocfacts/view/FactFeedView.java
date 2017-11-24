package com.manishpatole.pocfacts.view;

import com.manishpatole.pocfacts.base.IView;
import com.manishpatole.pocfacts.model.Fact;

/**
 * Fact feed view contract.
 *
 * @author manishpatole
 */

public interface FactFeedView extends IView {

  /**
   * Show feeds.
   *
   * @param fact - to display.
   */
  void showFeeds(Fact fact);

  /**
   * Shows error.
   *
   * @param error - to display.
   */
  void showError(String error);
}
