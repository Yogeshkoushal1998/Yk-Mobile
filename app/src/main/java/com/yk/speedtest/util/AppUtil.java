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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

public class AppUtil {

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
//    LayoutInflater inflater = getLayoutInflater();
//         View layout = inflater.inflate(R.layout.my_toast, null);
//          TextView text = (TextView) layout.findViewById(R.id.textView1);
//          text.setText(msg);
//          Toast toast = new Toast(SpeedBoosterActivity.this);
//          toast.setGravity(Gravity.CENTER_VERTICAL, 0, 70);
//          toast.setDuration(Toast.LENGTH_LONG);
//          toast.setView(layout);
//          toast.show();
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

  public static View getInflatedLayout(Context ctx, int layoutId, ViewGroup root) {
    return LayoutInflater.from(ctx).inflate(layoutId, root);
  }

  public static View getInflatedLayout(Context ctx, int layoutId, ViewGroup root, boolean attachToRoot) {
    return LayoutInflater.from(ctx).inflate(layoutId, root, attachToRoot);
  }

  public static float getCPUtemperature()
  {
    Process process;
    try {
      process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp");
      process.waitFor();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line = reader.readLine();
      if(line!=null) {
        float temp = Float.parseFloat(line);
        return temp / 1000.0f;
      }else{
        return 51.0f;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return 0.0f;
    }
  }

}
