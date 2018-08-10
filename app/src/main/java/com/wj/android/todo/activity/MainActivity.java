package com.wj.android.todo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wj.android.todo.R;
import com.wj.android.todo.activity.base.BaseActivity;
import com.wj.android.todo.bean.TodoDesBean;
import com.wj.android.todo.fragment.SettingFragment;
import com.wj.android.todo.fragment.TodoFragment;
import com.wj.android.todo.widget.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：wangwnejie on 2018/8/7 11:17
 * 邮箱：wangwenjie1303@stnts.com
 */
public class MainActivity extends BaseActivity {
    private static final int REQUEST_CODE_ADD_TODO = 0x100;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.navigation)
    BottomNavigationViewEx mNavigationView;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.add_todo)
    ImageView mAddTodo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mTitle.setText(R.string.to_do_list);
        mAddTodo.setVisibility(View.VISIBLE);

        mViewPager.setOffscreenPageLimit(3);

        List<Fragment> fragments = new ArrayList<>(3);
        fragments.add(TodoFragment.newInstance(false));
        fragments.add(TodoFragment.newInstance(true));
        fragments.add(SettingFragment.newInstance());

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);

        mNavigationView.setupWithViewPager(mViewPager);
    }

    @Override
    protected void applyEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTitle.setText(R.string.to_do_list);
                        mAddTodo.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mTitle.setText(R.string.complete_list);
                        mAddTodo.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        mTitle.setText(R.string.setting);
                        mAddTodo.setVisibility(View.INVISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });
    }

    @OnClick(R.id.add_todo)
    void onClickAddTodo() {
        startActivityForResult(AddTodoActivity.class, REQUEST_CODE_ADD_TODO);
    }

    private static class MainViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_TODO) {
            switch (resultCode) {
                case 0x200:
                    TodoDesBean todoDesBean = (TodoDesBean) data.getSerializableExtra("add_todo");
                    TodoFragment todoFragment = (TodoFragment)((MainViewPagerAdapter)mViewPager.getAdapter()).getItem(0);
                    todoFragment.updateAddTodoData(todoDesBean);
                    break;
            }
        }
    }

    public void updateDoneOrCancelData(TodoDesBean todoDesBean, int postition) {
        TodoFragment todoFragment = (TodoFragment)((MainViewPagerAdapter)mViewPager.getAdapter()).getItem(postition);
        todoFragment.updateAddTodoData(todoDesBean);
    }

}
