package com.yk.speedtest.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import androidx.preference.PreferenceManager;

public class PreferenceHelper {

    public static final String APP_THEME = "theme";
    public static final String THEME_SETTING_CHANGE_CHECK = "theme_setting_change_check";

    private static PreferenceHelper preferenceHelper;
    private final SharedPreferences sharedPreferences;
    private final Editor mEditor;

    @SuppressLint("CommitPrefEdits")
    private PreferenceHelper(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.mEditor = sharedPreferences.edit();
    }

    public static PreferenceHelper getInstance(Context context) {
        if (preferenceHelper == null) {
            preferenceHelper = new PreferenceHelper(context);
        }
        return preferenceHelper;
    }

    public void saveStringPreference(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getStringPreference(String key) {
        return sharedPreferences.getString(key, null);
    }


    public void saveBooleanPreference(String key, boolean isCreateNewList) {
        mEditor.putBoolean(key, isCreateNewList);
        mEditor.commit();
    }


}
