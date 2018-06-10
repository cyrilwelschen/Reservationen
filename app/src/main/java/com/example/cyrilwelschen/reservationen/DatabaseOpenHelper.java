package com.example.cyrilwelschen.reservationen;

/*
 * Created by cyril on 10.06.18.
 *
 */

import android.content.Context;

import com.javahelps.externalsqliteimporter.ExternalSQLiteOpenHelper;

class DatabaseOpenHelper extends ExternalSQLiteOpenHelper {
    /**
     * Name of the database.
     */
    static final String DATABASE_NAME = "gastrofull.db";

    /**
     * Version of the database. Only used to import from assets.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Use this constructor if you want to import database from assets/database directory.
     */
    DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Use this constructor if you want to import database from external directory.
     */
    DatabaseOpenHelper(Context context, String sourceDirectory) {
        super(context, DATABASE_NAME, sourceDirectory, null);
    }
}
