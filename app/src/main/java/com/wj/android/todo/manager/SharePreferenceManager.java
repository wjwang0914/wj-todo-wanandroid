package com.wj.android.todo.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/5/25.
 */

public class SharePreferenceManager {

    private static final String PREF_FILE = "wj_todo";
    private static final String KEY_USER_NAME = "user_name";

    private volatile static SharePreferenceManager sInstance;
    private SharedPreferences mSharedPreferences;

    private SharePreferenceManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
    }

    public static SharePreferenceManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SharePreferenceManager.class) {
                if (sInstance == null) {
                    sInstance = new SharePreferenceManager(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public void setUserName(String userName) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_USER_NAME, userName);
        PreferenceHelper.getInstance().save(editor);
    }

    public String getUserName() {
        return mSharedPreferences.getString(KEY_USER_NAME, null);
    }

    public void clear() {
        mSharedPreferences.edit().clear();
    }

}
