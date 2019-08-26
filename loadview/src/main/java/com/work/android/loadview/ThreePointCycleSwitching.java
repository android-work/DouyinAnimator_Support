package com.work.android.loadview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class ThreePointCycleSwitching extends View {

    private Paint normalPaint;
    private Paint selectPaint;
    private Path path;
    private int positon = 10;
    private ValueAnimator valueAnimator;
    private int normalColor = Color.GRAY;
    private int selectColor = Color.WHITE;
    private int mWidth;
    private int mHeight;

    public ThreePointCycleSwitching(Context context) {
        this(context,null,0);
    }

    public ThreePointCycleSwitching(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ThreePointCycleSwitching(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setColor(int normalColor,int selectColor) {
        this.normalColor = normalColor;
        this.selectColor = selectColor;
    }

    private void initView() {
        normalPaint = new Paint();
        normalPaint.setStyle(Paint.Style.FILL);

        selectPaint = new Paint();
        selectPaint.setStyle(Paint.Style.FILL);


        path = new Path();

    }

    public void startAnimator(){
        if (valueAnimator!=null && valueAnimator.isRunning()){
            stopAnimator();
        }
        valueAnimator = ValueAnimator.ofInt(0, 60);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int offset = (int) animation.getAnimatedValue() + 10;
                if (offset>0 && offset < 20){
                    positon = 10;
                }
                if (offset >30 && offset< 50){
                    positon = 40;
                }
                if (offset>60){
                    positon = 70;
                }


                invalidate();
            }
        });

        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    public void stopAnimator() {

        if (valueAnimator!=null){
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getSize(200, widthMeasureSpec);
        mHeight = getSize(200, heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);

    }

    public int getSize(int defaultSize,int measureSpace){

        int mode = MeasureSpec.getMode(measureSpace);
        int measureSize = MeasureSpec.getSize(measureSpace);

        int size = defaultSize;
        switch (mode){
            //没有限制模式
            case MeasureSpec.UNSPECIFIED:
                size = defaultSize;
                break;
            //最大模式：wrap_content
            case MeasureSpec.AT_MOST:
                size = measureSize;
                break;
            //精确模式:match_parent、具体大小
            case MeasureSpec.EXACTLY:
                size = measureSize;
                break;
        }

        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        normalPaint.setColor(normalColor);
        selectPaint.setColor(selectColor);

        canvas.drawCircle(10,10,10,normalPaint);
        canvas.drawCircle(40,10,10,normalPaint);
        canvas.drawCircle(70,10,10,normalPaint);

        path.reset();
        path.addCircle(positon,10,10, Path.Direction.CW);

        canvas.drawPath(path,selectPaint);

    }
}
