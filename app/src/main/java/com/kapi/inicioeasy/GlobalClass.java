package com.kapi.inicioeasy;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import com.parse.Parse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class GlobalClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("global", Build.MODEL);

        Parse.enableLocalDatastore(GlobalClass.this);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS);
        Parse.initialize(new Parse.Configuration.Builder(GlobalClass.this)
                //Devcdmx
                //.applicationId("x0zJ5QnGzG1RheVJ")
                //Productivo
                .applicationId("06iwBb4oI6K02WWtx")
                // if desired
                //Devcdmx
                //.server("http://kapitalmxdev.herokuapp.com/parse")
                //productivo
                .maxRetries(0)
                .clientBuilder(client)
                .server("http://parse.kapital.mx:1337/parse")
                .build()
        );
    }
}