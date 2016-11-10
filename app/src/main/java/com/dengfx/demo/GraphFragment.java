package com.dengfx.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 邓FX on 2016/11/9.
 */

public class GraphFragment extends Fragment {

    private static final String TAG = "GraphFragment";
    protected TextView mTvContent;
    private View rootView;

    public static GraphFragment newInstance() {
        return new GraphFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        rootView = inflater.inflate(R.layout.fragment_graph, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 与发布者在同一个线程
     *
     * @param msg 事件1
     */
    public void onEvent(MsgEvent1 msg) {
        String content = msg.getMsg()
                + "\n ThreadName: " + Thread.currentThread().getName()
                + "\n ThreadId: " + Thread.currentThread().getId();
        System.out.println("onEvent(MsgEvent1 msg)收到" + content);
    }

    /**
     * 执行在主线程。
     * 非常实用，可以在这里将子线程加载到的数据直接设置到界面中。
     *
     * @param msg 事件1
     */
    public void onEventMainThread(MsgEvent1 msg) {
        String content = msg.getMsg()
                + "\n ThreadName: " + Thread.currentThread().getName()
                + "\n ThreadId: " + Thread.currentThread().getId();
        System.out.println("onEventMainThread(MsgEvent1 msg)收到" + content);
        mTvContent.setText(content);
    }

    /**
     * 执行在子线程，如果发布者是子线程则直接执行，如果发布者不是子线程，则创建一个再执行
     * 此处可能会有线程阻塞问题。
     *
     * @param msg 事件1
     */
    public void onEventBackgroundThread(MsgEvent1 msg) {
        String content = msg.getMsg()
                + "\n ThreadName: " + Thread.currentThread().getName()
                + "\n ThreadId: " + Thread.currentThread().getId();
        System.out.println("onEventBackgroundThread(MsgEvent1 msg)收到" + content);
    }

    /**
     * 执行在在一个新的子线程
     * 适用于多个线程任务处理， 内部有线程池管理。
     *
     * @param msg 事件1
     */
    public void onEventAsync(MsgEvent1 msg) {
        String content = msg.getMsg()
                + "\n ThreadName: " + Thread.currentThread().getName()
                + "\n ThreadId: " + Thread.currentThread().getId();
        System.out.println("onEventAsync(MsgEvent1 msg)收到" + content);
    }

    /**
     * 与发布者在同一个线程
     *
     * @param msg 事件2
     */
    public void onEvent(MsgEvent2 msg) {
        String content = msg.getMsg()
                + "\n ThreadName: " + Thread.currentThread().getName()
                + "\n ThreadId: " + Thread.currentThread().getId();
        System.out.println("onEvent(MsgEvent2 msg)收到" + content);
        mTvContent.setText(content);
    }

    private void initView(View rootView) {
        mTvContent = (TextView) rootView.findViewById(R.id.tv_content);
    }
}