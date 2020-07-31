package com.example.nokhbahmdpanel.Notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.nokhbahmdpanel.R;

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        creatNotification();
    }
    private void creatNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel chanel= new NotificationChannel(getString(R.string.channal_id),getString(R.string.channal_nom), NotificationManager.IMPORTANCE_HIGH);
            chanel.setDescription(getString(R.string.channal_dsc));
            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.createNotificationChannel(chanel);


        }
    }
}
