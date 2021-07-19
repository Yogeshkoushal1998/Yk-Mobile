package com.yk.speedtest.util;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class SpeedMeterUtil {
    private static int position = 0;
    private static int lastPosition = 0;

    public static void startAnim(Activity m, double downloadRate, ImageView barImageView) {
        position = getPositionByRate(downloadRate);
        m.runOnUiThread(new Runnable() {
            public RotateAnimation rotate;

            @Override
            public void run() {
                rotate = new RotateAnimation(lastPosition, position, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setInterpolator(new LinearInterpolator());
                rotate.setDuration(100);
                barImageView.startAnimation(rotate);
            }
        });
        lastPosition = position;

    }


    public static int getPositionByRate(double rate) {
        if (rate <= 1) {
            return (int) (rate * 30);

        } else if (rate <= 10) {
            return (int) (rate * 6) + 30;

        } else if (rate <= 30) {
            return (int) ((rate - 10) * 3) + 90;

        } else if (rate <= 50) {
            return (int) ((rate - 30) * 1.5) + 150;

        } else if (rate <= 100) {
            return (int) ((rate - 50) * 1.2) + 180;
        } else if (rate > 100) {
            return (int) ((50 * 1.2) + 180);
        }

        return 0;
    }
}
