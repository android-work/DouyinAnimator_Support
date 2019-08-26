package com.work.android.loadview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DownloadPopupWindowView extends View {

    private Paint circlePaint;
    private Paint textPaint;
    private int radio = 50;
    private RectF rect;
    private float endAngel = 0;
    private Paint paint;
    private String content = "正在保存到本地";
    private int currentPorgress;
    private float strokeWidth = dip2px(2);
    private float progressTvSize = dip2px(14);
    private int color = Color.WHITE;
    private float size = dip2px(18);

    public DownloadPopupWindowView(Context context) {
        this(context,null,0);
    }

    public DownloadPopupWindowView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DownloadPopupWindowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    /**设置提示内容*/
    public void setContent(String content){
        this.content = content;
    }

    /**设置当前进度*/
    public void setCurrentPorgress(float progress){
        Log.e("tag","progress:"+progress);
        currentPorgress = (int)(progress * 100);
        endAngel = progress * 360;
        invalidate();
    }

    /**设置进度轮廓宽度*/
    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = dip2px(strokeWidth);
    }

    /**设置文字大小*/
    public void setProgressTvSize(float progressTvSize) {
        this.progressTvSize = dip2px(progressTvSize);

    }

    /**设置颜色*/
    public void setColor(int color) {
        this.color = color;
    }

    /**设置环形进度大小*/
    public void setSize(float size){
        this.size = dip2px(size);
    }

    private void initView() {

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(strokeWidth);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(progressTvSize);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(progressTvSize);

       rect = new RectF(-size, -size, size, size);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        textPaint.setColor(color); circlePaint.setColor(color);paint.setColor(color);

        canvas.translate(canvas.getWidth()/2,canvas.getHeight()/2);
        canvas.save();
            canvas.rotate(-135);
            canvas.drawArc(rect,0,endAngel,false,circlePaint);
        canvas.restore();

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float bottom = fontMetrics.bottom;
        float top = fontMetrics.top;
        float v = textPaint.measureText(currentPorgress +"%");
        canvas.drawText(currentPorgress+"%",( - v)/2,(-(top - bottom)/4),textPaint);

        Paint.FontMetrics fontMetrics1 = textPaint.getFontMetrics();
        float bottom1 = fontMetrics1.bottom;
        float top1 = fontMetrics1.top;
        float v1 = textPaint.measureText(content);
        canvas.drawText(content,( - v1)/2,(bottom1 - top1)/2+60,paint);


    }

    public int dip2px( float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



}
