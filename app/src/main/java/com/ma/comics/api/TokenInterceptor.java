package com.ma.comics.api;

import android.text.TextUtils;

import com.gutils.GTimeTransform;
import com.gutils.GUtils;
import com.gutils.cache.GCacheUtils;

import java.io.IOException;
import java.util.UUID;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
//        String token = GCacheUtils.getInstance().getString("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IjkzNDI0OTc3OUBxcS5jb20iLCJyb2xlIjoibWVtYmVyIiwibmFtZSI6IijgsqHPieCyoSkgZ2ciLCJ2ZXJzaW9uIjoiMi4xLjAuMiIsImlhdCI6MTUwNjQwNDU1MCwiZXhwIjoxNTA3MDA5MzUwfQ.-aaV5juZMBruskcOEKr9S129GwBmF1OXtOU7h4wbKSw");
//        if (!TextUtils.isEmpty(token)) {
//            builder.addHeader("authorization", token);
//        }

        String nonce = UUID.randomUUID().toString().replace("-", "");
        String signature = UUID.randomUUID().toString().replace("-", "");

        builder.addHeader("api-key", ApiConstans.API_KEY);
        builder.addHeader("accept", "application/vnd.picacomic.com.v1+json");
        builder.addHeader("time", "1506482239");
        builder.addHeader("nonce", "ede564f8366841b0b7553f3b52da1f2c");
        builder.addHeader("signature", "373558931529e89c4e0f3d2bbbd172cbf50f34a3cd99cfa40b5b8c280f00e378");
        builder.addHeader("app-version", "2.1.0.2");
        builder.addHeader("app-uuid", "d12bad8d-93c5-31f3-a1c1-2019a038740b");
        builder.addHeader("app-platform", "android");
        builder.addHeader("app-build-version", "37");
        builder.addHeader("content-type", "application/json; charset=UTF-8");
        builder.addHeader("content-length", "52");
        builder.addHeader("accept-encoding", "gzip");
        builder.addHeader("user-agent", "okhttp/3.8.1");
        request = builder.build();
        return chain.proceed(request);
    }
}
