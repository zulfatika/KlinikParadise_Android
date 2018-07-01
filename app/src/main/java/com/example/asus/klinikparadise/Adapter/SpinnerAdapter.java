package com.example.asus.klinikparadise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.klinikparadise.R;

import java.util.ArrayList;

/**
 * Created by Asus on 05/06/2018.
 */

public class SpinnerAdapter extends BaseAdapter{
    Context context;
    ArrayList<SpinnerItem> data;
    LayoutInflater inflater;

    public SpinnerAdapter(Context context, ArrayList<SpinnerItem> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_spinner, null);
        TextView text = view.findViewById(R.id.item);
        text.setText(data.get(i).getName());
        return view;
    }
}
