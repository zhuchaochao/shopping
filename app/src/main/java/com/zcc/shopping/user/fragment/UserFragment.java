package com.zcc.shopping.user.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zcc.shopping.base.BaseFragment;

/**
 * Created by 朱超超 on 2017-03-21.
 * 作用：用户页面
 */
public class UserFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        Log.e("LOG", "用户中心页面被初始化");
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
    @Override
    public void initData() {
        super.initData();
        textView.setText("用户中心");
    }
}
