package com.example.tservice.tservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity
{
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

        registerBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (!(
                    idEditText.getText().toString().trim().equals("") ||
                    pwEditText.getText().toString().trim().equals("") ||
                    nameEditText.getText().toString().trim().equals("") ||
                    contactEditText.getText().toString().trim().equals("") ||
                    addressEditText.getText().toString().trim().equals("")
                )) {
                    Log.v("LOG::::", "validation yes");
                } else {
                    Log.v("LOG::::", "validation no");
                }
            }
        });
    }
}