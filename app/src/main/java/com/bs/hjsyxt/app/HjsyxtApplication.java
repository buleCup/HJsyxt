package com.bs.hjsyxt.app;

import android.app.Application;
import android.content.Context;

import com.bs.hjsyxt.component.AppComponent;
import com.bs.hjsyxt.component.DaggerAppComponent;
import com.bs.hjsyxt.module.AppModule;
import com.bs.hjsyxt.module.HttpApiModule;
import com.bs.hjsyxt.utils.AppUtils;
import com.bs.hjsyxt.utils.SharedPreferencesUtil;


/**
 * Created by wf on 2017/5/3.
 */

public class HjsyxtApplication extends Application {

    private static HjsyxtApplication sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        AppUtils.init(this);
        initComponent();
    }

    public static HjsyxtApplication getsInstance() {
        return sInstance;
    }

    private void initComponent() {
        //对所有依赖AppComponent的xxxActivityComponent都可以使用httpApi和context
        appComponent = DaggerAppComponent.builder()
                .httpApiModule(new HttpApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
