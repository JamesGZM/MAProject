package com.ma.comics.api;

import com.ma.comics.entity.LoginEntity;
import com.ma.comics.entity.TokenEntity;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public interface ApiService {

    @POST("auth/sign-in")
    Observable<CustomWrapperRspEntity<TokenEntity>> login(@Body LoginEntity entity);


    @GET("categories")
    Observable<CustomWrapperRspEntity<TokenEntity>> getCategories(@Query("email") String email, @Query("password") String password);

}
