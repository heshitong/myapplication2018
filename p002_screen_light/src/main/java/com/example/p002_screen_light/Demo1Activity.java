package com.example.p002_screen_light;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.SparseArrayCompat;
import android.text.TextUtils;
import android.view.View;

import com.example.p002_screen_light.factorys.Demo1FragmentFactory;
import com.example.shining.libutils.utilslib.base.BaseActivity;
import com.example.shining.libutils.utilslib.base.BaseFragment;
import com.example.shining.libutils.utilslib.base.BaseIndexFragment;
import com.example.shining.libutils.utilslib.etc.ComFragmentHelper;

public class Demo1Activity extends BaseActivity implements View.OnClickListener {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo1;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclickListener();
        doNetWork();
    }

    private void doNetWork() {

    }

    private void onclickListener() {

    }

    private void findview() {
        setupFragments();
    }


    /**
     * 初始化首页fragments
     */
    private void setupFragments() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SparseArrayCompat<Class<? extends BaseFragment>> array = Demo1FragmentFactory.get();//一个版本模式bufen
        int size = array.size();
        BaseFragment item;
        for (int i = 0; i < size; i++) {
            item = ComFragmentHelper.newFragment(array.valueAt(i), null);
            ft.replace(array.keyAt(i), item, item.getClass().getName());
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * fragment间通讯bufen
     *
     * @param value 要传递的值
     * @param tag   要通知的fragment的tag
     */
    public void callFragment(Object value, String... tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment;
        for (String item : tag) {
            if (TextUtils.isEmpty(item)) {
                continue;
            }

            fragment = fm.findFragmentByTag(item);
            if (fragment != null && fragment instanceof BaseIndexFragment) {
                ((BaseIndexFragment) fragment).call(value);
            }
        }
    }
}

