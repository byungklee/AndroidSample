package com.byulando.temp;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

/**
 * Created by byung on 2/14/17.
 */

public class MyNestedScrollView extends NestedScrollView {
    private int slop;
    private float mInitialMotionX;
    private float mInitialMotionY;

    public MyNestedScrollView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        ViewConfiguration config = ViewConfiguration.get(context);
        slop = config.getScaledEdgeSlop();
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private float xDistance, yDistance, lastX, lastY;

    private boolean mIsBeingDragged;
    private int mActivePointerId;
    int mTouchSlop;
    int mLastMotionY;
    int mNestedYOffset;
    final int  INVALID_POINTER =-1;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        final float x = ev.getX();
        final float y = ev.getY();
        int action = ev.getAction() & MotionEvent.ACTION_MASK;

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("action down");
                xDistance = yDistance = 0f;
                lastX = ev.getX();
                lastY = ev.getY();

                // This is very important line that fixes
                computeScroll();


                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("action move");
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - lastX);
                System.out.println("X dis: " + xDistance);
                yDistance += Math.abs(curY - lastY);
                System.out.println("Y dis: " + yDistance);
                lastX = curX;
                lastY = curY;

                if (xDistance < yDistance) {
                    System.out.println("action move return tru");
                    return true;
                }
        }


        return false;

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        System.out.println(ev.getAction() + " ");
        return super.onTouchEvent(ev);
    }
}