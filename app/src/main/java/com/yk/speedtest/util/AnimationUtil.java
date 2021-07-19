package com.yk.speedtest.util;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

public class AnimationUtil {



  public static void expand(final View v) {
    v.measure(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    final int targetHeight = v.getMeasuredHeight();

    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    v.getLayoutParams().height = 1;
    v.setVisibility(View.VISIBLE);
    Animation a = new Animation()
    {
      @Override
      protected void applyTransformation(float interpolatedTime, Transformation t) {
        v.getLayoutParams().height = interpolatedTime == 1
                ? WindowManager.LayoutParams.WRAP_CONTENT
                : (int)(targetHeight * interpolatedTime);
        v.requestLayout();
      }

      @Override
      public boolean willChangeBounds() {
        return true;
      }
    };

    // 1dp/ms
    a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
    v.startAnimation(a);
  }

  public static void collapse(final View v) {
    final int initialHeight = v.getMeasuredHeight();

    Animation a = new Animation()
    {
      @Override
      protected void applyTransformation(float interpolatedTime, Transformation t) {
        if(interpolatedTime == 1){
          v.setVisibility(View.GONE);
        }else{
          v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
          v.requestLayout();
        }
      }

      @Override
      public boolean willChangeBounds() {
        return true;
      }
    };

    // 1dp/ms
    a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
    v.startAnimation(a);
  }


  public static Animation inFromRightAnimation(int duration) {
    Animation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, +1.0f,
            Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
    translateAnimation.setDuration(duration);
    translateAnimation.setInterpolator(new AccelerateInterpolator());
    return translateAnimation;
  }

  public static Animation inFromLeftAnimation(int duration) {
    Animation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f,
            Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
    translateAnimation.setDuration(duration);
    translateAnimation.setInterpolator(new AccelerateInterpolator());
    return translateAnimation;
  }

  public static Animation centerToOutside(int duration) {
    Animation animation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
            0.5f);
    animation.setFillAfter(true);
    animation.setDuration(duration);
    return animation;
  }

  public static void blink(final View view) {
    Animation anim = new AlphaAnimation(0.3f, 1.0f);
    anim.setDuration(2000); // You can manage the blinking time with this parameter
    anim.setStartOffset(40);
    anim.setRepeatMode(Animation.REVERSE);
    anim.setRepeatCount(Animation.INFINITE);
    view.startAnimation(anim);
    view.clearAnimation();
  }


}
