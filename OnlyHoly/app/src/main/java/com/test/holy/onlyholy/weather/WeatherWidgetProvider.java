package com.test.holy.onlyholy.weather;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.test.holy.onlyholy.R;


/**
 * Created by houlin.jiang on 2017/3/8.
 */

public class WeatherWidgetProvider extends AppWidgetProvider {
    public static final String TAG = "WeatherWidgetProvider";
    public static final String CLICK_ACTION = "com.holy.action.click";
    public static final String MAIN_UPDATE_UI = "main_activity_update_ui";  //Action
    public static final String UPDATE_DATA = "update_data";  //Action



    public WeatherWidgetProvider() {
        super();
    }
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId,MyWeatherInfo info ) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_weather_widget);

        if (!TextUtils.isEmpty(info.address.formatted_address)) {
            views.setTextViewText(R.id.widget_tv_address, info.address.formatted_address);
        }

        if (!TextUtils.isEmpty(info.weather.data.weather.content.today.time)) {
            views.setTextViewText(R.id.widget_tv_date, info.weather.data.weather.content.today.time);
        }


        if (!TextUtils.isEmpty(info.weather.data.weather.content.today.condition)) {
            String weather = "今日天气:"  + info.weather.data.weather.content.today.condition + "   风：" ;

            if (TextUtils.isEmpty(info.weather.data.weather.content.today.wind)) {
                weather += "无";
            } else {
                weather += info.weather.data.weather.content.today.wind;
            }

            if (TextUtils.isEmpty(info.weather.data.weather.content.today.pm25)) {
                weather += "  pm2.5 : 无";
            } else {
                weather += "  pm2.5 : " + info.weather.data.weather.content.today.pm25;
            }
            views.setTextViewText(R.id.widget_tv_weather_today, weather);
        }
        if (!TextUtils.isEmpty(info.weather.data.weather.content.tomorrow.condition)) {
            String tomorrowWeather = "明日天气:"  + info.weather.data.weather.content.tomorrow.condition + "   风：" ;
            if (TextUtils.isEmpty(info.weather.data.weather.content.tomorrow.wind)) {
                tomorrowWeather += "无";
            } else {
                tomorrowWeather += info.weather.data.weather.content.tomorrow.wind;
            }

            if (TextUtils.isEmpty(info.weather.data.weather.content.tomorrow.pm25)) {
                tomorrowWeather += "  pm2.5 : 无";
            } else {
                tomorrowWeather += "  pm2.5 : " + info.weather.data.weather.content.tomorrow.pm25;
            }
            views.setTextViewText(R.id.widget_tv_weather_tomorrow, tomorrowWeather);
        }

        if (!TextUtils.isEmpty(info.weather.data.weather.content.calendar.lunar)) {
            views.setTextViewText(R.id.widget_tv_lunar, info.weather.data.weather.content.calendar.lunar  + "  今天无人生日");
        }
        if (TextUtils.isEmpty(info.weather.data.weather.content.calendar.festival) || "false".equals(info.weather.data.weather.content.calendar.festival)) {
            views.setTextViewText(R.id.widget_tv_festival, "今天没有节日");
        } else {
            views.setTextViewText(R.id.widget_tv_festival, info.weather.data.weather.content.calendar.festival);
        }

        PendingIntent pi = PendingIntent.getService(context, 0, new Intent(context,RequestService.class), PendingIntent.FLAG_ONE_SHOT);
        views.setOnClickPendingIntent(R.id.widget_tv_refresh,pi);

        appWidgetManager.updateAppWidget(new ComponentName(context,WeatherWidgetProvider.class), views);
        Toast.makeText(context,"刷新完毕",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId,new MyWeatherInfo());
        }
    }

    @Override
    public void onEnabled(Context context) {
        context.startService(new Intent(context,RequestService.class));
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(MAIN_UPDATE_UI)) {
            MyWeatherInfo weatherInfo = (MyWeatherInfo)intent.getSerializableExtra(UPDATE_DATA);
            if (weatherInfo != null) {
                updateAppWidget(context,AppWidgetManager.getInstance(context),1,weatherInfo);
            } else {
                context.startService(new Intent(context,RequestService.class));
            }

        }

    }
}
