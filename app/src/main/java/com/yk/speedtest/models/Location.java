package com.yk.speedtest.models;

import com.google.gson.annotations.SerializedName;

public class Location {
  @SerializedName("City")
  public String City;
  @SerializedName("ContinentCode")
  public String ContinentCode;
  @SerializedName("Country")
  public String Country;
  @SerializedName("CountryCode")
  public String CountryCode;
  @SerializedName("PostCode")
  private String PostCode;
  @SerializedName("IPAddress")
  private String IPAddress;
  @SerializedName("Latitude")
  private Double ServerLatitude;

  public String getCity() {
    return City;
  }

  public void setCity(String city) {
    City = city;
  }

  public String getContinentCode() {
    return ContinentCode;
  }

  public void setContinentCode(String continentCode) {
    ContinentCode = continentCode;
  }

  public String getCountry() {
    return Country;
  }

  public void setCountry(String country) {
    Country = country;
  }

  public String getCountryCode() {
    return CountryCode;
  }

  public void setCountryCode(String countryCode) {
    CountryCode = countryCode;
  }

  public String getPostCode() {
    return PostCode;
  }

  public void setPostCode(String postCode) {
    PostCode = postCode;
  }

  public String getIPAddress() {
    return IPAddress;
  }

  public void setIPAddress(String IPAddress) {
    this.IPAddress = IPAddress;
  }

  public Double getServerLatitude() {
    return ServerLatitude;
  }

  public void setServerLatitude(Double serverLatitude) {
    ServerLatitude = serverLatitude;
  }

  public Double getServerLongitude() {
    return ServerLongitude;
  }

  public void setServerLongitude(Double serverLongitude) {
    ServerLongitude = serverLongitude;
  }

  public Double getServerAccuracy() {
    return ServerAccuracy;
  }

  public void setServerAccuracy(Double serverAccuracy) {
    ServerAccuracy = serverAccuracy;
  }

  @SerializedName("Longitude")
  private Double ServerLongitude;
  @SerializedName("Accuracy")
  private Double ServerAccuracy;

}