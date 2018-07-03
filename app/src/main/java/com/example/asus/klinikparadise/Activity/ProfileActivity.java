package com.example.asus.klinikparadise.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.klinikparadise.ApiConfig.BaseApiService;
import com.example.asus.klinikparadise.ApiConfig.UtilApi;
import com.example.asus.klinikparadise.Modal.MessageResponse;
import com.example.asus.klinikparadise.Modal.ProfileResponse;
import com.example.asus.klinikparadise.R;
import com.example.asus.klinikparadise.Tool.PrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private EditText edtNomerKartu, edtNIK, edtNama, edtTempat, edtAlamat, edtTelp, edtRiwayat;
    private TextView edtTanggalLahir;
    private BaseApiService mApiService;
    private PrefManager mPref;
    private Button btnSubmit, btnLogout, btnBatal;
    private RadioGroup radioGroup;
    private String kode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mPref        = new PrefManager(getApplicationContext());
        mApiService  = UtilApi.getAPIService();

        btnSubmit = findViewById(R.id.btnDaftar);
        btnLogout = findViewById(R.id.btnLogout);
        btnBatal = findViewById(R.id.btnBatal);

        //edtNomerKartu = findViewById(R.id.edtNomerKartu);
        edtNIK = findViewById(R.id.edtNIK);
        edtNama = findViewById(R.id.edtName);
        edtTempat = findViewById(R.id.edtTempatLahir);
        edtTanggalLahir = findViewById(R.id.edtTanggalLahir);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtTelp = findViewById(R.id.edtNoTelp);
        edtRiwayat = findViewById(R.id.edtRiwayatAlergi);

        radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.laki){
                    kode = "l";
                }else if (i == R.id.perempuan){
                    kode = "p";
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doingUdpate();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPref.logoutUser();
            }
        });
    }

    private void doingUdpate() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        mApiService.update_profile(
                new PrefManager(getApplicationContext()).getIdUser(),
                edtNama.getText().toString(),
                edtTempat.getText().toString(),
                edtTanggalLahir.getText().toString(),
                edtAlamat.getText().toString(),
                edtTelp.getText().toString(),
                kode,
                edtRiwayat.getText().toString()
        ).enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ProfileActivity.this, "Failed to get server", Toast.LENGTH_SHORT).show();
                }
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {

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

        mApiService.profile(mPref.getIdUser())
                .enqueue(new Callback<ProfileResponse>() {
                    @Override
                    public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body().status){
                                //edtNomerKartu.setText(response.body().data.get(0).no_kartu);
                                edtNIK.setText(response.body().data.get(0).nik);
                                edtNama.setText(response.body().data.get(0).nama_pasien);
                                edtTempat.setText(response.body().data.get(0).tempat_lahir);
                                edtTanggalLahir.setText(response.body().data.get(0).tgl_lahir);
                                edtAlamat.setText(response.body().data.get(0).alamat);
                                edtTelp.setText(response.body().data.get(0).no_telp);
                                edtRiwayat.setText(response.body().data.get(0).riwayat_alergi);
                                if (response.body().data.get(0).jenis_kelamin.equals("p")){
                                    radioGroup.check(R.id.perempuan);
                                }else {
                                    radioGroup.check(R.id.laki);
                                }
                            }else {
                                Toast.makeText(ProfileActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ProfileActivity.this, "Failed to get server", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.hide();
                    }

                    @Override
                    public void onFailure(Call<ProfileResponse> call, Throwable t) {

                    }
                });
    }
}
