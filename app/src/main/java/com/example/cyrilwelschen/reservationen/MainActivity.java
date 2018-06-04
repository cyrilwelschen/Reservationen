package com.example.cyrilwelschen.reservationen;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ScrollViewListener {

    private RelativeLayout mLayout;
    private RelativeLayout roomsColumnLayout;
    private RelativeLayout datesRowLayout;
    private int eventIndex;
    private int roomIndex;
    private int dateIndex;
    private ObservableScrollView scrollView1;
    private ObservableScrollView scrollView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = findViewById(R.id.relative_layout);
        eventIndex = mLayout.getChildCount();
        roomsColumnLayout = findViewById(R.id.rooms_column);
        roomIndex = roomsColumnLayout.getChildCount();
        datesRowLayout = findViewById(R.id.dates_row);
        dateIndex = datesRowLayout.getChildCount();
        scrollView1 = findViewById(R.id.dates_scroll_view);
        scrollView1.setScrollViewListener(this);
        scrollView2 = findViewById(R.id.horizontal_scroll_view);
        scrollView2.setScrollViewListener(this);
        standardGrid(datesRowLayout, dateIndex);
        //standardGrid(mLayout, eventIndex);
        roomGrid(roomsColumnLayout, roomIndex);
        int height_all = 90;
        createReservationView(1, 110, height_all, 200*3-20, "H1");
        createReservationView(2, 110, height_all, 200*1-20, "H2");
        createReservationView(2, 310, height_all, 200*1-20, "H2.2");
        createReservationView(3, 310, height_all, 200*9-20, "H3");
        createReservationView(4, 510, height_all, 200*3-20, "H4");
        createReservationView(5, 310, height_all, 200*4-20, "H5");
        createReservationView(6, 1210, height_all, 200*6-20, "H6");
        //sv.scrollTo(600, 0);

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

    private void createReservationView(int top, int left, int height, int width, String label) {
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
            createRoomView(layout, layoutCounter, i, 0, 90, 100, String.valueOf(300+j));
        }
    }

    private void createRoomView(RelativeLayout layout, int counter, int top, int left, int height, int width, String label) {
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
        layout.addView(mEventView, counter - 1);
    }

    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if(scrollView == scrollView1) {
            scrollView2.scrollTo(x, y);
        } else if(scrollView == scrollView2) {
            scrollView1.scrollTo(x, y);
        }
    }

}
