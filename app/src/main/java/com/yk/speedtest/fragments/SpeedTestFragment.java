package com.yk.speedtest.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.speedchecker.android.sdk.Public.SpeedTestListener;
import com.speedchecker.android.sdk.Public.SpeedTestResult;
import com.speedchecker.android.sdk.SpeedcheckerSDK;
import com.speedchecker.android.sdk.speedtest.R;
import com.yk.speedtest.activity.SpeedTestHistoryActivity;
import com.yk.speedtest.models.TestHistory;
import com.yk.speedtest.util.AppLogger;
import com.yk.speedtest.util.AppUtil;
import com.yk.speedtest.util.SpeedMeterUtil;
import com.yk.speedtest.util.TestUtil;

import soup.neumorphism.NeumorphTextView;

public class SpeedTestFragment extends Fragment implements SpeedTestListener {

    private static SpeedTestFragment fragment;
    private Activity mActivity;
    private AppCompatTextView mTextViewStage;
    private AppCompatTextView mTextViewResult;
    private ImageView barImageView;
    public boolean isTestRunning;
    private NeumorphTextView tvStart;
    private LinearLayout rlbtnAnimate;
    private ConstraintLayout llParent;


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
        rlbtnAnimate = rootview.findViewById(R.id.rlbtnAnimate);
        llParent=rootview.findViewById(R.id.llParent);
        tvStart = rootview.findViewById(R.id.tvStart);
        tvTitle.setText(R.string.speed_test);
        AppUtil.changeTextColorAnimation(tvTitle);
        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());
        rootview.findViewById(R.id.btnStart).setOnClickListener(v -> {
            if (!isTestRunning) {
                tvStart.setText(R.string.stop);
                SpeedcheckerSDK.SpeedTest.startTest(mActivity);
                isTestRunning = true;
                btnAnimation();
            } else {
                openStopTestAlertDialog();
            }
        });

        rootview.findViewById(R.id.btnHistory).setOnClickListener(v -> {
            if (!isTestRunning) {
                Intent intent = new Intent(requireActivity(), SpeedTestHistoryActivity.class);
                startActivity(intent);
            } else {
                openStopTestAlertDialog();
            }
        });
    }

    private void btnAnimation() {
        int yAnimation = llParent.getHeight() / 2 - (int) getResources().getDimension(R.dimen._25sdp);
        rlbtnAnimate.animate().y(yAnimation).setDuration(1000);
    }

    public void openStopTestAlertDialog() {
        Dialog dialog = new Dialog(mActivity, R.style.dialogThm);
        View view = AppUtil.getInflatedLayout(mActivity, R.layout.common_dialog, null);
        dialog.setContentView(view);
        AppCompatTextView tvTitle = view.findViewById(R.id.tvTitle);
        AppCompatTextView tvsubTitle = view.findViewById(R.id.tvsubTitle);
        tvTitle.setText(R.string.stop_test);
        tvsubTitle.setText(R.string.do_you_want_to_stop_test);
        view.findViewById(R.id.btnYes).setOnClickListener(v -> {
            resetTestView();
            SpeedcheckerSDK.SpeedTest.interruptTest();
            dialog.dismiss();
        });
        view.findViewById(R.id.btnNo).setOnClickListener(v -> dialog.dismiss());
        dialog.setCancelable(true);
        dialog.show();

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
        resetTestView();
    }

    @Override
    public void onFindingBestServerStarted() {
        mTextViewStage.setText(R.string.finding_best_server);
        mTextViewResult.setText("");
    }

    @Override
    public void onTestFinished(SpeedTestResult speedTestResult) {
        String finalStr = getString(R.string.ping) + ": " + speedTestResult.getPing() + " ms" + "\n"
                + getString(R.string.download_speed) + ": " + speedTestResult.getDownloadSpeed() + " Mb/s" + "\n"
                + getString(R.string.upload_speed) + ": " + speedTestResult.getUploadSpeed() + " Mb/s" + "\n"
                + getString(R.string.accuracy) + ": " + speedTestResult.getAccuracy() + "\n"
                + getString(R.string.connection_type) + ": " + speedTestResult.getConnectionTypeHuman() + "\n"
                + getString(R.string.address) + ": " + AppUtil.getAddress(mActivity, speedTestResult.getLatitude(), speedTestResult.getLongitude()) + "\n";
        mTextViewStage.setText(R.string.test_finised);
        mTextViewResult.setText(finalStr);
        AppLogger.d("TAG", new Gson().toJson(speedTestResult));
        setDataInDb(speedTestResult);
        resetTestView();
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
        mTextViewResult.setText("");
    }

    @Override
    public void onPingFinished(int i) {
        mTextViewStage.setText(R.string.ping_finished);
        mTextViewResult.setText((i + " ms"));
    }

    @Override
    public void onDownloadTestStarted() {
        mTextViewStage.setText(R.string.download_test_started);
        mTextViewResult.setText("");
    }

    @Override
    public void onDownloadTestProgress(int i, double speedMbs, double transferredMb) {
        mTextViewStage.setText(R.string.download_test_progress);
        mTextViewResult.setText((speedMbs + " Mb/s"));
        SpeedMeterUtil.startAnim(requireActivity(), speedMbs, barImageView);
    }

    @Override
    public void onDownloadTestFinished(double v) {
        mTextViewStage.setText(R.string.download_test_finished);
        mTextViewResult.setText((v + " Mb/s"));
    }

    @Override
    public void onUploadTestStarted() {
        mTextViewStage.setText(R.string.upload_test_started);
        mTextViewResult.setText("");
    }

    @Override
    public void onUploadTestProgress(int i, double speedMbs, double transferredMb) {
        mTextViewStage.setText(R.string.upload_test_progress);
        mTextViewResult.setText((speedMbs + " Mb/s"));
        SpeedMeterUtil.startAnim(requireActivity(), speedMbs, barImageView);
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
        resetTestView();
    }

    @Override
    public void onTestFatalError(String s) {
        mTextViewStage.setText(R.string.test_fatal_error);
        mTextViewResult.setText(s);
        resetTestView();
    }

    @Override
    public void onTestInterrupted(String s) {
        mTextViewStage.setText(R.string.test_intereeupted);
        mTextViewResult.setText(s);
        resetTestView();
    }

    private void resetTestView() {
        btnAnimation();
        tvStart.setText(R.string.start);
        isTestRunning = false;

    }

}