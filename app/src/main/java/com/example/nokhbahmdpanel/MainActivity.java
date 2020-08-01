package com.example.nokhbahmdpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView NavigationViewBottom;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("start","ok");

        Log.d("end","ok");
        // Navigation Bottom View
        NavigationViewBottom = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        NavController navController = Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(NavigationViewBottom,navController);

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }
    @Override
    public void onBackPressed() {
       AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("خروج")
                .setCancelable(false)
                .setMessage("هل تريد الخروج من التطبيق ؟")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
               Button negative = alertDialog.getButton(alertDialog.BUTTON_NEGATIVE);
               negative.setBackgroundColor(Color.TRANSPARENT);
               negative.setTextColor(Color.BLACK);

    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPrefs = getSharedPreferences("login", MODE_PRIVATE);
        if (!sharedPrefs.contains("user")){
            startLogin();
        }
    }

    private void startLogin() {
        Intent intent = new Intent(this,LoginScreen.class);
        startActivity(intent);
        finish();
    }
    private void logout(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("تسجيل الخروج ...");
        progressDialog.show();
        SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
            SharedPreferences.Editor pref= sharedPreferences.edit();
            pref.clear();
            pref.apply();
            FirebaseAuth.getInstance().signOut();
            startLogin();
        }


}