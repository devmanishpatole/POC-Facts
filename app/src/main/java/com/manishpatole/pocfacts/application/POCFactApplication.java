package com.manishpatole.pocfacts.application;

import android.app.Application;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

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
    initImageCaching();
  }

  private void initImageCaching() {
    Picasso.Builder builder = new Picasso.Builder(this);
    builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
    Picasso built = builder.build();
    Picasso.setSingletonInstance(built);
  }

  public static POCFactApplication getPocFactApplication() {
    return sPocFactApplication;
  }
}
