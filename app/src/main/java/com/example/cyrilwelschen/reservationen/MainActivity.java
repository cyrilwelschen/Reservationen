package com.example.cyrilwelschen.reservationen;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.icu.util.Calendar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public DisplayManager displayManager;
    private DbDownloadManager dbManager;
    private BroadcastReceiver messageReceiver;
    private boolean receiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // window size in pixel
        final Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        // todo: change subtitle to actual version of database
        MainActivity.this.getSupportActionBar().setSubtitle("Stand: 12.06.18 00:12");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            // todo: bug-fix when permission is granted for first time would have to restart app to see something....
            // todo: check full re-install for working.
        } else {
            //Toolbar myToolbar = findViewById(R.id.my_toolbar);
            //setSupportActionBar(myToolbar);

            // Setup DisplayManager
            dbManager = new DbDownloadManager(this);
            displayManager = new DisplayManager(width, height, this, MainActivity.this);
            displayManager.deviceSetup();
            displayManager.displayReservations();
            displayManager.scrollToToday();

            dbManager.downloadData();

            messageReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                        displayManager.displayReservations();
                        displayManager.scrollToToday();
                    }
                }
            };

            this.registerReceiver(messageReceiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            receiverRegistered = true;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.registerReceiver(messageReceiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiverRegistered) {
            this.unregisterReceiver(messageReceiver);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_home:
                displayManager.scrollToToday();
                return true;

            case R.id.pick_date:
                DatePickerDialog datePickerDialog = createDialogWithoutDateField();
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Calendar datePicked = Calendar.getInstance();
                        datePicked.set(Calendar.YEAR, datePicker.getYear());
                        datePicked.set(Calendar.MONTH, datePicker.getMonth());
                        datePicked.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                        displayManager.scrollToDate(datePicked);
                    }
                });
                datePickerDialog.show();
                return true;

            case R.id.reload:
                dbManager.downloadData();
                Toast toastDownloadDb = Toast.makeText(MainActivity.this, "Downloading DB", Toast.LENGTH_SHORT);
                toastDownloadDb.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private DatePickerDialog createDialogWithoutDateField() {
        // todo: this doesn't work, make it work without attempting to display only month and year
        // todo: change color of date picker to app theme
        DatePickerDialog dpd = new DatePickerDialog(this, null, 2018, 5, 24);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            Log.e("DatePicker", ex.toString());
        }
        return dpd;
    }
}
