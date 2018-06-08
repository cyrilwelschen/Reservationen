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
    int resId;
    String inString;
    String outString;
    int inDiff;
    int outDiff;
    Date inDate;
    Date outDate;
    String guestName;
    int roomNr;

    Reservation(String ID, String reservationId, String roomNumber, String checkIn, String checkOut, String guest){
        id = idToInteger(ID);
        resId = resIdToInteger(reservationId);
        roomNr = roomNumberToInteger(roomNumber);
        inDiff = stringToDateDiff(checkIn);
        outDiff = stringToDateDiff(checkOut);
        inDate = stringToDate(checkIn);
        outDate = stringToDate(checkOut);
        inString = checkIn;
        outString = checkOut;
        guestName = guest;
    }

    private int idToInteger(String ID){
        return Integer.parseInt(ID);
    }

    private int resIdToInteger(String reservationID){
        return Integer.parseInt(reservationID);
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
        long dateDiffLong = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return toIntExact(dateDiffLong);
    }

    private Date stringToDate(String checkIn){
        Date returnDate = new Date();
        try {
            returnDate = myFormat.parse(checkIn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnDate;
    }
}
