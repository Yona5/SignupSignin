package com.example.signupsignin.Controller;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import com.example.signupsignin.Model.User;


public class GetApiCall extends AsyncTask<Void, Void, JSONObject> {

    public interface AsyncResponse{
        void finalResponse(ArrayList<User> users);
    }

    public AsyncResponse delegate;

    public GetApiCall(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        URLConnection urlConn;
        BufferedReader bufferedReader = null;
        String urlStr = "https://reqres.in/api/users?page=2";
        try {
            URL url = new URL(urlStr);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            return new JSONObject(stringBuffer.toString());
        }catch (Exception ex){
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
        ArrayList<User> users = new ArrayList<>();
        try{
            JSONArray usersList = response.getJSONArray("data");
            for (int i = 0; i < usersList.length(); i++) {
                User user = new User(usersList.getJSONObject(i).getString("first_name"),
                    usersList.getJSONObject(i).getString("last_name"),
                    usersList.getJSONObject(i).getInt("id"),
                    usersList.getJSONObject(i).getString("email"),
                    usersList.getJSONObject(i).getString("avatar")
                );
                users.add(user);
            }
        }catch(Exception ex){
            System.out.println("Exception" +  ex);
        }
        this.delegate.finalResponse(users);
    }
}






