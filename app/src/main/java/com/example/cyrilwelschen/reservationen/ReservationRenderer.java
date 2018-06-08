package com.example.cyrilwelschen.reservationen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyril on 04.06.18.
 *
 */

class ReservationRenderer {

    String singleDataTest() {
        return "hello";
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
        Reservation res1 = new Reservation("1", "10", "315",
                "12.06.2018", "15.06.2018" ,"Sammy");
        Reservation res3 = new Reservation("1", "10", "312",
                "11.06.2018", "16.06.2018" ,"Elmi");
        Reservation res2 = new Reservation("1", "10", "317",
                "06.06.2018", "12.06.2018" ,"Cyril");
        Reservation res4 = new Reservation("1", "10", "313",
                "08.06.2018", "11.06.2018" ,"Kinan");
        List<Reservation> reservationListInRange = new ArrayList<>();
        reservationListInRange.add(res1);
        reservationListInRange.add(res2);
        reservationListInRange.add(res3);
        reservationListInRange.add(res4);
        return reservationListInRange;
    }

}
