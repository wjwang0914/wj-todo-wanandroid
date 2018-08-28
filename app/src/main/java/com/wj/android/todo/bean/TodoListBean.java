package com.wj.android.todo.bean;

import java.util.List;

/**
 * 作者：wangwnejie on 2018/8/9 14:55
 * 邮箱：wang20080990@163.com
 */
public class TodoListBean {
    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<TodoDesBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TodoDesBean> getDatas() {
        return datas;
    }

    public void setDatas(List<TodoDesBean> datas) {
        this.datas = datas;
    }
}
