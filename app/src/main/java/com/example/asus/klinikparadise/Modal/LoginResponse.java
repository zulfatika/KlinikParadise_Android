package com.example.asus.klinikparadise.Modal;

import retrofit2.http.Field;

/**
 * Created by Asus on 26/05/2018.
 */

public class LoginResponse {
    private String id_pasien;
    private String no_kartu;
    private String nik;
    private String nama_pasien;
    private String password;
    private String tempat_lahir;
    private String tgl_lahir;
    private String alamat;
    private String no_telp;
    private String jenis_kelamin;
    private String riwayat_alergi;

    public String getRiwayat_alergi() {
        return riwayat_alergi;
    }

    public void setRiwayat_alergi(String riwayat_alergi) {
        this.riwayat_alergi = riwayat_alergi;
    }

    public String getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(String id_pasien) {
        this.id_pasien = id_pasien;
    }

    public String getNo_kartu() {
        return no_kartu;
    }

    public void setNo_kartu(String no_kartu) {
        this.no_kartu = no_kartu;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }
}
