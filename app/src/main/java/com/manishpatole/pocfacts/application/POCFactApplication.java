package com.manishpatole.pocfacts.application;

import android.app.Application;

/**
 * Application class.
 *
 * @author manishpatole
 */

public class POCFactApplication extends Application {

  private static POCFactApplication sPocFactApplication;

  @Override
  public void onCreate() {
    super.onCreate();
    sPocFactApplication = this;
  }

  public static POCFactApplication getPocFactApplication() {
    return sPocFactApplication;
  }
}
