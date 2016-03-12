package com.smartalk.learnandroid.canvas.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by panl
 * Date at 16/2/12.
 */
public class CanvasLearnView extends View {
    private Paint paint;

    public CanvasLearnView(Context context) {
        super(context);
        initPaint();
    }

    public CanvasLearnView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CanvasLearnView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSomething(canvas);
    }

    private void drawSomething(Canvas canvas) {
       /* canvas.drawPoint(200,200,paint);//在坐标(200,200)位置绘制一个点
        canvas.drawPoints(new float[]{  //绘制一组点，坐标位置由float数组指定
                200f,400f,
                200f,500f,
                200f,600f
        },paint);

        canvas.drawLine(300,300,500,600,paint);    // 在坐标(300,300)(500,600)之间绘制一条直线
        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
                100,200,200,200,
                100,300,200,300
        },paint);*/

        /*//绘制矩形的三种方式
        //第一种
        canvas.drawRect(100, 100, 600, 400, paint);

        //第二种
        Rect rect = new Rect(100, 500, 600, 800);
        canvas.drawRect(rect, paint);

        //第三种
        RectF rectF = new RectF(100, 900, 600, 1200);
        canvas.drawRect(rectF, paint);*/

        // 第一种
        //RectF rectF = new RectF(100,100,600,400);
        //canvas.drawRoundRect(rectF,30,30,paint);

        // 第二种
        //canvas.drawRoundRect(100,100,800,400,30,30,paint);

        //绘制椭圆
        //canvas.drawOval(100,100,600,400,paint);
        //RectF rectF = new RectF(100,100,600,400);
        //canvas.drawOval(rectF,paint);

        //绘制圆形
        //canvas.drawCircle(500,500,100,paint);

        //绘制圆弧
        RectF rectf = new RectF(100,100,600,400);
        canvas.drawArc(rectf,0,90,true,paint);

    }
}
