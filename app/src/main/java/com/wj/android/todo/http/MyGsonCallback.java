package com.wj.android.todo.http;

import com.google.gson.Gson;
import com.wj.android.http.BaseView;
import com.wj.android.http.GsonCallback;
import com.wj.android.todo.exception.ApiException;

/**
 * 作者：wangwnejie on 2018/8/8 13:57
 * 邮箱：wangwenjie1303@stnts.com
 */
public abstract class MyGsonCallback<T> extends GsonCallback<T> {

    public MyGsonCallback(BaseView baseView) {
        super(baseView);
    }

    public MyGsonCallback(BaseView baseView, int requestId) {
        super(baseView, requestId);
    }

    @Override
    protected String convertResponse(String response) {
        ResponseItem responseItem = new Gson().fromJson(response, ResponseItem.class);
        if (!responseItem.isSuccess()) {
            throw new ApiException(responseItem.getErrorMsg(), responseItem.getErrorCode());
        }
        return super.convertResponse(response);
    }
}
