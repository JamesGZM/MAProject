package com.ma.comics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gutils.GUtils;
import com.ma.comics.api.ApiHelper;
import com.ma.comics.api.CustomWrapperRspEntity;
import com.ma.comics.api.RestClient;
import com.ma.comics.entity.LoginEntity;
import com.ma.comics.entity.TokenEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginEntity entity=new LoginEntity("934249779@qq.com","gongbin521");
        RestClient client=new RestClient(this);
        client.getApiService().login(entity).enqueue(new Callback<CustomWrapperRspEntity<TokenEntity>>() {
            @Override
            public void onResponse(Call<CustomWrapperRspEntity<TokenEntity>> call, Response<CustomWrapperRspEntity<TokenEntity>> response) {

            }

            @Override
            public void onFailure(Call<CustomWrapperRspEntity<TokenEntity>> call, Throwable t) {

            }
        });
    }

    private Observer<CustomWrapperRspEntity<TokenEntity>> login = new Observer<CustomWrapperRspEntity<TokenEntity>>() {

        @Override
        public void onNext(CustomWrapperRspEntity<TokenEntity> entity) {
            GUtils.showShort(entity.getData().token);
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {
        }
    };
}
