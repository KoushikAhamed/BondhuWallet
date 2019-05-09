package com.example.nevigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class RequestSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_success);
        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }
    public  void  back(View view){
        Intent intent = new Intent(RequestSuccessActivity.this , RequestActivity.class);
        startActivity(intent);
    }

    public void requestSuccessActivityBack(View view) {
        back(view);
    }
}
