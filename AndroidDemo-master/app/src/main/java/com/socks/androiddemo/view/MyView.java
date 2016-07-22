package com.socks.androiddemo.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.socks.androiddemo.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2016/7/21.
 */
public class MyView extends View {
    private Context context;
    private Paint paint;
    private Bitmap bitmap;
    private Random random=new Random();
   private ArrayList<Follower> list=new ArrayList<>();
    public MyView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_heart);
    }
    private int width;
    private int height;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
        for(int a=0;a<10;a++){
            Follower  follower = new Follower();
            follower.path=new Path();
            initPath(follower);
            follower.pathMeasure = new PathMeasure(follower.path,false);
            list.add(follower);
            follower=null;
        }
        start();
    }

    private void initPath(Follower follower) {
        int startOrEndX=width/8+random.nextInt(width*6/8);
        follower.x=startOrEndX;
        follower.y=0;
        buildPath(follower,startOrEndX);

    }

    private void buildPath(Follower follower, int startOrEndX) {
        Point point0=new Point(startOrEndX,0);
        Point point1=new Point();
        Point point2=new Point();
        if(startOrEndX<width/2){
            point1.x=startOrEndX-50;
            point2.x=width-startOrEndX+50;
        }else{
            point1.x=startOrEndX+50;
            point2.x=width-startOrEndX-50;
        }
         point1.y=height/3;
        point2.y=height*2/3;
        Point point3=new Point(startOrEndX,height+10);
        follower.path.moveTo(point0.x,point0.y);
        follower.path.cubicTo(point1.x,point1.y,point2.x,point2.y,point3.x,point3.y);
    }

    private void start() {
        ValueAnimator valueAnimator0=ValueAnimator.ofFloat(0,1);
        valueAnimator0.setFrameDelay(50);
        valueAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                list.get(0).setPoint(fraction);
                invalidate();
            }
        });
        valueAnimator0.setDuration(2000);
        valueAnimator0.setRepeatCount(ValueAnimator.INFINITE);


        ValueAnimator valueAnimator1=ValueAnimator.ofFloat(0,1);
        valueAnimator1.setFrameDelay(50);
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                list.get(1).setPoint(fraction);
                invalidate();
            }
        });
        valueAnimator1.setDuration(2050);
        valueAnimator1.setRepeatCount(ValueAnimator.INFINITE);



        ValueAnimator valueAnimator2=ValueAnimator.ofFloat(0,1);
        valueAnimator2.setFrameDelay(50);
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                list.get(2).setPoint(fraction);

                invalidate();
            }
        });
        valueAnimator2.setDuration(2100);
        valueAnimator2.setRepeatCount(ValueAnimator.INFINITE);




        ValueAnimator valueAnimator3=ValueAnimator.ofFloat(0,1);
        valueAnimator3.setFrameDelay(50);
        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                list.get(3).setPoint(fraction);

                invalidate();
            }
        });
        valueAnimator3.setDuration(2150);
        valueAnimator3.setRepeatCount(ValueAnimator.INFINITE);






        ValueAnimator valueAnimator4=ValueAnimator.ofFloat(0,1);
        valueAnimator4.setFrameDelay(50);
        valueAnimator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                list.get(4).setPoint(fraction);

                invalidate();
            }
        });
        valueAnimator4.setDuration(2200);
        valueAnimator4.setRepeatCount(ValueAnimator.INFINITE);




        ValueAnimator valueAnimator5=ValueAnimator.ofFloat(0,1);
        valueAnimator5.setFrameDelay(50);
        valueAnimator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                list.get(5).setPoint(fraction);

                invalidate();
            }
        });
        valueAnimator5.setDuration(2250);
        valueAnimator5.setRepeatCount(ValueAnimator.INFINITE);


        ValueAnimator valueAnimator6=ValueAnimator.ofFloat(0,1);
        valueAnimator6.setFrameDelay(50);
        valueAnimator6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                list.get(6).setPoint(fraction);

                invalidate();
            }
        });
        valueAnimator6.setDuration(2300);
        valueAnimator6.setRepeatCount(ValueAnimator.INFINITE);



        ValueAnimator valueAnimator7=ValueAnimator.ofFloat(0,1);
        valueAnimator7.setFrameDelay(50);
        valueAnimator7.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                list.get(7).setPoint(fraction);

                invalidate();
            }
        });
        valueAnimator7.setDuration(2350);
        valueAnimator7.setRepeatCount(ValueAnimator.INFINITE);





        ValueAnimator valueAnimator8=ValueAnimator.ofFloat(0,1);
        valueAnimator8.setFrameDelay(50);
        valueAnimator8.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                list.get(8).setPoint(fraction);

                invalidate();
            }
        });
        valueAnimator8.setDuration(2400);
        valueAnimator8.setRepeatCount(ValueAnimator.INFINITE);




        ValueAnimator valueAnimator9=ValueAnimator.ofFloat(0,1);
        valueAnimator9.setFrameDelay(50);
        valueAnimator9.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                list.get(9).setPoint(fraction);

                invalidate();
            }
        });
        valueAnimator9.setDuration(2450);
        valueAnimator9.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(valueAnimator0,valueAnimator1,valueAnimator2,valueAnimator3,valueAnimator4,valueAnimator5,valueAnimator6,valueAnimator7,valueAnimator8,valueAnimator9);
        animatorSet.start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int a=0;a<10;a++){
            Follower follower = list.get(a);
           // canvas.drawPath(follower.path,paint);
            canvas.drawBitmap(bitmap,follower.x,follower.y,null);
        }

    }
  private float aa[]=new float[]{0.001f,0.002f,0.003f};
    private  class Follower{
        public int x;
        public int y;
        public  Path path;
        public PathMeasure pathMeasure;
        public float fraction;
        public void setPoint(float fraction) {
            float pos[]=new float[2];
            pathMeasure.getPosTan(pathMeasure.getLength()* fraction,pos,null);
            x= (int) pos[0];
            y= (int) pos[1];
        }
    }

}
