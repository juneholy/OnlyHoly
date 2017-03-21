package com.test.holy.onlyholy.weather;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.test.holy.onlyholy.weather.MyWeatherInfo.Address;
import com.test.holy.onlyholy.weather.MyWeatherInfo.Weather;
import com.test.holy.onlyholy.location.LocationService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by houlin.jiang on 2017/3/8.
 */

public class RequestService extends Service {
    public static final String TAG = "RequestService";

    MyWeatherInfo weatherInfo = new MyWeatherInfo();
    private LocationService locationService;
    private OkHttpClient client;
    public Context bContext;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        requestLocation();
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        requestLocation();
        return super.onStartCommand(intent, flags, startId);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            bContext = context;
            String action = intent.getAction();
            if (WeatherWidgetProvider.CLICK_ACTION.equals(action)) {
                requestLocation();
            }
        }
    };

    private void requestLocation() {
        weatherInfo = new MyWeatherInfo();
        Toast.makeText(getApplicationContext(),"正在刷新...",Toast.LENGTH_SHORT).show();
        ConnectivityManager connMgr = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(getApplicationContext(), "网络不可用", Toast.LENGTH_LONG).show();
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            requestLocationFromWifi();
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            requestLocationFromBaidu();
        } else {
            requestLocationFromBaidu();
        }
    }

    private void requestLocationFromWifi() {
        final Request request = new Request.Builder()
                .url("http://map.baidu.com/?qt=ipLocation")
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("", call.toString());
                stopService(new Intent(getApplicationContext(), RequestService.class));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.e(TAG, str);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(str);
                    weatherInfo.address = (Address) JSON.parseObject(jsonObject.getJSONObject("rgc").getString("result"), Address.class);
                    requestWeather(weatherInfo.address.addressComponent.district);
                } catch (JSONException e) {
                    e.printStackTrace();
                    stopService(new Intent(getApplicationContext(), RequestService.class));
                }

            }
        });
    }

    private void updateWidget(MyWeatherInfo weather) {
        Intent actionIntent = new Intent(WeatherWidgetProvider.MAIN_UPDATE_UI);
        actionIntent.putExtra(WeatherWidgetProvider.UPDATE_DATA, weather);
        getApplicationContext().sendBroadcast(actionIntent);
    }

    private void requestWeather(String city) {
        city = city.replace("区", "").replace("县", "").replace("市", "");
        if (TextUtils.isEmpty(city)) {
            Toast.makeText(getApplicationContext(), "无法定位，请稍后重试", Toast.LENGTH_LONG).show();
            return;
        }
        Request request = new Request.Builder()
                .url("https://www.baidu.com/home/other/data/weatherInfo?city=" + city)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("", call.toString());
                stopService(new Intent(getApplicationContext(), RequestService.class));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.e("", str);
                JSONObject jsonObject = null;
                weatherInfo.weather = JSON.parseObject(str, Weather.class);
                updateWidget(weatherInfo);
                stopService(new Intent(getBaseContext(), RequestService.class));
            }
        });
    }

    private void requestLocationFromBaidu() {
        locationService = new LocationService(getApplicationContext());
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();// 定位SDK
    }

    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                weatherInfo.address.location.lat = location.getLatitude();
                weatherInfo.address.location.lng = location.getLongitude();
                weatherInfo.address.formatted_address = location.getAddrStr();
                weatherInfo.address.addressComponent.country = location.getCountry();
                weatherInfo.address.addressComponent.district = location.getDistrict();
                try {
                    weatherInfo.address.addressComponent.country_code = Integer.parseInt(location.getCountryCode());
                } catch (Exception e) {
                    stopService(new Intent(getApplicationContext(), RequestService.class));
                }
                requestWeather( weatherInfo.address.addressComponent.district);
                locationService.stop();
            } else {
                stopService(new Intent(getApplicationContext(), RequestService.class));
            }
        }

        public void onConnectHotSpotMessage(String s, int i) {
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        client = null;
        if (locationService != null) {
            locationService.stop();
        }
    }

}
