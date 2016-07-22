package com.socks.androiddemo.point;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2016/3/4.
 */
public class PointView extends View {

    private ValueAnimator valueAnimator;
    private final Paint paint;
    private ArrayList<MyPoint> list;

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }
    int w;
    int h;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w=w;
        this.h=h;
        addPoint();
    }
    Random random=new Random();
    private void addPoint() {
        list=new ArrayList<>();
        //0
        for (int a=0;a<3;a++){
            MyPoint myPoint=new MyPoint(0,w,h);
            list.add(myPoint);
        }
        //1
        for (int a=0;a<3;a++){
            MyPoint myPoint=new MyPoint(1,w,h);
            list.add(myPoint);
        }
        //2
        for (int a=0;a<3;a++){
            MyPoint myPoint=new MyPoint(2,w,h);

            list.add(myPoint);
        }
        //3
        for (int a=0;a<3;a++){
            MyPoint myPoint=new MyPoint(3,w,h);
            list.add(myPoint);
        }
    }

    public  void startAnimation(){

        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1f);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                postInvalidate();
            }
        });
        valueAnimator.setFrameDelay(200);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

  }

    public  void endAnimation(){
        list.clear();
        valueAnimator.cancel();
        invalidate();

    }
    private boolean isFirst=true;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isFirst){
            isFirst=false;
            return;
        }
        for (int a=0;a<list.size();a++){
            MyPoint myPoint = list.get(a);
            changePosition(myPoint);
            canvas.drawCircle(myPoint.x,myPoint.y,4,paint);
        }
    }

    private void changePosition(MyPoint myPoint) {
//        float x = startPoint.x + fraction * (endPoint.x - startPoint.x);
//        float y = startPoint.y+ fraction * (endPoint.y - startPoint.y);
        float fraction=random.nextFloat();
       myPoint.x = myPoint.x + fraction * (w/2 - myPoint.x);
      myPoint.y = myPoint.y+ fraction * (h/2 - myPoint.y);

        if(Math.abs(myPoint.x-w/2)<random.nextFloat()*33){
            myPoint.init(w,h);
        }

    }
}
