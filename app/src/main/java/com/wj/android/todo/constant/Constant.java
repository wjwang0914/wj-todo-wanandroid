package com.wj.android.todo.constant;

/**
 * 作者：wangwnejie on 2018/8/7 15:24
 * 邮箱：wangwenjie1303@stnts.com
 */
public class Constant {
    public static final String BASE_URL = "http://www.wanandroid.com";

    public static final String LOGIN_URI = "/user/login";
    public static final String REGISTER_URI = "/user/register";
    public static final String ADD_TODO_URI = "/lg/todo/add/json";
    public static final String TODO_LIST_URI = "/lg/todo/listnotdo/0/json/%d";
    public static final String DELETE_TODO_URI = "/lg/todo/delete/%d/json";
    public static final String UPDATE_TODO_URI = "/lg/todo/update/%d/json";
    public static final String DONE_TODO_URI = "/lg/todo/done/%d/json";
    public static final String DONE_LIST_URI = "/lg/todo/listdone/0/json/%d";
}
