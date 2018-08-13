package com.wj.android.todo.fragment.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.wj.android.http.BaseView;
import com.wj.android.todo.R;
import com.wj.android.todo.exception.ApiException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：wangwnejie on 2018/4/2 16:58
 * 邮箱：wangwenjie1303@stnts.com
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    private Unbinder mUnbinder;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    /**
     * 返回当前界面布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected boolean isLoadingEnable(int requestId) {
        return false;
    }

    @Override
    public void start(int requestId) {
        if (isLoadingEnable(requestId) && !getActivity().isFinishing()) {
            mProgressDialog = new ProgressDialog(getContext(),ProgressDialog.THEME_HOLO_LIGHT);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(getResources().getString(R.string.loading));
            mProgressDialog.show();
        }
    }

    @Override
    public void error(Throwable t, int requestId) {
        if (isAdded()) {
            if (t instanceof ApiException) {
                showToast(t.getMessage());
            } else {
                showToast(getResources().getString(R.string.service_error));
            }
        }
    }

    @Override
    public void end(int requestId) {
        if (isLoadingEnable(requestId) && !getActivity().isFinishing()) {
            mProgressDialog.dismiss();
        }

    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, Bundle bundle,int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }
}
