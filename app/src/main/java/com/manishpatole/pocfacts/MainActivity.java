package com.manishpatole.pocfacts;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.manishpatole.pocfacts.adapter.FactFeedAdapter;
import com.manishpatole.pocfacts.base.BaseActivity;
import com.manishpatole.pocfacts.model.Fact;
import com.manishpatole.pocfacts.presenter.FactFeedsPresenterImpl;
import com.manishpatole.pocfacts.utility.CollectionUtil;
import com.manishpatole.pocfacts.utility.DialogUtil;
import com.manishpatole.pocfacts.view.FactFeedView;

public class MainActivity extends BaseActivity<FactFeedsPresenterImpl> implements
    FactFeedView {

  private static final String TAG = MainActivity.class.getSimpleName();
  private RecyclerView rvFactList;
  private TextView tvNoFeedsAvailableMessage;
  private FactFeedAdapter mFactFeedAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initUi();
    getFeeds();
  }

  /**
   * Initialises UI components.
   */
  private void initUi() {
    rvFactList = (RecyclerView) findViewById(R.id.rv_fact_list);
    tvNoFeedsAvailableMessage = (TextView) findViewById(R.id.tv_no_data_available);
    final Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(getString(R.string.fetching_fact_feeds));


    mFactFeedAdapter = new FactFeedAdapter(null);
    rvFactList.setLayoutManager(new LinearLayoutManager(this));
    rvFactList.setHasFixedSize(true);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
        DividerItemDecoration.VERTICAL);
    rvFactList.addItemDecoration(dividerItemDecoration);
    rvFactList.setAdapter(mFactFeedAdapter);
  }

  @Override
  protected FactFeedsPresenterImpl getPresenter() {
    return new FactFeedsPresenterImpl(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
      case R.id.refresh:
        getFeeds();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void getFeeds() {
    if (checkInternet()) {
      mPresenter.getFactFeeds();
    }
  }

  /**
   * Show feeds.
   *
   * @param fact - to display.
   */
  @Override
  public void showFeeds(Fact fact) {
    Log.e(TAG, fact.getTitle());
    getSupportActionBar().setTitle(fact.getTitle());
    if (CollectionUtil.isEmpty(fact.getRows())) {
      showNoFeedsAvailableError();
    } else {
      rvFactList.setVisibility(View.VISIBLE);
      tvNoFeedsAvailableMessage.setVisibility(View.GONE);
      mFactFeedAdapter.refreshList(fact.getRows());
    }
  }

  /**
   * Shows error.
   *
   * @param error - to display.
   */
  @Override
  public void showError(String error) {
    getSupportActionBar().setTitle(getString(R.string.error));
    DialogUtil.showErrorDialog(this, getString(R.string.error), error,
        new DialogUtil.OnDialogClick() {
          @Override
          public void onPositiveButtonClick(DialogInterface dialog) {
            dialog.dismiss();
          }
        });
  }

  /**
   * Shows no feeds available error.
   */
  @Override
  public void showNoFeedsAvailableError() {
    rvFactList.setVisibility(View.GONE);
    tvNoFeedsAvailableMessage.setVisibility(View.VISIBLE);
  }

  /**
   * Handles no internet connection error dialogs positive button click.
   *
   * @param dialog
   */
  @Override
  public void onPositiveButtonClick(DialogInterface dialog) {
    getSupportActionBar().setTitle(getString(R.string.error));
    dialog.dismiss();
  }
}
