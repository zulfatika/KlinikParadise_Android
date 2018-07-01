package com.example.asus.klinikparadise.Tool;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asus.klinikparadise.Modal.LoginResponse;

/**
 * Created by Mehisa Nanda on 12/06/2017.
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "db_klinik_paradise";
    // All Table
    private static final String TABLE_USER_LOGIN = "user_pasien";

    //Column Name
    private static final String KEY_ID_PASIEN = "id_pasien";
    private static final String KEY_NIK = "nik";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_TEMPAT_LAHIR = "tpt_lahir";
    private static final String KEY_TANGGAL_LAHIR = "tgl_lahir";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_TELEPON = "telepon";
    private static final String KEY_JENIS_KELAMIN = "jenis_kelamin";
    private static final String KEY_RIWAYAT_ALERGI = "riwayat_alergi";

    private int id;
    SQLiteDatabase db;
    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        createUserTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Function To Create User Table
    private void createUserTable(){
        //Structure User Table (ID, NUMBER, NAME, EMAIL, PHONE, TYPE, UPDATED)
        String query = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT NOT NULL, %s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)",
                TABLE_USER_LOGIN, KEY_ID_PASIEN, KEY_NIK, KEY_NAMA, KEY_TEMPAT_LAHIR, KEY_TANGGAL_LAHIR, KEY_ALAMAT, KEY_TELEPON, KEY_JENIS_KELAMIN, KEY_RIWAYAT_ALERGI);
        db.execSQL(query);//Execution
    }

    //Function To Insert Schedule Data Table
    public void insertUserDetails(LoginResponse data){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_PASIEN,data.getId_pasien());
        values.put(KEY_NIK,data.getNik());
        values.put(KEY_NAMA,data.getNama_pasien());
        values.put(KEY_TEMPAT_LAHIR,data.getTempat_lahir());
        values.put(KEY_TANGGAL_LAHIR,data.getTgl_lahir());
        values.put(KEY_ALAMAT,data.getAlamat());
        values.put(KEY_TELEPON,data.getNo_telp());
        values.put(KEY_JENIS_KELAMIN,data.getJenis_kelamin());
        values.put(KEY_RIWAYAT_ALERGI,data.getRiwayat_alergi());
        db.insert(TABLE_USER_LOGIN, null, values);
        db.close();
    }

}
