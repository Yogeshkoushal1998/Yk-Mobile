package com.yk.speedtest.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yk.speedtest.db.dao.TestHistoryDataDao;

public class DBHelper {

  private static DBHelper dbHelper;
  private final AppDatabase appDatabase;

  private DBHelper(Context context) {
    appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DBConstants.DB_NAME)
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE).fallbackToDestructiveMigration().allowMainThreadQueries()
            .build();
  }

  public static DBHelper getInstance(Context context) {
    if (dbHelper == null) {
      dbHelper = new DBHelper(context);
    }
    return dbHelper;
  }

  public void clearDatabase() {
    if (appDatabase != null) {
      appDatabase.clearAllTables();
    }
  }

  public TestHistoryDataDao getSpeedTestHistoryDao() {
    return appDatabase.speedTestHistoryDataDao();
  }

}