package com.nokhba.nokhbahmdpanel.classes;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckConx {
    public static boolean isConnected(Context context){
        boolean connected = false;

        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Service.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if(nInfo != null){
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;

        }else {

            return connected;

        }


    }
}
