package com.example.asus.klinikparadise.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asus.klinikparadise.Adapter.SpinnerAdapter;
import com.example.asus.klinikparadise.Adapter.SpinnerItem;
import com.example.asus.klinikparadise.ApiConfig.BaseApiService;
import com.example.asus.klinikparadise.ApiConfig.UtilApi;
import com.example.asus.klinikparadise.Modal.LIstPoliResponse;
import com.example.asus.klinikparadise.Modal.ListJadwalResponse;
import com.example.asus.klinikparadise.R;
import com.example.asus.klinikparadise.Tool.PrefManager;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonfirmasiDataActivity extends AppCompatActivity {

    private TextView nomerPasien, namaPasien, tglPemeriksaan, poliTujuan;
    private Button btnDaftar, btnBatal;
    private Spinner spinner;
    private Context mContext;
    private BaseApiService mApiService;
    private ArrayList<SpinnerItem> spinnerItems = new ArrayList<>();
    private SpinnerAdapter spinnerAdapter;

    private int idJadwal = 0;
    private int idPoli = 0;
    private Date datenow = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_data);
        init();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void init(){
        mContext            = this;
        mApiService         = UtilApi.getAPIService();
        nomerPasien         = findViewById(R.id.nomorKartu);
        namaPasien          = findViewById(R.id.namaPasien);
        tglPemeriksaan      = findViewById(R.id.tglPeriksa);
        poliTujuan          = findViewById(R.id.poliTujuan);
        btnDaftar           = (Button) findViewById(R.id.btnDaftar);
        btnBatal            = (Button) findViewById(R.id.btnBatal);
        spinner             = findViewById(R.id.spinner);

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KonfirmasiDataActivity.this, DetailAntrianActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Date tanggal_sekarang = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-YYYY");
        try {
            datenow = dateFormat.parse(tanggal_sekarang.toString());
            tglPemeriksaan.setText(datenow.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PrefManager pref = new PrefManager(getApplicationContext());
        nomerPasien.setText(pref.getIdUser());
        namaPasien.setText(pref.getNameUser());
        poliTujuan.setText(getIntent().getExtras().getString("nama_poli"));

        idPoli = getIntent().getExtras().getInt("id_poli");

        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), spinnerItems);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idJadwal = spinnerItems.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        BaseApiService mApiService = UtilApi.getAPIService();
        mApiService.getJadwal()
                .enqueue(new Callback<ListJadwalResponse>() {
                    @Override
                    public void onResponse(Call<ListJadwalResponse> call, Response<ListJadwalResponse> response) {
                        Log.e("Data", "data : Proses");
                        Log.e("Response", response.message() + response.code());
                        if (response.isSuccessful()) {
                            Log.e("Data", "data : Sukses");
                            if (response.body().data.size() != 0) {
                                for (ListJadwalResponse.Jadwal poli : response.body().data) {
                                    spinnerItems.add(new SpinnerItem(poli.id_jadwalklinik, poli.shift_klinik + " (" + poli.jam_buka + " - " + poli.jam_tutup + ")"));
                                }
                            }
                        } else {
                            Log.e("Data", "data : Gagal");
                        }
                        progressDialog.hide();
                        spinnerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ListJadwalResponse> call, Throwable t) {
                        Log.e("Data", "data : " + t);
                    }
                });
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("KonfirmasiData Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
