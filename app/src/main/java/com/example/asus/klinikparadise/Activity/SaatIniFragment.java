package com.example.asus.klinikparadise.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.asus.klinikparadise.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Asus on 25/06/2018.
 */

public class SaatIniFragment extends Fragment {
    Button tambahbtn;
    private View view;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_saatini, container, false);
        }
        else {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        recyclerView = (RecyclerView)view.findViewById(R.id.recycle);
        tambahbtn   = (Button) view.findViewById(R.id.btnTambah);

        /*tambahbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SaatIniFragment.this, LayananActivity.class);
                startActivity(intent);
            }
        });*/

        return view;
    }
}
