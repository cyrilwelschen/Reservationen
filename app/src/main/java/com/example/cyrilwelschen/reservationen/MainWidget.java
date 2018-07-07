package com.example.cyrilwelschen.reservationen;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by cyril on 28.06.18.
 * Implementation of App Widget functionality.
 */

public class MainWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private String intDiffToDateString(int intDayDiff){
        Calendar todayCal = Calendar.getInstance();
        todayCal.add(Calendar.DATE, intDayDiff);
        SimpleDateFormat sdf1 = new SimpleDateFormat("ccc dd.MMM");
        return sdf1.format(todayCal);
    }

    void setDates(RemoteViews views){
        views.setTextViewText(R.id.date_row1, intDiffToDateString(-2));
        views.setTextViewText(R.id.date_row2, intDiffToDateString(-1));
        views.setTextViewText(R.id.date_row3, intDiffToDateString(0));
        views.setTextViewText(R.id.date_row4, intDiffToDateString(1));
        views.setTextViewText(R.id.date_row5, intDiffToDateString(2));

    }

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_main);

        // todo: update widget when clicking sync statement
        /*
        Intent intentSync = new Intent(context, MainWidget.class);
        intentSync.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE); //You need to specify the action for the intent. Right now that intent is doing nothing for there is no action to be broadcasted.
        PendingIntent pendingSync = PendingIntent.getBroadcast(context,0, intentSync, PendingIntent.FLAG_UPDATE_CURRENT); //You need to specify a proper flag for the intent. Or else the intent will become deleted.
        views.setOnClickPendingIntent(R.id.syncTextView, pendingSync);
        */


        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.relative_layout, pendingIntent);

        setDates(views);
        views.setTextViewText(R.id.syncTextView, "Sync: "+FileHelper.ReadFile("/ReservationenApp/upload_stamp.txt"));
        drawView(context, views);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    void drawView(Context context, RemoteViews views) {

        List<Reservation> reservations = renderReservationListInRange(context);
        String name;
        for (Reservation res : reservations) {
            name = res.guestName;

            // insert here
            if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30101, View.VISIBLE);
                views.setTextViewText(R.id.r30101, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30201, View.VISIBLE);
                views.setTextViewText(R.id.r30201, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30301, View.VISIBLE);
                views.setTextViewText(R.id.r30301, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30401, View.VISIBLE);
                views.setTextViewText(R.id.r30401, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30501, View.VISIBLE);
                views.setTextViewText(R.id.r30501, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30701, View.VISIBLE);
                views.setTextViewText(R.id.r30701, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30801, View.VISIBLE);
                views.setTextViewText(R.id.r30801, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30901, View.VISIBLE);
                views.setTextViewText(R.id.r30901, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31001, View.VISIBLE);
                views.setTextViewText(R.id.r31001, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31101, View.VISIBLE);
                views.setTextViewText(R.id.r31101, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31401, View.VISIBLE);
                views.setTextViewText(R.id.r31401, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31501, View.VISIBLE);
                views.setTextViewText(R.id.r31501, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31601, View.VISIBLE);
                views.setTextViewText(R.id.r31601, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31701, View.VISIBLE);
                views.setTextViewText(R.id.r31701, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30001, View.VISIBLE);
                views.setTextViewText(R.id.r30001, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32001, View.VISIBLE);
                views.setTextViewText(R.id.r32001, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33001, View.VISIBLE);
                views.setTextViewText(R.id.r33001, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34001, View.VISIBLE);
                views.setTextViewText(R.id.r34001, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -3) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35001, View.VISIBLE);
                views.setTextViewText(R.id.r35001, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30102, View.VISIBLE);
                views.setTextViewText(R.id.r30102, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30202, View.VISIBLE);
                views.setTextViewText(R.id.r30202, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30302, View.VISIBLE);
                views.setTextViewText(R.id.r30302, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30402, View.VISIBLE);
                views.setTextViewText(R.id.r30402, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30502, View.VISIBLE);
                views.setTextViewText(R.id.r30502, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30702, View.VISIBLE);
                views.setTextViewText(R.id.r30702, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30802, View.VISIBLE);
                views.setTextViewText(R.id.r30802, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30902, View.VISIBLE);
                views.setTextViewText(R.id.r30902, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31002, View.VISIBLE);
                views.setTextViewText(R.id.r31002, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31102, View.VISIBLE);
                views.setTextViewText(R.id.r31102, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31402, View.VISIBLE);
                views.setTextViewText(R.id.r31402, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31502, View.VISIBLE);
                views.setTextViewText(R.id.r31502, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31602, View.VISIBLE);
                views.setTextViewText(R.id.r31602, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31702, View.VISIBLE);
                views.setTextViewText(R.id.r31702, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30002, View.VISIBLE);
                views.setTextViewText(R.id.r30002, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32002, View.VISIBLE);
                views.setTextViewText(R.id.r32002, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33002, View.VISIBLE);
                views.setTextViewText(R.id.r33002, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34002, View.VISIBLE);
                views.setTextViewText(R.id.r34002, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -2) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35002, View.VISIBLE);
                views.setTextViewText(R.id.r35002, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30103, View.VISIBLE);
                views.setTextViewText(R.id.r30103, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30203, View.VISIBLE);
                views.setTextViewText(R.id.r30203, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30303, View.VISIBLE);
                views.setTextViewText(R.id.r30303, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30403, View.VISIBLE);
                views.setTextViewText(R.id.r30403, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30503, View.VISIBLE);
                views.setTextViewText(R.id.r30503, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30703, View.VISIBLE);
                views.setTextViewText(R.id.r30703, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30803, View.VISIBLE);
                views.setTextViewText(R.id.r30803, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30903, View.VISIBLE);
                views.setTextViewText(R.id.r30903, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31003, View.VISIBLE);
                views.setTextViewText(R.id.r31003, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31103, View.VISIBLE);
                views.setTextViewText(R.id.r31103, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31403, View.VISIBLE);
                views.setTextViewText(R.id.r31403, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31503, View.VISIBLE);
                views.setTextViewText(R.id.r31503, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31603, View.VISIBLE);
                views.setTextViewText(R.id.r31603, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31703, View.VISIBLE);
                views.setTextViewText(R.id.r31703, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30003, View.VISIBLE);
                views.setTextViewText(R.id.r30003, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32003, View.VISIBLE);
                views.setTextViewText(R.id.r32003, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33003, View.VISIBLE);
                views.setTextViewText(R.id.r33003, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34003, View.VISIBLE);
                views.setTextViewText(R.id.r34003, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == -1) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35003, View.VISIBLE);
                views.setTextViewText(R.id.r35003, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30104, View.VISIBLE);
                views.setTextViewText(R.id.r30104, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30204, View.VISIBLE);
                views.setTextViewText(R.id.r30204, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30304, View.VISIBLE);
                views.setTextViewText(R.id.r30304, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30404, View.VISIBLE);
                views.setTextViewText(R.id.r30404, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30504, View.VISIBLE);
                views.setTextViewText(R.id.r30504, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30704, View.VISIBLE);
                views.setTextViewText(R.id.r30704, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30804, View.VISIBLE);
                views.setTextViewText(R.id.r30804, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30904, View.VISIBLE);
                views.setTextViewText(R.id.r30904, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31004, View.VISIBLE);
                views.setTextViewText(R.id.r31004, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31104, View.VISIBLE);
                views.setTextViewText(R.id.r31104, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31404, View.VISIBLE);
                views.setTextViewText(R.id.r31404, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31504, View.VISIBLE);
                views.setTextViewText(R.id.r31504, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31604, View.VISIBLE);
                views.setTextViewText(R.id.r31604, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31704, View.VISIBLE);
                views.setTextViewText(R.id.r31704, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30004, View.VISIBLE);
                views.setTextViewText(R.id.r30004, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32004, View.VISIBLE);
                views.setTextViewText(R.id.r32004, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33004, View.VISIBLE);
                views.setTextViewText(R.id.r33004, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34004, View.VISIBLE);
                views.setTextViewText(R.id.r34004, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 0) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35004, View.VISIBLE);
                views.setTextViewText(R.id.r35004, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30105, View.VISIBLE);
                views.setTextViewText(R.id.r30105, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30205, View.VISIBLE);
                views.setTextViewText(R.id.r30205, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30305, View.VISIBLE);
                views.setTextViewText(R.id.r30305, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30405, View.VISIBLE);
                views.setTextViewText(R.id.r30405, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30505, View.VISIBLE);
                views.setTextViewText(R.id.r30505, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30705, View.VISIBLE);
                views.setTextViewText(R.id.r30705, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30805, View.VISIBLE);
                views.setTextViewText(R.id.r30805, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30905, View.VISIBLE);
                views.setTextViewText(R.id.r30905, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31005, View.VISIBLE);
                views.setTextViewText(R.id.r31005, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31105, View.VISIBLE);
                views.setTextViewText(R.id.r31105, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31405, View.VISIBLE);
                views.setTextViewText(R.id.r31405, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31505, View.VISIBLE);
                views.setTextViewText(R.id.r31505, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31605, View.VISIBLE);
                views.setTextViewText(R.id.r31605, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31705, View.VISIBLE);
                views.setTextViewText(R.id.r31705, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30005, View.VISIBLE);
                views.setTextViewText(R.id.r30005, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32005, View.VISIBLE);
                views.setTextViewText(R.id.r32005, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33005, View.VISIBLE);
                views.setTextViewText(R.id.r33005, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34005, View.VISIBLE);
                views.setTextViewText(R.id.r34005, name);
            }
}
if (res.inDiff <= -4 && res.outDiff == 1) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35005, View.VISIBLE);
                views.setTextViewText(R.id.r35005, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30106, View.VISIBLE);
                views.setTextViewText(R.id.r30106, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30206, View.VISIBLE);
                views.setTextViewText(R.id.r30206, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30306, View.VISIBLE);
                views.setTextViewText(R.id.r30306, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30406, View.VISIBLE);
                views.setTextViewText(R.id.r30406, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30506, View.VISIBLE);
                views.setTextViewText(R.id.r30506, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30706, View.VISIBLE);
                views.setTextViewText(R.id.r30706, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30806, View.VISIBLE);
                views.setTextViewText(R.id.r30806, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30906, View.VISIBLE);
                views.setTextViewText(R.id.r30906, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31006, View.VISIBLE);
                views.setTextViewText(R.id.r31006, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31106, View.VISIBLE);
                views.setTextViewText(R.id.r31106, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31406, View.VISIBLE);
                views.setTextViewText(R.id.r31406, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31506, View.VISIBLE);
                views.setTextViewText(R.id.r31506, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31606, View.VISIBLE);
                views.setTextViewText(R.id.r31606, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31706, View.VISIBLE);
                views.setTextViewText(R.id.r31706, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30006, View.VISIBLE);
                views.setTextViewText(R.id.r30006, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32006, View.VISIBLE);
                views.setTextViewText(R.id.r32006, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33006, View.VISIBLE);
                views.setTextViewText(R.id.r33006, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34006, View.VISIBLE);
                views.setTextViewText(R.id.r34006, name);
            }
}
if (res.inDiff <= -4 && res.outDiff >= 2) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35006, View.VISIBLE);
                views.setTextViewText(R.id.r35006, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30112, View.VISIBLE);
                views.setTextViewText(R.id.r30112, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30212, View.VISIBLE);
                views.setTextViewText(R.id.r30212, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30312, View.VISIBLE);
                views.setTextViewText(R.id.r30312, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30412, View.VISIBLE);
                views.setTextViewText(R.id.r30412, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30512, View.VISIBLE);
                views.setTextViewText(R.id.r30512, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30712, View.VISIBLE);
                views.setTextViewText(R.id.r30712, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30812, View.VISIBLE);
                views.setTextViewText(R.id.r30812, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30912, View.VISIBLE);
                views.setTextViewText(R.id.r30912, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31012, View.VISIBLE);
                views.setTextViewText(R.id.r31012, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31112, View.VISIBLE);
                views.setTextViewText(R.id.r31112, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31412, View.VISIBLE);
                views.setTextViewText(R.id.r31412, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31512, View.VISIBLE);
                views.setTextViewText(R.id.r31512, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31612, View.VISIBLE);
                views.setTextViewText(R.id.r31612, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31712, View.VISIBLE);
                views.setTextViewText(R.id.r31712, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30012, View.VISIBLE);
                views.setTextViewText(R.id.r30012, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32012, View.VISIBLE);
                views.setTextViewText(R.id.r32012, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33012, View.VISIBLE);
                views.setTextViewText(R.id.r33012, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34012, View.VISIBLE);
                views.setTextViewText(R.id.r34012, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -2) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35012, View.VISIBLE);
                views.setTextViewText(R.id.r35012, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30113, View.VISIBLE);
                views.setTextViewText(R.id.r30113, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30213, View.VISIBLE);
                views.setTextViewText(R.id.r30213, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30313, View.VISIBLE);
                views.setTextViewText(R.id.r30313, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30413, View.VISIBLE);
                views.setTextViewText(R.id.r30413, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30513, View.VISIBLE);
                views.setTextViewText(R.id.r30513, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30713, View.VISIBLE);
                views.setTextViewText(R.id.r30713, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30813, View.VISIBLE);
                views.setTextViewText(R.id.r30813, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30913, View.VISIBLE);
                views.setTextViewText(R.id.r30913, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31013, View.VISIBLE);
                views.setTextViewText(R.id.r31013, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31113, View.VISIBLE);
                views.setTextViewText(R.id.r31113, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31413, View.VISIBLE);
                views.setTextViewText(R.id.r31413, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31513, View.VISIBLE);
                views.setTextViewText(R.id.r31513, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31613, View.VISIBLE);
                views.setTextViewText(R.id.r31613, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31713, View.VISIBLE);
                views.setTextViewText(R.id.r31713, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30013, View.VISIBLE);
                views.setTextViewText(R.id.r30013, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32013, View.VISIBLE);
                views.setTextViewText(R.id.r32013, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33013, View.VISIBLE);
                views.setTextViewText(R.id.r33013, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34013, View.VISIBLE);
                views.setTextViewText(R.id.r34013, name);
            }
}
if (res.inDiff == -3 && res.outDiff == -1) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35013, View.VISIBLE);
                views.setTextViewText(R.id.r35013, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30114, View.VISIBLE);
                views.setTextViewText(R.id.r30114, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30214, View.VISIBLE);
                views.setTextViewText(R.id.r30214, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30314, View.VISIBLE);
                views.setTextViewText(R.id.r30314, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30414, View.VISIBLE);
                views.setTextViewText(R.id.r30414, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30514, View.VISIBLE);
                views.setTextViewText(R.id.r30514, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30714, View.VISIBLE);
                views.setTextViewText(R.id.r30714, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30814, View.VISIBLE);
                views.setTextViewText(R.id.r30814, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30914, View.VISIBLE);
                views.setTextViewText(R.id.r30914, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31014, View.VISIBLE);
                views.setTextViewText(R.id.r31014, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31114, View.VISIBLE);
                views.setTextViewText(R.id.r31114, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31414, View.VISIBLE);
                views.setTextViewText(R.id.r31414, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31514, View.VISIBLE);
                views.setTextViewText(R.id.r31514, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31614, View.VISIBLE);
                views.setTextViewText(R.id.r31614, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31714, View.VISIBLE);
                views.setTextViewText(R.id.r31714, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30014, View.VISIBLE);
                views.setTextViewText(R.id.r30014, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32014, View.VISIBLE);
                views.setTextViewText(R.id.r32014, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33014, View.VISIBLE);
                views.setTextViewText(R.id.r33014, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34014, View.VISIBLE);
                views.setTextViewText(R.id.r34014, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 0) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35014, View.VISIBLE);
                views.setTextViewText(R.id.r35014, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30115, View.VISIBLE);
                views.setTextViewText(R.id.r30115, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30215, View.VISIBLE);
                views.setTextViewText(R.id.r30215, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30315, View.VISIBLE);
                views.setTextViewText(R.id.r30315, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30415, View.VISIBLE);
                views.setTextViewText(R.id.r30415, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30515, View.VISIBLE);
                views.setTextViewText(R.id.r30515, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30715, View.VISIBLE);
                views.setTextViewText(R.id.r30715, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30815, View.VISIBLE);
                views.setTextViewText(R.id.r30815, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30915, View.VISIBLE);
                views.setTextViewText(R.id.r30915, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31015, View.VISIBLE);
                views.setTextViewText(R.id.r31015, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31115, View.VISIBLE);
                views.setTextViewText(R.id.r31115, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31415, View.VISIBLE);
                views.setTextViewText(R.id.r31415, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31515, View.VISIBLE);
                views.setTextViewText(R.id.r31515, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31615, View.VISIBLE);
                views.setTextViewText(R.id.r31615, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31715, View.VISIBLE);
                views.setTextViewText(R.id.r31715, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30015, View.VISIBLE);
                views.setTextViewText(R.id.r30015, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32015, View.VISIBLE);
                views.setTextViewText(R.id.r32015, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33015, View.VISIBLE);
                views.setTextViewText(R.id.r33015, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34015, View.VISIBLE);
                views.setTextViewText(R.id.r34015, name);
            }
}
if (res.inDiff == -3 && res.outDiff == 1) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35015, View.VISIBLE);
                views.setTextViewText(R.id.r35015, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30116, View.VISIBLE);
                views.setTextViewText(R.id.r30116, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30216, View.VISIBLE);
                views.setTextViewText(R.id.r30216, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30316, View.VISIBLE);
                views.setTextViewText(R.id.r30316, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30416, View.VISIBLE);
                views.setTextViewText(R.id.r30416, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30516, View.VISIBLE);
                views.setTextViewText(R.id.r30516, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30716, View.VISIBLE);
                views.setTextViewText(R.id.r30716, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30816, View.VISIBLE);
                views.setTextViewText(R.id.r30816, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30916, View.VISIBLE);
                views.setTextViewText(R.id.r30916, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31016, View.VISIBLE);
                views.setTextViewText(R.id.r31016, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31116, View.VISIBLE);
                views.setTextViewText(R.id.r31116, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31416, View.VISIBLE);
                views.setTextViewText(R.id.r31416, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31516, View.VISIBLE);
                views.setTextViewText(R.id.r31516, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31616, View.VISIBLE);
                views.setTextViewText(R.id.r31616, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31716, View.VISIBLE);
                views.setTextViewText(R.id.r31716, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30016, View.VISIBLE);
                views.setTextViewText(R.id.r30016, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32016, View.VISIBLE);
                views.setTextViewText(R.id.r32016, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33016, View.VISIBLE);
                views.setTextViewText(R.id.r33016, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34016, View.VISIBLE);
                views.setTextViewText(R.id.r34016, name);
            }
}
if (res.inDiff == -3 && res.outDiff >= 2) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35016, View.VISIBLE);
                views.setTextViewText(R.id.r35016, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30123, View.VISIBLE);
                views.setTextViewText(R.id.r30123, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30223, View.VISIBLE);
                views.setTextViewText(R.id.r30223, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30323, View.VISIBLE);
                views.setTextViewText(R.id.r30323, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30423, View.VISIBLE);
                views.setTextViewText(R.id.r30423, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30523, View.VISIBLE);
                views.setTextViewText(R.id.r30523, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30723, View.VISIBLE);
                views.setTextViewText(R.id.r30723, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30823, View.VISIBLE);
                views.setTextViewText(R.id.r30823, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30923, View.VISIBLE);
                views.setTextViewText(R.id.r30923, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31023, View.VISIBLE);
                views.setTextViewText(R.id.r31023, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31123, View.VISIBLE);
                views.setTextViewText(R.id.r31123, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31423, View.VISIBLE);
                views.setTextViewText(R.id.r31423, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31523, View.VISIBLE);
                views.setTextViewText(R.id.r31523, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31623, View.VISIBLE);
                views.setTextViewText(R.id.r31623, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31723, View.VISIBLE);
                views.setTextViewText(R.id.r31723, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30023, View.VISIBLE);
                views.setTextViewText(R.id.r30023, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32023, View.VISIBLE);
                views.setTextViewText(R.id.r32023, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33023, View.VISIBLE);
                views.setTextViewText(R.id.r33023, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34023, View.VISIBLE);
                views.setTextViewText(R.id.r34023, name);
            }
}
if (res.inDiff == -2 && res.outDiff == -1) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35023, View.VISIBLE);
                views.setTextViewText(R.id.r35023, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30124, View.VISIBLE);
                views.setTextViewText(R.id.r30124, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30224, View.VISIBLE);
                views.setTextViewText(R.id.r30224, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30324, View.VISIBLE);
                views.setTextViewText(R.id.r30324, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30424, View.VISIBLE);
                views.setTextViewText(R.id.r30424, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30524, View.VISIBLE);
                views.setTextViewText(R.id.r30524, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30724, View.VISIBLE);
                views.setTextViewText(R.id.r30724, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30824, View.VISIBLE);
                views.setTextViewText(R.id.r30824, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30924, View.VISIBLE);
                views.setTextViewText(R.id.r30924, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31024, View.VISIBLE);
                views.setTextViewText(R.id.r31024, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31124, View.VISIBLE);
                views.setTextViewText(R.id.r31124, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31424, View.VISIBLE);
                views.setTextViewText(R.id.r31424, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31524, View.VISIBLE);
                views.setTextViewText(R.id.r31524, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31624, View.VISIBLE);
                views.setTextViewText(R.id.r31624, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31724, View.VISIBLE);
                views.setTextViewText(R.id.r31724, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30024, View.VISIBLE);
                views.setTextViewText(R.id.r30024, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32024, View.VISIBLE);
                views.setTextViewText(R.id.r32024, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33024, View.VISIBLE);
                views.setTextViewText(R.id.r33024, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34024, View.VISIBLE);
                views.setTextViewText(R.id.r34024, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 0) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35024, View.VISIBLE);
                views.setTextViewText(R.id.r35024, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30125, View.VISIBLE);
                views.setTextViewText(R.id.r30125, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30225, View.VISIBLE);
                views.setTextViewText(R.id.r30225, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30325, View.VISIBLE);
                views.setTextViewText(R.id.r30325, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30425, View.VISIBLE);
                views.setTextViewText(R.id.r30425, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30525, View.VISIBLE);
                views.setTextViewText(R.id.r30525, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30725, View.VISIBLE);
                views.setTextViewText(R.id.r30725, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30825, View.VISIBLE);
                views.setTextViewText(R.id.r30825, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30925, View.VISIBLE);
                views.setTextViewText(R.id.r30925, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31025, View.VISIBLE);
                views.setTextViewText(R.id.r31025, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31125, View.VISIBLE);
                views.setTextViewText(R.id.r31125, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31425, View.VISIBLE);
                views.setTextViewText(R.id.r31425, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31525, View.VISIBLE);
                views.setTextViewText(R.id.r31525, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31625, View.VISIBLE);
                views.setTextViewText(R.id.r31625, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31725, View.VISIBLE);
                views.setTextViewText(R.id.r31725, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30025, View.VISIBLE);
                views.setTextViewText(R.id.r30025, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32025, View.VISIBLE);
                views.setTextViewText(R.id.r32025, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33025, View.VISIBLE);
                views.setTextViewText(R.id.r33025, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34025, View.VISIBLE);
                views.setTextViewText(R.id.r34025, name);
            }
}
if (res.inDiff == -2 && res.outDiff == 1) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35025, View.VISIBLE);
                views.setTextViewText(R.id.r35025, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30126, View.VISIBLE);
                views.setTextViewText(R.id.r30126, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30226, View.VISIBLE);
                views.setTextViewText(R.id.r30226, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30326, View.VISIBLE);
                views.setTextViewText(R.id.r30326, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30426, View.VISIBLE);
                views.setTextViewText(R.id.r30426, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30526, View.VISIBLE);
                views.setTextViewText(R.id.r30526, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30726, View.VISIBLE);
                views.setTextViewText(R.id.r30726, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30826, View.VISIBLE);
                views.setTextViewText(R.id.r30826, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30926, View.VISIBLE);
                views.setTextViewText(R.id.r30926, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31026, View.VISIBLE);
                views.setTextViewText(R.id.r31026, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31126, View.VISIBLE);
                views.setTextViewText(R.id.r31126, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31426, View.VISIBLE);
                views.setTextViewText(R.id.r31426, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31526, View.VISIBLE);
                views.setTextViewText(R.id.r31526, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31626, View.VISIBLE);
                views.setTextViewText(R.id.r31626, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31726, View.VISIBLE);
                views.setTextViewText(R.id.r31726, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30026, View.VISIBLE);
                views.setTextViewText(R.id.r30026, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32026, View.VISIBLE);
                views.setTextViewText(R.id.r32026, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33026, View.VISIBLE);
                views.setTextViewText(R.id.r33026, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34026, View.VISIBLE);
                views.setTextViewText(R.id.r34026, name);
            }
}
if (res.inDiff == -2 && res.outDiff >= 2) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35026, View.VISIBLE);
                views.setTextViewText(R.id.r35026, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30134, View.VISIBLE);
                views.setTextViewText(R.id.r30134, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30234, View.VISIBLE);
                views.setTextViewText(R.id.r30234, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30334, View.VISIBLE);
                views.setTextViewText(R.id.r30334, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30434, View.VISIBLE);
                views.setTextViewText(R.id.r30434, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30534, View.VISIBLE);
                views.setTextViewText(R.id.r30534, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30734, View.VISIBLE);
                views.setTextViewText(R.id.r30734, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30834, View.VISIBLE);
                views.setTextViewText(R.id.r30834, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30934, View.VISIBLE);
                views.setTextViewText(R.id.r30934, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31034, View.VISIBLE);
                views.setTextViewText(R.id.r31034, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31134, View.VISIBLE);
                views.setTextViewText(R.id.r31134, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31434, View.VISIBLE);
                views.setTextViewText(R.id.r31434, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31534, View.VISIBLE);
                views.setTextViewText(R.id.r31534, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31634, View.VISIBLE);
                views.setTextViewText(R.id.r31634, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31734, View.VISIBLE);
                views.setTextViewText(R.id.r31734, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30034, View.VISIBLE);
                views.setTextViewText(R.id.r30034, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32034, View.VISIBLE);
                views.setTextViewText(R.id.r32034, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33034, View.VISIBLE);
                views.setTextViewText(R.id.r33034, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34034, View.VISIBLE);
                views.setTextViewText(R.id.r34034, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 0) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35034, View.VISIBLE);
                views.setTextViewText(R.id.r35034, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30135, View.VISIBLE);
                views.setTextViewText(R.id.r30135, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30235, View.VISIBLE);
                views.setTextViewText(R.id.r30235, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30335, View.VISIBLE);
                views.setTextViewText(R.id.r30335, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30435, View.VISIBLE);
                views.setTextViewText(R.id.r30435, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30535, View.VISIBLE);
                views.setTextViewText(R.id.r30535, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30735, View.VISIBLE);
                views.setTextViewText(R.id.r30735, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30835, View.VISIBLE);
                views.setTextViewText(R.id.r30835, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30935, View.VISIBLE);
                views.setTextViewText(R.id.r30935, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31035, View.VISIBLE);
                views.setTextViewText(R.id.r31035, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31135, View.VISIBLE);
                views.setTextViewText(R.id.r31135, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31435, View.VISIBLE);
                views.setTextViewText(R.id.r31435, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31535, View.VISIBLE);
                views.setTextViewText(R.id.r31535, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31635, View.VISIBLE);
                views.setTextViewText(R.id.r31635, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31735, View.VISIBLE);
                views.setTextViewText(R.id.r31735, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30035, View.VISIBLE);
                views.setTextViewText(R.id.r30035, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32035, View.VISIBLE);
                views.setTextViewText(R.id.r32035, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33035, View.VISIBLE);
                views.setTextViewText(R.id.r33035, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34035, View.VISIBLE);
                views.setTextViewText(R.id.r34035, name);
            }
}
if (res.inDiff == -1 && res.outDiff == 1) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35035, View.VISIBLE);
                views.setTextViewText(R.id.r35035, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30136, View.VISIBLE);
                views.setTextViewText(R.id.r30136, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30236, View.VISIBLE);
                views.setTextViewText(R.id.r30236, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30336, View.VISIBLE);
                views.setTextViewText(R.id.r30336, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30436, View.VISIBLE);
                views.setTextViewText(R.id.r30436, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30536, View.VISIBLE);
                views.setTextViewText(R.id.r30536, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30736, View.VISIBLE);
                views.setTextViewText(R.id.r30736, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30836, View.VISIBLE);
                views.setTextViewText(R.id.r30836, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30936, View.VISIBLE);
                views.setTextViewText(R.id.r30936, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31036, View.VISIBLE);
                views.setTextViewText(R.id.r31036, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31136, View.VISIBLE);
                views.setTextViewText(R.id.r31136, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31436, View.VISIBLE);
                views.setTextViewText(R.id.r31436, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31536, View.VISIBLE);
                views.setTextViewText(R.id.r31536, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31636, View.VISIBLE);
                views.setTextViewText(R.id.r31636, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31736, View.VISIBLE);
                views.setTextViewText(R.id.r31736, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30036, View.VISIBLE);
                views.setTextViewText(R.id.r30036, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32036, View.VISIBLE);
                views.setTextViewText(R.id.r32036, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33036, View.VISIBLE);
                views.setTextViewText(R.id.r33036, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34036, View.VISIBLE);
                views.setTextViewText(R.id.r34036, name);
            }
}
if (res.inDiff == -1 && res.outDiff >= 2) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35036, View.VISIBLE);
                views.setTextViewText(R.id.r35036, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30145, View.VISIBLE);
                views.setTextViewText(R.id.r30145, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30245, View.VISIBLE);
                views.setTextViewText(R.id.r30245, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30345, View.VISIBLE);
                views.setTextViewText(R.id.r30345, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30445, View.VISIBLE);
                views.setTextViewText(R.id.r30445, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30545, View.VISIBLE);
                views.setTextViewText(R.id.r30545, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30745, View.VISIBLE);
                views.setTextViewText(R.id.r30745, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30845, View.VISIBLE);
                views.setTextViewText(R.id.r30845, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30945, View.VISIBLE);
                views.setTextViewText(R.id.r30945, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31045, View.VISIBLE);
                views.setTextViewText(R.id.r31045, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31145, View.VISIBLE);
                views.setTextViewText(R.id.r31145, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31445, View.VISIBLE);
                views.setTextViewText(R.id.r31445, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31545, View.VISIBLE);
                views.setTextViewText(R.id.r31545, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31645, View.VISIBLE);
                views.setTextViewText(R.id.r31645, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31745, View.VISIBLE);
                views.setTextViewText(R.id.r31745, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30045, View.VISIBLE);
                views.setTextViewText(R.id.r30045, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32045, View.VISIBLE);
                views.setTextViewText(R.id.r32045, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33045, View.VISIBLE);
                views.setTextViewText(R.id.r33045, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34045, View.VISIBLE);
                views.setTextViewText(R.id.r34045, name);
            }
}
if (res.inDiff == 0 && res.outDiff == 1) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35045, View.VISIBLE);
                views.setTextViewText(R.id.r35045, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30146, View.VISIBLE);
                views.setTextViewText(R.id.r30146, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30246, View.VISIBLE);
                views.setTextViewText(R.id.r30246, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30346, View.VISIBLE);
                views.setTextViewText(R.id.r30346, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30446, View.VISIBLE);
                views.setTextViewText(R.id.r30446, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30546, View.VISIBLE);
                views.setTextViewText(R.id.r30546, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30746, View.VISIBLE);
                views.setTextViewText(R.id.r30746, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30846, View.VISIBLE);
                views.setTextViewText(R.id.r30846, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30946, View.VISIBLE);
                views.setTextViewText(R.id.r30946, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31046, View.VISIBLE);
                views.setTextViewText(R.id.r31046, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31146, View.VISIBLE);
                views.setTextViewText(R.id.r31146, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31446, View.VISIBLE);
                views.setTextViewText(R.id.r31446, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31546, View.VISIBLE);
                views.setTextViewText(R.id.r31546, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31646, View.VISIBLE);
                views.setTextViewText(R.id.r31646, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31746, View.VISIBLE);
                views.setTextViewText(R.id.r31746, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30046, View.VISIBLE);
                views.setTextViewText(R.id.r30046, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32046, View.VISIBLE);
                views.setTextViewText(R.id.r32046, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33046, View.VISIBLE);
                views.setTextViewText(R.id.r33046, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34046, View.VISIBLE);
                views.setTextViewText(R.id.r34046, name);
            }
}
if (res.inDiff == 0 && res.outDiff >= 2) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35046, View.VISIBLE);
                views.setTextViewText(R.id.r35046, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 301) {
                views.setViewVisibility(R.id.r30156, View.VISIBLE);
                views.setTextViewText(R.id.r30156, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 302) {
                views.setViewVisibility(R.id.r30256, View.VISIBLE);
                views.setTextViewText(R.id.r30256, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 303) {
                views.setViewVisibility(R.id.r30356, View.VISIBLE);
                views.setTextViewText(R.id.r30356, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 304) {
                views.setViewVisibility(R.id.r30456, View.VISIBLE);
                views.setTextViewText(R.id.r30456, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 305) {
                views.setViewVisibility(R.id.r30556, View.VISIBLE);
                views.setTextViewText(R.id.r30556, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 307) {
                views.setViewVisibility(R.id.r30756, View.VISIBLE);
                views.setTextViewText(R.id.r30756, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 308) {
                views.setViewVisibility(R.id.r30856, View.VISIBLE);
                views.setTextViewText(R.id.r30856, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 309) {
                views.setViewVisibility(R.id.r30956, View.VISIBLE);
                views.setTextViewText(R.id.r30956, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 310) {
                views.setViewVisibility(R.id.r31056, View.VISIBLE);
                views.setTextViewText(R.id.r31056, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 311) {
                views.setViewVisibility(R.id.r31156, View.VISIBLE);
                views.setTextViewText(R.id.r31156, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 314) {
                views.setViewVisibility(R.id.r31456, View.VISIBLE);
                views.setTextViewText(R.id.r31456, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 315) {
                views.setViewVisibility(R.id.r31556, View.VISIBLE);
                views.setTextViewText(R.id.r31556, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 316) {
                views.setViewVisibility(R.id.r31656, View.VISIBLE);
                views.setTextViewText(R.id.r31656, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 317) {
                views.setViewVisibility(R.id.r31756, View.VISIBLE);
                views.setTextViewText(R.id.r31756, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 300) {
                views.setViewVisibility(R.id.r30056, View.VISIBLE);
                views.setTextViewText(R.id.r30056, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 320) {
                views.setViewVisibility(R.id.r32056, View.VISIBLE);
                views.setTextViewText(R.id.r32056, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 330) {
                views.setViewVisibility(R.id.r33056, View.VISIBLE);
                views.setTextViewText(R.id.r33056, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 340) {
                views.setViewVisibility(R.id.r34056, View.VISIBLE);
                views.setTextViewText(R.id.r34056, name);
            }
}
if (res.inDiff == 1 && res.outDiff >= 2) {
		if (res.roomNr == 350) {
                views.setViewVisibility(R.id.r35056, View.VISIBLE);
                views.setTextViewText(R.id.r35056, name);
            }
}
        }

    }

    List<Reservation> renderReservationListInRange(Context context){
        return getReservations(context);
    }

    private List<Reservation> getReservations(Context context) {
        DatabaseAccess databaseAccess;
        // Check the external database file. External database must be available for the first time deployment.
        String externalDirectory = Environment.getExternalStoragePublicDirectory("ReservationenApp").getAbsolutePath();
        File dbFile = new File(externalDirectory, DatabaseOpenHelper.DATABASE_NAME);
        Log.d("Widget", "external dir path: " + dbFile.toString());
        if (!dbFile.exists()) {
            List<Reservation> returnListFail = new ArrayList<>();
            Log.d("Widget", "------- DIDN'T FIND DB ---------");
            return returnListFail;
        } else {
            Log.d("Widget", "------- Found DB from get Res ---------");
        }
        // If external database is available, deploy it
        databaseAccess = DatabaseAccess.getInstance(context, externalDirectory);

        databaseAccess.open();
        List<Reservation> allReservations = databaseAccess.getReservationsInRange();
        databaseAccess.close();

        return allReservations;
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
