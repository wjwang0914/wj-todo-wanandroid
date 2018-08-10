package com.wj.android.todo.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.wj.android.todo.R;
import com.wj.android.todo.activity.base.BaseActivity;
import com.wj.android.todo.bean.TodoDesBean;
import com.wj.android.todo.http.HttpUtils;
import com.wj.android.todo.http.ResponseItem;
import com.wj.android.todo.util.TimeUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：wangwnejie on 2018/8/8 19:21
 * 邮箱：wangwenjie1303@stnts.com
 */
public class AddTodoActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.todo_date)
    TextView mTodoDate;
    @BindView(R.id.todo_name)
    TextInputEditText mTodoName;
    @BindView(R.id.todo_des)
    TextInputEditText mTodoDes;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_todo;
    }

    @Override
    protected void initData() {
        mTitle.setText(R.string.add_todo);
        mBack.setVisibility(View.VISIBLE);

        mTodoDate.setText(TimeUtils.date2String(new Date(),"yyyy-MM-dd"));
    }

    @OnClick(R.id.back)
    void onClickBack() {
        finish();
    }

    @OnClick(R.id.todo_date)
    void onClickTodoDate() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                mTodoDate.setText(String.format("%d-%d-%d", year, month+1, dayOfMonth));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @OnClick(R.id.save_todo)
    void onClickAddTodo() {
        mTodoName.setError(null);
        if (TextUtils.isEmpty(mTodoName.getText())) {
            mTodoName.setError(getString(R.string.input_todo_name_toast));
            mTodoName.setFocusable(true);
            mTodoName.setFocusableInTouchMode(true);
            mTodoName.requestFocus();
            return;
        }

        requestAddTodoData();
    }

    private void requestAddTodoData() {
        HttpUtils.requestAddTodoData(this, mTodoName.getText().toString(), mTodoDes.getText().toString(), mTodoDate.getText().toString());
    }

    public void updateUI(ResponseItem<TodoDesBean> response) {
        if (response.isSuccess()) {
            showToast(getString(R.string.add_todo_success));
            Intent intent = new Intent();
            intent.putExtra("add_todo", response.getData());
            setResult(0x200, intent);
            finish();
        }
    }

    @Override
    protected boolean isLoadingEnable() {
        return true;
    }
}
