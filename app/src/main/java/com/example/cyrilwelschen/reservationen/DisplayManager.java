package com.example.cyrilwelschen.reservationen;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by cyril on 07.06.18.
 * Class to handle display of reservations: Gets screen resolution, day to pixel ration,
 * sets default grids (dateGrid and roomGrid),
 * gets Reservation instances and displays them, handles changes in view.
 */

public class DisplayManager {

    private int GUESSED_ROOMS_IN_DISPLAY = 18;
    private int DATES_IN_DISPLAY = 50;
    private int xScreenPixelNumber;
    private int yScreenPixelNumber;
    private long dayToPixelRation;
    private long roomToPixelRation;

    DisplayManager(int width, int height) {
        xScreenPixelNumber = width;
        yScreenPixelNumber = height;
        setDayToPixelRation();
        setRoomToPixelRation();
    }

    void deviceSetup(){
        displayDateGrid();
        displayRoomGrid();
    }

    private void displayDateGrid(){
    }

    private void displayRoomGrid() {
    }

    private void setDayToPixelRation(){
        roomToPixelRation = DATES_IN_DISPLAY/yScreenPixelNumber;
    }

    private void setRoomToPixelRation(){
        dayToPixelRation = GUESSED_ROOMS_IN_DISPLAY/xScreenPixelNumber;
    }

    void displayReservations(){
    }

    private void getReservations(){
    }
}
