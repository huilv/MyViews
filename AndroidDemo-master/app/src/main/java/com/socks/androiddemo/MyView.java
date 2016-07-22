package com.socks.androiddemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by Administrator on 2016/7/19.
 */
public class MyView extends RelativeLayout {
    private  Context context;
    Random random=new Random();
    private LayoutParams layoutParams;

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

    public MyView(Context context) {
        super(context);
        this.context=context;
        init();

    }
    private Drawable[] drawables=new Drawable[3];
    private void init( ) {
        Drawable blueHerart = context.getResources().getDrawable(R.drawable.pl_blue);
        Drawable redHerart = context.getResources().getDrawable(R.drawable.pl_red);
        Drawable yellowHerart = context.getResources().getDrawable(R.drawable.pl_yellow);
        drawables[0]=blueHerart;
        drawables[1]=redHerart;
        drawables[2]=yellowHerart;
        layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(CENTER_HORIZONTAL,TRUE);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM,TRUE);
    }

    public void addHeart() {
        final ImageView imageView=new ImageView(context);
        Drawable dd=drawables[random.nextInt(3)];
        imageView.setImageDrawable(dd);
        addView(imageView,layoutParams);
        AnimatorSet animator = getAnimator(imageView);

        ValueAnimator pathAnimator = getPathAnimator(imageView);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(pathAnimator,animator);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                removeView(imageView);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }


    public AnimatorSet getAnimator(ImageView imageView) {
        ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(imageView,"scaleX",0.2f,0.5f);
        ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(imageView,"scaleY",0.2f,0.5f);
        ObjectAnimator objectAnimator3=ObjectAnimator.ofFloat(imageView,"alpha",0.2f,1f);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(objectAnimator1,objectAnimator2,objectAnimator3);
        animatorSet.setDuration(500);
        return animatorSet;
    }

    public ValueAnimator getPathAnimator(final ImageView imageView) {
        PointF startPointF=new PointF(screenWith/2,screenHeight);
        PointF endPointF=new PointF(100+random.nextInt(screenHeight-200),0);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new MyEvaluate(getPointF(1),getPointF(2)),startPointF,endPointF);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF point = (PointF) valueAnimator.getAnimatedValue();
                imageView.setX(point.x);
                imageView.setY(point.y);
                imageView.setAlpha(1-valueAnimator.getAnimatedFraction());
            }
        });
        valueAnimator.setDuration(5000);
        return valueAnimator;
    }

  private  int screenWith;
    private int screenHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWith=w;
        screenHeight=h;
    }

    public PointF getPointF(int i) {
        PointF pointF=new PointF();
        pointF.x=50+random.nextInt(screenHeight-100);
        if(i==1){
            pointF.y=screenHeight/2+random.nextInt(200);
        }else {
            pointF.y=screenHeight/2-random.nextInt(200);
        }

        return pointF;
    }
}
