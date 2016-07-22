package com.socks.androiddemo.point;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.socks.androiddemo.R;

public class PointActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout relativeLayout;
    private ImageView pic1;
    private ImageView pic2;
    private PointView smallView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        relativeLayout = (RelativeLayout) findViewById(R.id.clean_click);
        pic1 = (ImageView) findViewById(R.id.clean_pic1);
        pic2 = (ImageView) findViewById(R.id.clean_pic2);
        smallView = (PointView) findViewById(R.id.smallview);
        relativeLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        pic2.setBackgroundResource(R.drawable.rotate);
        smallView.startAnimation();
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(pic2,"rotation",0,3600);
        objectAnimator.setRepeatCount(1111);
        objectAnimator.setDuration(5000);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

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
        objectAnimator.start();
    }

}
