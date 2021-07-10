package com.yk.speedtest.models;


// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available


import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;
import com.speedchecker.android.sdk.Public.Server;

public class TestServer  {
  @SerializedName("Id")
  public Integer Id;
  @SerializedName("Domain")
  public String Domain;
  @SerializedName("Version")
  public Integer Version;

  public Integer getId() {
    return Id;
  }

  public void setId(Integer id) {
    Id = id;
  }

  public String getDomain() {
    return Domain;
  }

  public void setDomain(String domain) {
    Domain = domain;
  }

  public Integer getVersion() {
    return Version;
  }

  public void setVersion(Integer version) {
    Version = version;
  }


  public String getScheme() {
    return Scheme;
  }

  public void setScheme(String scheme) {
    Scheme = scheme;
  }

  public String getScript() {
    return Script;
  }

  public void setScript(String script) {
    Script = script;
  }

  public String getDownloadFolderPath() {
    return DownloadFolderPath;
  }

  public void setDownloadFolderPath(String downloadFolderPath) {
    DownloadFolderPath = downloadFolderPath;
  }

  public String getUploadFolderPath() {
    return UploadFolderPath;
  }

  public void setUploadFolderPath(String uploadFolderPath) {
    UploadFolderPath = uploadFolderPath;
  }

  public Boolean getCloseServer() {
    return closeServer;
  }

  public void setCloseServer(Boolean closeServer) {
    this.closeServer = closeServer;
  }

  public String getCustomDownloadURL() {
    return CustomDownloadURL;
  }

  public void setCustomDownloadURL(String customDownloadURL) {
    CustomDownloadURL = customDownloadURL;
  }

  public String getCustomUploadURL() {
    return CustomUploadURL;
  }

  public void setCustomUploadURL(String customUploadURL) {
    CustomUploadURL = customUploadURL;
  }

  public String getUserIP() {
    return UserIP;
  }

  public void setUserIP(String userIP) {
    UserIP = userIP;
  }

  public String getUserISP() {
    return UserISP;
  }

  public void setUserISP(String userISP) {
    UserISP = userISP;
  }

  @SerializedName("Location")
  @Embedded
  public Location Location;
  @SerializedName("Scheme")
  public String Scheme;
  @SerializedName("Script")
  public String Script;
  @SerializedName("DownloadFolderPath")
  public String DownloadFolderPath;
  @SerializedName("UploadFolderPath")
  public String UploadFolderPath;
  @SerializedName("closeServer")
  public Boolean closeServer;
  @SerializedName("CustomDownloadURL")
  public String CustomDownloadURL;
  @SerializedName("CustomUploadURL")
  public String CustomUploadURL;
  @SerializedName("UserIP")
  public String UserIP;
  @SerializedName("UserISP")
  public String UserISP;

}