package com.ma.comics.api;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gutils.GUtils;
import com.gutils.retrofit2.GSubscribeManager;

import java.lang.reflect.Modifier;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class ApiHelper {

    private static ApiHelper helper;
    private Retrofit retrofit;
    private OkHttpClient mOkHttpClient;

    public static <T> Subscription getSubscription(Observable<T> mObservable, Observer<T> mObserver) {
        return GSubscribeManager.CustomSendSubScribe(mObservable, mObserver);
    }

    // 返回接口实例
    public static ApiService getService() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
        Gson gson = builder.create();
        return init(ApiConstans.BASE_URL, gson).create(ApiService.class);
    }

    // 解析器类型
    public enum FactoryType {
        GSON, OBJECT;
    }

    public static ApiHelper init(String BaseUrl) {
        helper = new ApiHelper(BaseUrl, null, FactoryType.GSON);
        return helper;
    }

    public static ApiHelper init(String BaseUrl, Gson gson) {
        helper = new ApiHelper(BaseUrl, gson, FactoryType.GSON);
        return helper;
    }

    public static ApiHelper init(String BaseUrl, FactoryType type) {
        helper = new ApiHelper(BaseUrl, null, type);
        return helper;
    }

    private ApiHelper(String BaseUrl, Gson gson, FactoryType type) {
        if (TextUtils.isEmpty(BaseUrl)) {
            throw new NullPointerException(getClass().getSimpleName() + " baseUrl is null");
        } else {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();


            if (GUtils.DEBUG) {
                HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor();
                LoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(LoginInterceptor); // 添加retrofit日志打印
            }

            builder.addInterceptor(new TokenInterceptor());

            mOkHttpClient = builder.build();

            if (gson != null) {
                retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(mOkHttpClient).build();
            } else {
                retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                        .addConverterFactory(type == FactoryType.OBJECT ? ScalarsConverterFactory.create()
                                : GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(mOkHttpClient).build();
            }
        }
    }

    public <T> T create(Class<T> cls) {
        if (retrofit == null) {
            throw new NullPointerException(getClass().getSimpleName() + " retrofit is null");
        }
        return retrofit.create(cls);
    }

}
