package com.example.asus.klinikparadise.Modal;

/**
 * Created by Asus on 07/06/2018.
 */

public class DaftarAntrianResponse {
    private String id_antrian;
    private String tgl_periksa;
    private String urutan_antrian;
    private String status_cek;
    private String id_pasien;
    private String id_poli;

    public String getId_antrian() {
        return id_antrian;
    }

    public void setId_antrian(String id_antrian) {
        this.id_antrian = id_antrian;
    }

    public String getTgl_periksa() {
        return tgl_periksa;
    }

    public void setTgl_periksa(String tgl_periksa) {
        this.tgl_periksa = tgl_periksa;
    }

    public String getUrutan_antrian() {
        return urutan_antrian;
    }

    public void setUrutan_antrian(String urutan_antrian) {
        this.urutan_antrian = urutan_antrian;
    }

    public String getStatus_cek() {
        return status_cek;
    }

    public void setStatus_cek(String status_cek) {
        this.status_cek = status_cek;
    }

    public String getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(String id_pasien) {
        this.id_pasien = id_pasien;
    }

    public String getId_poli() {
        return id_poli;
    }

    public void setId_poli(String id_poli) {
        this.id_poli = id_poli;
    }
}
