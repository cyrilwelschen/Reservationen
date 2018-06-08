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
        Reservation res1 = new Reservation("1", "10", "305",
                "12.06.2018", "15.06.2018" ,"Sammy");
        Reservation res2 = new Reservation("1", "10", "307",
                "02.06.2018", "10.06.2018" ,"Cyril");
        List<Reservation> reservationListInRange = new ArrayList<>();
        reservationListInRange.add(res1);
        reservationListInRange.add(res2);
        return reservationListInRange;
    }

}
