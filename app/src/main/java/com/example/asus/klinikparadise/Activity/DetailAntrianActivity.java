package com.example.asus.klinikparadise.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.asus.klinikparadise.R;

public class DetailAntrianActivity extends AppCompatActivity {
    Button historybtn, btnBatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_antrian);

        historybtn = (Button) findViewById(R.id.btnHistory);
        btnBatal   = (Button) findViewById(R.id.btnBatal);

        historybtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(DetailAntrianActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
