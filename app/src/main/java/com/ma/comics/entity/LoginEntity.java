package com.ma.comics.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class LoginEntity implements Serializable{

   public LoginEntity(String email,String password){
        this.email=email;
       this.password=password;
    }

    public String email;
    public String password;
}
