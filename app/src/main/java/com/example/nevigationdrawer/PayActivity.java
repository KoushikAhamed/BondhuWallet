package com.example.nevigationdrawer;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

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


        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);



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
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                    searchView.setQuery(listView.getTextFilter(),false);
//                    searchView.setTooltipText(adapter.getItem(adapter.getPosition(query)));
//                    searchView.setQuery(listView.getAdapter().toString(),true);
//                    searchView.setQuery(adapter.getItem(adapter.getPosition(query)),true);
                }else{
                    Toast.makeText(PayActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);


                return false;
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
//        Intent intent = new Intent(this,QRCode.class);
//
//    }

    public void sendConfirmed(View view){

        Intent intent = new Intent(this, SendSuccessActivity.class);
        startActivity(intent);
    }


    public void payActivityBack(View view) {
        Intent intent = new Intent(PayActivity.this , MainActivity.class);
        startActivity(intent);
    }




}
