package com.nokhba.nokhbahmdpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nokhba.nokhbahmdpanel.classes.CheckConx;
import com.nokhba.nokhbahmdpanel.classes.Snackbar;
import com.nokhba.nokhbahmdpanel.model.Valunteer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class VinfoScreen extends AppCompatActivity {
    final String permissionToCall = Manifest.permission.CALL_PHONE;
    private static Intent phoneCallIntent;
    private Button call_btn;
    private TextView phone_number,vfullname,vtype;
    private LinearLayout linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vinfo_screen);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(task.isSuccessful()){
                    String token = task.getResult().getToken();
                    Log.d("token",token);
                    updateToken(token);
                }else{
                    Toast.makeText(VinfoScreen.this,"token not finding",Toast.LENGTH_LONG).show();
                }
            }
        });
        linear=findViewById(R.id.linear);
        // Text Views
        phone_number = findViewById(R.id.v_phone_number);
        vfullname = findViewById(R.id.v_info);
        vtype = findViewById(R.id.v_type_id);
        // Button
        call_btn = findViewById(R.id.call_id);
        Valunteer v = (Valunteer) getIntent().getSerializableExtra("valunteer");
        vfullname.setText(v.getNom()+" "+v.getPrenom());
        phone_number.setText(v.getPhone());
        vtype.setText(v.getService());
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
    @Override
    protected void onStart() {
        super.onStart();
        if(CheckConx.isConnected(this)==false){
            Snackbar.SnackBarMessage(linear,getString(R.string.checkConx), com.google.android.material.snackbar.Snackbar.LENGTH_SHORT,getResources().getColor(R.color.Eblack));
        }
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
    private void updateToken(String refreshToken) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        SharedPreferences sharedPrefs = getSharedPreferences("login", MODE_PRIVATE);
      String id=  sharedPrefs.getString("id","");
        DocumentReference user = db.collection("Users").document(id);
        user.update("Token",refreshToken);

    }
}