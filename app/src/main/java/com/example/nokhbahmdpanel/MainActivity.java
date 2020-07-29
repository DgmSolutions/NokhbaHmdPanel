package com.example.nokhbahmdpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView NavigationViewBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // Navigation Bottom View
        NavigationViewBottom = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        NavController navController = Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(NavigationViewBottom,navController);
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

}