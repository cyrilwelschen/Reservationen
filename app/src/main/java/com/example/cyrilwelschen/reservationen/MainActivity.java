package com.example.cyrilwelschen.reservationen;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // window size in pixel
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        DbDownloadManager dbManager = new DbDownloadManager(MainActivity.this, this);

        // Setup DisplayManager
        DisplayManager displayManager = new DisplayManager(width, height, this, MainActivity.this);
        displayManager.deviceSetup();
        displayManager.displayReservations();
    }
}
