package com.yk.speedtest.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.speedchecker.android.sdk.speedtest.R;
import com.yk.speedtest.constact.AppConstants;
import com.yk.speedtest.db.helper.SpeedTestHistoryDataDbHelper;
import com.yk.speedtest.models.SpeedTestHistory;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
  private static GoogleApiClient googleApiClient;

  public static void insertTestDataInDb(Context mContext, SpeedTestHistory history) {
    SpeedTestHistoryDataDbHelper.getInstance(mContext).insert(history);
    updateListDataInDb(mContext);
  }

  public static void updateListDataInDb(Context mContext) {
    SpeedTestHistoryDataDbHelper helper = SpeedTestHistoryDataDbHelper.getInstance(mContext);
    List<SpeedTestHistory> historyListOrdered = helper.getHistoryListOrdered();
    if (historyListOrdered != null && !historyListOrdered.isEmpty()) {
      ArrayList<SpeedTestHistory> updatedList = new ArrayList<>();
      if (historyListOrdered.size() > AppConstants.FIVE) {
        for (int count = AppConstants._0; count < AppConstants.FIVE; count++) {
          updatedList.add(historyListOrdered.get(count));
        }
        helper.deleteAll();
        SpeedTestHistoryDataDbHelper.getInstance(mContext).insertList(updatedList);
      }
    }
  }

  public static List<SpeedTestHistory> getSortedHistoryList(Context mContext) {
    return SpeedTestHistoryDataDbHelper.getInstance(mContext).getHistoryListOrdered();
  }

  // Check location is avaliable or not
  public static boolean checkLocationPermission(Context mContext) {
    final LocationManager manager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    if (!hasGPSDevice(mContext)) {
      String gpsNotSupported = mContext.getString(R.string.gps_not_supported);
      AppUtill.showNeumorphToast(mContext, gpsNotSupported);
      return false;
    }
    if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(mContext)) {
      return true;
    } else if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(mContext)) {
      enableLocation(mContext);
      return false;
    }
    return false;
  }

  // Check Gps is available or not in device
  private static boolean hasGPSDevice(Context context) {
    final LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    if (mgr == null) {
      return false;
    }
    final List<String> providers = mgr.getAllProviders();
    if (providers == null) {
      return false;
    }
    return providers.contains(LocationManager.GPS_PROVIDER);
  }

  // Enable location or gps
  private static void enableLocation(final Context mContext) {
    if (googleApiClient == null) {
      googleApiClient = new GoogleApiClient.Builder(mContext).addApi(LocationServices.API)
              .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                @Override
                public void onConnected(Bundle bundle) {
                }

                @Override
                public void onConnectionSuspended(int i) {
                  googleApiClient.connect();
                }
              })
              .addOnConnectionFailedListener(
                      new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                          AppLogger.d("Location error", "Location error " + connectionResult.getErrorCode());
                        }
                      })
              .build();
      googleApiClient.connect();
    }
    LocationRequest locationRequest = LocationRequest.create();
    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    locationRequest.setInterval(30000);
    locationRequest.setFastestInterval(5000);
    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
    builder.setAlwaysShow(true);

    Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(mContext)
            .checkLocationSettings(builder.build());
    result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
      @Override
      public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
        try {
          task.getResult(ApiException.class);
          // All location settings are satisfied. The client can initialize location
          // requests here.
        } catch (ApiException exception) {
          int statusCode = exception.getStatusCode();
          switch (statusCode) {
            case CommonStatusCodes.RESOLUTION_REQUIRED:
              // Location settings are not satisfied. But could be fixed by showing the
              // user a dialog.
              try {
                ResolvableApiException resolvable = (ResolvableApiException) exception;
                // Show the dialog by calling startResolutionForResult(),
                // and check the result in onActivityResult().
                resolvable.startResolutionForResult((Activity) mContext, 199);
              } catch (IntentSender.SendIntentException e) {
                AppLogger.ex(e);
              }
              break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
              // Location settings are not satisfied. However, we have no way to fix the
              // settings so we won't show the dialog.
              AppUtill.showNeumorphToast(mContext, mContext.getString(R.string.location_sett_insufficient));
              break;
            default:
              break;
          }
        }
      }
    });
  }


  public static void startSpeedTestActivity(Activity mActivity, String type) {
    try {
      if (ContextCompat.checkSelfPermission(mActivity,
              Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(mActivity,
              Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE},
                AppConstants.PERMISSION_REQUEST_CODE);
      } else {
        if (checkLocationPermission(mActivity)) {
//          Intent intent = new Intent(mActivity, StartSpeedTestActivity.class);
//          intent.putExtra(AppConstants.TYPE, type);
//          mActivity.startActivityForResult(intent, AppConstants.HUNDREAD);

        }
      }
    } catch (Exception e) {
      AppLogger.ex(e);
    }
  }

  public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, Activity mActivity, String type) {
    if (requestCode == AppConstants.PERMISSION_REQUEST_CODE) {
      checkPermission(permissions, grantResults, mActivity, type);
    }
  }

  public static void checkPermission(String[] permissions, int[] grantResults, Activity mActivity, String type) {
    for (int counter = 0; counter < permissions.length; counter++) {
      if (grantResults[counter] == PackageManager.PERMISSION_GRANTED) {
        if ((counter + 1) == permissions.length) {
          startSpeedTestActivity(mActivity, type);
        }
      } else {
        checkNeverAskConditionForPermission(permissions[counter], mActivity);
        break;
      }
    }

  }

  public static void checkNeverAskConditionForPermission(String permission, final Activity mContext) {
    if (ActivityCompat.shouldShowRequestPermissionRationale(mContext, permission)) {
      showDialogOK(permission + " " + mContext.getString(R.string.required_for_this_app_msg),
              new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                      checkAndRequestPermissions(mContext);
                      break;
                    case DialogInterface.BUTTON_NEGATIVE:
                      dialog.dismiss();
                      break;
                    default:
                      break;
                  }
                }
              }, mContext);
    } else {
      showDialogOK(mContext.getString(R.string.go_to_settings_msg), new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
              sendPermissionScreen(mContext);
              break;
            case DialogInterface.BUTTON_NEGATIVE:
              dialog.dismiss();
              break;
            default:
              break;
          }
        }
      }, mContext);
    }
  }

  public static void showDialogOK(String message, DialogInterface.OnClickListener okListener, Context mContext) {
    new AlertDialog.Builder(mContext).setMessage(message).setPositiveButton(mContext.getString(R.string.OK), okListener)
            .setNegativeButton(mContext.getString(R.string.CANCEL), okListener).create().show();
  }

  public static void sendPermissionScreen(Context mContext) {
    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", mContext.getPackageName(), null));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mContext.startActivity(intent);
  }

  public static void checkAndRequestPermissions(Activity mActivity) {
    if (ContextCompat.checkSelfPermission(mActivity,
            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(mActivity,
            Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE},
              AppConstants.PERMISSION_REQUEST_CODE);
    }
  }


  /*public static Drawable getNetwokTypeIcon(Context mContext, String type) {
    if (type != null) {
      switch (type.toLowerCase()) {
        case AppConstants.WIFI:
          return ContextCompat.getDrawable(mContext, R.drawable.ic_wifi);
        case AppConstants._2G:
          return ContextCompat.getDrawable(mContext, R.drawable.ic_2g);
        case AppConstants._3G:
          return ContextCompat.getDrawable(mContext, R.drawable.ic_3g);
        case AppConstants._4G:
          return ContextCompat.getDrawable(mContext, R.drawable.ic_4g);
        case AppConstants.LTE:
          return ContextCompat.getDrawable(mContext, R.drawable.ic_lte);
        case AppConstants._5G:
          return ContextCompat.getDrawable(mContext, R.drawable.ic_5g);
      }
    }
    return ContextCompat.getDrawable(mContext, R.drawable.ic_lte_3g_3g);
  }*/

}
