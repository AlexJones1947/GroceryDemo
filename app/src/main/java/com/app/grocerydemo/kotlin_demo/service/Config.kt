package com.app.grocerydemo.kotlin_demo.service

import retrofit2.Retrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object Config {
    var myBaseURL = "https://rotas-online.com/RotaWebAPI/"
    var myBaseURL1 = "https://rotas-online.com/TestRotaWebAPI/"

    // public static String myBaseURL = "http://10.0.28.5:9023/";
    //public static String myBaseURLGetInfo = "https://rotas-online.com/RotaWebAPIInfo/"; // 31 Jan 2023 enable when set live
    var myBaseURLGetInfo = "http://10.0.28.4/RotaWebAPIInfo/"
    var rotaBaseURL = "http://10.0.28.5:9023/"
    var rotaFindAddress = "https://api.getAddress.io/"
    var rotaBankAccount = "https://bawe.accountis.net/"
    private var retrofit: Retrofit? = null
    private var retrofitRota: Retrofit? = null
    private var retrofitGetInfoRota: Retrofit? = null
    private var retrofitFindAddress: Retrofit? = null
    // set your desired log level
    // add your other interceptors …

    // add logging as last interceptor
    // <-- this is the important line!
    var retrofitBankAccount: Retrofit? = null
        get() {
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            // add your other interceptors …
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(60, TimeUnit.SECONDS)
            httpClient.readTimeout(80, TimeUnit.SECONDS)
            httpClient.writeTimeout(90, TimeUnit.SECONDS)

// add logging as last interceptor
            httpClient.addInterceptor(logging) // <-- this is the important line!
            if (field == null) {
                field = Retrofit.Builder()
                    .baseUrl(rotaBankAccount)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return field
        }
        private set
    var gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .setLenient()
        .create()//  .baseUrl(myBaseURL)// set your desired log level
    // add your other interceptors …

    // add logging as last interceptor
    // <-- this is the important line!
    //clock in app
    val client: Retrofit?
        get() {
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            // add your other interceptors …
            httpClient.protocols(listOf(Protocol.HTTP_1_1))
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(120, TimeUnit.MINUTES)
            httpClient.readTimeout(120, TimeUnit.MINUTES)
            httpClient.writeTimeout(120, TimeUnit.MINUTES)

// add logging as last interceptor
            httpClient.addInterceptor(logging) // <-- this is the important line!
            if (retrofit == null) {
                retrofit = Retrofit.Builder() //  .baseUrl(myBaseURL)
                    .baseUrl(myBaseURL1)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofit
        }// set your desired log level
    // add your other interceptors …

    // add logging as last interceptor
    // <-- this is the important line!
    //clock in Employee Portal App
    val clientRota: Retrofit?
        get() {
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            // add your other interceptors …
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(60, TimeUnit.SECONDS)
            httpClient.readTimeout(80, TimeUnit.SECONDS)
            httpClient.writeTimeout(90, TimeUnit.SECONDS)

// add logging as last interceptor
            httpClient.addInterceptor(logging) // <-- this is the important line!
            if (retrofitRota == null) {
                retrofitRota = Retrofit.Builder()
                    .baseUrl(myBaseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofitRota
        }
    // set your desired log level
    // add your other interceptors …

    // add logging as last interceptor
    // <-- this is the important line!
    val getInfoClientRota: Retrofit?
        get() {
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            // add your other interceptors …
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(60, TimeUnit.SECONDS)
            httpClient.readTimeout(80, TimeUnit.SECONDS)
            httpClient.writeTimeout(90, TimeUnit.SECONDS)

// add logging as last interceptor
            httpClient.addInterceptor(logging) // <-- this is the important line!
            if (retrofitGetInfoRota == null) {
                retrofitGetInfoRota = Retrofit.Builder()
                    .baseUrl(myBaseURLGetInfo)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofitGetInfoRota
        }
    // set your desired log level
    // add your other interceptors …

    // add logging as last interceptor
    // <-- this is the important line!
    val findAddress: Retrofit?
        get() {
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            // add your other interceptors …
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(60, TimeUnit.SECONDS)
            httpClient.readTimeout(80, TimeUnit.SECONDS)
            httpClient.writeTimeout(90, TimeUnit.SECONDS)

// add logging as last interceptor
            httpClient.addInterceptor(logging) // <-- this is the important line!
            if (retrofitFindAddress == null) {
                retrofitFindAddress = Retrofit.Builder()
                    .baseUrl(rotaFindAddress)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofitFindAddress
        }
}