package com.smartalk.learnandroid.customview;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.smartalk.learnandroid.DisplayUtil;

/**
 * Created by panl on 16/3/14.
 * contact panlei106@gmail.com
 */
public class PageLayout extends FrameLayout {

    private ViewDragHelper viewDragHelper;
    private View menuView, mainView;
    private int width;
    private int slideWidth;
    private ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mainView == child;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left > 0 ? left : 0;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            Log.i("dx", dx + "");
            Log.i("left", left + "");
            float scale = 1.0f - 0.2f * left / slideWidth;
            changedView.setPivotX(0);
            changedView.setPivotX(width / 2);
            changedView.setScaleX(scale);
            changedView.setScaleY(scale);
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (mainView.getLeft() < width / 3) {
                viewDragHelper.smoothSlideViewTo(mainView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(PageLayout.this);
            } else {
                viewDragHelper.smoothSlideViewTo(mainView, slideWidth, 0);
                ViewCompat.postInvalidateOnAnimation(PageLayout.this);
            }
        }
    };

    public PageLayout(Context context) {
        super(context);
        initView();
    }

    public PageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        viewDragHelper = ViewDragHelper.create(this, callBack);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView = getChildAt(0);
        mainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = menuView.getMeasuredWidth();
        slideWidth = width - DisplayUtil.dp2px(getContext(),150);
        Log.i("SlideWidth",slideWidth+"");
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
