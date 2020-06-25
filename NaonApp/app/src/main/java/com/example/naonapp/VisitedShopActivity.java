package com.example.naonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class VisitedShopActivity extends AppCompatActivity {

    private TextView name, jsonRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited_shop);

        name  = (TextView)findViewById(R.id.name);
        jsonRes = (TextView)findViewById(R.id.jsonRes);

        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);

        name.setText(sharedPreferences.getString("name", "")+"님 환영합니다.");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("no", String.valueOf(sharedPreferences.getInt("no", 0)));

        try {
            JSONObject jsonObject = new HttpNetworking(getApplicationContext(), "/getVisitedShopList", map).execute().get();
            //Log.d("RES", jsonObject.toString());

            if(jsonObject == null){
                jsonRes.setText("데이터가 없습니다.");
            }
            else {
                jsonRes.setText(jsonObject.toString());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 


    }
}