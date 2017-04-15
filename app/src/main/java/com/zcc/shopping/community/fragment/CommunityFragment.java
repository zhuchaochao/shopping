package com.zcc.shopping.community.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zcc.shopping.base.BaseFragment;

/**
 * Created by 朱超超 on 2017-03-21.
 * 作用：发现页面
 */
public class CommunityFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        Log.e("LOG", "发现页面被初始化");
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
    @Override
    public void initData() {
        super.initData();
        textView.setText("发现");
    }
}
