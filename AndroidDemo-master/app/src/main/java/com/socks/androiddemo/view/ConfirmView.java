package com.socks.androiddemo.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.Random;

public class ConfirmView extends View {
    private int[] colors = {0xFF0099CC, 0xFF99CC00, 0xFFCC0099, 0xFFCC9900, 0xFF9900CC, 0xFF00CC99};
    private Paint mPaint;
    private float mEndDraw;
    private float mStartAngle;
    private float mCircleAngle=0;
    private RectF oval;
    Random random=new Random();
    private ValueAnimator endDraw;
    private ValueAnimator startDraw;
    private ValueAnimator circleAnimator;
    private float startAngle;
    private float endAngle;

    public ConfirmView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xFF0099CC);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        oval = new RectF();
        initAngleAnimation();
    }

    private void initAngleAnimation() {

        endDraw = ValueAnimator.ofFloat(0.0F, 1.0F);
        startDraw = ValueAnimator.ofFloat(0.0F, 1.0F);
        circleAnimator = ValueAnimator.ofFloat(0.0F, 1.0F);

        endDraw.setDuration(1000);
        startDraw.setDuration(1000);
        circleAnimator.setDuration(100000);

        endDraw.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mEndDraw = (Float) animation.getAnimatedValue();
               // invalidate();
            }
        });
        startDraw.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mStartAngle = (Float) animation.getAnimatedValue();
             //   invalidate();
            }
        });

        endDraw.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                if (startDraw != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startAngle=0;
                            startDraw.start();
                        }
                    }, 400L);

                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        startDraw.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (endDraw != null) {
                    mPaint.setColor(colors[random.nextInt(6)]);
                    endDraw.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        circleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCircleAngle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });


        endDraw.setInterpolator(new AccelerateDecelerateInterpolator());
        startDraw.setInterpolator(new AccelerateDecelerateInterpolator());
        circleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        circleAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    public void startCircleAnimation() {
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(endDraw, startDraw, circleAnimator);
        animatorSet.start();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int realRadius = w/2 - 10;
        oval.left = w/2 - realRadius;
        oval.top = w/2 - realRadius;
        oval.right = w/2 + realRadius;
        oval.bottom = w/2 + realRadius;

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        float offsetAngle = mCircleAngle * 360;
        startAngle = mStartAngle * 360;
        endAngle = mEndDraw * 360;
       float sweepAngle = endAngle - startAngle;
      startAngle += offsetAngle;

        if (sweepAngle < 0)
            sweepAngle =1;

        canvas.drawArc(oval, startAngle, sweepAngle, false, mPaint);
    }


}
