package com.example.asus.klinikparadise.ApiConfig;

import com.example.asus.klinikparadise.Modal.HistoryResponse;
import com.example.asus.klinikparadise.Modal.LIstPoliResponse;
import com.example.asus.klinikparadise.Modal.ListJadwalResponse;
import com.example.asus.klinikparadise.Modal.LoginResponse;
import com.example.asus.klinikparadise.Modal.MessageResponse;
import com.example.asus.klinikparadise.Modal.ProfileResponse;
import com.example.asus.klinikparadise.Modal.RegistResponse;
import com.example.asus.klinikparadise.Modal.SaatIniResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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


    @FormUrlEncoded
    @POST("updateProfile")
    public Call<MessageResponse> update_profile(
            @Field("idProfile") String idProfile,
            @Field("nama_pasien") String nama_pasien,
            @Field("tempat_lahir") String tempat_lahir,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("alamat") String alamat,
            @Field("no_telp") String no_telp,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("riwayat_alergi") String riwayat_alergi
    );

    @FormUrlEncoded
    @POST("profile")
    public Call<ProfileResponse> profile(
            @Field("idProfile") String idProfile
    );

    @GET("getPoli")
    public Call<LIstPoliResponse> getPoli();

    @GET("jadwal")
    public Call<ListJadwalResponse> getJadwal();

    @Multipart
    @POST("tambahAntrian")
    public Call<MessageResponse> tambahAntrian(
            @Part("idPasien") RequestBody idPasien,
            @Part("tanggalPeriksa") RequestBody tanggalPeriksa,
            @Part("idPoli") RequestBody idPoli,
            @Part("idJadwal") RequestBody idJadwal
    );


    @Multipart
    @POST("detailAntrian")
    public Call<SaatIniResponse> antrianSaatIni(
            @Part("idPasien") RequestBody idPasien
    );

    @Multipart
    @POST("history")
    public Call<HistoryResponse> riwayatAntrian(
            @Part("idPasien") RequestBody idPasien
    );
}
