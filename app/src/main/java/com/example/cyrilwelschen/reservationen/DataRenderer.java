package com.example.cyrilwelschen.reservationen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyril on 04.06.18.
 *
 */

class DataRenderer {

    String singleDataTest(){
        return "hello";
    }

    List<String> singleReservationTest(){
        List<String> reservation = new ArrayList<>();
        reservation.add("1");
        reservation.add("302");
        reservation.add("03.06.2018");
        reservation.add("05.06.2018");
        reservation.add("GuestName");
        return reservation;
    }

}
