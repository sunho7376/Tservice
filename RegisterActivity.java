package com.example.sunho.tservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText edAge = (EditText)findViewById(R.id.etAge);
        final EditText etName = (EditText)findViewById(R.id.etName);
        final EditText edUsername = (EditText)findViewById(R.id.etUsername);
        final EditText edPassword = (EditText)findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
    }
}
