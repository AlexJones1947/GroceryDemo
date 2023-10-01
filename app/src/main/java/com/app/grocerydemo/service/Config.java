package com.app.grocerydemo.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Config {

    public static String myBaseURL = "https://rotas-online.com/RotaWebAPI/";
    public static String myBaseURL1 = "https://rotas-online.com/TestRotaWebAPI/";
    // public static String myBaseURL = "http://10.0.28.5:9023/";
    //public static String myBaseURLGetInfo = "https://rotas-online.com/RotaWebAPIInfo/"; // 31 Jan 2023 enable when set live
    public static String myBaseURLGetInfo = "http://10.0.28.4/RotaWebAPIInfo/";
    public static String rotaBaseURL = "http://10.0.28.5:9023/";
    public static String rotaFindAddress = "https://api.getAddress.io/";
    public static String rotaBankAccount = "https://bawe.accountis.net/";


    private static Retrofit retrofit = null;
    private static Retrofit retrofitRota = null;
    private static Retrofit retrofitGetInfoRota = null;
    private static Retrofit retrofitFindAddress = null;
    private static Retrofit retrofitBankAccount = null;

    public static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .setLenient()
            .create();


    //clock in app
    public static Retrofit getClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
        httpClient.protocols( Collections.singletonList(Protocol.HTTP_1_1) );
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(120, TimeUnit.MINUTES);
        httpClient.readTimeout(120, TimeUnit.MINUTES);
        httpClient.writeTimeout(120, TimeUnit.MINUTES);

// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                  //  .baseUrl(myBaseURL)
                    .baseUrl(myBaseURL1)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    //clock in Employee Portal App

    public static Retrofit getClientRota() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(80, TimeUnit.SECONDS);
        httpClient.writeTimeout(90, TimeUnit.SECONDS);

// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        if (retrofitRota == null) {
            retrofitRota = new Retrofit.Builder()
                    .baseUrl(myBaseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofitRota;
    }

    public static Retrofit getGetInfoClientRota() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(80, TimeUnit.SECONDS);
        httpClient.writeTimeout(90, TimeUnit.SECONDS);

// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        if (retrofitGetInfoRota == null) {
            retrofitGetInfoRota = new Retrofit.Builder()
                    .baseUrl(myBaseURLGetInfo)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofitGetInfoRota;
    }


    public static Retrofit getFindAddress() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(80, TimeUnit.SECONDS);
        httpClient.writeTimeout(90, TimeUnit.SECONDS);

// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        if (retrofitFindAddress == null) {
            retrofitFindAddress = new Retrofit.Builder()
                    .baseUrl(rotaFindAddress)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofitFindAddress;
    }

    public static Retrofit getRetrofitBankAccount() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(80, TimeUnit.SECONDS);
        httpClient.writeTimeout(90, TimeUnit.SECONDS);

// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        if (retrofitBankAccount == null) {
            retrofitBankAccount = new Retrofit.Builder()
                    .baseUrl(rotaBankAccount)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofitBankAccount;
    }


}