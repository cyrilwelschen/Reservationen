package com.example.cyrilwelschen.reservationen;

/*
 * Created by cyril on 10.06.18.
 *
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.javahelps.externalsqliteimporter.ExternalSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class DatabaseAccess {
    private ExternalSQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to avoid object creation from outside classes.
     */
    private DatabaseAccess(Context context, String sourceDirectory) {
        if (sourceDirectory == null) {
            this.openHelper = new DatabaseOpenHelper(context);
        } else {
            this.openHelper = new DatabaseOpenHelper(context, sourceDirectory);
        }
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context         the Context
     * @param sourceDirectory optional external directory
     * @return the instance of DatabaseAccess
     */
    static DatabaseAccess getInstance(Context context, String sourceDirectory) {
        if (instance == null) {
            instance = new DatabaseAccess(context, sourceDirectory);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    List<Reservation> getAllReservations() {
        List<Reservation> allRes = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM reservations", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Reservation currentRes = new Reservation(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            allRes.add(currentRes);
            cursor.moveToNext();
        }
        cursor.close();
        return allRes;
    }

    List<Reservation> getReservationsInRange() {
        List<Reservation> returnRes = new ArrayList<>();
        List<Reservation> allRes;
        allRes = getAllReservations();
        for (Reservation res : allRes) {
            Log.d("Widget DB bdAccess", "-------------- found db --------------"+Integer.toString(res.inDiff)+" "+Integer.toString(res.outDiff)+" "+res.inString+" "+res.outString+" "+res.guestName);
            if (res.outDiff > -4 && res.inDiff < 2) {
                returnRes.add(res);
            }
        }
        return returnRes;
    }
}
