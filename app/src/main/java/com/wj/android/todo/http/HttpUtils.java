package com.wj.android.todo.http;

import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.wj.android.http.BaseView;
import com.wj.android.http.XRetrofit;
import com.wj.android.todo.activity.AddTodoActivity;
import com.wj.android.todo.activity.EditTodoActivity;
import com.wj.android.todo.activity.LoginActivity;
import com.wj.android.todo.activity.RegisterActivity;
import com.wj.android.todo.bean.TodoDesBean;
import com.wj.android.todo.bean.TodoListBean;
import com.wj.android.todo.constant.Constant;
import com.wj.android.todo.fragment.TodoFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：wangwnejie on 2018/8/7 15:21
 * 邮箱：wangwenjie1303@stnts.com
 */
public class HttpUtils {

    private static String buildUrl(String uri) {
        return String.format("%s%s", Constant.BASE_URL, uri);
    }

    public static void requestLogin(BaseView baseView, String userName, String password){
        Map<String, String> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", password);
        XRetrofit.post(buildUrl(Constant.LOGIN_URI), params, new MyGsonCallback<ResponseItem<JsonObject>>(baseView) {
            @Override
            protected void onSuccess(ResponseItem<JsonObject> response, BaseView baseView) {
                ((LoginActivity)baseView).updateUI(response);
            }
        });
    }

    public static void requestRegister(BaseView baseView, String userName, String password, String repassword) {
        Map<String, String> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", password);
        params.put("repassword", repassword);
        XRetrofit.post(buildUrl(Constant.REGISTER_URI), params, new MyGsonCallback<ResponseItem<JsonObject>>(baseView) {
            @Override
            protected void onSuccess(ResponseItem<JsonObject> response, BaseView baseView) {
                ((RegisterActivity)baseView).updateUI(response);
            }
        });
    }

    public static void requestAddTodoData(BaseView baseView, String todoName, String todoDes, String todoDate) {
        Map<String, String> params = new HashMap<>();
        params.put("title", todoName);
        if (!TextUtils.isEmpty(todoDes)) {
            params.put("content", todoDes);
        }
        params.put("date", todoDate);
        params.put("type", "0");

        XRetrofit.post(buildUrl(Constant.ADD_TODO_URI), params, new MyGsonCallback<ResponseItem<TodoDesBean>>(baseView) {
            @Override
            protected void onSuccess(ResponseItem<TodoDesBean> response, BaseView baseView) {
                ((AddTodoActivity)baseView).updateUI(response);
            }
        });
    }

    public static void requestTodoList(BaseView baseView, int page, boolean isDone) {
        XRetrofit.post(isDone ? buildUrl(String.format(Constant.DONE_LIST_URI, page)) : buildUrl(String.format(Constant.TODO_LIST_URI, page)), new MyGsonCallback<ResponseItem<TodoListBean>>(baseView) {
            @Override
            protected void onSuccess(ResponseItem<TodoListBean> response, BaseView baseView) {
                ((TodoFragment)baseView).updateUI(response);
            }
        });
    }

    public static void deleteTodoById(BaseView baseView, int todoId) {
        XRetrofit.post(buildUrl(String.format(Constant.DELETE_TODO_URI, todoId)), new MyGsonCallback<ResponseItem>(baseView,1) {
            @Override
            protected void onSuccess(ResponseItem response, BaseView baseView) {
                ((TodoFragment)baseView).updateRemovedData(response);
            }
        });
    }

    public static void updateTodoById(BaseView baseView, int todoId, String title, String content, String date) {

    }

    public static void doneTodoById(BaseView baseView, int todoId, int status) {
        Map<String, String> params = new HashMap<>();
        params.put("status", String.valueOf(status));

        XRetrofit.post(buildUrl(String.format(Constant.DONE_TODO_URI, todoId)),params, new MyGsonCallback<ResponseItem<TodoDesBean>>(baseView,1) {
            @Override
            protected void onSuccess(ResponseItem<TodoDesBean> response, BaseView baseView) {
                ((TodoFragment)baseView).updateDoneData(response);
            }
        });
    }

    public static void requestUpdateTodoData(BaseView baseView, int todoId, String todoName, String todoDes, String todoDate) {
        Map<String, String> params = new HashMap<>();
        params.put("title", todoName);
        if (!TextUtils.isEmpty(todoDes)) {
            params.put("content", todoDes);
        }
        params.put("date", todoDate);

        XRetrofit.post(buildUrl(String.format(Constant.UPDATE_TODO_URI, todoId)), params, new MyGsonCallback<ResponseItem<TodoDesBean>>(baseView) {
            @Override
            protected void onSuccess(ResponseItem<TodoDesBean> response, BaseView baseView) {
                ((EditTodoActivity)baseView).updateUI(response);
            }
        });
    }
}
