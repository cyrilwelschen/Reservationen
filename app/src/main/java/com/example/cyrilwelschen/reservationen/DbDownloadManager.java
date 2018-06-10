package com.example.cyrilwelschen.reservationen;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;


/**
 * Created by cyril on 09.06.18.
 *
 */

class DbDownloadManager {

    private Activity activity;

    DbDownloadManager(Activity _activity) {
        activity = _activity;
        checkDiskPermission();
    }

    void downloadData(){
        downloadFromDropBoxUrl("https://dl.dropboxusercontent.com/s/34h5r7nkapro2m2/version.info");
        downloadFromDropBoxUrl("https://dl.dropboxusercontent.com/s/sot724lwd5sf1a7/gastrofull.db");
    }

    private void checkDiskPermission ()
    {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    private void downloadFromDropBoxUrl(String url) {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        String[] parts = url.split("/");
        String basename = parts[parts.length-1];
        request.setDescription("Datenbank von Reservationen");
        request.setTitle(basename);
        request.allowScanningByMediaScanner();
        //todo: override existing db file
        //todo: check if version.info is really needed
        //todo: bug-fix when db doesn't exist already, loading is to fast and nothing is displayed
        request.setDestinationInExternalPublicDir("ReservationenApp", basename);

        DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        assert manager != null;
        manager.enqueue(request);

    }
}
