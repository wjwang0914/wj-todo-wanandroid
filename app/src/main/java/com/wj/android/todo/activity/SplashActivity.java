package com.wj.android.todo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.wj.android.todo.BuildConfig;
import com.wj.android.todo.R;
import com.wj.android.todo.activity.base.BaseActivity;
import com.wj.android.todo.constant.Constant;
import com.wj.android.todo.manager.PersistentCookieJarManager;

import java.util.List;

import butterknife.BindView;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * 作者：wangwnejie on 2018/8/8 16:01
 * 邮箱：wang20080990@163.com
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.root_view)
    View mView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(3000);
        mView.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void redirectTo() {
        List<Cookie> cookies = PersistentCookieJarManager.getInstance(this).getPersistentCookieJar().loadForRequest(HttpUrl.parse(BuildConfig.BASE_URL));
        if (cookies.isEmpty()) {
            startActivity(LoginActivity.class);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("user_name", cookies.get(0).name());
            startActivity(MainActivity.class, bundle);
        }
        finish();

    }
}
