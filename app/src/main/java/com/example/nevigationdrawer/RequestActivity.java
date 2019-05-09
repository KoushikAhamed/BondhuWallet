package com.example.nevigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;


public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toolbar toolbar1 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("");

    }
    public void requestSuccess(View view){
        Intent intent =new Intent(RequestActivity.this , RequestSuccessActivity.class);
        startActivity(intent);
    }

    public void requestActivityBack(View view) {
        Intent intent = new Intent(RequestActivity.this , MainActivity.class);
        startActivity(intent);
    }
    public void receiveQRCode(View view){
        Intent intent = new Intent(RequestActivity.this,QRCode.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
