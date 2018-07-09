package com.example.asus.klinikparadise.Modal;

/**
 * Created by Asus on 07/06/2018.
 */

public class SaatIniResponse {
    public boolean status;
    public String message;
    public Antrian data;
    public Antrian umum;
    public Antrian gigi;
    public Antrian kecantikan;

    public class Antrian{
        public int id_antrian;
        public String tgl_periksa;
        public int urutan_antrian;
        public int status_cek;
        public String nama_poli;
    }
}
