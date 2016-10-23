package com.sky31.gonggong.util;

import com.sky31.gonggong.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by caspar on 16-9-12.
 */
public class RetrofitUtils {

    private Retrofit mRetrofit;
    private static RetrofitUtils retrofitUtils;


    //private final String baseUrl = "https://api.douban.com/v2/movie/";
    private final String baseUrl = "http://10.0.2.2/";

    private RetrofitUtils(){
        if (mRetrofit==null) {
            init();
        }
    }

    private void init() {

        OkHttpClient httpClient = new OkHttpClient();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();
    }

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public Retrofit getmRetrofit() {
        return mRetrofit;
    }
}
