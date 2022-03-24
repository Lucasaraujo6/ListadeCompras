package com.jogos.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toast.makeText(this,  UUID.randomUUID().toString(), Toast.LENGTH_LONG).show();


    }
    public void onBtnClicked(View v){

        Intent intent = new Intent(MainActivity.this, ItemsListActivity.class);
        startActivity(intent);
    }


}