package com.yk.speedtest;

import android.content.Context;


public class ContextHolder {
  private static final ContextHolder ourInstance = new ContextHolder();
  private Context appContext;

  private ContextHolder() {
  }

  public static Context get() {
    return getInstance().getContext();
  }

  public static synchronized ContextHolder getInstance() {
    return ourInstance;
  }

  public void init(Context context) {
    if (appContext == null) {
      this.appContext = context;
    }
  }

  private Context getContext() {
    return appContext;
  }

}
