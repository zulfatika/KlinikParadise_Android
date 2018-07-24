package com.example.asus.klinikparadise.Modal;

import java.util.List;

/**
 * Created by Asus on 04/06/2018.
 */

public class ListJadwalResponse {
    public boolean status;
    public String message;
    public List<Jadwal> data;

    public class Jadwal{
        public int id_jadwalklinik;
        public String shift_klinik;
        public String jam_buka;
        public String jam_tutup;
    }

}
