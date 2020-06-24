package com.example.naonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.http.naonapp.HttpNetworking;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new HttpNetworking(getApplicationContext(),"", new HashMap<String, String>()).execute();
    }
}