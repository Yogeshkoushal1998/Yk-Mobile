package com.yk.speedtest.util;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;

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


}
