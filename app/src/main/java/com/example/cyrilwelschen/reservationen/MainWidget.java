package com.example.cyrilwelschen.reservationen;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

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

        //setRemoteAdapter(context, views);
        views.setRemoteAdapter(R.id.widget_list, new Intent(context, WidgetService.class));

        //views.setViewVisibility(R.id.textView6, View.INVISIBLE);
        //views.setTextViewText(R.id.textView8, "cyril");

        /*
        for(int i = 0; i < 8; i++) {
            RemoteViews textView = new RemoteViews(context.getPackageName(), R.layout.widget_text_view_template);
            textView.setTextViewText(R.id.textView1, "TextView number " + String.valueOf(i));
            textView.setViewPadding(R.id.textView1, 0, 0, 0, 0);
            //views.addView(R.id.widget_relative_layout, textView);
            views.addView(R.id.widget_linear_layout, textView);
        }
        RemoteViews textView = new RemoteViews(context.getPackageName(), R.layout.widget_text_view_template);
        textView.setTextViewText(R.id.textView1, "TextView number X");
        textView.setViewPadding(R.id.textView1, 0, 100, 0, 0);
        views.addView(R.id.widget_relative_layout, textView);
        */

        // Instruct the widget manager to update the widget
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
