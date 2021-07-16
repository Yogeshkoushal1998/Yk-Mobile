package com.yk.speedtest.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.speedchecker.android.sdk.speedtest.R;
import com.yk.speedtest.util.AppLogger;

public class BoostAlarm extends BroadcastReceiver {
  public final static String PREFERENCES_RES_BOOSTER = "akash";

  @Override
  public void onReceive(Context context, Intent intent) {
    SharedPreferences sharedpreferences = context.getSharedPreferences(PREFERENCES_RES_BOOSTER, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedpreferences.edit();
    editor.putString("booster", "1");
    editor.commit();
    try {
      SpeedBoosterActivity.optimizebutton.setBackgroundResource(0);
      SpeedBoosterActivity.optimizebutton.setImageResource(0);
      SpeedBoosterActivity.optimizebutton.setImageResource(R.drawable.n_bt);
    } catch (Exception e) {
      AppLogger.ex(e);
    }
  }
}
