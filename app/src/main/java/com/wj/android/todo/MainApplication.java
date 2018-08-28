package com.wj.android.todo;

import android.app.Application;

import com.wj.android.http.XRetrofit;
import com.wj.android.todo.manager.PersistentCookieJarManager;

/**
 * 作者：wangwnejie on 2018/8/7 16:03
 * 邮箱：wang20080990@163.com
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XRetrofit.init()
                .debug(BuildConfig.DEBUG)
                .cookieJar(PersistentCookieJarManager.getInstance(this).getPersistentCookieJar());
    }

}
