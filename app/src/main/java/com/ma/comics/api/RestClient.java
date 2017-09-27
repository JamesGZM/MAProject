package com.ma.comics.api;

import android.content.*;

import com.gutils.GUtils;
import com.gutils.cache.GCacheUtils;
import com.picacomic.fregata.MyApplication;

import okhttp3.Response;
import okhttp3.logging.*;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;
import okhttp3.*;
import retrofit2.converter.gson.*;
import retrofit2.*;
import javax.net.ssl.*;

public class RestClient
{
    public static final String API_KEY = "C69BAF41DA5ABD1FFEDC6D2FEA56B";
    public static final String BASE_URL = "https://picaapi.picacomic.com/";
    public static final String CERT_URL = "picaapi.picacomic.com";
    public static final String CHATROOM = "https://chat.picacomic.com";
    public static final String CHATROOM_GAME = "https://game.picacomic.com";
    public static final String TAG;
    public static String buildVersion;
    public static String uuid;
    public static String version;
    private ApiService apiService;
    
    static {
        TAG = RestClient.class.getSimpleName();
    }
    
    public RestClient(Context build) {
        RestClient.uuid = "d12bad8d-93c5-31f3-a1c1-2019a038740b";
        RestClient.version = "2.1.0.2";
        RestClient.buildVersion = "37";
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (GUtils.DEBUG) {
            HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor();
            LoginInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            builder.addInterceptor(LoginInterceptor); // 添加retrofit日志打印
        }
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain proceed) throws IOException {
                final Request request = proceed.request();
                 String replace = UUID.randomUUID().toString().replace("-", "");
                replace="7f69ae36a92f4bc0b961109e08268976";
                final String replace2 = request.url().toString().replace("https://picaapi.picacomic.com/", "");
                String string = System.currentTimeMillis() / 1000L + Long.parseLong(GCacheUtils.getInstance().getString("buid","0"))+"";
                string="1506502251";
                final String stringCon = MyApplication.getInstance().getStringCon(new String[] { "https://picaapi.picacomic.com/", replace2, string, replace, request.method(), "C69BAF41DA5ABD1FFEDC6D2FEA56B", RestClient.version, RestClient.buildVersion });

                Request newRequest=request.newBuilder().header("api-key", "C69BAF41DA5ABD1FFEDC6D2FEA56B").header("accept", "application/vnd.picacomic.com.v1+json").header("time", string).header("nonce", replace).header("signature", stringCon).header("app-version", RestClient.version).header("app-uuid", RestClient.uuid).header("app-platform", "android").header("app-build-version", RestClient.buildVersion).method(request.method(), request.body()).build();

                Response response= proceed.proceed(newRequest);

                final String value = response.headers().get("Server-Time");
                try {
                    final long n = Long.parseLong(value) - System.currentTimeMillis() / 1000L;
                    GCacheUtils.getInstance().put("buid",n+"");
                    return response;
                }
                catch (Exception ex) {
                    return response;
                }
            }
        });
        try {
            TLSSocketFactory tlss=new TLSSocketFactory();
            builder.sslSocketFactory(tlss,tlss.systemDefaultTrustManager());
        }catch (KeyManagementException e){

        }catch (NoSuchAlgorithmException e){

        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build()).build();
        this.apiService = retrofit.create(ApiService.class);

    }
    
    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(final String s, final SSLSession sslSession) {
                return HttpsURLConnection.getDefaultHostnameVerifier().verify("picaapi.picacomic.com", sslSession);
            }
        };
    }
    
    public ApiService getApiService() {
        return this.apiService;
    }
    
    public String signature(final String... array) {
        return array[0] + array[1] + array[2] + array[3] + array[4];
    }
}
