package com.example.nevigationdrawer;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PayActivity extends AppCompatActivity {

    SearchView searchView;
    SearchView searchContact;
    EditText editAmount;
    private  static  String URL_REGIST = "http://192.168.1.109/transaction.php";
    ListView listContact;
    ListView listView;
    ArrayList<String> list;
    ArrayList<String> listCon;
    ArrayAdapter<String > adapter;
    ArrayAdapter<String> adapterContact;

    LinearLayout qrLay;
    RequestQueue requestQueue;

   private String urlJsonObj = "https://api.androidhive.info/volley/person_object.json";

    private static String TAG = PayActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private TextView txtResponse;
    private String jsonResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        qrLay = findViewById(R.id.qrlay);
        requestQueue = Volley.newRequestQueue(PayActivity.this);;

        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("");

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

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
        editAmount = findViewById(R.id.edit_amount);
    }

    public void sendConfirmed(View view) {
//        Intent intent = new Intent(this, SendSuccessActivity.class);
//        startActivity(intent);
//        finish();
//        send();
//        fetchJsonResponse();
//        notSpecified();
        makeJsonObjectRequest();
    }
    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("name");
//                    String email = response.getString("email");
////                    JSONObject phone = response.getJSONObject("phone");
////                    String home = phone.getString("home");
////                    String mobile = phone.getString("mobile");
////
////                    jsonResponse = "";
////                    jsonResponse += "Name: " + name + "\n\n";
////                    jsonResponse += "Email: " + email + "\n\n";
////                    jsonResponse += "Home: " + home + "\n\n";
////                    jsonResponse += "Mobile: " + mobile + "\n\n";

//                    txtResponse.setText(jsonResponse);
                    System.out.println(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });
        // Adding request to request queue
        requestQueue.add(jsonObjReq);
    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    @SuppressLint("StaticFieldLeak")
    public void notSpecified(){
        new AsyncTask<String, Integer, String>() {
            Context context;
            @Override
            protected String doInBackground(String... params) {

                StringBuilder responseString = new StringBuilder();
                try {
                    HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://192.168.1.109/transaction.php").openConnection();
                    urlConnection.setRequestMethod("POST");
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200){
                        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            responseString.append(line);
                        }
                    }
                    urlConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return responseString.toString();
            }

            @Override
            protected void onPostExecute(String o) {
                Toast toast = Toast.makeText(context, (CharSequence) o, Toast.LENGTH_SHORT);
                toast.show();

                /* if your json structure this type then it will work now
                 * {
                 *   "id":123,
                 *   "name":abc,
                 *   "status":"ok"
                 * }
                 */
                try {
                    JSONObject jsonObject = new JSONObject(o);
//                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("success");
                    String status = jsonObject.getString("message");
                    Toast.makeText(PayActivity.this, "name #"+name+", status #"+status, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) { e.printStackTrace(); }


            }
        }.execute("");
    }

    private void fetchJsonResponse() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(PayActivity.this);

        final String amount = this.editAmount.getText().toString().trim();
        final String contact = this.searchContact.getQuery().toString().trim();
        final String transaction = "1";
            Map<String , String> params = new HashMap<>();
            params.put("amount" , amount);
            params.put("contact",contact);
            params.put("transaction",transaction);
            JSONObject jsonObject = new JSONObject(params);
            System.out.println(jsonObject);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, "http://localhost/api.php", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String name = response.getString("status");
                            String result = "Your IP Address is " + response.getString("status");
                            Toast.makeText(PayActivity.this, result, Toast.LENGTH_SHORT).show();
                            System.out.println(name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("jjjjjjjjjjjjjjj");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.data != null) {
                    String jsonError = new String(networkResponse.data);
                    // Print Error!
                    VolleyLog.e("Error: ", error.networkResponse.statusCode);
                }

                System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
            }
        });
        mRequestQueue.add(req);
    }
//    public void send(){
//        System.out.println("send executed!!!!!!!!");
//        final String amount = this.editAmount.getText().toString().trim();
//        final String contact = this.searchContact.getQuery().toString().trim();
//        final String transaction = "1";
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject((response));
//                            String success = jsonObject.getString("success");
//                            if(success.equals("1") ){
//                                Toast.makeText(PayActivity.this,"okay", Toast.LENGTH_SHORT);
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(PayActivity.this," not okay!!!!!!", Toast.LENGTH_SHORT);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(PayActivity.this,"not okay!!!!!!", Toast.LENGTH_SHORT);
//            }
//        })
//        {
//            protected Map<String , String> getParams() throws AuthFailureError{
//                Map<String , String> params = new HashMap<>();
//                params.put("amount" , amount);
//                params.put("contact",contact);
//                params.put("transaction",transaction);
//                System.out.println(params.get("transaction"));
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
    public void payActivityBack(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
