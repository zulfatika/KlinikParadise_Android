package com.example.asus.klinikparadise.Tool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.asus.klinikparadise.Activity.LoginActivity;
import com.example.asus.klinikparadise.Modal.DaftarAntrianResponse;
import com.example.asus.klinikparadise.Modal.LoginResponse;

import java.util.HashMap;

/**
 * Created by NandaRahman on 18/04/2018.
 */
public class PrefManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private Intent i;
    private int PRIVATE_MODE = 0;
    private SQLiteHandler mData;
    private static final String PREF_NAME = "klinik_paradise";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_LOGIN = "IsLoggedIn";
    //private static final String IS_DAFTAR_ANTRIAN = "IsDaftarAntrian";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "user_name";
//    public static final String KEY_USER_NOMOR_KARTU = "user_nomor_kartu";
//    public static final String KEY_USER_TAMBAH_ANTRIAN = "user_tambah_antrian";

    public PrefManager(Context context) {
        this._context = context;
        mData = new SQLiteHandler(context);
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    //Membuat Login
    public void makeLogin(LoginResponse user){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USER_ID, user.getNik());
        editor.putString(KEY_USER_NAME, user.getNama_pasien());
        editor.commit();
        mData.insertUserDetails(user);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USER_ID, pref.getString(KEY_USER_ID, null));
        user.put(KEY_USER_NAME, pref.getString(KEY_USER_NAME, null));
        return user;
    }

    public String getIdUser(){
        return pref.getString(KEY_USER_ID, null);
    }

    public String getNameUser(){
        return pref.getString(KEY_USER_NAME, null);
    }

    //Membuat DaftarAntrianResponse
//    public void makeDaftarAntrian(DaftarAntrianResponse user){
//        editor.putBoolean(IS_DAFTAR_ANTRIAN, true);
//        editor.putString(KEY_USER_NOMOR_KARTU, user.getNik());
//        editor.putString(KEY_USER_TAMBAH_ANTRIAN, user.getNama_pasien());
//        editor.commit();
//        mData.insertUserDetails(user);
//    }
//
//    public boolean isDaftarAntrian(){
//        return pref.getBoolean(IS_DAFTAR_ANTRIAN, false);
//    }
//
//    public HashMap<String, String> getUserDetails(){
//        HashMap<String, String> user = new HashMap<String, String>();
//        user.put(KEY_USER_NOMOR_KARTU, pref.getString(KEY_USER_NOMOR_KARTU, null));
//        user.put(KEY_USER_TAMBAH_ANTRIAN, pref.getString(KEY_USER_TAMBAH_ANTRIAN, null));
//        return user;
//    }
//
//    public String getIdUser(){
//        return pref.getString(KEY_USER_NOMOR_KARTU, null);
//    }
//
//    public String getNameUser(){
//        return pref.getString(KEY_USER_TAMBAH_ANTRIAN, null);
//    }


    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }
}
