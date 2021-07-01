package com.kgprostudio.mytrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TypeSelectionActivity extends AppCompatActivity {

    private Button truck_btn, excavator_btn;
    static final String truck_str = "truck", excavator_str = "excavator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selection);

        truck_btn = findViewById(R.id.truck_btn);
        excavator_btn = findViewById(R.id.excavator_btn);

    }

    public void onClick(View view) {

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