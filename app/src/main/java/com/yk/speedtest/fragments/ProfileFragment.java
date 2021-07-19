package com.yk.speedtest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.speedchecker.android.sdk.speedtest.R;
import com.yk.speedtest.util.AppUtil;

import soup.neumorphism.NeumorphTextView;

public class ProfileFragment extends Fragment {

  private static ProfileFragment fragment;


  public static ProfileFragment getInstance() {
    if (fragment == null) {
      fragment = new ProfileFragment();
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
    View rootview = inflater.inflate(R.layout.fragment_profile, container, false);
    initView(rootview);
    return rootview;
  }

  private void initView(View rootview) {
    NeumorphTextView tvTitle = rootview.findViewById(R.id.tvTitle);
    tvTitle.setText(R.string.profile);
    AppUtil.changeTextColorAnimation(tvTitle);
  }
}