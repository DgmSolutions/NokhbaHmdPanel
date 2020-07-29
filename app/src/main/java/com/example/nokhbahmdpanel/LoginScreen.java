package com.example.nokhbahmdpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nokhbahmdpanel.model.Security;
import com.example.nokhbahmdpanel.model.users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginScreen extends AppCompatActivity {
    private TextInputEditText username,password;
    private Button login_btn;
    private CollectionReference db = FirebaseFirestore.getInstance().collection("Users");
    private static final String TAG = "LoginScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        // input text
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString().trim();
                String mdp=password.getText().toString().trim();

                      getUsers(user,mdp);
            }
        });
    }
    private void startMain(){
        Intent intent = new Intent(LoginScreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void getUsers(final String u,final String p){
      db.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
          @Override
          public void onSuccess(QuerySnapshot Snapshots) {
              for (QueryDocumentSnapshot documentSnapshot :Snapshots) {
                 users users = documentSnapshot.toObject(users.class);

                  String user = users.getUser();
                  String mdp = users.getMdp();
                  int act = users.getIsActive();


                  try {
                      String d =Security.decrypt(mdp);
                      if(u.equals(user) && p.equals(d) && act ==1) {
                       saveData(u,p);
                       break;
              }else {

                      }
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }

          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              Log.d(TAG,e.getMessage());
          }
      });

    }
    public void saveData(String u,String p) {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user",u );
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        startMain();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPrefs = getSharedPreferences("login", MODE_PRIVATE);
        if (sharedPrefs.contains("user")){
            startMain();
        }
    }
}