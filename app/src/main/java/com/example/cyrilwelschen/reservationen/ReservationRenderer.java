package com.example.cyrilwelschen.reservationen;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by cyril on 04.06.18.
 *
 */

class ReservationRenderer {

    //SQLiteDatabase db = openDatabase();

    String singleDataTest() {
        return "hello";
    }
    Activity activity;

    ReservationRenderer(Activity _activity) {
        activity = _activity;
    }

    List<String> singleReservationTest() {
        List<String> reservation = new ArrayList<>();
        reservation.add("1");
        reservation.add("302");
        reservation.add("03.06.2018");
        reservation.add("05.06.2018");
        reservation.add("GuestName");
        return reservation;
    }

    List<Reservation> renderReservationListInRange(){
        Reservation res1 = new Reservation("10", "315",
                "12.06.2018", "15.06.2018" ,"Sammy");
        Reservation res3 = new Reservation("10", "312",
                "11.06.2018", "16.06.2018" ,"Elmi");
        Reservation res2 = new Reservation("10", "317",
                "06.06.2018", "12.06.2018" ,"Cyril");
        Reservation res4 = new Reservation("10", "313",
                "08.06.2018", "11.06.2018" ,"Kinan");
        List<Reservation> reservationListInRange = new ArrayList<>();
        reservationListInRange.add(res1);
        reservationListInRange.add(res2);
        reservationListInRange.add(res3);
        reservationListInRange.add(res4);
        List<Reservation> allReservationsFromDb = getReservations();
        //return reservationListInRange;
        return allReservationsFromDb;
    }

    List<Reservation> getReservations() {
        Log.d("db reading", "start call of function getReservations");
        DatabaseAccess databaseAccess;
        boolean fromExternalSource = true;
        if (fromExternalSource) {
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
        } else {
            // From assets
            databaseAccess = DatabaseAccess.getInstance(activity, null);
        }

        databaseAccess.open();
        List<Reservation> allReservations = databaseAccess.getAllReservations();
        databaseAccess.close();
        for (Reservation res : allReservations) {
            Log.d("db reading", res.toString);
        }

        return allReservations;
    }


}
