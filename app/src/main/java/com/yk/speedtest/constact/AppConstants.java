package com.yk.speedtest.constact;

import com.yk.speedtest.ContextHolder;

public class AppConstants {
  public static final String PKG_NAME = ContextHolder.get().getPackageName();
  public static final String TYPE = "TYPE";
  public static final int SECOND_MILLIS = 1000;
  public static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    public static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    public static final long KB = 1024;
    public static final long MB = 1048576;
    public static final long GB = 1073741824;
    public static final int FIVE = 5;
    public static final int HUNDREAD = 100;
    public static final int TWENTY = 20;
    public static final int PERMISSION_REQUEST_CODE = 108;
    public static final int _0 = 0;

    public static final int PRESSED = 2;
    public static final int BASIN = 1;
    public static final int FLAT = 0;
    public static final int THEME_WHITE = 0;
    public static final int THEME_BLUE = 1;
    public static final int THEME_BLACK = 2;
    public static final boolean YES = true;
    public static final boolean NO = false;
    public static final String EN = "en";
    public static final String SIMPLE_DATE_FORMAT_V = "dd/MM/yyyy, HH:mm";

}
