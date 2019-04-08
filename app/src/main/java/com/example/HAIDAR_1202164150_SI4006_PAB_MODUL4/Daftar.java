package com.example.HAIDAR_1202164150_SI4006_PAB_MODUL4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Daftar extends AppCompatActivity {
    EditText username,useremail,userpass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        username =  findViewById(R.id.edNameRegist);
        useremail = findViewById(R.id.edEmailRegist);
        userpass = findViewById(R.id.edPassRegist);
    }
    public void regist(View view){
        if (check()){
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(useremail.getText().toString(),userpass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(username.getText().toString()).build();

                                user.updateProfile(userProfileChangeRequest);
                                startActivity(new Intent(Daftar.this, Masuk.class));
                                finish();
                            }
                        }
                    });
        }
    }
    public boolean check(){
        if (username.getText().toString().equals("")){
            username.setError("Masukkan Nama");
            username.requestFocus();
            return false;
        }
        if (useremail.getText().toString().equals("")){
            useremail.setError("Masukkan Nama");
            useremail.requestFocus();
            return false;
        }
        if (userpass.getText().toString().equals("")){
            userpass.setError("Masukkan Nama");
            userpass.requestFocus();
            return false;
        }
        return true;
    }
    public void gotoLogin(View view){
        startActivity(new Intent(Daftar.this, Masuk.class));
        finish();
    }
}