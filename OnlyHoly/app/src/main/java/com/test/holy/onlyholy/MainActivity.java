package com.test.holy.onlyholy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.test.holy.onlyholy.weather.RequestService;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        startService(new Intent(this, RequestService.class));
        Log.e(TAG,"this =" + this);
        Log.e(TAG,"getBaseContext() =" + getBaseContext());
        Log.e(TAG,"this.equals(getBaseContext()) =" + this.equals(getBaseContext()));
        Log.e(TAG,"getApplication = " + getApplication());
        Log.e(TAG,"getApplicationContext =" + getApplicationContext());
        Log.e(TAG,"getApplication.equals(getApplicationContext()) =" + getApplication().equals(getApplicationContext()));
//        Intent intent = new Intent(this,WebViewActivity.class);
//        intent.putExtra("URL","http://www.importnew.com/22083.html");
//        startActivity(intent);
    }
}
