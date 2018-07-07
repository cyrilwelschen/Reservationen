package com.example.cyrilwelschen.reservationen;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by cyril on 04.06.18.
 *
 */

class ReservationRenderer {

    //SQLiteDatabase db = openDatabase();

    private Activity activity;

    ReservationRenderer(Activity _activity) {
        activity = _activity;
    }


    List<Reservation> renderReservationListInRange(){
        return getReservations();
    }

    private List<Reservation> getReservations() {
        Log.d("db reading", "start call of function getReservations");
        DatabaseAccess databaseAccess;
        // Check the external database file. External database must be available for the first time deployment.
        String externalDirectory = Environment.getExternalStoragePublicDirectory("ReservationenApp").getAbsolutePath();
        File dbFile = new File(externalDirectory, DatabaseOpenHelper.DATABASE_NAME);
        Log.d("db reading", "external dir path: " + dbFile.toString());
        if (!dbFile.exists()) {
            List<Reservation> returnListFail = new ArrayList<>();
            Log.d("db reading", "------- DIDN'T FIND DB ---------");
            return returnListFail;
        }
        // If external database is available, deploy it
        databaseAccess = DatabaseAccess.getInstance(activity, externalDirectory);

        databaseAccess.open();
        List<Reservation> allReservations = databaseAccess.getAllReservations();
        databaseAccess.close();

        return allReservations;
    }


}
