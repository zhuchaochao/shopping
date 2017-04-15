package com.zcc.shopping;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zcc.shopping.R;
import com.zcc.shopping.base.BaseFragment;
import com.zcc.shopping.cart.fragment.CartFragment;
import com.zcc.shopping.community.fragment.CommunityFragment;
import com.zcc.shopping.home.fragment.HomeFragment;
import com.zcc.shopping.type.fragment.TypeFragment;
import com.zcc.shopping.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 朱超超 on 2017-03-22.
 * 作用：MainActivity
 */
public class MainActivity extends FragmentActivity {
    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragmentArray;
    private int position = 0;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initListener();

    }

    private void initListener() {

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                BaseFragment nextFragment = getFragment(position);
                switchFragment(tempFragment, nextFragment);
            }
        });

        rgMain.check(R.id.rb_home);
    }

    /**
     *
     * @param fromFragment  当前显示的fragment
     * @param nextFragment  切换的fragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if(tempFragment != nextFragment){
            tempFragment = nextFragment;
            if(nextFragment != null){
                FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if(!nextFragment.isAdded()){
                    //nextFragment 还未添加
                    if(fromFragment != null){
                        transaction.hide(fromFragment);
                    }

                    transaction.add(R.id.fl_main,nextFragment).commit();
                }else {
                    //nextFragment 已经添加
                    if(fromFragment != null){
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    private void initFragment() {
        fragmentArray = new ArrayList<>();
        fragmentArray.add(new HomeFragment());
        fragmentArray.add(new TypeFragment());
        fragmentArray.add(new CommunityFragment());
        fragmentArray.add(new CartFragment());
        fragmentArray.add(new UserFragment());
    }

    private BaseFragment getFragment(int position) {
        if (fragmentArray != null && fragmentArray.size() > 0) {
            BaseFragment baseFragment = fragmentArray.get(position);
            return baseFragment;
        }
        return null;
    }
}
