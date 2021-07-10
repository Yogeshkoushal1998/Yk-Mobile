package com.yk.speedtest.db.convertor;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.speedchecker.android.sdk.Public.Server;
import com.yk.speedtest.models.TestServer;

import java.util.ArrayList;

public class Converter {
  public Converter() {}


  @TypeConverter
  public ArrayList<Long> getLongList(String json) {
    if (json == null || json.isEmpty()) {
      return new ArrayList<>();
    } else {
      return new Gson().fromJson(json, new TypeToken<ArrayList<Long>>() {
      }.getType());
    }
  }

  @TypeConverter
  public String getJsonFromLongList(ArrayList<Long> longList) {
    if (longList == null) {
      return new Gson().toJson(new ArrayList<>());
    } else {
      return new Gson().toJson(longList);
    }
  }

  @TypeConverter
  public ArrayList<Double> getDoubleList(String json) {
    if (json == null || json.isEmpty()) {
      return new ArrayList<>();
    } else {
      return new Gson().fromJson(json, new TypeToken<ArrayList<Double>>() {
      }.getType());
    }
  }

  @TypeConverter
  public String getJsonFromDoubleList(ArrayList<Double> doubleList) {
    if (doubleList == null) {
      return new Gson().toJson(new ArrayList<>());
    } else {
      return new Gson().toJson(doubleList);
    }
  }

  public ArrayList<Float> getFloatList(String json) {
    if (json == null || json.isEmpty()) {
      return new ArrayList<>();
    } else {
      return new Gson().fromJson(json, new TypeToken<ArrayList<Float>>() {
      }.getType());
    }
  }

  @TypeConverter
  public String getJsonFromFloatList(ArrayList<Float> floatList) {
    if (floatList == null) {
      return new Gson().toJson(new ArrayList<>());
    } else {
      return new Gson().toJson(floatList);
    }
  }

  public ArrayList<Integer> getIntegerList(String json) {
    if (json == null || json.isEmpty())
      return new ArrayList<>();
    else
      return new Gson().fromJson(json, new TypeToken<ArrayList<Integer>>() {
      }.getType());
  }

  @TypeConverter
  public  String getJsonFromIntegerList(ArrayList<Integer> integerList) {
    if (integerList == null)
      return new Gson().toJson(new ArrayList<>());
    else
      return new Gson().toJson(integerList);
  }


  public TestServer getTestServer(String json) {
    if (json == null || json.isEmpty()) {
      return new TestServer();
    } else {
      return new Gson().fromJson(json, TestServer.class);
    }
  }

  @TypeConverter
  public String getJsonFromServer(TestServer server) {
    if (server == null) {
      return new Gson().toJson(new TestServer());
    } else {
      return new Gson().toJson(server);
    }
  }



  public Server getServer(String json) {
    if (json == null || json.isEmpty()) {
      return new Server();
    } else {
      return new Gson().fromJson(json, Server.class);
    }
  }

  @TypeConverter
  public String getJsonFromServer(Server server) {
    if (server == null) {
      return new Gson().toJson(new Server());
    } else {
      return new Gson().toJson(server);
    }
  }

}