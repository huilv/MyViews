package com.socks.androiddemo.dust;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2016/3/8.
 */
public class DustView extends View {

    private  int screenWith;
    private  int screenHeight;
    private  int positionHeight;
    private Paint paint;
    private final int cleanerHeight;
    private ImageView imageView;

    public DustView(Context context, AttributeSet attrs) {
        super(context, attrs);
       WindowManager wm= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        screenWith = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();
        cleanerHeight = Utils.dip2px(getContext(), 200);
        init();
    }
    Random random=new Random();
   private ArrayList<MyPoint> list;
    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        list=new ArrayList<>();
        for(int a=0;a<15;a++){
            MyPoint myPoint=new MyPoint();
            myPoint.x=(screenWith/2-140)+random.nextFloat()*250;//中间 -100 到50处
            myPoint.y=(screenHeight/5)+random.nextFloat()*100f;//大致在三分之一处
            myPoint.size=7f+random.nextFloat()*10f;
            list.add(myPoint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        positionHeight = h- cleanerHeight -16;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int a=0;a<list.size();a++){
            MyPoint myPoint = list.get(a);
            changePosition(myPoint);
          //  paint.setAlpha(myPoint.alpha);
            canvas.drawCircle(myPoint.x, myPoint.y, myPoint.size, paint);
        }
    }

    private void changePosition(MyPoint myPoint) {
        myPoint.y=myPoint.y+random.nextFloat()*10;
      //  myPoint.alpha= (int) (myPoint.alpha+random.nextFloat()*5);
       if(myPoint.y>positionHeight+20+random.nextFloat()*10){
           myPoint.y=(screenHeight/5)+random.nextFloat()*100f;//大致在三分之一处
         //  myPoint.alpha=180;
       }


        postInvalidate();

    }

    public void startValueAnimation() {
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1.0f);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatCount(ValueAnimator.RESTART);

        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(imageView,"translationY",0,60F);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        valueAnimator.setRepeatCount(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
        objectAnimator.start();
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    private  class  MyPoint{
        float x;
        float y;
        float size;
       // int alpha=180;
    }
}
