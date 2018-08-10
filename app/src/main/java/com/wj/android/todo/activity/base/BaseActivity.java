package com.wj.android.todo.activity.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.wj.android.http.BaseView;
import com.wj.android.todo.R;
import com.wj.android.todo.exception.ApiException;

import butterknife.ButterKnife;

/**
 * 作者：wangwnejie on 2018/3/22 16:57
 * 邮箱：wangwenjie1303@stnts.com
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, String.format("%s:onCreate", this));
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);

        initView();
        initData();
        applyEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, String.format("%s:onStart", this));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, String.format("%s:onRestart", this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, String.format("%s:onResume", this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, String.format("%s:onPause", this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, String.format("%s:onStop", this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, String.format("%s:onDestroy", this));
        if (isLoadingEnable() && mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }
    /**
     * 返回当前界面布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected void initView(){}

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置事件监听
     */
    protected void applyEvent(){}

    protected boolean isLoadingEnable() {
        return false;
    }

    @Override
    public void start(int requestId) {
        if (isLoadingEnable() && !isFinishing()) {
            mProgressDialog = new ProgressDialog(this,ProgressDialog.THEME_HOLO_LIGHT);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(getResources().getString(R.string.loading));
            mProgressDialog.show();
        }
    }

    @Override
    public void error(Throwable t, int requestId) {
        if (t instanceof ApiException) {
            showToast(t.getMessage());
        } else {
            showToast(getResources().getString(R.string.service_error));
        }
    }

    @Override
    public void end(int requestId) {
        if (isLoadingEnable() && !isFinishing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

}
