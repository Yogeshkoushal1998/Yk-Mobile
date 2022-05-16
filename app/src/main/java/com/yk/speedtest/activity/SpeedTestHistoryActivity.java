package com.yk.speedtest.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.inn.sitemanagement.sitedashboard.ui.adapter.HistoryAdapter;
import com.speedchecker.android.sdk.speedtest.R;
import com.yk.speedtest.models.TestHistory;
import com.yk.speedtest.util.AppUtil;
import com.yk.speedtest.util.TestUtil;
import com.yk.speedtest.util.ThemeUtils;

import java.util.List;

import soup.neumorphism.NeumorphTextView;

public class SpeedTestHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_test_history);
        initView();
    }


    private void initView() {
        NeumorphTextView tvTitle = findViewById(R.id.title);
        tvTitle.setText(R.string.test_history);
        AppUtil.changeTextColorAnimation(tvTitle);
        recyclerView = findViewById(R.id.rv_list);
        List<TestHistory> sortedHistoryList = TestUtil.getSortedHistoryList(this);
        if (sortedHistoryList == null || sortedHistoryList.isEmpty()) {
            findViewById(R.id.llNoDataAvailable).setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(new HistoryAdapter(sortedHistoryList));
    }
}