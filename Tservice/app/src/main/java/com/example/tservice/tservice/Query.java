package com.example.tservice.tservice;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class Query
{
    private CallBackListener<String> mCallBack;

    private final String urlPath = "http://yangjunghoon.xyz/open/query.php";
    private final String TAG = "PHPTEST";
    private String TYPE = "";
    private String QUERY = "";

    public Query(CallBackListener callback, String type, String query) {
        mCallBack = callback;

        TYPE = type;
        QUERY = query;

        new HttpTask().execute();
    }

    class HttpTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try{
                HttpPost request = new HttpPost(urlPath);

                Vector<NameValuePair> sendData = new Vector<NameValuePair>();

                sendData.add(new BasicNameValuePair("type", TYPE));
                sendData.add(new BasicNameValuePair("query", QUERY));

                HttpEntity enty = new UrlEncodedFormEntity(sendData, HTTP.UTF_8);
                request.setEntity(enty);

                HttpClient client = new DefaultHttpClient();
                HttpResponse res = client.execute(request);

                HttpEntity entityResponse = res.getEntity();
                InputStream im = entityResponse.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(im, HTTP.UTF_8));

                String total = "";
                String tmp = "";

                while((tmp = reader.readLine())!= null)
                {
                    if(tmp != null)
                    {
                        total += tmp;
                    }
                }
                im.close();

                return total;
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();

            }catch(IOException e){
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String value){
            super.onPostExecute(value);
            mCallBack.onSuccess(value);
        }
    }
}