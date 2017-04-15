package com.zcc.shopping.home.fragment;


import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.zcc.shopping.R;
import com.zcc.shopping.base.BaseFragment;
import com.zcc.shopping.home.adpater.HomeFragmentAdpater;
import com.zcc.shopping.home.bean.HomeFragmentBean;
import com.zcc.shopping.utils.Constants;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 朱超超 on 2017-03-21.
 * 作用：主页Fragment
 */
public class HomeFragment extends BaseFragment{
    private static final String TAG = HomeFragment.class.getSimpleName();

    private TextView tv_search_home;
    private TextView tv_message_home;
    private RecyclerView rv_home;
    private ImageButton ib_top;

    @Override
    public View initView() {
        Log.e(TAG,"主页面视图被初始化");
        View view = View.inflate(mContext, R.layout.homefragment,null);
        rv_home = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageButton) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);

        initListener();
        return view;
    }

    @Override
    public void initData() {
        Log.e(TAG,"主页面数据被初始化");
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {

            OkHttpUtils.get(Constants.HOME_URL).execute(new StringCallback() {
                @Override
                public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                    Log.e(TAG, "OkHttp联网成功");
                    processData(s);
                }

                @Override
                public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                    super.onError(isFromCache, call, response, e);
                    Log.e(TAG, "OkHttp联网失败");
                }
            });
    }

    private void processData(String s) {
        HomeFragmentBean homeFragmentBean = new Gson().fromJson(s,HomeFragmentBean.class);
        //设置适配器
        rv_home.setAdapter(new HomeFragmentAdpater(mContext,homeFragmentBean.getResult()));
        GridLayoutManager manager =  new GridLayoutManager(mContext,1);
        //设置跨度大小监听
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position <= 3){
                    //隐藏
                    ib_top.setVisibility(View.GONE);
                }else{
                    //显示
                    ib_top.setVisibility(View.VISIBLE);
                }
                //只能返回1
                return 1;
            }
        });
        //设置布局管理者
        rv_home.setLayoutManager(manager);
    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rv_home.scrollToPosition(0);
            }
        });

        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
