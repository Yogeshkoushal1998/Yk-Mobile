package com.yk.speedtest.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yk.speedtest.db.DBConstants;
import com.yk.speedtest.models.SpeedTestHistory;

import java.util.List;


@Dao
public interface SpeedTestHistoryDataDao {

  @Insert
  Long[] insertList(List<SpeedTestHistory> listData);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(SpeedTestHistory testHistory);

  @Query("DELETE FROM " + DBConstants.TEST_HISTORY_TABLE)
  int deleteAll();

  @Query("select * from " + DBConstants.TEST_HISTORY_TABLE)
  List<SpeedTestHistory> getHistoryList();

  @Query("select * from " + DBConstants.TEST_HISTORY_TABLE
          + " GROUP BY  testStartedOn ORDER BY testStartedOn DESC")
  List<SpeedTestHistory> getHistoryListOrdered();
}
