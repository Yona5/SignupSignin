package com.example.signupsignin;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.signupsignin.Controller.GetApiCall;
import com.example.signupsignin.Controller.PostApiCall;
import com.example.signupsignin.Model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetApiCall.AsyncResponse,
        View.OnClickListener, PostApiCall.AsyncResponse{

    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loginBtn = findViewById(R.id.loginBtn);
        this.loginBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case (R.id.loginBtn):
                GetApiCall getApiCall = new GetApiCall(this);
                getApiCall.execute();

                PostApiCall postApiCall = new PostApiCall(this);
                postApiCall.execute();
                break;
        }
    }

    @Override
    public void finalResponse(ArrayList<User> users) {
        System.out.println(users.size());
    }

    @Override
    public void finalResponse(String token) {
        System.out.println(token);
    }
}
