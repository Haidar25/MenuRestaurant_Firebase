package com.example.HAIDAR_1202164150_SI4006_PAB_MODUL4;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Masuk extends AppCompatActivity {
    EditText useremail, userpass;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
        useremail = findViewById(R.id.edmailLogin);
        userpass = findViewById(R.id.edPassLogin);
        mAuth = FirebaseAuth.getInstance();
    }
    public boolean check(){
        if (useremail.getText().toString().equals("")){
            useremail.setError("Isi email");
            useremail.requestFocus();
            return false;
        }
        if (userpass.getText().toString().equals("")){
            userpass.setError("Isi pass");
            userpass.requestFocus();
            return false;
        }
        return true;
    }
    public void login(View view){
        if (check()) {
            new AsyncTask<Void,Void,Boolean>(){
                @Override
                protected Boolean doInBackground(Void... voids) {
                    mAuth.signInWithEmailAndPassword(useremail.getText().toString(), userpass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(Masuk.this, MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(Masuk.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    } }
                            });
                    return null;
                }
                @Override
                protected void onPreExecute() {
                    Toast.makeText(Masuk.this, "Anda Berhasil Masuk", Toast.LENGTH_SHORT).show();
                }
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                }
            }.execute();
        }
    }
    public void gotORegist(View view){
        startActivity(new Intent(Masuk.this, Daftar.class));
        finish();
    } }