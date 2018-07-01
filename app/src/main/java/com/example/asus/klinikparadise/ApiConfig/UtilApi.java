package com.example.asus.klinikparadise.ApiConfig;

/**
 * Created by Asus on 25/05/2018.
 */

public class UtilApi {
    public static final String BASE_URL_API = "http://192.168.43.76/klinik_paradise/public/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
