package com.nokhba.nokhbahmdpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class NotificationScreen extends AppCompatActivity {
    private Button send;
    private TextInputEditText title,message;
    private ProgressDialog progressDialog;
    private LinearLayout linearLayout;

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

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(NotificationScreen.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("يرجى الانتظار ...");
                progressDialog.show();


                //close progressDialog when finish
                //progressDialog.dismiss();

                //snackbar onclick
                Snackbar.make(linearLayout, "تم ارسال الإشعار بنجاح", Snackbar.LENGTH_INDEFINITE)
                        .setAction("موافق", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onBackPressed();
                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_green_light))
                        .show();

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
}