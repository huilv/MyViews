package com.socks.androiddemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Administrator on 2016/7/19.
 */
public class MyHeart extends View {

    private Paint paint;
    private Path path;
    private PathMeasure pathMeasure;

    public MyHeart(Context context) {
        super(context);
    }

    public MyHeart(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    public MyHeart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        buildPath(path);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
        canvas.drawCircle(circlePoint.x,circlePoint.y,10,paint);
    }
    private Point circlePoint=new Point();
    private void buildPath(Path path) {
        Point point0=new Point(getWidth()/2,getHeight()/2+100);
        Point point2=new Point(getWidth()/2,getHeight()/2-100);

        Point point1=new Point(getWidth()/2-150,getHeight()/2-200);
        Point point3=new Point(getWidth()/2+150,getHeight()/2-200);

        path.moveTo(point0.x,point0.y);
        path.quadTo(point1.x,point1.y,point2.x,point2.y);
        path.quadTo(point3.x,point3.y,point0.x,point0.y);
        pathMeasure = new PathMeasure(path,true);
        start();

    }

    public void start() {

        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,pathMeasure.getLength());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float pos[]=new float[2];
                Log.i("===","======"+animation.getAnimatedValue());
                pathMeasure.getPosTan((Float) animation.getAnimatedValue(),pos,null);
                circlePoint.x= (int) pos[0];
                circlePoint.y= (int) pos[1];
                postInvalidate();
            }
        });
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setDuration(3000);
        valueAnimator.setFrameDelay(50);
        valueAnimator.start();
    }
}
