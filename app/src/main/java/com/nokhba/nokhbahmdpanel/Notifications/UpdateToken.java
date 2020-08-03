package com.nokhba.nokhbahmdpanel.Notifications;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;

public class UpdateToken extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        SharedPreferences sharedPrefs = getSharedPreferences("login", MODE_PRIVATE);
        String id=  sharedPrefs.getString("id",null);
        if(id != null) {
            updateToken(s,id);
        }
           Log.d("token",s);
    }

    private void updateToken(String refreshToken,String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference user = db.collection("Users").document(id);
        user.update("Token",refreshToken);

    }
}
