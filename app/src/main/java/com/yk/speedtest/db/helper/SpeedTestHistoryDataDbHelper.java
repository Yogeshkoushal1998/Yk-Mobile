package com.yk.speedtest.db.helper;

import android.content.Context;

import com.yk.speedtest.db.DBHelper;
import com.yk.speedtest.db.dao.SpeedTestHistoryDataDao;
import com.yk.speedtest.models.SpeedTestHistory;

import java.util.List;

public class SpeedTestHistoryDataDbHelper implements SpeedTestHistoryDataDao {

  private static SpeedTestHistoryDataDbHelper speedTestHistoryDataDbHelper;
  private final SpeedTestHistoryDataDao speedTestHistoryDataDao;

  private SpeedTestHistoryDataDbHelper(Context context) {
    this.speedTestHistoryDataDao = DBHelper.getInstance(context).getSpeedTestHistoryDao();
  }

  public static SpeedTestHistoryDataDbHelper getInstance(Context context) {
    if (speedTestHistoryDataDbHelper == null) {
      speedTestHistoryDataDbHelper = new SpeedTestHistoryDataDbHelper(context);
    }
    return speedTestHistoryDataDbHelper;
  }

  @Override
  public Long[] insertList(List<SpeedTestHistory> listData) {
    return speedTestHistoryDataDao.insertList(listData);
  }

  @Override
  public long insert(SpeedTestHistory testHistory) {
    return speedTestHistoryDataDao.insert(testHistory);
  }

  @Override
  public int deleteAll() {
    return speedTestHistoryDataDao.deleteAll();
  }

  @Override
  public List<SpeedTestHistory> getHistoryList() {
    return speedTestHistoryDataDao.getHistoryList();
  }

  @Override
  public List<SpeedTestHistory> getHistoryListOrdered() {
    return speedTestHistoryDataDao.getHistoryListOrdered();
  }

}
