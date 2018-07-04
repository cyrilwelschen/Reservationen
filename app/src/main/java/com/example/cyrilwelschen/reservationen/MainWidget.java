package com.example.cyrilwelschen.reservationen;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.view.View;
import android.widget.RemoteViews;

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

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_main);

        views.setViewVisibility(R.id.r30036, View.VISIBLE);
        views.setTextViewText(R.id.r30036, "test1");
        views.setViewVisibility(R.id.r30013, View.VISIBLE);
        views.setTextViewText(R.id.r30013, "test1");
        views.setViewVisibility(R.id.r30214, View.VISIBLE);
        views.setTextViewText(R.id.r30214, "test2");
        views.setViewVisibility(R.id.r30806, View.VISIBLE);
        views.setTextViewText(R.id.r30806, "test2");

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
