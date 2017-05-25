package com.bs.hjsyxt.module;


import com.bs.hjsyxt.api.HttpApi;
import com.bs.hjsyxt.api.support.HeaderInterceptor;
import com.bs.hjsyxt.api.support.Logger;
import com.bs.hjsyxt.api.support.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by wf on 2017/5/5.
 */

//Dagger2的module提供类
@Module
public class HttpApiModule {
    @Provides
    public OkHttpClient provideOkHttpClient() {

        LoggingInterceptor logging = new LoggingInterceptor(new Logger());
        logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging);
        return builder.build();
    }

    @Provides
    protected HttpApi provideBookService(OkHttpClient okHttpClient) {
        return HttpApi.getInstance(okHttpClient);
    }
}
