package com.ma.comics.api;

import com.gutils.retrofit2.WrapperRspEntity;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class CustomWrapperRspEntity<T>{

        private int code;
        private T data;
        private String message;

        public CustomWrapperRspEntity() {
        }

        public int getCode() {
            return this.code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

}
