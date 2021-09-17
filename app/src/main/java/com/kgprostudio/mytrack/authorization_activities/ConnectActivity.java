package com.kgprostudio.mytrack.authorization_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.kgprostudio.mytrack.MainActivity;
import com.kgprostudio.mytrack.R;

public class ConnectActivity extends AppCompatActivity {

    private EditText ip_adress, port;
    private Button connect;

    // Реализация окна подлкючения к серверу
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_server);
        ip_adress= findViewById(R.id.ip_eT);
        port = findViewById(R.id.port_eT);
        connect = (Button)findViewById(R.id.connect_server_btn);
    }

    public void onClick1(View view){

        // Получение введенных данных
        String ip = ip_adress.getText().toString();
        int port_num = Integer.parseInt(port.getText().toString());

        // Переход в след. активити
        Intent intent = new Intent(ConnectActivity.this, MainActivity.class);

        // Передача данных  в след. активити
        intent.putExtra("ip", ip);
        intent.putExtra("port", port_num);
        startActivity(intent);
    }
}