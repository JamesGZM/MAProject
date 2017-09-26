package com.ma.comics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gutils.GUtils;
import com.ma.comics.api.ApiHelper;
import com.ma.comics.api.CustomWrapperRspEntity;
import com.ma.comics.entity.LoginEntity;
import com.ma.comics.entity.TokenEntity;

import rx.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginEntity entity=new LoginEntity("934249779@qq.com","gongbin521");
        ApiHelper.getSubscription(ApiHelper.getService().login(entity),login);
    }

    private Observer<CustomWrapperRspEntity<TokenEntity>> login = new Observer<CustomWrapperRspEntity<TokenEntity>>() {

        @Override
        public void onNext(CustomWrapperRspEntity<TokenEntity> entity) {
            GUtils.showShort(entity.getMessage());
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {
        }
    };
}
