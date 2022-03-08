package com.example.mvvmdos;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import com.example.mvvmdos.db.MyDatabase;

public class MVVMDosApplication extends Application {
    public static final String CHANNEL_ID = "123456";
    @Override
    public void onCreate() {
        super.onCreate();
        MyDatabase.create(this);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        String nameChannel = "micanal";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, nameChannel, importance);
        channel.enableLights(true);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }
}
