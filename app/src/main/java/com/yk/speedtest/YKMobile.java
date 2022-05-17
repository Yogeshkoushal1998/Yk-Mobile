package com.yk.speedtest;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


public class YKMobile extends Application {

  @SuppressLint("StaticFieldLeak")
  private static Context mContext;

  public static Context getContext() {
    return YKMobile.mContext;
  }

  public static void setContext(Context mContext) {
    YKMobile.mContext = mContext;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    ContextHolder.getInstance().init(this.getApplicationContext());
    if (this.getApplicationContext() != null) {
      setContext(this.getApplicationContext());
    }
  }

}
