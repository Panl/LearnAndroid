package com.smartalk.learnandroid.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.smartalk.learnandroid.imageloader.MyUtils;

/**
 * Created by panl on 16/3/14.
 * contact panlei106@gmail.com
 */
public class PageLayout extends ViewGroup {
    private int screenWidth;
    private int mLastX;
    private int mStart;
    private int mEnd;
    private Scroller scroller;

    public PageLayout(Context context) {
        super(context);
        init(context);
    }

    public PageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        scroller = new Scroller(context);
        screenWidth = MyUtils.getScreenMetrics(context).widthPixels;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
        layoutParams.width = screenWidth * childCount;
        setLayoutParams(layoutParams);

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE){
                child.layout(i*screenWidth,t,(i+1)*screenWidth,b);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        Log.i("x",x+"");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mStart = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                Log.i("getScrollX",getScrollX()+"");
                int dx = mLastX - x;
                scrollBy(dx,0);
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollX();
                int dScrollX = mEnd - mStart;
                if (dScrollX > 0){
                    if (dScrollX < screenWidth/3){
                        scroller.startScroll(getScrollX(),0,-dScrollX,0);
                    }else {
                        scroller.startScroll(getScrollX(),0,screenWidth = dScrollX,0);
                    }
                }else {
                    if (-dScrollX < screenWidth/3){
                        scroller.startScroll(getScrollX(),0,-dScrollX,0);
                    }else {
                        scroller.startScroll(getScrollX(),0,-screenWidth - dScrollX,0);
                    }
                }
                break;
        }
        mLastX = x;
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),0);
            postInvalidate();
        }
    }
}
