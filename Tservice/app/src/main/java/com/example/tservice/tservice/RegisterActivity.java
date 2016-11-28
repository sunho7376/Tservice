package com.example.tservice.tservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity
{
    Query mQuery;

    EditText idEditText;
    EditText pwEditText;
    EditText nameEditText;
    EditText contactEditText;
    EditText addressEditText;
    Button registerBtn;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = new Intent(this.getIntent());

        idEditText = (EditText)findViewById(R.id.idEditText);
        pwEditText = (EditText)findViewById(R.id.pwEditText);
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        contactEditText = (EditText)findViewById(R.id.contactEditText);
        addressEditText = (EditText)findViewById(R.id.addressEditText);
        registerBtn = (Button)findViewById(R.id.registerBtn);

        idEditText.setFilters(new InputFilter[] {filterAlphaNum, new InputFilter.LengthFilter(20)});
        pwEditText.setFilters(new InputFilter[] {filterAlphaNum, new InputFilter.LengthFilter(20)});
        nameEditText.setFilters(new InputFilter[] {filterSpace, new InputFilter.LengthFilter(16)});
        contactEditText.setFilters(new InputFilter[] {filterContact, new InputFilter.LengthFilter(20)});
        addressEditText.setFilters(new InputFilter[] {filterSpace});

        registerBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (!(
                    idEditText.getText().toString().equals("") ||
                    pwEditText.getText().toString().equals("") ||
                    nameEditText.getText().toString().equals("") ||
                    contactEditText.getText().toString().equals("") ||
                    addressEditText.getText().toString().equals("")
                )) {
                    mQuery = new Query(new CallBackListener<String>(){
                        @Override
                        public void onSuccess(String value) {
                            Log.v("LOG::::", "result : " + value);
                        }
                    }, "select", "select * from `user_open` where user_id='"
                        + idEditText.getText().toString() + "'");
                } else {
                    Log.v("LOG::::", "validation no");
                }
            }
        });
    }

    protected InputFilter filterAlphaNum = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
           int dstart, int dend) {
            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };
    protected InputFilter filterSpace = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
           int dstart, int dend) {
            Pattern ps = Pattern.compile("[^\\s]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };
    protected InputFilter filterContact = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
           int dstart, int dend) {
            Pattern ps = Pattern.compile("^[0-9-]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };
}