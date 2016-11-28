//query 사용 example source
//button을 click하면 query의 결과값이 LOG로 나타남

package com.example.yanghaesuk.ticket2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button bn;
    Query mQuery;
    //먼저 query 객체 생성할 것

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bn = (Button)findViewById(R.id.btn);

        bn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mQuery = new Query(new CallBackListener<String>(){
                    @Override
                    public void onSuccess(String value) {
                        Log.v("LOG:::::::", "" + value);
                        //query의 결과값이 LOG로 찍힘
                    }
                }, "select", "select * from `user_open` where user_id='test_id2'");
            }
            //객체이용해서 query문 실행
            //생성자 구조 : Query(CallBackListener객체, type문자열, query문자열)
            //CallBackListener객체를 생성하며 onSuccess함수 정의 할 것(value parameter로 query의 결과값 return)
            //type string : "insert", "select", "update", "delete" 넷 중 하나
            //query string : query문 그대로 써주면 됨
            //
            //value의 종류
            //insert, update, delete의 경우 query가 잘 실행되면 1 return
            //select의 경우 query가 잘 실행되면
            //success;data;[pk];[id];[pw];[name];data;[pk];[id];[pw];[name];
            //과 같은 식의 string이 return됨
            //세미콜론(;)을 기준으로 자바 함수 split를 이용해 사용할 것
            //select의 결과값이 없을 경우 fail;
            //있을 경우 success;...
            //데이터가 여러개일 경우 data;로 구분되므로
            //마찬가지로 split를 이용해 구분해 사용 가능
        });
    }
}
