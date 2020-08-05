package com.nokhba.nokhbahmdpanel.Notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.provider.CalendarContract;

import com.google.type.Color;
import com.nokhba.nokhbahmdpanel.R;

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
            chanel.enableLights(true);
            chanel.setLightColor(Color.GREEN_FIELD_NUMBER);
            chanel.enableVibration(true);
            chanel.setVibrationPattern(new long[] { 1000, 1000, 1000, 1000, 1000 });
            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.createNotificationChannel(chanel);


        }
    }
}
