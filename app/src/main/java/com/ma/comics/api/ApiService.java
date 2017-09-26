package com.ma.comics.api;

import com.ma.comics.entity.TokenEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("/auth/sign-in")
    Observable<CustomWrapperRspEntity<TokenEntity>> login(@Field("email") String email, @Field("password") String password);


}
