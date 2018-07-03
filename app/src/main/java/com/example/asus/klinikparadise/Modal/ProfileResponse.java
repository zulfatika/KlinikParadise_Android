package com.example.asus.klinikparadise.Modal;

import java.util.List;

/**
 * Created by Asus on 04/06/2018.
 */

public class ProfileResponse {
    public boolean status;
    public String message;
    public List<Profile> data;

    public class Profile{
        public int id_pasien;
        public String no_kartu;
        public String  nik;
        public String  nama_pasien;
        public String  tempat_lahir;
        public String  tgl_lahir;
        public String  alamat;
        public String  no_telp;
        public String  jenis_kelamin;
        public String  riwayat_alergi;
    }
}
