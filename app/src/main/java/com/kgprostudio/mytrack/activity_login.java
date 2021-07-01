package com.kgprostudio.mytrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class activity_login extends AppCompatActivity {

    TextView textView = (TextView) findViewById(R.id.login_textEditor);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login, 0, 0, 0);

    }
}