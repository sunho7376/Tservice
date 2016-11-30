package com.example.tservice.tservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.net.URL;

public class ListActivity extends AppCompatActivity {
    String myJSON;
    private static final String TAG_RESULTS = "allTickets";
    private static final String TAG_TITLE = "ticketTitle";
    private static final String TAG_NUMBER = "ticketNumber";
    private static final String TAG_PRICE = "ticketPrice";


    JSONArray tickets = null;

    ArrayList<HashMap<String, String>> TicketList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        list = (ListView) findViewById(R.id.listView);
        TicketList = new ArrayList<HashMap<String, String>>();
        query("http://yangjunghoon.xyz/open/query.php");
    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            tickets = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i=0; i<tickets.length(); i++){
                JSONObject c = tickets.getJSONObject(i);
                String title = c.getString(TAG_TITLE);
                String number = c.getString(TAG_NUMBER);
                String price = c.getString(TAG_PRICE);

                HashMap<String,String> tickets = new HashMap<String,String>();

                tickets.put(TAG_TITLE,title);
                tickets.put(TAG_NUMBER,number);
                tickets.put(TAG_PRICE,price);

                TicketList.add(tickets);
            }
            ListAdapter adapter = new SimpleAdapter(
                    ListActivity.this, TicketList, R.layout.list_item,
                    new String[]{TAG_TITLE, TAG_NUMBER, TAG_PRICE},
                    new int[]{R.id.ticketTitle, R.id.ticketNumber, R.id.ticketPrice}
            );
            list.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void query(String url){
        class GetQuery extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params){
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine())!=null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                }catch (Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                myJSON=s;
                showList();
            }
        }
        GetQuery q = new GetQuery();
        q.execute(url);
    }
}