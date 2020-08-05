package com.nokhba.nokhbahmdpanel.Notifications;

import com.nokhba.nokhbahmdpanel.model.Notification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Service {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAhDsWOC8:APA91bHnbTbOTVckwyN33xTE4228RdiGK0Qu3VqvubJGoI7s_4T5kKu6dfvsKbPn0VxWS2xbG46MeDV5y6ojR-8taYy1t8rfFaCMq8MOkjCqOUTXieUFYVxvLdQJ6DaNYcgHBA5T1QCt"
            }
    )
    @POST("/fcm/send")
    Call<response> sendNotifcation(@Body Notification body);
}
