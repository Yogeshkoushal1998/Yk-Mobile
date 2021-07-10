package com.yk.speedtest.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.yk.speedtest.db.convertor.Converter;
import com.yk.speedtest.db.dao.TestHistoryDataDao;
import com.yk.speedtest.models.TestHistory;

@Database(entities = { TestHistory.class }, version = DBConstants.DB_VERSION, exportSchema = false)
@TypeConverters({ Converter.class })
public abstract class AppDatabase extends RoomDatabase {

  public abstract TestHistoryDataDao speedTestHistoryDataDao();

}
