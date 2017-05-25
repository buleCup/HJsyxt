package com.bs.hjsyxt.api;


import com.bs.hjsyxt.base.Constant;
import com.bs.hjsyxt.bean.User;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wf on 2017/5/5.
 * 单例模式
 */

public class HttpApi {

    public static HttpApi instance;

    private HttpApiService service;

    public HttpApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(HttpApiService.class);
    }

    public static HttpApi getInstance(OkHttpClient okHttpClient) {
        return instance == null ? new HttpApi(okHttpClient) : instance;
    }

    public Observable<User> loginCUser(String username, String password) {
        return service.loginCUser(username, password);
    }

    public Observable<User> loginIUser(String username, String password) {
        return service.loginIUser(username, password);
    }
}
