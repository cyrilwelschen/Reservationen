package com.example.cyrilwelschen.reservationen;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.toIntExact;

/**
 * Created by cyril on 06.06.18.
 *
 */

public class Reservation {

    private SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy");
    public int id;
    int inDiff;
    int outDiff;
    String guestName;
    String toString;
    int roomNr;

    Reservation(String reservationId, String roomNumber, String checkIn, String checkOut, String guest){
        roomNr = roomNumberToInteger(roomNumber);
        inDiff = stringToDateDiff(checkIn);
        outDiff = stringToDateDiff(checkOut);
        guestName = guest;
        toString = reservationId + " " + guest + " " + roomNumber + " " + checkIn + " " + checkOut + " ";
    }

    private int roomNumberToInteger(String roomNumber){
        return Integer.parseInt(roomNumber);
    }

    private int stringToDateDiff(String checkIn){
        Date returnDate = new Date();
        Calendar todayCal = Calendar.getInstance();
        todayCal.set(Calendar.HOUR_OF_DAY, 0);
        Date today = todayCal.getTime();
        try {
            returnDate = myFormat.parse(checkIn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = returnDate.getTime() - today.getTime();
        int correctForPast = 0;
        if (diff < 1) {
            correctForPast = 1;
        }
        long dateDiffLong = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return toIntExact(dateDiffLong - correctForPast);
    }

}
