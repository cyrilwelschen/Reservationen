package com.example.cyrilwelschen.reservationen;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Environment;

import java.io.File;


/**
 * Created by cyril on 09.06.18.
 *
 */

class DbDownloadManager {

    private Activity activity;

    DbDownloadManager(Activity _activity) {
        activity = _activity;
    }

    String dbTimeStampRemote() {
        return FileHelper.ReadFile("/ReservationenApp/upload_stamp.txt");
    }

    void downloadData(){
        downloadFromDropBoxUrl("https://dl.dropboxusercontent.com/s/723yk7nzlpr88vt/upload_stamp.txt");
        downloadFromDropBoxUrl("https://dl.dropboxusercontent.com/s/34h5r7nkapro2m2/version.info");
        downloadFromDropBoxUrl("https://dl.dropboxusercontent.com/s/ensu6eqe6x86q1a/gastrofull.db");
    }

    private void downloadFromDropBoxUrl(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        String[] parts = url.split("/");
        String basename = parts[parts.length-1];
        request.setDescription("Datenbank von Reservationen");
        request.setTitle(basename);
        request.allowScanningByMediaScanner();
        //todo: bug-fix when db doesn't exist already or is downloading, display is to fast and nothing is shown
        if (isFileExists("/ReservationenApp/"+basename)){
            deleteFile("/ReservationenApp/"+basename);
        }
        request.setDestinationInExternalPublicDir("ReservationenApp", basename);

        DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        assert manager != null;
        manager.enqueue(request);
    }

    private boolean isFileExists(String filename){
        File folder1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + filename);
        return folder1.exists();
    }

    private void deleteFile( String filename){
        File folder1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + filename);
        folder1.delete();
    }
}
