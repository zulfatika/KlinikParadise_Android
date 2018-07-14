package com.example.asus.klinikparadise.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.asus.klinikparadise.R;

public class MainActivity extends AppCompatActivity {
    LinearLayout daftar_periksa;
    LinearLayout history;
    LinearLayout panduan;
    LinearLayout tentang;
    LinearLayout info;
    LinearLayout profil;
    LinearLayout settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daftar_periksa = (LinearLayout)findViewById(R.id.daftar_layanan);
        history = (LinearLayout)findViewById(R.id.history);
        panduan = (LinearLayout)findViewById(R.id.panduan);
        tentang = (LinearLayout)findViewById(R.id.tentang);
        info = (LinearLayout)findViewById(R.id.info);
        profil = (LinearLayout)findViewById(R.id.profil);
        settings = (LinearLayout)findViewById(R.id.settings);

        daftar_periksa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, LayananActivity.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        panduan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, PanduanActivity.class);
                startActivity(intent);
            }
        });

        tentang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, TentangActivity.class);
                startActivity(intent);
            }
        });

        info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        profil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
