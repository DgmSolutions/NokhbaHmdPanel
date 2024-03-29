package com.nokhba.nokhbahmdpanel.Notifications;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Vibrator;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.nokhba.nokhbahmdpanel.MainActivity;
import com.nokhba.nokhbahmdpanel.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class NotificationReciever extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title=remoteMessage.getData().get("title");
        String body=remoteMessage.getData().get("body");
        showNotification(getApplicationContext(),title,body);;

    }
    private void showNotification(Context context, String title, String body){
        vibrate(context);
        Random random=new Random();
        int id=random.nextInt();
        NotificationCompat.Builder buler = new NotificationCompat.Builder(context, context.getString(R.string.channal_id))
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.new_logo))
                .setSmallIcon(R.drawable.new_logo)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setLights(Color.GREEN, 3000, 3000)
                .setColor(Color.GREEN)
                .setContentIntent(intentTask(context))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM);

        NotificationManagerCompat notmanager = NotificationManagerCompat.from(context);
        notmanager.notify(id, buler.build());
    }
    private static PendingIntent intentTask(Context context){
        Intent i =new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent p =PendingIntent.getActivity(context,0,i,0);
        return p;
    }
    private static void vibrate(Context context){
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(2000);

    }
}
