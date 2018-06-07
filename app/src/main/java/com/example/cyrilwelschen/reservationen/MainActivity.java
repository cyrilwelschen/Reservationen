package com.example.cyrilwelschen.reservationen;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ScrollViewListener {

    final int ROOM_GRID_HEIGHT = 90;
    final int ROOM_GRID_WIDTH = 100;
    private RelativeLayout mLayout;
    private int eventIndex;
    private ObservableScrollView scrollView1;
    private ObservableScrollView scrollView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = findViewById(R.id.relative_layout);
        eventIndex = mLayout.getChildCount();
        RelativeLayout roomsColumnLayout = findViewById(R.id.rooms_column);
        int roomIndex = roomsColumnLayout.getChildCount();
        RelativeLayout datesRowLayout = findViewById(R.id.dates_row);
        int dateIndex = datesRowLayout.getChildCount();
        scrollView1 = findViewById(R.id.dates_scroll_view);
        scrollView1.setScrollViewListener(this);
        scrollView2 = findViewById(R.id.horizontal_scroll_view);
        scrollView2.setScrollViewListener(this);
        standardGrid(datesRowLayout, dateIndex);
        //standardGrid(mLayout, eventIndex);
        roomGrid(roomsColumnLayout, roomIndex);
        //sv.scrollTo(600, 0);
        createReservationView();

        // window size in pixel
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        DisplayManager displayManager = new DisplayManager(width, height);
        displayManager.deviceSetup();
        displayManager.displayReservations();

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView2.scrollTo(800, 0);
            }
        }, 500);
    }

    private void standardGrid(RelativeLayout layout, int layout_counter){
        for (int i = 0; i<15; i++) {
            if (i%2 == 0) {
                drawString(layout, layout_counter, 200 * i, "0"+i+".06", "#dcdcdc");
            } else {
                drawString(layout, layout_counter, 200 * i, "0"+i+".06", "#ffffff");
            }
        }
    }

    private void drawString(RelativeLayout layout, int counter, int leftMargin, String label, String color){
        TextView darkGrayView = new TextView(MainActivity.this);
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
        //mLayout.addView(darkGrayView, eventIndex - 1);
        layout.addView(darkGrayView, counter - 1);
    }

    private void createReservationView() {
        int height_all = 90;
        ReservationRenderer dataTest = new ReservationRenderer();
        String labelOne = dataTest.singleDataTest();
        List<String> reservation = dataTest.singleReservationTest();
        Reservation resTest = new Reservation("1", "2", "12.06.2018", "15.06.2018", "Cyril");
        createSingleReservationView(1, 110, height_all, 200*3-20, labelOne);
        createSingleReservationView(2, 110, height_all, 200-20, resTest.inString);
        createSingleReservationView(2, 310, height_all, 200-20, Integer.toString(resTest.inDiff));
        createSingleReservationView(3, 310, height_all, 200*9-20, reservation.get(1));
        createSingleReservationView(4, 510, height_all, 200*3-20, "H4");
        createSingleReservationView(5, 310, height_all, 200*4-20, "H5");
        createSingleReservationView(6, 1210, height_all, 200*6-20, "H6");
    }

    private void createSingleReservationView(int top, int left, int height, int width, String label) {
        TextView mEventView = new TextView(MainActivity.this);
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
        for (int i = 0; i<17; i++){
            int j = i+1;
            createRoomView(layout, layoutCounter, i, ROOM_GRID_HEIGHT, ROOM_GRID_WIDTH, String.valueOf(300+j));
        }
    }

    private void createRoomView(RelativeLayout layout, int counter, int top, int height, int width, String label) {
        TextView mEventView = new TextView(MainActivity.this);
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
        if(scrollView == scrollView1) {
            scrollView2.scrollTo(x, y);
        } else if(scrollView == scrollView2) {
            scrollView1.scrollTo(x, y);
        }
    }

}
