package com.nokhba.nokhbahmdpanel.Notifications;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit;
    public static Retrofit getBuild(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(host.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
