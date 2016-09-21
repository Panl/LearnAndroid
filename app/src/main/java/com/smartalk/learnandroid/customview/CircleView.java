package com.smartalk.learnandroid.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by panl on 16/9/21.
 * contact panlei106@gmail.com
 */

public class CircleView  extends View{

  private final int defaultDiameter = (int) (100 * Resources.getSystem().getDisplayMetrics().density);
  private Paint paint;
  private Paint.Style style;
  private int diameter;
  private int strokeWidth = (int) (2 * Resources.getSystem().getDisplayMetrics().density);
  private LinearGradient linearGradient;
  @ColorInt
  private int gradientStartColor = Color.YELLOW;
  @ColorInt
  private int gradientEndColor = Color.RED;



  public CircleView(Context context) {
    this(context, null);
  }

  public CircleView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context,attrs,defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {
    strokeWidth = 0;
    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(Color.RED);
    paint.setStyle(Paint.Style.FILL);
    paint.setStrokeWidth(strokeWidth);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width, height;
    if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
      width = MeasureSpec.getSize(widthMeasureSpec);
    }else {
      width = defaultDiameter;
    }
    if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
      height = MeasureSpec.getSize(heightMeasureSpec);
    }else {
      height = defaultDiameter;
    }
    diameter = Math.max(width, height);
    setMeasuredDimension(diameter, diameter);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int center = diameter/2;
    int radius = (diameter-strokeWidth)/2;
    if (linearGradient == null) {
      linearGradient = new LinearGradient(0, 0, diameter, diameter , gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
    }
    paint.setShader(linearGradient);
    canvas.drawCircle(center,center,radius,paint);
  }
}
