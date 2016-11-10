package com.dengfx.demo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;
    private VideoFragment mVideoFragment;
    private PersonFragment mPersonFragment;
    private GraphFragment mGraphFragment;

    private static final String TAB_NAME_1 = "流量图像";
    private static final String TAB_NAME_2 = "监控视频";
    private static final String TAB_NAME_3 = "个人中心";
    private List<String> tabNames;
    private List<Fragment> fragments;
    private List<Integer> tabIcs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        super.setContentView(R.layout.activity_main);
        initView();
        initTablayoutAndViewPager();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initTablayoutAndViewPager() {
//        tabNames = new ArrayList<>();
        fragments = new ArrayList<>();
        tabIcs = new ArrayList<>();

//        tabNames.add(TAB_NAME_1);
//        tabNames.add(TAB_NAME_2);
//        tabNames.add(TAB_NAME_3);

        fragments.add(GraphFragment.newInstance());
        fragments.add(VideoFragment.newInstance());
        fragments.add(PersonFragment.newInstance());

        tabIcs.add(R.drawable.cloud);
        tabIcs.add(R.drawable.heart);
        tabIcs.add(R.drawable.star);

//        VPAndTLAdapter mVpAndTLAdapter = new VPAndTLAdapter(getSupportFragmentManager(), tabNames, fragments);
        VPAndTLAdapter_v2 mVpAndTLAdapter = new VPAndTLAdapter_v2(getSupportFragmentManager(),this,tabNames,fragments,tabIcs);
        mViewPager.setAdapter(mVpAndTLAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
