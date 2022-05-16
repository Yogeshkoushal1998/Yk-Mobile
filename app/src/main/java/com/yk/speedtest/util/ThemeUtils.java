package com.yk.speedtest.util;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import com.yk.speedtest.constact.AppConstants;


public class ThemeUtils {

    private static int sTheme = AppConstants.THEME_WHITE;
    private static boolean themeChanged;

    private ThemeUtils() {
    }

    public static boolean isThemeChanged() {
        return themeChanged;
    }

    public static void setThemeChanged(boolean themeChanged) {
        ThemeUtils.themeChanged = themeChanged;
    }

    public static void changeTheme(int theme, Context context) {
        sTheme = theme;
        setThemeChanged(true);
        PreferenceHelper.getInstance(context).saveStringPreference(PreferenceHelper.APP_THEME, String.valueOf(sTheme));
    }

    public static int getsTheme(Context context) {
        String theme = PreferenceHelper.getInstance(context).getStringPreference(PreferenceHelper.APP_THEME);
        if (theme == null || theme.isEmpty()) {
            sTheme = AppConstants.THEME_WHITE;
        } else {
            sTheme = Integer.parseInt(theme);
        }

        return sTheme;
    }

    public static void setTheme(Activity activity) {
        switch (getsTheme(activity)) {
            case AppConstants.THEME_WHITE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case AppConstants.THEME_BLACK:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            default:
                break;
        }
    }


}
