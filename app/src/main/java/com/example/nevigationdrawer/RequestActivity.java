package com.example.nevigationdrawer;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class RequestActivity extends AppCompatActivity {

    SearchView searchApp;
    SearchView searchContact;
    ListView listContact;
    ListView listApp;
    ArrayList<String> list;
    ArrayList<String> listCon;
    ArrayAdapter<String > adapter;
    ArrayAdapter<String> adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toolbar toolbar1 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("");

        list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Pineapple");
        list.add("Orange");
        list.add("Lychee");
        list.add("Gavava");
        list.add("Peech");
        list.add("Melon");
        list.add("Watermelon");
        list.add("Papaya");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);

        listCon = new ArrayList<>();
        listCon.add("12345");
        listCon.add("12348657");
        listCon.add("564654567");
        listCon.add("99999999");
        adapterContact = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listCon);

        searchContact = (SearchView) findViewById(R.id.ser_con_con);
        listContact = (ListView) findViewById(R.id.list_con_con);
        listContact.setAdapter(adapterContact);
        listContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectContact = listContact.getItemAtPosition(position).toString();
                searchContact.setQuery(selectContact , true);
                listContact.setVisibility(View.GONE);

            }
        });
        searchContact.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterContact.getFilter().filter(s);
                listApp.setVisibility(View.GONE);
                listContact.setVisibility(View.VISIBLE);
                return false;
            }
        });
        searchApp = (SearchView) findViewById(R.id.ser_con_app);
        listApp = (ListView) findViewById(R.id.list_con_app);
        listApp.setAdapter(adapter);

        listApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = listApp.getItemAtPosition(position).toString();
                searchApp.setQuery(selectedItem,true);
                listApp.setVisibility(View.GONE);
                System.out.println("**************"+selectedItem);
            }
        });
        searchApp.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(list.contains(query)){

//                    adapter.getFilter().filter(query);
//                    searchView.setQuery(listView.getTextFilter(),false);
//                    searchView.setTooltipText(adapter.getItem(adapter.getPosition(query)));
//                    searchView.setQuery(listView.getAdapter().toString(),true);
//                    searchView.setQuery(adapter.getItem(adapter.getPosition(query)),true);
                }else{
                    Toast.makeText(RequestActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                RelativeLayout rr = findViewById(R.id.radd);
                rr.setVisibility(View.GONE);
                adapter.getFilter().filter(newText);
                listContact.setVisibility(View.GONE);
                listApp.setVisibility(View.VISIBLE);
                return false;
            }
        });

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
