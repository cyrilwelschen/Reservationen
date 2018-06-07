package com.example.cyrilwelschen.reservationen;

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
    private long dayToPixelRatio;
    private long roomToPixelRatio;

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
        roomToPixelRatio = DATES_IN_DISPLAY/yScreenPixelNumber;
    }

    private void setRoomToPixelRation(){
        dayToPixelRatio = GUESSED_ROOMS_IN_DISPLAY/xScreenPixelNumber;
    }

    void displayReservations(){
    }

    private void getReservations(){
    }
}
