package com.example.nevigationdrawer;

import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {

    SearchView searchView;
    SearchView searchContact;
    ListView listContact;
    ListView listView;
    ArrayList<String> list;
    ArrayList<String> listCon;
    ArrayAdapter<String > adapter;
    ArrayAdapter<String> adapterContact;

    LinearLayout qrLay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        qrLay = findViewById(R.id.qrlay);

        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("");
//        SearchView searchViewContact = findViewById(R.id.search_contact);
//        searchViewContact.setQueryHint("Search From Contact");


        Button sendQr = (Button)findViewById(R.id.send_qr);
        sendQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayActivity.this,QRCode.class);
                startActivity(intent);
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 0);
//                scan(v);
            }
        });

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

        adapterContact = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listCon);

        searchContact = (SearchView) findViewById(R.id.search_contact);
        listContact = (ListView) findViewById(R.id.list_contact);
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
                listView.setVisibility(View.GONE);
                listContact.setVisibility(View.VISIBLE);
                return false;
            }
        });

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);
        listView.setVisibility(View.GONE );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = listView.getItemAtPosition(position).toString();
                searchView.setQuery(selectedItem,true);
                listView.setVisibility(View.GONE);
                System.out.println("**************"+selectedItem);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

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
                    Toast.makeText(PayActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    RelativeLayout b = findViewById(R.id.balance);
                    b.setVisibility(View.GONE);
                    RelativeLayout r = findViewById(R.id.relative);

                    adapter.getFilter().filter(newText);
                    listContact.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);

                return false;
            }
        });
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                searchView.setQuery(adapter.getItem(position), true);
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {
                return true;
            }
        });
    }

    public void sendConfirmed(View view) {
        Intent intent = new Intent(this, SendSuccessActivity.class);
        startActivity(intent);
    }

    public void payActivityBack(View view) {
        Intent intent = new Intent(PayActivity.this , MainActivity.class);
        startActivity(intent);
    }
}
