package com.yk.speedtest.util;

import android.util.Log;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class AppLogger {
  private static final boolean DEBUG = false;
  private static final String PREFIX = "<--Yk Mobile Log--> ";

  private AppLogger() {
  }

  public static void d(String tag, String string) {
    if (DEBUG) {
      Log.d(tag, PREFIX + string);
    }
  }

  public static void v(String tag, String string) {
    if (DEBUG) {
      Log.v(tag, PREFIX + string);
    }
  }

  public static void i(String tag, String string) {
    if (DEBUG) {
      Log.i(tag, PREFIX + string);
    }
  }

  public static void e(String tag, String string) {
    if (DEBUG) {
      Log.e(tag, PREFIX + string);
    }
  }

  public static void ex(Exception ex) {
    if (DEBUG) {
      Log.e(TAG, PREFIX + Objects.requireNonNull(ex.getMessage()));
    }
  }

  public static void i(String string) {
    if (DEBUG) {
      Log.i(TAG, PREFIX + string);

    }
  }

}
