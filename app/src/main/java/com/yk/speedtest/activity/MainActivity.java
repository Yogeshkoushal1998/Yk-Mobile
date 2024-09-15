package com.yk.speedtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.MobileAds;
import com.speedchecker.android.sdk.speedtest.R;
import com.yk.speedtest.fragments.HomeFragment;
import com.yk.speedtest.fragments.ProfileFragment;
import com.yk.speedtest.fragments.SettingsFragment;
import com.yk.speedtest.fragments.SpeedTestFragment;
import com.yk.speedtest.util.AppUtil;
import com.yk.speedtest.util.ThemeUtils;

import java.util.List;

import soup.neumorphism.NeumorphImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    NeumorphImageView ivProfile;
    NeumorphImageView ivSpeedTest;
    NeumorphImageView ivHome;
    NeumorphImageView ivSetting;
    private Fragment currentFragment;
    private boolean isRecreate;
    private static final String RECREATE = "recreate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        getSavedInstances(savedInstanceState);
        initView();
        MobileAds.initialize(this, initializationStatus -> {
        });
        if (!isRecreate) {
            loadHomeFragment();
        } else {
            isRecreate = false;
        }
    }

    private void loadHomeFragment() {
        setAnimation(true, false, false, false);
        loadFragment(SpeedTestFragment.getInstance());
    }

  private void loadSpeedTestFragment() {
    setAnimation(false, false, false, true);
    loadFragment(SpeedTestFragment.getInstance());
  }

    public void setAnimation(boolean isHome, boolean isSetting, boolean isProfile, boolean isSpeedTest) {
        AppUtil.setAnimationImage(this, R.drawable.setting, ivSetting, isSetting);
        AppUtil.setAnimationImage(this, R.drawable.ic_baseline_history_24, ivProfile, false);
        AppUtil.setAnimationImage(this, R.drawable.home, ivHome, isHome);
        AppUtil.setAnimationImage(this, R.drawable.speed_test_icn, ivSpeedTest, isSpeedTest);
    }

    private void initView() {
        ivProfile = findViewById(R.id.ivProfile);
        ivSetting = findViewById(R.id.ivSetting);
        ivHome = findViewById(R.id.ivHome);
        ivSpeedTest = findViewById(R.id.ivSpeedTest);
        ivHome.setOnClickListener(this);
        ivProfile.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        ivSpeedTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivHome:
                if (speedTestRunningCheck()) return;
                loadHomeFragment();
                break;
            case R.id.ivSetting:
                if (speedTestRunningCheck()) return;
                setAnimation(false, true, false, false);
                loadFragment(SettingsFragment.getInstance());
                break;
            case R.id.ivSpeedTest:
                if (speedTestRunningCheck()) return;
                loadSpeedTestFragment();
                break;
            case R.id.ivProfile:
                if (speedTestRunningCheck()) return;
                setAnimation(false, false, true, false);
//                loadFragment(ProfileFragment.getInstance());

//        Intent intent = new Intent(this, SpeedBoosterActivity.class);
//        startActivity(intent);

                Intent intent = new Intent(this, SpeedTestHistoryActivity.class);
                startActivity(intent);

                break;
        }
    }

    private boolean speedTestRunningCheck() {
        if (SpeedTestFragment.getInstance().isTestRunning) {
            SpeedTestFragment.getInstance().openStopTestAlertDialog();
            return true;
        }
        return false;
    }


    public void loadFragment(Fragment fragment) {
        currentFragment = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.llFragmentContainer, fragment);
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }


    @Override
    public void onBackPressed() {
      List<Fragment> fragments = getSupportFragmentManager().getFragments();
      for (Fragment f : fragments) {
        if (f instanceof SpeedTestFragment ) {
          if (speedTestRunningCheck()) return;
        }
      }

        if (!(currentFragment instanceof HomeFragment)) {
                loadHomeFragment();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void recreate() {
        isRecreate = true;
        super.recreate();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(RECREATE, isRecreate);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void getSavedInstances(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            isRecreate = savedInstanceState.getBoolean(RECREATE);
        } else {
            isRecreate = false;
        }
    }

}
