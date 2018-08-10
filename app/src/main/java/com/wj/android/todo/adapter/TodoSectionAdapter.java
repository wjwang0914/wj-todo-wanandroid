package com.wj.android.todo.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.android.todo.R;
import com.wj.android.todo.bean.TodoSection;

import java.util.List;

/**
 * 作者：wangwnejie on 2018/8/9 14:41
 * 邮箱：wangwenjie1303@stnts.com
 */
public class TodoSectionAdapter extends BaseSectionQuickAdapter<TodoSection, BaseViewHolder> {
    private boolean isDone;

    public TodoSectionAdapter(int layoutResId, int sectionHeadResId, List<TodoSection> data, boolean isDone) {
        super(layoutResId, sectionHeadResId, data);
        this.isDone = isDone;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, TodoSection item) {
        helper.setText(R.id.todo_head, item.header);
        if (isDone) {
            helper.setTextColor(R.id.todo_head, mContext.getResources().getColor(R.color.done_todo_date));
        } else {
            helper.setTextColor(R.id.todo_head, mContext.getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, TodoSection item) {
        helper.setText(R.id.item_name, item.t.getTitle());
        if (TextUtils.isEmpty(item.t.getContent())) {
            helper.setGone(R.id.item_des, false);
        } else {
            helper.setGone(R.id.item_des, true);
            helper.setText(R.id.item_des, item.t.getContent());
        }

        if (isDone) {
            helper.setGone(R.id.item_done_time, true);
            helper.setText(R.id.item_done_time, String.format(mContext.getResources().getString(R.string.done_todo_date),item.t.getCompleteDateStr()));
            helper.setImageResource(R.id.item_complete, R.drawable.cancel_todo);
        } else {
            helper.setGone(R.id.item_done_time, false);
            helper.setImageResource(R.id.item_complete, R.drawable.complete_todo);
        }

        helper.addOnClickListener(R.id.item_complete);
        helper.addOnClickListener(R.id.item_delete);
    }
}
