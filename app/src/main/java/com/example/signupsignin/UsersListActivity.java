package com.example.signupsignin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.signupsignin.Controller.PostApiCall;

public class UsersListActivity extends AppCompatActivity implements PostApiCall.AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
    }
    @Override
    public void finalResponse(String token) {

    }
}
