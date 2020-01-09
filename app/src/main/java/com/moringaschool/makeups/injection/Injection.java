package com.moringaschool.makeups.injection;

import com.moringaschool.makeups.data.MakeUpRepository;
import com.moringaschool.makeups.data.MakeUpRepositoryImpl;
import com.moringaschool.makeups.data.remote.MakeUpProductRestService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class Injection {

    private static final String BASE_URL = "https://api.github.com";
    private static OkHttpClient okHttpClient;
    private static MakeUpProductRestService makeupRestService;
    private static Retrofit retrofitInstance;


    public static MakeUpRepository provideUserRepo() {
        return new MakeUpRepositoryImpl(provideMakeupUserRestService());
    }

    static MakeUpProductRestService provideMakeupUserRestService() {
        if (makeupRestService == null) {
            makeupRestService = getRetrofitInstance().create(MakeUpProductRestService.class);
        }
        return makeupRestService;
    }

    static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }

        return okHttpClient;
    }

    static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder().client(Injection.getOkHttpClient()
            ).baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            retrofitInstance = retrofit.build();

        }
        return retrofitInstance;
    }
}
