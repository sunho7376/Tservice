package com.example.tservice.tservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class FixUserActivity extends AppCompatActivity
{
    //회원정보 수정 페이지

    Intent intent;
    Intent mainIntent;
    Intent loginedIntent;
    InputMethodManager imm;

    EditText pwEditText;
    EditText nameEditText;
    EditText contactEditText;
    EditText addressEditText;
    Button fixBtn;
    Button cancelBtn;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_user);

        intent = new Intent(this.getIntent());
        mainIntent = new Intent(FixUserActivity.this, MainActivity.class);
        loginedIntent = new Intent(FixUserActivity.this, LoginedActivity.class);
        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        pwEditText = (EditText)findViewById(R.id.pwEditText);
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        contactEditText = (EditText)findViewById(R.id.contactEditText);
        addressEditText = (EditText)findViewById(R.id.addressEditText);
        fixBtn = (Button)findViewById(R.id.fixBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);

        //초기 EditText값 설정
        Query mQuery = new Query(new CallBackListener<String>() {
            @Override
            public void onSuccess(String value) {
                String[] flag = value.split(";");
                if (flag[0].equals("success")) {
                    pwEditText.setText(flag[4]);
                    nameEditText.setText(flag[5]);
                    contactEditText.setText(flag[6]);
                    addressEditText.setText(flag[7]);

                } else {
                    //user primary key 문제시 user정보 초기화 + mainActivity로 돌려버림
                    User.userPk = "";
                    User.loginState= false;
                    startActivity(mainIntent);
                }
            }

        }, "select", "select * from `user_open` where id='" + User.userPk + "';");

        pwEditText.setFilters(new InputFilter[] {filterAlphaNum, new InputFilter.LengthFilter(20)});
        nameEditText.setFilters(new InputFilter[] {filterSpace, new InputFilter.LengthFilter(16)});
        contactEditText.setFilters(new InputFilter[] {filterContact, new InputFilter.LengthFilter(20)});
        addressEditText.setFilters(new InputFilter[] {filterSpace});

        fixBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (!(
                    pwEditText.getText().toString().equals("") ||
                    nameEditText.getText().toString().equals("") ||
                    contactEditText.getText().toString().equals("") ||
                    addressEditText.getText().toString().equals("")
                )) {

                    String pw = pwEditText.getText().toString();
                    String name = nameEditText.getText().toString();
                    String contact = contactEditText.getText().toString();
                    String address = addressEditText.getText().toString();

                    Query mmQuery = new Query(new CallBackListener<String>() {
                        @Override
                        public void onSuccess(String value) {
                            Toast.makeText(getApplicationContext(), "정보가 수정되었습니다.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(loginedIntent);
                        }
                    }, "update", "update `user_open` set pw='" + pw + "', name='" + name
                        + "', contact='" + contact + "', address='" + address
                        + "' where id='" + User.userPk + "';");

                } else {
                    imm.hideSoftInputFromWindow(pwEditText.getWindowToken(),0);
                    Toast.makeText(getApplicationContext(), "잘못된 입력입니다.",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginedIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
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
