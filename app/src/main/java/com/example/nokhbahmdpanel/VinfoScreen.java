package com.example.nokhbahmdpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VinfoScreen extends AppCompatActivity {
    final String permissionToCall = Manifest.permission.CALL_PHONE;
    private static Intent phoneCallIntent;
    private Button call_btn;
    private TextView phone_number,vfullname,vtype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinfo_screen);

        // Text Views
        phone_number = findViewById(R.id.v_phone_number);
        vfullname = findViewById(R.id.v_info);
        vtype = findViewById(R.id.v_type_id);
        // Button
        call_btn = findViewById(R.id.call_id);

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
                if (ActivityCompat.checkSelfPermission(VinfoScreen.this, permissionToCall) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(VinfoScreen.this, new String[]{permissionToCall}, 1);
                    return;
                }
                startActivity(phoneCallIntent);
            }
        });
    }
}