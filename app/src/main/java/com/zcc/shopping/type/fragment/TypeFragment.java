package com.zcc.shopping.type.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zcc.shopping.base.BaseFragment;

/**
 * Created by 朱超超 on 2017-03-21.
 * 作用：分类页面
 */
public class TypeFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        Log.e("LOG", "分类页面被初始化");
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
    @Override
    public void initData() {
        super.initData();
        textView.setText("分类");
    }
}
