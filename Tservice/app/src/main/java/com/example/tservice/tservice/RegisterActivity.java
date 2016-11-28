package com.example.tservice.tservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity
{
    EditText idEditText;
    EditText pwEditText;
    EditText nameEditText;
    EditText contactEditText;
    EditText addressEditText;
    Button registerBtn;

    Intent intent;
    InputMethodManager imm;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        intent = new Intent(this.getIntent());
        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

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

                    Query mQuery = new Query(new CallBackListener<String>(){
                        @Override
                        public void onSuccess(String value) {
                            String[] flag = value.split(";");
                            if (flag[0].equals("success")) {
                                imm.hideSoftInputFromWindow(idEditText.getWindowToken(),0);
                                Toast.makeText(getApplicationContext(), "중복된 아이디 입니다.",
                                    Toast.LENGTH_SHORT).show();
                                //중복된 아이디로 회원가입 시 토트메시지

                            } else {
                                String id = idEditText.getText().toString();
                                String pw = pwEditText.getText().toString();
                                String name = nameEditText.getText().toString();
                                String contact = contactEditText.getText().toString();
                                String address = addressEditText.getText().toString();

                                Query mmQuery = new Query(new CallBackListener<String>(){
                                    @Override
                                    public void onSuccess(String value) {
                                        Toast.makeText(getApplicationContext(), "회원가입 되었습니다.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent mainIntent
                                                = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(mainIntent);
                                    }

                                }, "insert"
                                    , "insert into `user_open` (user_id, pw, name, contact, address)"
                                    + " values ('" + id + "', '" + pw + "', '" + name + "', '"
                                    + contact + "', '" + address + "');");
                                //문제 없으면 회원가입 insert query 실행 후 MainActivity로 이동
                            }

                        }

                    }, "select", "select * from `user_open` where user_id='"
                        + idEditText.getText().toString() + "';");
                } else {
                    Log.v("LOG::::", "validation error");
                }
            }
        });
    }

    //회원가입 입력 EditText Validation 필터들
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