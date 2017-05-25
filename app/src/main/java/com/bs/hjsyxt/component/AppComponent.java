package com.bs.hjsyxt.component;

import android.content.Context;


import com.bs.hjsyxt.api.HttpApi;
import com.bs.hjsyxt.module.AppModule;
import com.bs.hjsyxt.module.HttpApiModule;

import dagger.Component;

/**
 * Created by wf on 2017/5/5.
 */

@Component(modules={AppModule.class,HttpApiModule.class})
public interface AppComponent {

        //Application Context
        Context getContext();

        //Network Api
        HttpApi getHttpApi();
}
