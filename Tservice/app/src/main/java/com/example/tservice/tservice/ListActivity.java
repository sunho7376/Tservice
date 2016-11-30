package com.example.tservice.tservice;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.net.URL;

public class ListActivity extends AppCompatActivity {

    TextView txtView;
    phpdo task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //flag[0]이 뭐인지
        Query mQuery = new Query(new CallBackListener<String>() {
            @Override
            public void onSuccess(String value) {
                String[] flag = value.split(";");
                if (flag[0].equals("success")) {

                    task = new phpdo();
                    txtView = (TextView) findViewById(R.id.txtView);
                    task.execute(flag[0]);
                }
            }
        }, "select", "select * from `user_open` where user_id='user'");
    }

    private class phpdo extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
                String id = arg0[0];

                String link = "http://yangjunghoon.xyz/open/query.php" + id;
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception" + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            txtView.setText(s);
        }
    }
}
//    String myJSON;
//    private static final String TAG_RESULTS = "allTickets";
//    private static final String TAG_TITLE = "ticketTitle";
//    private static final String TAG_NUMBER = "ticketNumber";
//    private static final String TAG_PRICE = "ticketPrice";
//
//    InputMethodManager imm;
//
//
//    JSONArray tickets = null;
//
//    ArrayList<HashMap<String, String>> TicketList;
//
//    ListView list;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list);
//        list = (ListView) findViewById(R.id.listView);
//        TicketList = new ArrayList<HashMap<String, String>>();
//        query("http://yangjunghoon.xyz/open/query.php");
//
//        Query mmQuery = new Query(new CallBackListener<String>() {
//            @Override
//            public void onSuccess(String value) {
//            }
//        }, "select", "select * from `post_open` where user_pk=user");
//    }
//
//    protected void showList(){
//        try {
//            JSONObject jsonObj = new JSONObject(myJSON);
//            tickets = jsonObj.getJSONArray(TAG_RESULTS);
//
//            for (int i=0; i<tickets.length(); i++){
//                JSONObject c = tickets.getJSONObject(i);
//                String title = c.getString(TAG_TITLE);
//                String number = c.getString(TAG_NUMBER);
//                String price = c.getString(TAG_PRICE);
//
//                HashMap<String,String> tickets = new HashMap<String,String>();
//
//                tickets.put(TAG_TITLE,title);
//                tickets.put(TAG_NUMBER,number);
//                tickets.put(TAG_PRICE,price);
//
//                TicketList.add(tickets);
//            }
//            ListAdapter adapter = new SimpleAdapter(
//                    ListActivity.this, TicketList, R.layout.list_item,
//                    new String[]{TAG_TITLE, TAG_NUMBER, TAG_PRICE},
//                    new int[]{R.id.ticketTitle, R.id.ticketNumber, R.id.ticketPrice}
//            );
//            list.setAdapter(adapter);
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void query(String url){
//        class GetQuery extends AsyncTask<String, Void, String> {
//
//            @Override
//            protected String doInBackground(String... params){
//                String uri = params[0];
//
//                BufferedReader bufferedReader = null;
//                try{
//                    URL url = new URL(uri);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//
//                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//                    String json;
//                    while ((json = bufferedReader.readLine())!=null){
//                        sb.append(json+"\n");
//                    }
//                    return sb.toString().trim();
//                }catch (Exception e){
//                    return null;
//                }
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                myJSON=s;
//                showList();
//            }
//        }
//        GetQuery q = new GetQuery();
//        q.execute(url);
//    }
//}