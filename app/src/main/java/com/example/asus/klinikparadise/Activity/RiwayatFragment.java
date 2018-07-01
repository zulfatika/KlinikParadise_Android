package com.example.asus.klinikparadise.Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.asus.klinikparadise.R;

/**
 * Created by Asus on 25/06/2018.
 */

public class RiwayatFragment extends Fragment {
    ImageButton btnDelete;
    private View view;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_riwayat, container, false);
        }
        else {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        recyclerView = (RecyclerView)view.findViewById(R.id.recycle);
        btnDelete   = (ImageButton) view.findViewById(R.id.btnDelete);

        return view;
    }
}
