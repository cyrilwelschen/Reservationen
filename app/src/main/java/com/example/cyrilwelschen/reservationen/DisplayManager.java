package com.example.cyrilwelschen.reservationen;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.toIntExact;

/**
 * Created by cyril on 07.06.18.
 * Class to handle display of reservations: Gets screen resolution, day to pixel ration,
 * sets default grids (dateGrid and roomGrid),
 * gets Reservation instances and displays them, handles changes in view.
 */

public class DisplayManager implements ScrollViewListener{

    private int xScreenPixelNumber;
    private int yScreenPixelNumber;

    private final int ROOM_GRID_WIDTH = 100;
    private RelativeLayout mLayout;
    private int eventIndex;
    private ObservableScrollView dateScroll;
    private ObservableScrollView resHorizontalScroll;

    private Context context;
    private Activity activity;

    private ReservationRenderer resRenderer;

    private RelativeLayout roomsColumnLayout;
    private RelativeLayout datesRowLayout;

    /// Display constants
    private List<Integer> roomList = Arrays.asList(301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 314, 315, 316, 317, 300, 320, 330, 340, 350);
    private int NUMBER_OF_DAYS_IN_PAST = 400;
    private int PIXELS_PER_DAY = 100;
    private int PIXELS_PER_ROOM = 100;

    DisplayManager(int width, int height, Activity _activity, Context _context) {
        context = _context;
        activity = _activity;
        xScreenPixelNumber = width;
        yScreenPixelNumber = height;
        setDayToPixelRation();
        setRoomToPixelRation();

        mLayout = activity.findViewById(R.id.relative_layout);
        roomsColumnLayout = activity.findViewById(R.id.rooms_column);
        datesRowLayout = activity.findViewById(R.id.dates_row);

        dateScroll = activity.findViewById(R.id.dates_scroll_view);
        dateScroll.setScrollViewListener(this);
        resHorizontalScroll = activity.findViewById(R.id.horizontal_scroll_view);
        resHorizontalScroll.setScrollViewListener(this);
        eventIndex = mLayout.getChildCount();

        resRenderer = new ReservationRenderer(activity);
    }

    void deviceSetup(){
        int roomIndex = roomsColumnLayout.getChildCount();
        int dateIndex = datesRowLayout.getChildCount();

        setDayToPixelRation();
        standardGrid(datesRowLayout, dateIndex);
        roomGrid(roomsColumnLayout, roomIndex);
    }

    void scrollToToday() {
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                resHorizontalScroll.scrollTo(PIXELS_PER_DAY * (NUMBER_OF_DAYS_IN_PAST - 1), 0);
            }
        }, 500);
    }

    void scrollToDate(Calendar returnDate) {
        Calendar todayCal = Calendar.getInstance();
        Date today = todayCal.getTime();
        long diff = returnDate.getTime().getTime() - today.getTime();
        long dateDiffLong = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        resHorizontalScroll.scrollTo(PIXELS_PER_DAY*(toIntExact(dateDiffLong) + NUMBER_OF_DAYS_IN_PAST - 1), 0);
    }

    private void setDayToPixelRation(){
        int DATES_IN_DISPLAY = 7;
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            DATES_IN_DISPLAY = 21;
        }
        PIXELS_PER_DAY = (xScreenPixelNumber-ROOM_GRID_WIDTH)/DATES_IN_DISPLAY;
    }

    private void setRoomToPixelRation(){
        int GUESSED_ROOMS_IN_DISPLAY = roomList.size();
        PIXELS_PER_ROOM = (yScreenPixelNumber-290)/GUESSED_ROOMS_IN_DISPLAY;
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            PIXELS_PER_ROOM = 2*(yScreenPixelNumber-290)/GUESSED_ROOMS_IN_DISPLAY;
        }
    }

    void displayReservations(){
        List<Reservation> resToDisplay = resRenderer.renderReservationListInRange();
        for (Reservation res : resToDisplay) {
            createSingleReservationView(roomToIndex(res.roomNr),
                    PIXELS_PER_DAY*res.inDiff + PIXELS_PER_DAY/2 + 10 + PIXELS_PER_DAY*(NUMBER_OF_DAYS_IN_PAST+1),
                    PIXELS_PER_ROOM - 10,
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
        int TOTAL_NUMBER_OF_DAYS = 800;
        for (int i = 1; i< TOTAL_NUMBER_OF_DAYS; i++) {
            String color;
            int tf = Typeface.NORMAL;
            int intDayDiff = i-NUMBER_OF_DAYS_IN_PAST;
            if (isToday(intDayDiff)) {
                color = "#cd5c5c";
                tf = Typeface.BOLD_ITALIC;
            } else if (isWeekend(intDayDiff)) {
                color = "#c0c0c0";
                tf = Typeface.BOLD;
            } else if (isOddDay(intDayDiff)){
                color = "#dcdcdc";
            } else {
                color = "#f5f5f5";
            }
            // todo: adapt display of date in landscape mode towards better readability
            drawString(layout, layout_counter, PIXELS_PER_DAY * i, intDiffToDateString(i - NUMBER_OF_DAYS_IN_PAST), color, tf);
        }
    }

    private String intDiffToDateString(int intDayDiff){
        Calendar todayCal = Calendar.getInstance();
        todayCal.add(Calendar.DATE, intDayDiff);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM");
        return sdf.format(todayCal);
    }

    private boolean isToday(int intDayDiff){
        return intDayDiff == 0;
    }

    private boolean isOddDay(int intDayDiff){
        Calendar todayCal = Calendar.getInstance();
        todayCal.add(Calendar.DATE, intDayDiff);
        return todayCal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY || todayCal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY;
    }

    private boolean isWeekend(int intDayDiff){
        Calendar todayCal = Calendar.getInstance();
        todayCal.add(Calendar.DATE, intDayDiff);
        return todayCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || todayCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
    }

    private void drawString(RelativeLayout layout, int counter, int leftMargin, String label, String color, int typeface){
        TextView darkGrayView = new TextView(context);
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = 0;
        lParam.leftMargin = leftMargin;
        darkGrayView.setLayoutParams(lParam);
        darkGrayView.setWidth(PIXELS_PER_DAY);
        darkGrayView.setTypeface(null, typeface);
        darkGrayView.setTextColor(Color.parseColor("#000000"));
        darkGrayView.setText(label);
        darkGrayView.setTextSize(12);
        darkGrayView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        darkGrayView.setBackgroundColor(Color.parseColor(color));
        layout.addView(darkGrayView, counter - 1);
    }

    private void createSingleReservationView(int top, int left, int height, int width, String label) {
        TextView mEventView = new TextView(context);
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = top * PIXELS_PER_ROOM;
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
            createRoomView(layout, layoutCounter, i, PIXELS_PER_ROOM-10, ROOM_GRID_WIDTH, roomList.get(i).toString());
        }
    }

    private void createRoomView(RelativeLayout layout, int counter, int top, int height, int width, String label) {
        TextView mEventView = new TextView(context);
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = top * PIXELS_PER_ROOM;
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
