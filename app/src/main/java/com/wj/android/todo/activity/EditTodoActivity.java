package com.wj.android.todo.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

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
 * 邮箱：wang20080990@163.com
 */
public class EditTodoActivity extends BaseActivity {
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
    @BindView(R.id.save_todo)
    Button mSaveTodo;

    private TodoDesBean mTodoDesBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_todo;
    }

    @Override
    protected void initData() {
        mTitle.setText(R.string.update_todo);
        mBack.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mTodoDesBean = (TodoDesBean) bundle.getSerializable("todo_des");
        if (mTodoDesBean == null) {
            return;
        }
        mTodoName.setText(mTodoDesBean.getTitle());
        if (!TextUtils.isEmpty(mTodoDesBean.getContent())) {
            mTodoDes.setText(mTodoDesBean.getContent());
        }
        mTodoDate.setText(mTodoDesBean.getDateStr());

        if (mTodoDesBean.getStatus() == 1) {
            mSaveTodo.setVisibility(View.GONE);
            mTodoName.setEnabled(false);
            mTodoName.setFocusable(false);
            mTodoDes.setEnabled(false);
            mTodoDes.setFocusable(false);
            mTodoDate.setEnabled(false);
            mTodoDate.setFocusable(false);
        }
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
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
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

        requestUpdateTodoData();
    }

    private void requestUpdateTodoData() {
        HttpUtils.requestUpdateTodoData(this, mTodoDesBean.getId(), mTodoName.getText().toString(), mTodoDes.getText().toString(), mTodoDate.getText().toString());
    }

    public void updateUI(ResponseItem<TodoDesBean> response) {
        if (response.isSuccess()) {
            showToast(getString(R.string.update_todo_success));
            Intent intent = new Intent();
            intent.putExtra("update_todo", response.getData());
            setResult(0x210, intent);
            finish();
        }
    }

    @Override
    public boolean isLoadingEnable(int requestId) {
        return super.isLoadingEnable(requestId);
    }
}
