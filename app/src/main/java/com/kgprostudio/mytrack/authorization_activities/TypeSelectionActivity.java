package com.kgprostudio.mytrack.authorization_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kgprostudio.mytrack.R;

public class TypeSelectionActivity extends AppCompatActivity {

    private Button truck_btn, excavator_btn;
    static final String truck_str = "truck", excavator_str = "excavator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selection);
        init();
    }

    public void init(){
        truck_btn = findViewById(R.id.truck_btn);
        excavator_btn = findViewById(R.id.excavator_btn);
        onClick();
    }

    public void onClick() {
    truck_btn.setOnClickListener(new View.OnClickListener() {
      @Override
         public void onClick(View v) {
          Intent intent = new Intent(TypeSelectionActivity.this, AuthorizationActivity.class);
          intent.putExtra("truck_key", truck_str);
          startActivity(intent);
         }
        });

    excavator_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TypeSelectionActivity.this, AuthorizationActivity.class);
            intent.putExtra("excavator_key", excavator_str);
            startActivity(intent);
        }
    });
    }
}