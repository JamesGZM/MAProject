package com.ma.comics.api;

import android.text.TextUtils;

import com.gutils.GTimeTransform;
import com.gutils.GUtils;
import com.gutils.cache.GCacheUtils;

import java.io.IOException;

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
        String token = GCacheUtils.getInstance().getString("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IjkzNDI0OTc3OUBxcS5jb20iLCJyb2xlIjoibWVtYmVyIiwibmFtZSI6IijgsqHPieCyoSkgZ2ciLCJ2ZXJzaW9uIjoiMi4xLjAuMiIsImlhdCI6MTUwNjQwNDU1MCwiZXhwIjoxNTA3MDA5MzUwfQ.-aaV5juZMBruskcOEKr9S129GwBmF1OXtOU7h4wbKSw");
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader("authorization", token);
        }
        builder.addHeader("api-key", ApiConstans.API_KEY);
        builder.addHeader("accept", "application/vnd.picacomic.com.v1+json");
        builder.addHeader("time", new GTimeTransform().getTimestamp() + "");
        builder.addHeader("app-version", "2.1.0.2");
        builder.addHeader("app-uuid", GUtils.getUUID());
        builder.addHeader("app-platfrom", "android");
        builder.addHeader("accept-encoding", "gzip");
        builder.addHeader("user-agent", "okhttp/3.8.1");
        request = builder.build();
        return chain.proceed(request);
    }
}
