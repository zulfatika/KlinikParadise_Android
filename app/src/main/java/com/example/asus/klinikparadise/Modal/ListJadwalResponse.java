package com.example.asus.klinikparadise.Modal;

import java.util.List;

/**
 * Created by Asus on 04/06/2018.
 */

public class ListJadwalResponse {
    public String status;
    public List<Jadwal> data;

    public class Jadwal{
        public int id_jadwal;
        public String shift_praktek;
        public String hari_praktek;
        public String jam_praktek;
    }

}
