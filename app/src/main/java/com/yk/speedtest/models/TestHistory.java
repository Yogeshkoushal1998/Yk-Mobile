package com.yk.speedtest.models;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.yk.speedtest.db.DBConstants;


@Entity(tableName = DBConstants.TEST_HISTORY_TABLE)
public class TestHistory {
  @PrimaryKey
  @NonNull
  private long dateInMillis;
  private Float downloadSpeed;
  private Float uploadSpeed;
  private double downloadTransferredMb;
  private double uploadTransferredMb;
  private Integer ping;
  private int connectionType;
  private String connectionTypeHuman;
  private String serverInfo;
  private Float latitude;
  private Float longitude;
  private Float accuracy;
  @Embedded
  private TestServer server;
  private long length;
  private Float downloadCrossTrafficSpeed;
  private Float uploadCrossTrafficSpeed;
  private Float downloadCrossTrafficTransferredMb;
  private Float uploadCrossTrafficTransferredMb;
  private String crossTrafficStatsErrors;
  private int downloadThreadsUsed;
  private int uploadThreadsUsed;


  @NonNull
  public long getDateInMillis() {
    return dateInMillis;
  }

  public void setDateInMillis(@NonNull long dateInMillis) {
    this.dateInMillis = dateInMillis;
  }

  public Float getDownloadSpeed() {
    return downloadSpeed;
  }

  public void setDownloadSpeed(Float downloadSpeed) {
    this.downloadSpeed = downloadSpeed;
  }

  public Float getUploadSpeed() {
    return uploadSpeed;
  }

  public void setUploadSpeed(Float uploadSpeed) {
    this.uploadSpeed = uploadSpeed;
  }

  public double getDownloadTransferredMb() {
    return downloadTransferredMb;
  }

  public void setDownloadTransferredMb(double downloadTransferredMb) {
    this.downloadTransferredMb = downloadTransferredMb;
  }

  public double getUploadTransferredMb() {
    return uploadTransferredMb;
  }

  public void setUploadTransferredMb(double uploadTransferredMb) {
    this.uploadTransferredMb = uploadTransferredMb;
  }

  public Integer getPing() {
    return ping;
  }

  public void setPing(Integer ping) {
    this.ping = ping;
  }

  public int getConnectionType() {
    return connectionType;
  }

  public void setConnectionType(int connectionType) {
    this.connectionType = connectionType;
  }

  public String getConnectionTypeHuman() {
    return connectionTypeHuman;
  }

  public void setConnectionTypeHuman(String connectionTypeHuman) {
    this.connectionTypeHuman = connectionTypeHuman;
  }

  public String getServerInfo() {
    return serverInfo;
  }

  public void setServerInfo(String serverInfo) {
    this.serverInfo = serverInfo;
  }

  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }

  public Float getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(Float accuracy) {
    this.accuracy = accuracy;
  }

  public TestServer getServer() {
    return server;
  }

  public void setServer(TestServer server) {
    this.server = server;
  }

  public long getLength() {
    return length;
  }

  public void setLength(long length) {
    this.length = length;
  }

  public Float getDownloadCrossTrafficSpeed() {
    return downloadCrossTrafficSpeed;
  }

  public void setDownloadCrossTrafficSpeed(Float downloadCrossTrafficSpeed) {
    this.downloadCrossTrafficSpeed = downloadCrossTrafficSpeed;
  }

  public Float getUploadCrossTrafficSpeed() {
    return uploadCrossTrafficSpeed;
  }

  public void setUploadCrossTrafficSpeed(Float uploadCrossTrafficSpeed) {
    this.uploadCrossTrafficSpeed = uploadCrossTrafficSpeed;
  }

  public Float getDownloadCrossTrafficTransferredMb() {
    return downloadCrossTrafficTransferredMb;
  }

  public void setDownloadCrossTrafficTransferredMb(Float downloadCrossTrafficTransferredMb) {
    this.downloadCrossTrafficTransferredMb = downloadCrossTrafficTransferredMb;
  }

  public Float getUploadCrossTrafficTransferredMb() {
    return uploadCrossTrafficTransferredMb;
  }

  public void setUploadCrossTrafficTransferredMb(Float uploadCrossTrafficTransferredMb) {
    this.uploadCrossTrafficTransferredMb = uploadCrossTrafficTransferredMb;
  }

  public String getCrossTrafficStatsErrors() {
    return crossTrafficStatsErrors;
  }

  public void setCrossTrafficStatsErrors(String crossTrafficStatsErrors) {
    this.crossTrafficStatsErrors = crossTrafficStatsErrors;
  }


  public int getDownloadThreadsUsed() {
    return downloadThreadsUsed;
  }

  public void setDownloadThreadsUsed(int downloadThreadsUsed) {
    this.downloadThreadsUsed = downloadThreadsUsed;
  }

  public int getUploadThreadsUsed() {
    return uploadThreadsUsed;
  }

  public void setUploadThreadsUsed(int uploadThreadsUsed) {
    this.uploadThreadsUsed = uploadThreadsUsed;
  }


}
