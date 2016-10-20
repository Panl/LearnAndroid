package com.smartalk.learnandroid.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

import com.smartalk.learnandroid.DisplayUtil;
import com.smartalk.learnandroid.R;

public class CircleView extends View{

  private int defaultDiameter = DisplayUtil.dp2px(100);
  private int diameter;
  private float circleStrokeWidth;
  private boolean isFillStyle;

  @ColorInt
  private int gradientStartColor;
  @ColorInt
  private int gradientEndColor;
  @ColorInt
  private int circleStrokeColor;

  private Paint paint;
  private Paint.Style style;
  private LinearGradient linearGradient;

  public CircleView(Context context) {
    this(context, null);
  }

  public CircleView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
    circleStrokeColor = typedArray.getColor(R.styleable.CircleView_circleStrokeColor, Color.RED);
    circleStrokeWidth = typedArray.getDimension(R.styleable.CircleView_circleStrokeWidth, DisplayUtil.dp2px(2));
    gradientStartColor = typedArray.getColor(R.styleable.CircleView_circleGradientStartColor, Color.RED);
    gradientEndColor = typedArray.getColor(R.styleable.CircleView_circleGradientEndColor, Color.YELLOW);
    isFillStyle = typedArray.getBoolean(R.styleable.CircleView_circleIsFillStyle, true);
    style = getPaintStyle(isFillStyle);
    typedArray.recycle();
    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
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
    int radius = (int) ((diameter - circleStrokeWidth)/2);
    if (isFillStyle) {
      radius = diameter/2;
    }
    if (linearGradient == null && isFillStyle) {
      linearGradient = new LinearGradient(0, 0, diameter, diameter, gradientStartColor, gradientEndColor, Shader.TileMode.CLAMP);
      paint.setShader(linearGradient);
    }
    paint.setColor(circleStrokeColor);
    paint.setStrokeWidth(circleStrokeWidth);
    paint.setStyle(style);
    canvas.drawCircle(center, center, radius, paint);
  }

  public void setCircleStrokeWidth(float circleStrokeWidth) {
    this.circleStrokeWidth = circleStrokeWidth;
  }

  public void setFillStyle() {
    this.style = getPaintStyle(true);
    invalidate();
  }

  public void setStrokeStyle() {
    this.style = getPaintStyle(false);
    invalidate();
  }

  public void setGradientColor(@ColorInt int gradientStartColor, @ColorInt int gradientEndColor) {
    this.gradientStartColor = gradientStartColor;
    this.gradientEndColor = gradientEndColor;
    invalidate();
  }

  public void setCircleStrokeColor(@ColorInt int circleStrokeColor) {
    this.circleStrokeColor = circleStrokeColor;
    invalidate();
  }

  private Paint.Style getPaintStyle(boolean isFillStyle) {
    return isFillStyle ? Paint.Style.FILL : Paint.Style.STROKE;
  }
}
