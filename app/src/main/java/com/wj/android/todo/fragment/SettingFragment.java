package com.wj.android.todo.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.wj.android.todo.R;
import com.wj.android.todo.activity.LoginActivity;
import com.wj.android.todo.activity.MainActivity;
import com.wj.android.todo.fragment.base.BaseFragment;
import com.wj.android.todo.manager.PersistentCookieJarManager;
import com.wj.android.todo.manager.SharePreferenceManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Cookie;

/**
 * 作者：wangwnejie on 2018/8/8 13:42
 * 邮箱：wangwenjie1303@stnts.com
 */
public class SettingFragment extends BaseFragment {
    @BindView(R.id.user_name)
    TextView mUserName;

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initData() {
        String userName = SharePreferenceManager.getInstance(getContext()).getUserName();
        if (!TextUtils.isEmpty(userName)) {
            mUserName.setText(SharePreferenceManager.getInstance(getContext()).getUserName());
        }

    }

    @OnClick(R.id.logout)
    void onClickLogout() {
        SharePreferenceManager.getInstance(getContext()).clear();
        PersistentCookieJarManager.getInstance(getContext()).getPersistentCookieJar().clear();
        startActivity(LoginActivity.class);
        getActivity().finish();
    }
}
