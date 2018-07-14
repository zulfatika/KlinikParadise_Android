package com.example.asus.klinikparadise.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.klinikparadise.ApiConfig.BaseApiService;
import com.example.asus.klinikparadise.ApiConfig.UtilApi;
import com.example.asus.klinikparadise.Modal.HistoryResponse;
import com.example.asus.klinikparadise.R;
import com.example.asus.klinikparadise.Tool.PrefManager;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus on 25/06/2018.
 */

public class RiwayatFragment extends Fragment {
    private View view;

    private ArrayList<HistoryResponse.Antrian> datas = new ArrayList<>();
    private RecyclerView recyclerView;
    private SlimAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_riwayat, container, false);
        }
        else {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = SlimAdapter.create()
                .register(R.layout.item_riwayat, new SlimInjector<HistoryResponse.Antrian>() {
                    @Override
                    public void onInject(final HistoryResponse.Antrian data, IViewInjector injector) {
                        injector.with(R.id.item_riwayat, new IViewInjector.Action() {
                            @Override
                            public void action(View view) {
                                TextView jadwal  = view.findViewById(R.id.txtJadwal);
                                TextView tanggal = view.findViewById(R.id.txtTanggal);
                                TextView poli = view.findViewById(R.id.txtPoli);
                                TextView status = view.findViewById(R.id.txtStatus);
                                ImageButton btnHapus = view.findViewById(R.id.btnDelete);

                                if (data.status_cek == 0){
                                    status.setText("Belum di panggil");
                                }else if (data.status_cek == 1){
                                    status.setText("Sedang di panggil");
                                }else {
                                    status.setText("Selesai di panggil");
                                }

                                jadwal.setText(data.shift_klinik);
                                tanggal.setText(data.tgl_periksa);
                                poli.setText(data.nama_poli);

                                btnHapus.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(view.getContext(), "Hapus !!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(view.getContext(), data.id_antrian + "", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                })
                .attachTo(recyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromServer();
    }

    private void getDataFromServer() {
        final ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final RequestBody reIdUser = RequestBody.create(MediaType.parse("text/plain"), new PrefManager(view.getContext()).getIdUser());

        BaseApiService mApiService = UtilApi.getAPIService();
        mApiService.riwayatAntrian(reIdUser)
                .enqueue(new Callback<HistoryResponse>() {
                    @Override
                    public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().status){
                                if (response.body().data.size() != 0){
                                    datas.clear();
                                    for (HistoryResponse.Antrian data : response.body().data){
                                        datas.add(data);
                                    }
                                }
                            }else {
                                Toast.makeText(view.getContext(), response.body().message, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(view.getContext(), "Gagal connect ke server", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.hide();
                        adapter.updateData(datas);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<HistoryResponse> call, Throwable t) {
                        Log.e("Data", "data : " + t);
                    }
                });
    }
}
