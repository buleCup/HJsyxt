package com.bs.hjsyxt.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;


import com.bs.hjsyxt.R;
import com.bs.hjsyxt.app.HjsyxtApplication;
import com.bs.hjsyxt.component.AppComponent;
import com.bs.hjsyxt.utils.SharedPreferencesUtil;
import com.bs.hjsyxt.utils.StatusBarCompat;

import butterknife.ButterKnife;


/**
 * Created by wf on 2017/5/2.
 * 封装公有的基本方法
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    public Toolbar mCommonToolbar;
    private boolean mNowMode;//判断现在是什么模式（夜间/白天）
    protected View statusBarView = null;
    protected int statusBarColor = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (statusBarColor == 0) {
            statusBarView = StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        } else if (statusBarColor != -1) {
            statusBarView = StatusBarCompat.compat(this, statusBarColor);
        }

        //初始化
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mContext = this;

        //对所有继承baseActivity的类  都可以注入httpApi,context
        setupActivityComponent(HjsyxtApplication.getsInstance().getAppComponent());

        //屏蔽系统自带的toolbar，使用自定义的toolbar
        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if(mCommonToolbar != null) {
            initToolBar();
            setSupportActionBar(mCommonToolbar);
        }

        //抽象方法
        initDatas();
        configViews();

    }

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    protected void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if(statusBarView != null){
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if(statusBarView != null){
            statusBarView.setBackgroundColor(statusBarColor);
        }
    }

    /**
     * 设置菜单点击监听事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 统一设定layoutId
     */
    public abstract int getLayoutId();

    /**
     * 初始化数据，加载数据
     */
    public abstract void initDatas();

    /**
     *  初始化toolbar
     */
    public abstract void initToolBar();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void configViews();

    /**
     * 对需要注入的activity实现该方法
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);

    /**
     *  在Activity准备阶段的操作
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 摧毁该activity时的操作
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
