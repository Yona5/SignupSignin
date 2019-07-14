package com.example.signupsignin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.signupsignin.Controller.GetApiCall;
import com.example.signupsignin.Model.User;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity implements GetApiCall.AsyncResponse {
    private ListView listView;
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        GetApiCall getApiCall = new GetApiCall(this);
        getApiCall.execute();

    }

    @Override
    public void finalResponse(ArrayList<User> users) {
//        System.out.println(users.get(0).getFname());
        listView = findViewById(R.id.userName);
        userAdapter = new UserAdapter(this, users);
        listView.setAdapter(userAdapter);
    }
}
