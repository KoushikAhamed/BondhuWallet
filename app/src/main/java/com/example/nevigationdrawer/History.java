package com.example.nevigationdrawer;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class History extends AppCompatActivity {
//
//    ListView amount ;
//    ListView con ;
//    ListView trans ;
//    ArrayAdapter<String> adapterAmount;
//    ArrayAdapter<String> adapterCon;
//    ArrayAdapter<Integer> adapterImage;
//    ArrayList<String> arrayAmount;
//    ArrayList<String> arrayCon;
//    ArrayList<Integer> arrayImage;

    String[] countries = new String[] {
            "500",
            "1000",
            "300",
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
            R.drawable.re44,
            R.drawable.pay14,
            R.drawable.re44,

    };

    // Array of strings to store currencies
    String[] currency = new String[]{
            "01933260024",
            "10723232322",
            "04236289909",
    };

//    Transaction transaction;
//    ImageView imagePay = new ImageView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<3;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", countries[i]);
            hm.put("cur", currency[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }
        String[] from = { "flag","txt","cur" };

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt,R.id.cur};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);
        ListView listView = ( ListView ) findViewById(R.id.listview);
        listView.setAdapter(adapter);

//        transaction = new Transaction();
//        transaction.setAmount("500");
//        transaction.setContactNumber("123354567");
////        transaction.setImageTransactin();
//        amount = findViewById(R.id.amount);
//        con = findViewById(R.id.con);
//        trans = findViewById(R.id.transaction);
//
//        arrayAmount = new ArrayList<>();
//        arrayAmount.add("500");
//        arrayAmount.add("400");
//        arrayAmount.add("100");
//        adapterAmount = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayAmount);
//        amount.setAdapter(adapterAmount);
//        arrayCon = new ArrayList<>();
//        arrayCon.add("01932190001");
//        arrayCon.add("01749770919");
//        arrayCon.add("08237777878");
//        adapterCon = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayCon);
//        con.setAdapter(adapterCon);


//        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
//
//        int[] flags = new int[]{
//                R.drawable.send,
//                R.drawable.receive_logo,
//                R.drawable.receive_logo,
//        };
//        HashMap<String, String> hm = new HashMap<String,String>();
//        for(int i= 0; i <3 ; i++){
//            hm.put("image_get", Integer.toString(flags[i]) );
//            aList.add(hm);
//        }
//        String[] from = { "image_get" };
//        int[] to = { R.id.image_get};
//        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.row, from, to);
//        trans.setAdapter(adapter);

//        arrayImage = new ArrayList<>();
//        arrayImage.add(R.drawable.receive_logo);
//        arrayImage.add(R.drawable.send);
//        arrayImage.add(R.drawable.receive_logo);
//        adapterImage = new ArrayAdapter<Integer>(getBaseContext(),R.layout.row,arrayImage);
//        trans.setAdapter(adapterImage);


//        ImageView imageView = findViewById(R.id.image_get);
//        imageView.setImageResource(R.drawable.receive_logo);
//        adapterImage = new ArrayAdapter<Integer>(getBaseContext(),R.layout.row, (List<Integer>) imageView);
//        trans.setAdapter(adapterImage);


    }

    public void hitoryBack(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
