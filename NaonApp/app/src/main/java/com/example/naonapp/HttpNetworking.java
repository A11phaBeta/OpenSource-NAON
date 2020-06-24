package com.example.naonapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.naonapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class HttpNetworking extends AsyncTask<Void, Void, JSONObject> {

    private Context mContext = null;
    private String mQueryString;
    private HashMap<String, String> mParams;

    public HttpNetworking(Context context, String queryString, HashMap<String, String> params){
        mContext = context;
        mQueryString = queryString;
        mParams = params;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {

        JSONObject object = null;
        try {
            URL url = new URL(mContext.getString(R.string.host) + mQueryString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);                // 읽기모드 지정
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);             // 캐싱데이터를 받을지 안받을지
            httpURLConnection.setConnectTimeout(15000);        // 통신 타임아웃

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            StringBuffer sb = new StringBuffer();

            boolean isAnd = false;
            for(String key:mParams.keySet()){
                if(isAnd){
                    sb.append("&");
                }
                sb.append(key).append("=").append(mParams.get(key));
                if(!isAnd){
                    if(mParams.size() >= 2){
                        isAnd = true;
                    }
                }
            }

            wr.write(sb.toString());
            wr.flush();
            wr.close();


            int responseCode = httpURLConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED){
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }
                in.close();
                object = new JSONObject(response.toString());
            }else {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }
                in.close();
                object = new JSONObject(response.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        if(jsonObject != null)
            Log.d("RES", jsonObject.toString());

    }
}