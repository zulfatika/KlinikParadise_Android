package com.example.asus.klinikparadise.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.klinikparadise.ApiConfig.BaseApiService;
import com.example.asus.klinikparadise.ApiConfig.UtilApi;
import com.example.asus.klinikparadise.Modal.LIstPoliResponse;
import com.example.asus.klinikparadise.Modal.RegistResponse;
import com.example.asus.klinikparadise.R;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayananActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Object> listPoli = new ArrayList<>();
    private SlimAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan);

        recyclerView = findViewById(R.id.recycle);
        manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = SlimAdapter.create()
                .register(R.layout.item_layanan, new SlimInjector<LIstPoliResponse.Poli>() {
                    @Override
                    public void onInject(final LIstPoliResponse.Poli data, IViewInjector injector) {
                        injector.with(R.id.item, new IViewInjector.Action() {
                            @Override
                            public void action(View view) {
                                TextView text = view.findViewById(R.id.text);
                                text.setText(data.nama_poli);

                                view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getApplicationContext(), KonfirmasiDataActivity.class);
                                        intent.putExtra("id_poli", data.id_poli);
                                        intent.putExtra("nama_poli", data.nama_poli);
                                        startActivity(intent);
                                    }
                                });

                            }
                        });
                    }
                }).attachTo(recyclerView);

        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        BaseApiService mApiService = UtilApi.getAPIService();
        mApiService.getPoli()
                .enqueue(new Callback<LIstPoliResponse>() {
                    @Override
                    public void onResponse(Call<LIstPoliResponse> call, Response<LIstPoliResponse> response) {
                        Log.e("Data", "data : Proses");
                        if (response.isSuccessful()){
                            Log.e("Data", "data : Sukses");
                                if (response.body().data.size() != 0) {
                                    for (LIstPoliResponse.Poli poli : response.body().data) {
                                        listPoli.add(poli);
                                    }
                                }
                        }else {
                            Log.e("Data", "data : Gagal");
                        }
                        progressDialog.hide();
                        adapter.updateData(listPoli);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<LIstPoliResponse> call, Throwable t) {
                        Log.e("Data", "data : "+t);
                    }
                });
    }
}
