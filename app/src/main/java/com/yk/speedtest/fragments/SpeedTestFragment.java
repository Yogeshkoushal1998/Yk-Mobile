package com.yk.speedtest.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.speedchecker.android.sdk.Public.SpeedTestListener;
import com.speedchecker.android.sdk.Public.SpeedTestResult;
import com.speedchecker.android.sdk.SpeedcheckerSDK;
import com.speedchecker.android.sdk.speedtest.R;
import com.yk.speedtest.models.TestHistory;
import com.yk.speedtest.util.AppLogger;
import com.yk.speedtest.util.AppUtill;
import com.yk.speedtest.util.SpeedMeterUtil;
import com.yk.speedtest.util.TestUtil;

import soup.neumorphism.NeumorphTextView;

public class SpeedTestFragment extends Fragment implements SpeedTestListener {

  private static SpeedTestFragment fragment;
  private Activity mActivity;
  private AppCompatTextView mTextViewStage;
  private AppCompatTextView mTextViewResult;
  private ImageView barImageView;
  private boolean isTestRunning;
  private NeumorphTextView tvStart;


  public static SpeedTestFragment getInstance() {
    if (fragment == null) {
      fragment = new SpeedTestFragment();
    }
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivity = getActivity();
    SpeedcheckerSDK.init(mActivity);
    SpeedcheckerSDK.askPermissions(mActivity);
    SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootview = inflater.inflate(R.layout.fragment_speed_test, container, false);
    initView(rootview);
    return rootview;
  }

  private void initView(View rootview) {
    mTextViewResult = rootview.findViewById(R.id.textView_result);
    mTextViewStage = rootview.findViewById(R.id.textView_stage);
    barImageView = rootview.findViewById(R.id.barImageView);
    NeumorphTextView tvTitle = rootview.findViewById(R.id.tvTitle);
    tvStart = rootview.findViewById(R.id.tvStart);
    tvTitle.setText(R.string.speed_test);
    AppUtill.changeTextColorAnimation(tvTitle);
    rootview.findViewById(R.id.startBtn).setOnClickListener(v -> {
      if (!isTestRunning) {
        tvStart.setText(R.string.stop);
        SpeedcheckerSDK.SpeedTest.startTest(mActivity);
        isTestRunning = true;
      } else {
        tvStart.setText(R.string.start);
        isTestRunning = false;
        SpeedcheckerSDK.SpeedTest.interruptTest();
      }


    });
  }

  @Override
  public void onTestStarted() {
    mTextViewStage.setText(R.string.test_started);
    mTextViewResult.setText("");
  }

  @Override
  public void onFetchServerFailed() {
    mTextViewStage.setText(R.string.fetch_server_failed);
    mTextViewResult.setText("");
    tvStart.setText(R.string.start);
    isTestRunning = false;
  }

  @Override
  public void onFindingBestServerStarted() {
    mTextViewStage.setText(R.string.finding_best_server);
    mTextViewResult.setText("...");
  }

  @Override
  public void onTestFinished(SpeedTestResult speedTestResult) {
    String finalStr = getString(R.string.ping) + ": " + speedTestResult.getPing() + " ms" + "\n"
            + getString(R.string.download_speed) + ": " + speedTestResult.getDownloadSpeed() + " Mb/s" + "\n"
            + getString(R.string.upload_speed) + ": " + speedTestResult.getUploadSpeed() + " Mb/s" + "\n"
            + getString(R.string.accuracy) + ": " + speedTestResult.getAccuracy() + "\n"
            + getString(R.string.connection_type) + ": " + speedTestResult.getConnectionTypeHuman() + "\n"
            + getString(R.string.address) + ": " + AppUtill.getAddress(mActivity, speedTestResult.getLatitude(), speedTestResult.getLongitude()) + "\n";
    mTextViewStage.setText(R.string.test_finised);
    mTextViewResult.setText(finalStr);
    setDataInDb(speedTestResult);
    tvStart.setText(R.string.start);
    isTestRunning = false;
  }

  private void setDataInDb(SpeedTestResult speedTestResult) {
    try {
      TestHistory testHistory = new Gson().fromJson(new Gson().toJson(speedTestResult), TestHistory.class);
      TestUtil.insertTestDataInDb(mActivity, testHistory);
    } catch (Exception e) {
      AppLogger.ex(e);
    }
  }

  @Override
  public void onPingStarted() {
    mTextViewStage.setText(R.string.ping_started);
    mTextViewResult.setText("...");
  }

  @Override
  public void onPingFinished(int i) {
    mTextViewStage.setText(R.string.ping_finished);
    mTextViewResult.setText((i + " ms"));
  }

  @Override
  public void onDownloadTestStarted() {
    mTextViewStage.setText(R.string.download_test_started);
    mTextViewResult.setText("...");
  }

  @Override
  public void onDownloadTestProgress(int i, double speedMbs, double transferredMb) {
    mTextViewStage.setText(R.string.download_test_progress);
    mTextViewResult.setText((i + "% -> " + speedMbs + " Mb/s\nTransferredMb: " + transferredMb));
    SpeedMeterUtil.startAnim(getActivity(), speedMbs, barImageView);
  }

  @Override
  public void onDownloadTestFinished(double v) {
    mTextViewStage.setText(R.string.download_test_finished);
    mTextViewResult.setText((v + " Mb/s"));
  }

  @Override
  public void onUploadTestStarted() {
    mTextViewStage.setText(R.string.upload_test_started);
    mTextViewResult.setText("...");
  }

  @Override
  public void onUploadTestProgress(int i, double speedMbs, double transferredMb) {
    mTextViewStage.setText(R.string.upload_test_progress);
    mTextViewResult.setText((i + "% -> " + speedMbs + " Mb/s\nTransferredMb: " + transferredMb));
    SpeedMeterUtil.startAnim(getActivity(), speedMbs, barImageView);
  }

  @Override
  public void onUploadTestFinished(double v) {
    mTextViewStage.setText(R.string.upload_test_finished);
    mTextViewResult.setText((v + " Mb/s"));
  }

  @Override
  public void onTestWarning(String s) {
    mTextViewStage.setText(R.string.test_warning);
    mTextViewResult.setText(s);
    tvStart.setText(R.string.start);
    isTestRunning = false;
  }

  @Override
  public void onTestFatalError(String s) {
    mTextViewStage.setText(R.string.test_fatal_error);
    mTextViewResult.setText(s);
    tvStart.setText(R.string.start);
    isTestRunning = false;
  }

  @Override
  public void onTestInterrupted(String s) {
    mTextViewStage.setText(R.string.test_intereeupted);
    mTextViewResult.setText(s);
    tvStart.setText(R.string.start);
    isTestRunning = false;
  }
}