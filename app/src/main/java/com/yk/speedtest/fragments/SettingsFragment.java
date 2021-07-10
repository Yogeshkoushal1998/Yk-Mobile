package com.yk.speedtest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.speedchecker.android.sdk.speedtest.R;
import com.yk.speedtest.activity.MainActivity;
import com.yk.speedtest.util.AppUtill;

import soup.neumorphism.NeumorphTextView;

public class SettingsFragment extends Fragment {



  private static SettingsFragment fragment;


  public static SettingsFragment getInstance() {
    if (fragment == null) {
      fragment = new SettingsFragment();
    }
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootview = inflater.inflate(R.layout.fragment_settings, container, false);
    ((MainActivity)requireActivity()).setAnimation(false,true,false,false);
    initView(rootview);
    return rootview;
  }

  private void initView(View rootview) {
    NeumorphTextView tvTitle = rootview.findViewById(R.id.tvTitle);
    tvTitle.setText(R.string.settings);
    AppUtill.changeTextColorAnimation(tvTitle);

    SwitchCompat switch_theme = rootview.findViewById(R.id.switch_theme);
    switch_theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
      }
    });

  }

}