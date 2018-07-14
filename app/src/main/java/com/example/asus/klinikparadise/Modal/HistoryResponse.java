package com.example.asus.klinikparadise.Modal;

import java.util.List;

/**
 * Created by Asus on 07/06/2018.
 */

public class HistoryResponse {
    public boolean status;
    public String message;
    public List<Antrian> data;

    public class Antrian{
        public int id_antrian;
        public String tgl_periksa;
        public int urutan_antrian;
        public int status_cek;
        public String nama_poli;
        public String shift_klinik;
    }
}
