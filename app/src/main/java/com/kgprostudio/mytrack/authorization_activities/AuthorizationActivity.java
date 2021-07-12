package com.kgprostudio.mytrack.authorization_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kgprostudio.mytrack.R;

public class AuthorizationActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        username = (EditText) findViewById(R.id.ip_eT);
        password = (EditText) findViewById(R.id.port_eT);
        login = (Button) findViewById(R.id.connect_server_btn);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());

    }

    public void onClick2(View view){

        if (username.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "Вход выполнен!",Toast.LENGTH_SHORT).show();

            // Выполняем переход на другой экран:
            Intent intent = new Intent(AuthorizationActivity.this, ConnectActivity.class);
            startActivity(intent);
        }

        // В другом случае выдаем сообщение с ошибкой:
        else {
            Toast.makeText(getApplicationContext(), "Неправильные данные!", Toast.LENGTH_SHORT).show();
        }

    }


}
