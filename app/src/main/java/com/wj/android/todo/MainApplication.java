package com.wj.android.todo;

import android.app.Application;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.wj.android.http.XRetrofit;
import com.wj.android.todo.manager.PersistentCookieJarManager;

/**
 * 作者：wangwnejie on 2018/8/7 16:03
 * 邮箱：wangwenjie1303@stnts.com
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XRetrofit.init()
                .debug(true)
                .cookieJar(PersistentCookieJarManager.getInstance(this).getPersistentCookieJar());
    }
}
