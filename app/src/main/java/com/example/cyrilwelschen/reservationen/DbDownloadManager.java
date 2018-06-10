package com.example.cyrilwelschen.reservationen;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;


/**
 * Created by cyril on 09.06.18.
 *
 */

class DbDownloadManager {

    private Context context;
    private Activity activity;

    DbDownloadManager(Context _context, Activity _activity) {
        context = _context;
        activity = _activity;
        checkDiskPermission();
        downloadFromDropBoxUrl("https://dl.dropboxusercontent.com/s/387m0eytkoey2ii/gastro.db");
        downloadFromDropBoxUrl("https://dl.dropboxusercontent.com/s/34h5r7nkapro2m2/version.info");
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
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, basename);

        DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }
}
