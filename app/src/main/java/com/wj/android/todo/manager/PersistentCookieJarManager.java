package com.wj.android.todo.manager;


import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.wj.android.http.RetrofitHttpManager;

/**
 * 作者：wangwnejie on 2018/8/8 16:34
 * 邮箱：wangwenjie1303@stnts.com
 */
public class PersistentCookieJarManager {

    private volatile static PersistentCookieJarManager sInstance;
    private PersistentCookieJar mPersistentCookieJar;

    private PersistentCookieJarManager(Context context) {
        mPersistentCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
    }

    public static PersistentCookieJarManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (RetrofitHttpManager.class) {
                if (sInstance == null) {
                    sInstance = new PersistentCookieJarManager(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public PersistentCookieJar getPersistentCookieJar() {
        return mPersistentCookieJar;
    }
}
