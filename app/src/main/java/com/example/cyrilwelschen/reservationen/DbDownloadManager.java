package com.example.cyrilwelschen.reservationen;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by cyril on 09.06.18.
 *
 */

public class DbDownloadManager {

    Context context;
    Activity activity;

    DbDownloadManager(Context _context, Activity _activity) {
        context = _context;
        activity = _activity;
        checkDiskPermission();
        downloadFromDropBoxUrl();
    }

    private void checkDiskPermission ()
    {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    private void downloadFromDropBoxUrl() {

        String url = "https://dl.dropboxusercontent.com/s/0i38llqyxfylc2l/2018-01-02%2013.42.18.jpg";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Some descrition");
        request.setTitle("Downloading Reservations");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "my-map.jpg");

        DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }
}
