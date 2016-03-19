package com.smartalk.learnandroid.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by panl on 16/3/19.
 * contact panlei106@gmail.com
 */
public class YellowManView extends View {

    private Paint mPaint;
    private float bodyWidth;
    private float bodyHeigh;
    private static final float BODY_SCALE = 0.6f;//身体主干占整个view的比重
    private static final float BODY_WIDTH_HEIGHT_SCALE = 0.6f; //  身体的比例设定为 w:h = 3:5

    private float mStrokeWidth = 4;//描边宽度
    private float offset;//计算时，部分需要 考虑描边偏移
    private float radius;//身体上下半圆的半径
    private int colorClothes = Color.rgb(32, 116, 160);//衣服的颜色
    private int colorBody = Color.rgb(249, 217, 70);//身体的颜色
    private int colorStroke = Color.BLACK;
    private RectF bodyRect;
    private float handsHeight;//计算出吊带的高度时，可以用来做手的高度
    private float footHeigh;//脚的高度，用来画脚部阴影时用

    public YellowManView(Context context) {
        super(context);
    }

    public YellowManView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YellowManView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initParams();
    }

    private void initParams() {

        bodyWidth = Math.min(getWidth(), getHeight() * BODY_WIDTH_HEIGHT_SCALE) * BODY_SCALE;
        bodyHeigh = Math.min(getWidth(), getHeight() * BODY_WIDTH_HEIGHT_SCALE) / BODY_WIDTH_HEIGHT_SCALE * BODY_SCALE;

        mStrokeWidth = Math.max(bodyWidth / 50, mStrokeWidth);
        offset = mStrokeWidth / 2;

        bodyRect = new RectF();
        bodyRect.left = (getWidth() - bodyWidth) / 2;
        bodyRect.top = (getHeight() - bodyHeigh) / 2;
        bodyRect.right = bodyRect.left + bodyWidth;
        bodyRect.bottom = bodyRect.top + bodyHeigh;

        radius = bodyWidth / 2;
        footHeigh = radius * 0.4333f;

        handsHeight = (getHeight() + bodyHeigh) / 2 + offset - radius * 1.65f;
    }

    private void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
        } else {
            mPaint.reset();
        }
        mPaint.setAntiAlias(true);//边缘无锯齿
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBody(canvas);
        drawBodyStroke(canvas);
        drawClothes(canvas);
    }

    private void drawBody(Canvas canvas) {
        initPaint();
        mPaint.setColor(colorBody);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(bodyRect, radius, radius, mPaint);

    }

    private void drawBodyStroke(Canvas canvas) {
        initPaint();
        mPaint.setColor(colorStroke);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(bodyRect, radius, radius, mPaint);
    }

    private void drawClothes(Canvas canvas) {
        initPaint();

        RectF rect = new RectF();

        rect.left = (getWidth() - bodyWidth) / 2 + offset;
        rect.top = (getHeight() + bodyHeigh) / 2 - radius * 2 + offset;
        rect.right = rect.left + bodyWidth - offset * 2;
        rect.bottom = rect.top + radius * 2 - offset * 2;

        mPaint.setColor(colorClothes);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mStrokeWidth);
        canvas.drawArc(rect, 0, 180, true, mPaint);

        int h = (int) (radius * 0.5);
        int w = (int) (radius * 0.3);

        rect.left += w;
        rect.top = rect.top + radius - h;
        rect.right -= w;
        rect.bottom = rect.top + h;

        canvas.drawRect(rect, mPaint);

        //画横线
        initPaint();
        mPaint.setColor(colorStroke);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mStrokeWidth);
        float[] pts = new float[20];//5条线

        pts[0] = rect.left - w;
        pts[1] = rect.top + h;
        pts[2] = pts[0] + w;
        pts[3] = pts[1];

        pts[4] = pts[2];
        pts[5] = pts[3] + offset;
        pts[6] = pts[4];
        pts[7] = pts[3] - h;

        pts[8] = pts[6] - offset;
        pts[9] = pts[7];
        pts[10] = pts[8] + (radius - w) * 2;
        pts[11] = pts[9];

        pts[12] = pts[10];
        pts[13] = pts[11] - offset;
        pts[14] = pts[12];
        pts[15] = pts[13] + h;

        pts[16] = pts[14] - offset;
        pts[17] = pts[15];
        pts[18] = pts[16] + w;
        pts[19] = pts[17];
        canvas.drawLines(pts, mPaint);

        //画左吊带
        initPaint();
        mPaint.setColor(colorClothes);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(rect.left - w - offset, handsHeight);
        path.lineTo(rect.left + h / 4, rect.top + h / 2);
        final float smallW = w / 2 * (float) Math.sin(Math.PI / 4);
        path.lineTo(rect.left + h / 4 + smallW, rect.top + h / 2 - smallW);
        final float smallW2 = w / (float) Math.sin(Math.PI / 4) / 2;
        path.lineTo(rect.left - w - offset, handsHeight - smallW2);

        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setColor(colorStroke);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(rect.left + h / 5, rect.top + h / 4, mStrokeWidth * 0.7f, mPaint);

        //画右吊带

        initPaint();
        mPaint.setColor(colorClothes);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        path.reset();
        path.moveTo(rect.left - w + 2 * radius - offset, handsHeight);
        path.lineTo(rect.right - h / 4, rect.top + h / 2);
        path.lineTo(rect.right - h / 4 - smallW, rect.top + h / 2 - smallW);
        path.lineTo(rect.left - w + 2 * radius - offset, handsHeight - smallW2);

        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setColor(colorStroke);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(rect.right - h / 5, rect.top + h / 4, mStrokeWidth * 0.7f, mPaint);

        //中间口袋
        initPaint();
        mPaint.setColor(colorStroke);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        path.reset();
        float radiusBigPokect = w / 2.0f;
        path.moveTo(rect.left + 1.5f * w, rect.bottom - h / 4);
        path.lineTo(rect.right - 1.5f * w, rect.bottom - h / 4);
        path.lineTo(rect.right - 1.5f * w, rect.bottom + h / 4);
        //path.addArc(rect.right - 1.5f * w - radiusBigPokect * 2, rect.bottom + h / 4 - radiusBigPokect,
        //rect.right - 1.5f * w, rect.bottom + h / 4 + radiusBigPokect, 0, 90);
        path.addArc(new RectF(rect.right - 1.5f * w - radiusBigPokect * 2, rect.bottom + h / 4 - radiusBigPokect,
                rect.right - 1.5f * w, rect.bottom + h / 4 + radiusBigPokect), 0, 90);
        path.lineTo(rect.left + 1.5f * w + radiusBigPokect, rect.bottom + h / 4 + radiusBigPokect);

        //path.addArc(rect.left + 1.5f * w, rect.bottom + h / 4 - radiusBigPokect,
        //rect.left + 1.5f * w + 2 * radiusBigPokect, rect.bottom + h / 4 + radiusBigPokect, 90, 90);
        path.addArc(new RectF(rect.left + 1.5f * w, rect.bottom + h / 4 - radiusBigPokect,
                rect.left + 1.5f * w + 2 * radiusBigPokect, rect.bottom + h / 4 + radiusBigPokect), 90, 90);
        path.lineTo(rect.left + 1.5f * w, rect.bottom - h / 4 - offset);
        canvas.drawPath(path, mPaint);

