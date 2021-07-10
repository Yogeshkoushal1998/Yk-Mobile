package com.yk.speedtest.db.helper;

import android.content.Context;

import com.yk.speedtest.db.DBHelper;
import com.yk.speedtest.db.dao.TestHistoryDataDao;
import com.yk.speedtest.models.TestHistory;

import java.util.List;

public class TestHistoryDataDbHelper implements TestHistoryDataDao {

  private static TestHistoryDataDbHelper testHistoryDataDbHelper;
  private final TestHistoryDataDao testHistoryDataDao;

  private TestHistoryDataDbHelper(Context context) {
    this.testHistoryDataDao = DBHelper.getInstance(context).getSpeedTestHistoryDao();
  }

  public static TestHistoryDataDbHelper getInstance(Context context) {
    if (testHistoryDataDbHelper == null) {
      testHistoryDataDbHelper = new TestHistoryDataDbHelper(context);
    }
    return testHistoryDataDbHelper;
  }

  @Override
  public Long[] insertList(List<TestHistory> listData) {
    return testHistoryDataDao.insertList(listData);
  }

  @Override
  public long insert(TestHistory testHistory) {
    return testHistoryDataDao.insert(testHistory);
  }

  @Override
  public int deleteAll() {
    return testHistoryDataDao.deleteAll();
  }

  @Override
  public List<TestHistory> getHistoryList() {
    return testHistoryDataDao.getHistoryList();
  }

  @Override
  public List<TestHistory> getHistoryListOrdered() {
    return testHistoryDataDao.getHistoryListOrdered();
  }

}
