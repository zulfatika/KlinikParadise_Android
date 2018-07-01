package com.example.asus.klinikparadise.Modal;

import java.util.List;

/**
 * Created by Asus on 04/06/2018.
 */

public class LIstPoliResponse {
    public String status;
    public List<Poli> data;

    public class Poli{
        public int id_poli;
        public String nama_poli;
        public String keterangan;
    }

}
