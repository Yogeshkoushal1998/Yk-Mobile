package com.yk.speedtest.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.yk.speedtest.db.convertor.InfoConverter;
import com.yk.speedtest.db.dao.SpeedTestHistoryDataDao;
import com.yk.speedtest.models.SpeedTestHistory;

@Database(entities = { SpeedTestHistory.class }, version = DBConstants.DB_VERSION, exportSchema = false)
@TypeConverters({ InfoConverter.class })
public abstract class AppDatabase extends RoomDatabase {

  public abstract SpeedTestHistoryDataDao speedTestHistoryDataDao();

}
