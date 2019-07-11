package com.example.signupsignin.Controller;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class PostApiCall extends AsyncTask<Void, Void, JSONObject> {

    public interface AsyncResponse{
        void finalResponse(String token);
    }

    public PostApiCall.AsyncResponse delegate;

    public PostApiCall(PostApiCall.AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {

        String urlStr = "https://reqres.in/api/login";
        URL url;
        HttpURLConnection urlConn;

        BufferedReader bufferedReader = null;

        try {
            url = new URL(urlStr);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Content-Type", "application/json; utf-8");

            //set response format type
            urlConn.setRequestProperty("Accept", "application/json");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);

            String details = "{\"email\" : \"eve.holt@reqres.in\", \"password\" : \"cityslicka\"}";
            System.out.println(details);
            try(OutputStream os = urlConn.getOutputStream()) {
                os.write(details.getBytes("UTF-8"));
            }
            System.out.println(urlConn.getResponseCode() + urlConn.getResponseMessage());

            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            return new JSONObject(stringBuffer.toString());
        } catch (Exception ex) {
            Log.e("app", "exception", ex);
            return null;
        }finally {
            if (bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPostExecute(JSONObject response) {
        String token = "";
        try{
            token = response.getString("token");
        }catch (Exception ex){

        }
        this.delegate.finalResponse(token);
    }
}
