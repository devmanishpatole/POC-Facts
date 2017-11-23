package com.manishpatole.pocfacts.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.manishpatole.pocfacts.R;
import com.manishpatole.pocfacts.utility.DialogUtil;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = getPresenter();
    Utility.isConnectedToInternet = Utility.isConnectedToInternet(this);
  }

  protected boolean checkInternet() {
    if (!Utility.isConnectedToInternet) {
      DialogUtil.showErrorDialog(this, getString(R.string.error), getString(R.string.no_internet),
          this);
      return false;
    }
    return true;
  }

  protected abstract T getPresenter();

  @Override
  public void onPositiveButtonClick(DialogInterface dialog) {
    dialog.dismiss();
    finish();
  }
}