//        下边一竖，分开裤子
        canvas.drawLine(bodyRect.left + bodyWidth / 2, bodyRect.bottom - h * 0.8f, bodyRect.left + bodyWidth / 2, bodyRect.bottom, mPaint);
//      左边的小口袋
        float radiusSamllPokect = w * 1.2f;
        //canvas.drawArc(bodyRect.left - radiusSamllPokect, bodyRect.bottom - radius - radiusSamllPokect,
        //bodyRect.left + radiusSamllPokect, bodyRect.bottom - radius + radiusSamllPokect, 80, -60, false, mPaint);
        canvas.drawArc(new RectF(bodyRect.left - radiusSamllPokect, bodyRect.bottom - radius - radiusSamllPokect,
                bodyRect.left + radiusSamllPokect, bodyRect.bottom - radius + radiusSamllPokect), 80, -60, false, mPaint);
//      右边小口袋
        //canvas.drawArc(bodyRect.right - radiusSamllPokect, bodyRect.bottom - radius - radiusSamllPokect,
                //bodyRect.right + radiusSamllPokect, bodyRect.bottom - radius + radiusSamllPokect, 100, 60, false, mPaint);
        canvas.drawArc(new RectF(bodyRect.right - radiusSamllPokect, bodyRect.bottom - radius - radiusSamllPokect,
                bodyRect.right + radiusSamllPokect, bodyRect.bottom - radius + radiusSamllPokect), 100, 60, false, mPaint);
    }


}
