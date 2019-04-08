package com.example.HAIDAR_1202164150_SI4006_PAB_MODUL4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Card> list;
    AdapterCard adapterCard;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    int REQUEST_MENU = 404;
    ProgressBar progressBarMain;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void logout() {
        mAuth.signOut();
        startActivity(new Intent(MainActivity.this, Masuk.class));
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        progressBarMain = findViewById(R.id.progressBar2);

        recyclerView = findViewById(R.id.rvMain);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapterCard = new AdapterCard(list, MainActivity.this);
        recyclerView.setAdapter(adapterCard);
        db = FirebaseFirestore.getInstance();

        init();
    }
    public void add(View view) {
        startActivityForResult(new Intent(MainActivity.this, InputActivity.class), REQUEST_MENU);
    }
    @SuppressLint("StaticFieldLeak")
    public void init() {
        list.clear();
        progressBarMain.setVisibility(View.VISIBLE);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                getthat();
                return null;
            }
        }.execute();
    }
    public void loadMenu(View view) {
        init();
    }

    public void getthat() {
        db.collection(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Log.d("Result", document.getId() + " => " + document.getData());
                        list.add(new Card(document.getId(), document.get("NamaMenu").toString(), document.get("Harga").toString(), document.get("Deskripsi").toString()));
                    }
                    adapterCard.notifyDataSetChanged();
                }
                progressBarMain.setVisibility(View.GONE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        init();
        super.onActivityResult(requestCode, resultCode, data);
    }
}