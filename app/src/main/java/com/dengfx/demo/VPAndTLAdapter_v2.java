package com.dengfx.demo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import java.util.List;

/**
 * Created by 邓FX on 2016/11/9.
 */

public class VPAndTLAdapter_v2 extends FragmentStatePagerAdapter {

    private List<String> tabNames;
    private List<Fragment> fragments;

    private List<Integer> tabIcs;
    private SpannableString spannableString;
    private Context mContext;

    public VPAndTLAdapter_v2(FragmentManager fm, Context mContext, List<String> tabNames, List<Fragment> fragments, List<Integer> tabIcs) {
        super(fm);
        this.mContext = mContext; //获取图片资源的时候需要Context参数
        this.fragments = fragments;
        this.tabNames = tabNames; //每个Tab上的文字、可以为null
        this.tabIcs = tabIcs; //每个Tab上的图标，可以为null
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 这个函数就是给TabLayout的Tab设定Title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (tabNames == null) {     //不设置文字
            spannableString = new SpannableString(" ");  //这里要给图片留一个字符的空间。
        } else {
            spannableString = new SpannableString(" " + tabNames.get(position));
        }
        if (tabIcs != null) {    //设置图标
            spannableString.setSpan(new ImageSpan(mContext, tabIcs.get(position)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }
}