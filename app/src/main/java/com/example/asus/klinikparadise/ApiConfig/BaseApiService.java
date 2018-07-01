package com.example.asus.klinikparadise.ApiConfig;

import com.example.asus.klinikparadise.Modal.DaftarAntrianResponse;
import com.example.asus.klinikparadise.Modal.LIstPoliResponse;
import com.example.asus.klinikparadise.Modal.ListJadwalResponse;
import com.example.asus.klinikparadise.Modal.LoginResponse;
import com.example.asus.klinikparadise.Modal.RegistResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Asus on 25/05/2018.
 */

public interface BaseApiService {
    // Fungsi ini untuk memanggil API http://localhost/laravel/loginAuth
    @FormUrlEncoded
    @POST("pasien/login")
    public Call<LoginResponse> user_login(
            @Field("nik") String nik,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("pasien/register")
    public Call<RegistResponse> user_register(
            @Field("nama_pasien") String nama_pasien,
            @Field("password") String password,
            @Field("nik") String nik,
            @Field("tempat_lahir") String tempat_lahir,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("alamat") String alamat,
            @Field("no_telp") String no_telp,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("riwayat_alergi") String riwayat_alergi
    );

    @GET("getPoli")
    public Call<LIstPoliResponse> getPoli();

    @GET("jadwal")
    public Call<ListJadwalResponse> getJadwal();

    @FormUrlEncoded
    @POST("tambahAntrian")
    public Call<DaftarAntrianResponse> getAntrian(
            @Field("tgl_periksa") String tgl_periksa,
            @Field("urutan_antrian") String urutan_antrian,
            @Field("status_cek") String status_cek,
            @Field("id_pasien") String id_pasien,
            @Field("id_poli") String id_poli
    );



}
