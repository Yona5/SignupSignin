package com.example.signupsignin;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.example.signupsignin.Controller.PostApiCall;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PostApiCall.AsyncResponse{
    private ProgressBar spinner;
    private Button loginBtn;
    private String token = "";
    private EditText email;
    private EditText password;

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
                checkFields();
                break;
        }
    }

    @Override
    public void finalResponse(String token) {
        this.token = token;
        goToNextView();
        this.spinner.setVisibility(View.GONE);
    }

    public void checkFields(){
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            new AlertDialog.Builder(this)
                    .setTitle("Empty email or password")
                    .setMessage("Please enter your email and password")
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else{
            PostApiCall postApiCall = new PostApiCall(this);
            postApiCall.execute();
            this.spinner = findViewById(R.id.progressBar);
            this.spinner.setVisibility(View.VISIBLE);
        }
    }
    
    public void goToNextView(){
        if(!this.token.isEmpty()){
            Intent intent = new Intent(this, UsersListActivity.class);
            this.startActivity(intent);
        }
    }
}
