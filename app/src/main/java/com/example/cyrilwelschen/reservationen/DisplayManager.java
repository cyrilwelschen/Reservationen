package com.example.cyrilwelschen.reservationen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cyril on 07.06.18.
 * Class to handle display of reservations: Gets screen resolution, day to pixel ration,
 * sets default grids (dateGrid and roomGrid),
 * gets Reservation instances and displays them, handles changes in view.
 */

public class DisplayManager implements ScrollViewListener{

    private int GUESSED_ROOMS_IN_DISPLAY = 18;
    private int DATES_IN_DISPLAY = 50;
    private int xScreenPixelNumber;
    private int yScreenPixelNumber;
    private long dayToPixelRatio;
    private long roomToPixelRatio;

    final int ROOM_GRID_HEIGHT = 90;
    final int ROOM_GRID_WIDTH = 100;
    private RelativeLayout mLayout;
    private int eventIndex;
    private ObservableScrollView dateScroll;
    private ObservableScrollView resHorizontalScroll;

    private Context context;
    public Activity activity;

    private ReservationRenderer resRenderer;


    /// Display constants
    int TOTAL_NUMBER_OF_DAYS = 50;
    int NUMBER_OF_DAYS_IN_PAST = 10;
    int PIXELS_PER_DAY = 200;
    double INTER_DAY_PIXEL_OFFSET_PERCENTAGE = 0.05;

    DisplayManager(int width, int height, Activity _activity, Context _context) {
        this.activity = _activity;
        context = _context;
        xScreenPixelNumber = width;
        yScreenPixelNumber = height;
        setDayToPixelRation();
        setRoomToPixelRation();

        mLayout = this.activity.findViewById(R.id.relative_layout);
        RelativeLayout roomsColumnLayout = this.activity.findViewById(R.id.rooms_column);
        RelativeLayout datesRowLayout = this.activity.findViewById(R.id.dates_row);

        dateScroll = this.activity.findViewById(R.id.dates_scroll_view);
        dateScroll.setScrollViewListener(this);
        resHorizontalScroll = this.activity.findViewById(R.id.horizontal_scroll_view);
        resHorizontalScroll.setScrollViewListener(this);

        eventIndex = mLayout.getChildCount();
        int roomIndex = roomsColumnLayout.getChildCount();
        int dateIndex = datesRowLayout.getChildCount();

        resRenderer = new ReservationRenderer();

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                resHorizontalScroll.scrollTo(PIXELS_PER_DAY*(NUMBER_OF_DAYS_IN_PAST - 1), 0);
            }
        }, 500);

        standardGrid(datesRowLayout, dateIndex);
        roomGrid(roomsColumnLayout, roomIndex);
        createReservationView();
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
        List<Reservation> resToDisplay = resRenderer.renderReservationListInRange();
        for (Reservation res : resToDisplay) {
            createSingleReservationView(roomToIndex(res.roomNr),
                    200*res.inDiff + 110 + PIXELS_PER_DAY*NUMBER_OF_DAYS_IN_PAST,
                    90,
                    200*(res.outDiff- res.inDiff)-20,
                     res.guestName);
        }
    }

    private int roomToIndex(int roomNumber) {
        List<Integer> roomList = Arrays.asList(301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319);
        return roomList.indexOf(roomNumber);
    }

    private void getReservations(){
    }

    private void standardGrid(RelativeLayout layout, int layout_counter){
        for (int i = 0; i<50; i++) {
            String color;
            if (i-NUMBER_OF_DAYS_IN_PAST == 0) {
                color = "#ababab";
            } else if (i%2 == 0) {
                color = "#dcdcdc";
            } else {
                color = "#ffffff";
            }
            drawString(layout, layout_counter, 200 * i, Integer.toString(i-NUMBER_OF_DAYS_IN_PAST), color);
        }
    }

    private void drawString(RelativeLayout layout, int counter, int leftMargin, String label, String color){
        TextView darkGrayView = new TextView(context);
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = 0;
        lParam.leftMargin = leftMargin;
        darkGrayView.setLayoutParams(lParam);
        darkGrayView.setWidth(200);
        darkGrayView.setTextColor(Color.parseColor("#000000"));
        darkGrayView.setText(label);
        darkGrayView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        darkGrayView.setBackgroundColor(Color.parseColor(color));
        layout.addView(darkGrayView, counter - 1);
    }

    private void createReservationView() {
        int height_all = 90;
        ReservationRenderer dataTest = new ReservationRenderer();
        String labelOne = dataTest.singleDataTest();
        List<String> reservation = dataTest.singleReservationTest();
        Reservation resTest = new Reservation("1", "2", "303","12.06.2018", "15.06.2018", "Cyril");
        createSingleReservationView(1, 110, height_all, 200*3-20, labelOne);
        createSingleReservationView(2, 110, height_all, 200-20, resTest.inString);
        createSingleReservationView(2, 310, height_all, 200-20, Integer.toString(resTest.inDiff));
        createSingleReservationView(3, 310, height_all, 200*9-20, reservation.get(1));
        createSingleReservationView(4, 510, height_all, 200*3-20, "H4");
        createSingleReservationView(5, 310, height_all, 200*4-20, "H5");
        createSingleReservationView(6, 1210, height_all, 200*6-20, "H6");
    }

    private void createSingleReservationView(int top, int left, int height, int width, String label) {
        TextView mEventView = new TextView(context);
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = top * 100;
        lParam.leftMargin = left;
        mEventView.setLayoutParams(lParam);
        mEventView.setPadding(0, 0, 0, 0);
        mEventView.setHeight(height);
        mEventView.setWidth(width);
        mEventView.setGravity(0x11);
        mEventView.setTextColor(Color.parseColor("#ffffff"));
        mEventView.setText(label);
        mEventView.setBackgroundColor(Color.parseColor("#3F51B5"));
        mLayout.addView(mEventView, eventIndex - 1);
    }

    private void roomGrid(RelativeLayout layout, int layoutCounter){
        for (int i = 0; i<21; i++){
            int j = i+1;
            createRoomView(layout, layoutCounter, i, ROOM_GRID_HEIGHT, ROOM_GRID_WIDTH, String.valueOf(300+j));
        }
    }

    private void createRoomView(RelativeLayout layout, int counter, int top, int height, int width, String label) {
        TextView mEventView = new TextView(context);
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = top * 100;
        lParam.leftMargin = 0;
        mEventView.setLayoutParams(lParam);
        mEventView.setPadding(0, 0, 0, 0);
        mEventView.setHeight(height);
        mEventView.setWidth(width);
        mEventView.setGravity(0x11);
        mEventView.setTextColor(Color.parseColor("#ffffff"));
        mEventView.setText(label);
        mEventView.setBackgroundColor(Color.parseColor("#3F51B5"));
        layout.addView(mEventView, counter - 1);
    }

    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldX, int oldY) {
        if(scrollView == dateScroll) {
            resHorizontalScroll.scrollTo(x, y);
        } else if(scrollView == resHorizontalScroll) {
            dateScroll.scrollTo(x, y);
        }
    }
}
