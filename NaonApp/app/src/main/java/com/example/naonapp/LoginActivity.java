package com.example.naonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private EditText id, pw;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = (EditText)findViewById(R.id.id);
        pw = (EditText)findViewById(R.id.pw);

        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idStr, pwStr;

                idStr = id.getText().toString();
                pwStr = pw.getText().toString();

                if(idStr.length() != 0 && pwStr.length() != 0) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("id", id.getText().toString());
                    map.put("pw", pw.getText().toString());

                    try {
                        JSONObject res = new HttpNetworking(getApplicationContext(), "/doLogin", map).execute().get();
                        if(res!=null) {
                            Log.d("RES", String.valueOf(res.getInt("count")));

                            int count = res.getInt("count");

                            if(count == 0){
                                Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                            }else {
                                SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt("no", res.getInt("no"));
                                editor.putString("name", res.getString("name"));
                                editor.commit();

                                Toast.makeText(LoginActivity.this, res.getString("name")+"님 환영합니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginActivity.this, VisitedShopActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}