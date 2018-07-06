package com.example.cyrilwelschen.reservationen;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.view.View;
import android.widget.RemoteViews;

import java.util.Date;

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

    /*
    private String intDiffToDateString(int intDayDiff){
        Calendar todayCal = Calendar.getInstance();
        todayCal.add(Calendar.DATE, intDayDiff);
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MMM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("cccc");
        return sdf1.format(todayCal) + "\n" + sdf2.format(todayCal);
    }
    */

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

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_main);
        views.setOnClickPendingIntent(R.id.relative_layout, pendingIntent);

        setDates(views);

        views.setViewVisibility(R.id.r30036, View.VISIBLE);
        views.setTextViewText(R.id.r30036, "test1");
        views.setViewVisibility(R.id.r30013, View.VISIBLE);
        views.setTextViewText(R.id.r30013, "test1");
        views.setViewVisibility(R.id.r30114, View.VISIBLE);
        views.setTextViewText(R.id.r30214, "test2");
        views.setViewVisibility(R.id.r30706, View.VISIBLE);
        views.setTextViewText(R.id.r30706, "hello");
        views.setViewVisibility(R.id.r30806, View.VISIBLE);
        views.setTextViewText(R.id.r30806, "you");

        appWidgetManager.updateAppWidget(appWidgetId, views);
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
