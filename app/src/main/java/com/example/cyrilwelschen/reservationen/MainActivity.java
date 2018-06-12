package com.example.cyrilwelschen.reservationen;

import android.app.DatePickerDialog;
import android.graphics.Point;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private  DisplayManager displayManager;
    private DbDownloadManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar myToolbar = findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

        // window size in pixel
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        // todo: change subtitle to actual version of database
        MainActivity.this.getSupportActionBar().setSubtitle("Stand: 12.06.18 00:12");

        // Setup DisplayManager
        displayManager = new DisplayManager(width, height, this, MainActivity.this);
        displayManager.deviceSetup();
        displayManager.displayReservations();
        displayManager.scrollToToday();

        dbManager = new DbDownloadManager(this);
        dbManager.downloadData();
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
