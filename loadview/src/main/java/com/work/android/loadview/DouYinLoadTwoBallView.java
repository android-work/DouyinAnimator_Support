package com.work.android.loadview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class DouYinLoadTwoBallView extends View {

    private Path redPath;
    private Paint redPaint;
    private Paint bluePaint;
    private Path bluePath;
    private Paint paint;
    private Path path;
    private int redPoint = 0;
    private int redRadio = 10;
    private int bluePoint = 40;
    private int blueRadio = 15;
    private ValueAnimator valueAnimator;
    private int color1 = getResources().getColor(R.color.bigColor);
    private int color2 = getResources().getColor(R.color.smallColor);
    private int measuredHeight;
    private int mWidth;
    private int mHeight;


    public DouYinLoadTwoBallView(Context context) {
        this(context,null,0);
    }

    public DouYinLoadTwoBallView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DouYinLoadTwoBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    public void setColor(int color1,int color2){
        this.color1 = color1;
        this.color2 = color2;
    }

    private void initView() {

        redPaint = new Paint();
        redPaint.setStyle(Paint.Style.FILL);
        redPaint.setAntiAlias(true);
        redPath = new Path();

        bluePaint = new Paint();
        bluePaint.setStyle(Paint.Style.FILL);
        bluePaint.setAntiAlias(true);
        bluePath = new Path();

        paint = new Paint();
        path = new Path();

    }

    /**开启动画*/
    public void startAnimator(){
        if (valueAnimator!=null && valueAnimator.isRunning()) {
            stopAnimator();
        }
        valueAnimator = ValueAnimator.ofInt(0, 40, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                //计算小球移动位置
                redPoint = (int) animation.getAnimatedValue();
                bluePoint = 40 - redPoint;

                //计算小球半径
                redRadio = 10 * redPoint / 40 + 10;
                blueRadio = 20 - 10 * redPoint / 40;

                postInvalidate();

            }
        });
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(1500);
        valueAnimator.start();
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

    /**停止动画*/
    public void stopAnimator(){

        if (valueAnimator!=null){
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        bluePaint.setColor(color1);
        redPaint.setColor(color2);

        canvas.translate(canvas.getWidth()/2,canvas.getHeight()/2);

        //重置path
        redPath.reset();
        bluePath.reset();
        path.reset();

        //绘制两个零界小球
        redPath.addCircle(redPoint,0,redRadio, Path.Direction.CW);
        bluePath.addCircle(bluePoint,0,blueRadio, Path.Direction.CW);
        path.op(redPath,bluePath, Path.Op.INTERSECT);

        canvas.drawPath(redPath,redPaint);
        canvas.drawPath(bluePath,bluePaint);
        canvas.drawPath(path,paint);
    }
}
