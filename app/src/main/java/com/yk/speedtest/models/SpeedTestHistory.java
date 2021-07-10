package com.yk.speedtest.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.yk.speedtest.db.DBConstants;


@Entity(tableName = DBConstants.TEST_HISTORY_TABLE)
public class SpeedTestHistory {
  @PrimaryKey
  @NonNull
  private String date;
  private String activeTestType;
  private String address;
  private String appVersionName;
  private String make;
  private String deviceOS;
  private String gpsStatus;
  private String model;
  private String networkType;
  private String operatorName;

  private String nearestServerCity;
  private String nearestServerIP;
  private Double serverLatitude;
  private Double serverLongitude;

  private Double avgLatency;

  public String getNearestServerCity() {
    return nearestServerCity;
  }

  public void setNearestServerCity(String nearestServerCity) {
    this.nearestServerCity = nearestServerCity;
  }

  public String getNearestServerIP() {
    return nearestServerIP;
  }

  public void setNearestServerIP(String nearestServerIP) {
    this.nearestServerIP = nearestServerIP;
  }

  public Double getServerLatitude() {
    return serverLatitude;
  }

  public void setServerLatitude(Double serverLatitude) {
    this.serverLatitude = serverLatitude;
  }

  public Double getServerLongitude() {
    return serverLongitude;
  }

  public void setServerLongitude(Double serverLongitude) {
    this.serverLongitude = serverLongitude;
  }

  private Double maxLatency;
  private Double minLatency;
  private Double latitude;
  private Double longitude;
  private Double avgDlRate;
  private Double avgUlRate;
  private Double maxDlRate;
  private Double minUlRate;
  private Double minDlRate;
  private Double maxUlRate;
  private Long testStartedOn;
  private Integer cgi;
  private Integer cellId;
  private Integer mcc;
  private Integer tac;
  private Integer mnc;

  public String getPing() {
    return ping;
  }

  public void setPing(String ping) {
    this.ping = ping;
  }

  public String getPacketLoss() {
    return packetLoss;
  }

  public void setPacketLoss(String packetLoss) {
    this.packetLoss = packetLoss;
  }

  public String getJitter() {
    return jitter;
  }

  public void setJitter(String jitter) {
    this.jitter = jitter;
  }

  private Integer pci;
  private Integer avgRsrp;
  private Integer avgRsrq;
  private Integer avgRssi;

  private String ping;
  private String packetLoss;
  private String jitter;

  public Double getAvgLatency() {
    return avgLatency;
  }

  public void setAvgLatency(Double avgLatency) {
    this.avgLatency = avgLatency;
  }

  public Integer getCgi() {
    return cgi;
  }

  public void setCgi(Integer cgi) {
    this.cgi = cgi;
  }

  public String getActiveTestType() {
    return activeTestType;
  }

  public void setActiveTestType(String activeTestType) {
    this.activeTestType = activeTestType;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAppVersionName() {
    return appVersionName;
  }

  public void setAppVersionName(String appVersionName) {
    this.appVersionName = appVersionName;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getDeviceOS() {
    return deviceOS;
  }

  public void setDeviceOS(String deviceOS) {
    this.deviceOS = deviceOS;
  }

  public String getGpsStatus() {
    return gpsStatus;
  }

  public void setGpsStatus(String gpsStatus) {
    this.gpsStatus = gpsStatus;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getNetworkType() {
    return networkType;
  }

  public void setNetworkType(String networkType) {
    this.networkType = networkType;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public Double getMaxLatency() {
    return maxLatency;
  }

  public void setMaxLatency(Double maxLatency) {
    this.maxLatency = maxLatency;
  }

  public Double getMinLatency() {
    return minLatency;
  }

  public void setMinLatency(Double minLatency) {
    this.minLatency = minLatency;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getAvgDlRate() {
    return avgDlRate;
  }

  public void setAvgDlRate(Double avgDlRate) {
    this.avgDlRate = avgDlRate;
  }

  public Double getAvgUlRate() {
    return avgUlRate;
  }

  public void setAvgUlRate(Double avgUlRate) {
    this.avgUlRate = avgUlRate;
  }

  public Double getMaxDlRate() {
    return maxDlRate;
  }

  public void setMaxDlRate(Double maxDlRate) {
    this.maxDlRate = maxDlRate;
  }

  public Double getMinUlRate() {
    return minUlRate;
  }

  public void setMinUlRate(Double minUlRate) {
    this.minUlRate = minUlRate;
  }

  public Double getMinDlRate() {
    return minDlRate;
  }

  public void setMinDlRate(Double minDlRate) {
    this.minDlRate = minDlRate;
  }

  public Double getMaxUlRate() {
    return maxUlRate;
  }

  public void setMaxUlRate(Double maxUlRate) {
    this.maxUlRate = maxUlRate;
  }

  public Long getTestStartedOn() {
    return testStartedOn;
  }

  public void setTestStartedOn(Long testStartedOn) {
    this.testStartedOn = testStartedOn;
  }

  public Integer getCellId() {
    return cellId;
  }

  public void setCellId(Integer cellId) {
    this.cellId = cellId;
  }

  public Integer getMcc() {
    return mcc;
  }

  public void setMcc(Integer mcc) {
    this.mcc = mcc;
  }

  public Integer getMnc() {
    return mnc;
  }

  public void setMnc(Integer mnc) {
    this.mnc = mnc;
  }

  public Integer getPci() {
    return pci;
  }

  public void setPci(Integer pci) {
    this.pci = pci;
  }

  public Integer getTac() {
    return tac;
  }

  public void setTac(Integer tac) {
    this.tac = tac;
  }

  public Integer getAvgRsrp() {
    return avgRsrp;
  }

  public void setAvgRsrp(Integer avgRsrp) {
    this.avgRsrp = avgRsrp;
  }

  public Integer getAvgRsrq() {
    return avgRsrq;
  }

  public void setAvgRsrq(Integer avgRsrq) {
    this.avgRsrq = avgRsrq;
  }

  public Integer getAvgRssi() {
    return avgRssi;
  }

  public void setAvgRssi(Integer avgRssi) {
    this.avgRssi = avgRssi;
  }

}
