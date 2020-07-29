package com.example.nokhbahmdpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nokhbahmdpanel.model.Help;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class InfoScreen extends AppCompatActivity {


    final String permissionToCall = Manifest.permission.CALL_PHONE;
    final String permissionToFineLocation = Manifest.permission.ACCESS_FINE_LOCATION;
    private static Intent phoneCallIntent;
    private Button call_btn,map_btn;
    private TextView phone_number,fullname,covid_y_n,fcovid_y_n,covid_number,help_desc,help_type;
    private final int LOCATION_REQUEST_CODE=1000;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);
        // Text Views
        phone_number = findViewById(R.id.phone_number);
        fullname = findViewById(R.id.info_id);
        covid_y_n = findViewById(R.id.covid);
        fcovid_y_n = findViewById(R.id.f_covid);
        covid_number = findViewById(R.id.covid_num);
        help_desc = findViewById(R.id.help_desc_id);
        help_type = findViewById(R.id.help_type_id);
        // Button
        call_btn = findViewById(R.id.call_id);
        map_btn = findViewById(R.id.map_id);

            Help user = (Help) getIntent().getSerializableExtra("help");
            fullname.setText(user.getNom()+" "+user.getPrenom());
            phone_number.setText(user.getPhone());
            covid_y_n.setText(user.getCovid_you());
            fcovid_y_n.setText(user.getFcovid());
            covid_number.setText(String.valueOf(user.getNumCovide()));
            help_desc.setText(user.getDesc());
            help_type.setText(user.getService());

        double latitudeUser =user.getLocalisation().get("latitude");
        double longtitudeUser=user.getLocalisation().get("longtitude");

        // click listener
        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askpermission();
                getMyLocation();
            }
        });

        // arrow back
        findViewById(R.id.back_arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneCallIntent = new Intent(Intent.ACTION_CALL);
                phoneCallIntent.setData(Uri.parse("tel:"+phone_number.getText())); //Uri.parse("tel:your number")
                    if (ActivityCompat.checkSelfPermission(InfoScreen.this, permissionToCall) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(InfoScreen.this, new String[]{permissionToCall}, LOCATION_REQUEST_CODE);
                    return;
                }
                startActivity(phoneCallIntent);
            }
        });


    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(InfoScreen.this);
    }


    private void startLogin() {
        Intent intent = new Intent(this,LoginScreen.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPrefs = getSharedPreferences("login", MODE_PRIVATE);
        if (!sharedPrefs.contains("user")){
            startLogin();
        }
        if(ActivityCompat.checkSelfPermission(InfoScreen.this,permissionToFineLocation)
                == PackageManager.PERMISSION_GRANTED){

            getMyLocation();
        }else{
            askpermission();
        }
    }

    private void askpermission() {
        if(ActivityCompat.checkSelfPermission(InfoScreen.this,permissionToFineLocation)
                != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(InfoScreen.this,permissionToFineLocation)){
                //dialog

                ActivityCompat.requestPermissions(InfoScreen.this,new String[]{permissionToFineLocation},LOCATION_REQUEST_CODE);
            }else {
                ActivityCompat.requestPermissions(InfoScreen.this,new String[]{permissionToFineLocation},LOCATION_REQUEST_CODE);

            }
        }

        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == LOCATION_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //permission Garanted
                getMyLocation();
            }else {
                //permission not Garanted
            }
        }
    }

    private void getMyLocation() {

        @SuppressLint("MissingPermission")
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){

                    Toast.makeText(InfoScreen.this, String.valueOf(location.getLatitude()+" "+location.getLatitude()), Toast.LENGTH_SHORT).show();

                }

            }
        });
        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InfoScreen.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}