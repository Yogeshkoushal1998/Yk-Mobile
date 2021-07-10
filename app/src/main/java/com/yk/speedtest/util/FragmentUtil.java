package com.yk.speedtest.util;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.speedchecker.android.sdk.speedtest.R;


public class FragmentUtil {

  public static void loadFragment(AppCompatActivity mActivity, Fragment fragment, boolean addToBackStack) {
    FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.llFragmentContainer, fragment);
    if (addToBackStack) {
      fragmentTransaction.addToBackStack(null);
    }
    fragmentTransaction.commit();
    fragmentManager.executePendingTransactions();
  }

  public static void loadFragment(AppCompatActivity mActivity, Fragment fragment, boolean addToBackStack,
                                  Bundle bundle) {
    FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.llFragmentContainer, fragment);
    if (addToBackStack) {
      fragmentTransaction.addToBackStack(null);
    }
    fragment.setArguments(bundle);
    fragmentTransaction.commit();
    fragmentManager.executePendingTransactions();
  }

  public static void replaceFrag(AppCompatActivity mActivity, Fragment fragment,
                                 boolean addToBackStack) {
    FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.llFragmentContainer, fragment);
    if (addToBackStack) {
      fragmentTransaction.addToBackStack(null);
    }
    fragmentTransaction.commit();
    fragmentManager.executePendingTransactions();
  }
}
