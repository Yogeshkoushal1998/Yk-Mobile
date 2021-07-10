package com.yk.speedtest.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yk.speedtest.db.DBConstants;
import com.yk.speedtest.models.TestHistory;

import java.util.List;


@Dao
public interface TestHistoryDataDao {

  @Insert
  Long[] insertList(List<TestHistory> listData);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(TestHistory testHistory);

  @Query("DELETE FROM " + DBConstants.TEST_HISTORY_TABLE)
  int deleteAll();

  @Query("select * from " + DBConstants.TEST_HISTORY_TABLE)
  List<TestHistory> getHistoryList();

  @Query("select * from " + DBConstants.TEST_HISTORY_TABLE
          + " GROUP BY  dateInMillis ORDER BY dateInMillis DESC")
  List<TestHistory> getHistoryListOrdered();
}
