package com.yk.speedtest.util;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

public class AppUtill {

  public static void setAnimationImage(Context mContext, int id, ImageView imageView, boolean isAnimation) {
    if (isAnimation) {
      Glide.with(mContext)
              .asGif()
              .load(id)
              .into(imageView);
    } else {
      imageView.setImageResource(id);
    }
  }

  public static void changeTextColorAnimation(AppCompatTextView tv) {
    ObjectAnimator a = ObjectAnimator.ofInt(tv, "textColor", Color.GREEN, Color.BLUE, Color.RED);
    a.setInterpolator(new LinearInterpolator());
    a.setDuration(5000);
    a.setRepeatCount(ValueAnimator.INFINITE);
    a.setRepeatMode(ValueAnimator.REVERSE);
    a.setEvaluator(new ArgbEvaluator());
    AnimatorSet t = new AnimatorSet();
    t.play(a);
    t.start();
  }

  public static void showNeumorphToast(Context context, String msg) {
    Toast.makeText(context, msg + "", Toast.LENGTH_SHORT).show();
  }


  public static String getAddress(Context mContext,double latitude, double longitude) {
    String strAdd = "";
    Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
    try {
      List<Address> addresses = geocoder.getFromLocation(latitude,
              longitude, 1);
      if (addresses != null) {
        Address returnedAddress = addresses.get(0);
        strAdd = "" + returnedAddress.getAddressLine(0);
      }
    } catch (Exception e) {
      AppLogger.ex(e);
    }
    return strAdd;
  }

}
