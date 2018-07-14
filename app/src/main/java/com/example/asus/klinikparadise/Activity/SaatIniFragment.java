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
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.klinikparadise.ApiConfig.BaseApiService;
import com.example.asus.klinikparadise.ApiConfig.UtilApi;
import com.example.asus.klinikparadise.Modal.SaatIniResponse;
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

public class SaatIniFragment extends Fragment {
    private View view;

    private ArrayList<SaatIniResponse.Antrian> datas = new ArrayList<>();
    private RecyclerView recyclerView;
    private SlimAdapter adapter;
    private LinearLayoutManager manager;

    private TextView jmlAntrianUmum, jmlAntrianGigi, jmlAntrianKecantikan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_saatini, container, false);
        }
        else {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        jmlAntrianUmum = view.findViewById(R.id.txtPoliUmum);
        jmlAntrianGigi = view.findViewById(R.id.txtPoliGigi);
        jmlAntrianKecantikan = view.findViewById(R.id.txtPolikecantikan);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycle);
        manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = SlimAdapter.create()
                .register(R.layout.item_saatini, new SlimInjector<SaatIniResponse.Antrian>() {
                    @Override
                    public void onInject(final SaatIniResponse.Antrian data, IViewInjector injector) {
                        injector.with(R.id.item_saat_ini, new IViewInjector.Action() {
                            @Override
                            public void action(View view) {
                                TextView nomer = view.findViewById(R.id.txtNomer);
                                TextView jadwal  = view.findViewById(R.id.txtJadwal);
                                TextView tanggal = view.findViewById(R.id.txtTanggal);
                                TextView poli = view.findViewById(R.id.txtPoli);

                                nomer.setText(data.urutan_antrian + "");
                                jadwal.setText(data.shift_klinik);
                                tanggal.setText(data.tgl_periksa);
                                poli.setText(data.nama_poli);

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
        mApiService.antrianSaatIni(reIdUser)
                .enqueue(new Callback<SaatIniResponse>() {
                    @Override
                    public void onResponse(Call<SaatIniResponse> call, Response<SaatIniResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().status){
                                datas.clear();
                                if (response.body().data != null){
                                    datas.add(response.body().data);
                                }
                                if (response.body().kecantikan != null)
                                    jmlAntrianKecantikan.setText(response.body().kecantikan.urutan_antrian + "");
                                if (response.body().gigi != null)
                                    jmlAntrianGigi.setText(response.body().gigi.urutan_antrian + "");
                                if (response.body().umum != null)
                                    jmlAntrianUmum.setText(response.body().umum.urutan_antrian + "");
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
                    public void onFailure(Call<SaatIniResponse> call, Throwable t) {
                        Log.e("Data", "data : " + t);
                    }
                });
    }
}
