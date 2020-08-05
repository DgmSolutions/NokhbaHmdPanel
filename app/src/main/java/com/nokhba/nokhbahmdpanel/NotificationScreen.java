package com.nokhba.nokhbahmdpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.nokhba.nokhbahmdpanel.Notifications.Api;
import com.nokhba.nokhbahmdpanel.Notifications.Service;
import com.nokhba.nokhbahmdpanel.Notifications.response;
import com.nokhba.nokhbahmdpanel.model.Data;
import com.nokhba.nokhbahmdpanel.model.Notification;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nokhba.nokhbahmdpanel.classes.DateTime.getDateTime;
import static com.nokhba.nokhbahmdpanel.classes.Snackbar.SnackBarMessage;

public class NotificationScreen extends AppCompatActivity {
    private Button send;
    private TextInputEditText title,message;
    private ProgressDialog progressDialog;
    private LinearLayout linearLayout;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_screen);
        // Layout
        linearLayout = findViewById(R.id.not_layout);
        // Edit text
        title = findViewById(R.id.not_title);
        message = findViewById(R.id.not_msg);
        //Button
        send = findViewById(R.id.not_send);
        String phone =getIntent().getStringExtra("phone");
        String token = getIntent().getStringExtra("token");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               progressDialog = new ProgressDialog(NotificationScreen.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("يرجى الانتظار ...");
                progressDialog.show();
                String body =message.getText().toString().replaceAll("( )+", " ");

              saveData(phone,title.getText().toString().trim(),body,token);
            }
        });


        //back button
        findViewById(R.id.back_arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        // Scroll Edit text
       message.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (message.hasFocus()) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_SCROLL:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });
    }
    private void saveData(String phone, String title,String body,String token){
        Data data =new Data(title,body,phone,getDateTime());
        Data data2=new Data(title,body);
       Notification not =new Notification(token,data2);
        db.collection("Data")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot Snap) {
                int size =Snap.size()+1;
                db.collection("Data").document(String.valueOf(size)).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //close progressDialog when finish
                        progressDialog.dismiss();

                        pushNotification(not);


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        SnackBarMessage(linearLayout, getString(R.string.errorMssg), Snackbar.LENGTH_SHORT, getResources().getColor(R.color.Eblack));
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                SnackBarMessage(linearLayout, getString(R.string.errorMssg), Snackbar.LENGTH_SHORT, getResources().getColor(R.color.Eblack));

            }
        });
/**/
    }

    private void pushNotification(Notification notification) {
       // Data data =new Data("panel","rio");
        //Notification notification=new Notification(to,data);
        Service service = Api.getBuild().create(Service.class);
        service.sendNotifcation(notification).enqueue(new Callback<response>() {
            @Override
            public void onResponse(Call<response> call, Response<response> response) {
                if (response.code() == 200) {

                    if (response.body().success != 1) {

                        SnackBarMessage(linearLayout, getString(R.string.notSend), Snackbar.LENGTH_SHORT, getResources().getColor(R.color.Eblack));
                    } else{

                        SnackBarMessage(linearLayout, getString(R.string.send), Snackbar.LENGTH_INDEFINITE, getResources().getColor(R.color.Eblack));
                    }
                }
            }
            @Override
            public void onFailure(Call<response> call, Throwable t) {
                Toast.makeText(NotificationScreen.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}