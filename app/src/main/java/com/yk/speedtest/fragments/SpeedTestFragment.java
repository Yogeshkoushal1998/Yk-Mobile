package com.yk.speedtest.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.speedchecker.android.sdk.Public.SpeedTestListener;
import com.speedchecker.android.sdk.Public.SpeedTestResult;
import com.speedchecker.android.sdk.SpeedcheckerSDK;
import com.speedchecker.android.sdk.speedtest.R;
import com.yk.speedtest.util.AppUtill;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import soup.neumorphism.NeumorphTextView;

public class SpeedTestFragment extends Fragment implements SpeedTestListener {

  private static SpeedTestFragment fragment;
  private Activity mActivity;
  private AppCompatTextView mTextViewLog;
  private AppCompatTextView mTextViewStage;
  private AppCompatTextView mTextViewResult;
  private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);


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
    mTextViewLog = rootview.findViewById(R.id.textView_log);
    mTextViewResult = rootview.findViewById(R.id.textView_result);
    mTextViewStage = rootview.findViewById(R.id.textView_stage);
    NeumorphTextView tvTitle = rootview.findViewById(R.id.tvTitle);
    NeumorphTextView tvStart = rootview.findViewById(R.id.tvStart);
    tvTitle.setText(R.string.speed_test);
//    AppUtill.changeTextColorAnimation(tvStart);
    AppUtill.changeTextColorAnimation(tvTitle);
    rootview.findViewById(R.id.startBtn).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SpeedcheckerSDK.SpeedTest.startTest(mActivity);
      }
    });


  }

  @Override
  public void onTestStarted() {
    mTextViewStage.setText("Test Started");
    mTextViewResult.setText("-");
    log("Test Started");
  }

  @Override
  public void onFetchServerFailed() {
    mTextViewStage.setText("Fetch Server Failed");
    mTextViewResult.setText("-");
    log("Fetch Server Failed");
  }

  @Override
  public void onFindingBestServerStarted() {
    mTextViewStage.setText("Finding best server");
    mTextViewResult.setText("...");
    log("Finding best server");
  }

  @Override
  public void onTestFinished(SpeedTestResult speedTestResult) {
    String finalStr =
            "Server: " + speedTestResult.getServer().Domain + "\n"
                    + "Ping: " + speedTestResult.getPing() + " ms" + "\n"
                    + "Download speed: " + speedTestResult.getDownloadSpeed() + " Mb/s" + "\n"
                    + "Upload speed: " + speedTestResult.getUploadSpeed() + " Mb/s" + "\n"
                    + "ServerInfo: " + speedTestResult.getServerInfo() + "\n"
                    + "Location city: " + speedTestResult.getServer().Location.City + "\n"
                    + "Accuracy: " + speedTestResult.getAccuracy() + "\n"
                    + "Date: " + speedTestResult.getDate() + "\n"
                    + "Connection type: " + speedTestResult.getConnectionTypeHuman() + "\n";
    mTextViewStage.setText("Test Finished");
    mTextViewResult.setText(finalStr);
    log("Test Finished: Server[" + speedTestResult.getServer().Domain + "] -> " + speedTestResult.toString());
  }

  @Override
  public void onPingStarted() {
    mTextViewStage.setText("Ping Started");
    mTextViewResult.setText("...");
    log("Ping Started");
  }

  @Override
  public void onPingFinished(int i) {
    mTextViewStage.setText("Ping Finished");
    mTextViewResult.setText(i + " ms");
    log("Ping Finished: " + i + " ms");
  }

  @Override
  public void onDownloadTestStarted() {
    mTextViewStage.setText("Download Test Started");
    mTextViewResult.setText("...");
    log("Download Test Started");
  }

  @Override
  public void onDownloadTestProgress(int i, double speedMbs, double transferredMb) {
    mTextViewStage.setText("Download Test Progress");
    mTextViewResult.setText(i + "% -> " + speedMbs + " Mb/s\nTransferredMb: " + transferredMb);
    log("Download Test Progress: " + i + "% -> " + speedMbs + " Mb/s\nTransferredMb: " + transferredMb);
  }

  @Override
  public void onDownloadTestFinished(double v) {
    mTextViewStage.setText("Download Test Finished");
    mTextViewResult.setText(v + " Mb/s");
    log("Download Test Finished: " + v + " Mb/s");
  }

  @Override
  public void onUploadTestStarted() {
    mTextViewStage.setText("Upload Test Started");
    mTextViewResult.setText("...");
    log("Upload Test Started");
  }

  @Override
  public void onUploadTestProgress(int i, double speedMbs, double transferredMb) {
    mTextViewStage.setText("Upload Test Progress");
    mTextViewResult.setText(i + "% -> " + speedMbs + " Mb/s\nTransferredMb: " + transferredMb);
    log("Upload Test Progress: " + i + "% -> " + speedMbs + " Mb/s\nTransferredMb: " + transferredMb);
  }

  @Override
  public void onUploadTestFinished(double v) {
    mTextViewStage.setText("Upload Test Finished");
    mTextViewResult.setText(v + " Mb/s");
    log("Upload Test Finished: " + v + " Mb/s");
  }

  @Override
  public void onTestWarning(String s) {
    mTextViewStage.setText(R.string.test_warning);
    mTextViewResult.setText(s);
    log("Test Warning: " + s);
  }

  @Override
  public void onTestFatalError(String s) {
    mTextViewStage.setText(R.string.test_fatal_error);
    mTextViewResult.setText(s);
    log("Test Fatal Error: " + s);
  }

  @Override
  public void onTestInterrupted(String s) {
    mTextViewStage.setText(R.string.test_intereeupted);
    mTextViewResult.setText(s);
    log("Test Interrupted: " + s);
  }

  private void log(String message) {
//    mTextViewLog.setText(("[" + sdf.format(new Date(System.currentTimeMillis())) + "]: "
//            + message + "\n" + mTextViewLog.getText()));
  }

}