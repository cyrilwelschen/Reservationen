package com.example.cyrilwelschen.reservationen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Handler;
import android.util.Log;
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

    private int xScreenPixelNumber;
    private int yScreenPixelNumber;
    private long dayToPixelRatio;
    private long roomToPixelRatio;

    private final int ROOM_GRID_HEIGHT = 90;
    private final int ROOM_GRID_WIDTH = 100;
    private RelativeLayout mLayout;
    private int eventIndex;
    private ObservableScrollView dateScroll;
    private ObservableScrollView resHorizontalScroll;

    private Context context;

    private ReservationRenderer resRenderer;


    /// Display constants
    private List<Integer> roomList = Arrays.asList(301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 314, 315, 316, 317, 300, 320, 330, 340, 350);
    private int TOTAL_NUMBER_OF_DAYS = 300;
    private int NUMBER_OF_DAYS_IN_PAST = 250;
    private int PIXELS_PER_DAY = 200;

    DisplayManager(int width, int height, Activity activity, Context _context) {
        context = _context;
        xScreenPixelNumber = width;
        yScreenPixelNumber = height;
        setDayToPixelRation();
        setRoomToPixelRation();

        mLayout = activity.findViewById(R.id.relative_layout);
        RelativeLayout roomsColumnLayout = activity.findViewById(R.id.rooms_column);
        RelativeLayout datesRowLayout = activity.findViewById(R.id.dates_row);

        dateScroll = activity.findViewById(R.id.dates_scroll_view);
        dateScroll.setScrollViewListener(this);
        resHorizontalScroll = activity.findViewById(R.id.horizontal_scroll_view);
        resHorizontalScroll.setScrollViewListener(this);

        eventIndex = mLayout.getChildCount();
        int roomIndex = roomsColumnLayout.getChildCount();
        int dateIndex = datesRowLayout.getChildCount();

        resRenderer = new ReservationRenderer(activity);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                resHorizontalScroll.scrollTo(PIXELS_PER_DAY*(NUMBER_OF_DAYS_IN_PAST - 1), 0);
            }
        }, 500);

        standardGrid(datesRowLayout, dateIndex);
        roomGrid(roomsColumnLayout, roomIndex);
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
        int DATES_IN_DISPLAY = 50;
        roomToPixelRatio = DATES_IN_DISPLAY /yScreenPixelNumber;
    }

    private void setRoomToPixelRation(){
        int GUESSED_ROOMS_IN_DISPLAY = 18;
        dayToPixelRatio = GUESSED_ROOMS_IN_DISPLAY /xScreenPixelNumber;
    }

    void displayReservations(){
        List<Reservation> resToDisplay = resRenderer.renderReservationListInRange();
        for (Reservation res : resToDisplay) {
            createSingleReservationView(roomToIndex(res.roomNr),
                    PIXELS_PER_DAY*res.inDiff + 110 + PIXELS_PER_DAY*(NUMBER_OF_DAYS_IN_PAST+1),
                    90,
                    PIXELS_PER_DAY*(res.outDiff- res.inDiff)-20,
                     res.guestName);
        }
    }

    private int roomToIndex(int roomNumber) {
        Log.d("ROOM NR", "input: "+ Integer.toString(roomNumber));
        Log.d("ROOM NR", "output: "+ roomList.indexOf(roomNumber));
        return roomList.indexOf(roomNumber);
    }

    private void standardGrid(RelativeLayout layout, int layout_counter){
        for (int i = 1; i<TOTAL_NUMBER_OF_DAYS; i++) {
            String color;
            if (i-NUMBER_OF_DAYS_IN_PAST == 0) {
                color = "#ababab";
            } else if (i%2 == 0) {
                color = "#dcdcdc";
            } else {
                color = "#ffffff";
            }
            drawString(layout, layout_counter, 200 * i, intDiffToDateString(i-NUMBER_OF_DAYS_IN_PAST), color);
        }
    }

    private String intDiffToDateString(int intDayDiff){
        Calendar todayCal = Calendar.getInstance();
        todayCal.add(Calendar.DATE, intDayDiff);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM.yy");
        return sdf.format(todayCal);
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
        for (int i = 0; i<roomList.size(); i++){
            createRoomView(layout, layoutCounter, i, ROOM_GRID_HEIGHT, ROOM_GRID_WIDTH, roomList.get(i).toString());
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
