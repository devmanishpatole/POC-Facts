package com.manishpatole.pocfacts.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
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

    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
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
