package com.manishpatole.pocfacts.base;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.manishpatole.pocfacts.R;
import com.manishpatole.pocfacts.utility.DialogUtil;
import com.manishpatole.pocfacts.utility.NetworkStateReceiver;
import com.manishpatole.pocfacts.utility.Utility;


/**
 * Base Activity.
 * Handles the common initialisations and operations.
 *
 * @param <T> - Presenter implementor.
 * @author manishpatole
 */
public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity
    implements DialogUtil.OnDialogClick {

  protected T mPresenter;
  private static NetworkStateReceiver sNetworkStateReceiver = new NetworkStateReceiver();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = getPresenter();
    Utility.isConnectedToInternet = Utility.isConnectedToInternet(this);
  }

  protected abstract T getPresenter();

  @Override
  protected void onResume() {
    super.onResume();
    registerNetworkReceiver();
  }

  @Override
  protected void onStop() {
    super.onStop();
    unregisterNetworkReceiver();
  }

  /**
   * Checks the internet connectivity.
   *
   * @return
   */
  protected boolean checkInternet() {
    if (!Utility.isConnectedToInternet) {
      DialogUtil.showErrorDialog(this, getString(R.string.error), getString(R.string.no_internet),
          this);
      return false;
    }
    return true;
  }

  /**
   * Registers the network receiver.
   */
  private void registerNetworkReceiver() {
    final IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    registerReceiver(sNetworkStateReceiver, intentFilter);
  }

  /**
   * Unregisters the network receiver.
   */
  private void unregisterNetworkReceiver() {
    unregisterReceiver(sNetworkStateReceiver);
  }

  /**
   * Shows the progress dialog.
   */
  public void showLoading() {
    DialogUtil.showProgressDialog(this);
  }

  /**
   * Hides the progress dialog.
   */
  public void hideLoading() {
    DialogUtil.hideProgressDialog();
  }

  /**
   * Handles the no network connection error dialogs ok click in generic way by finishing the
   * activity.
   *
   * @param dialog
   */
  @Override
  public void onPositiveButtonClick(DialogInterface dialog) {
    dialog.dismiss();
    finish();
  }
}
