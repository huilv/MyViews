package com.socks.androiddemo;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by Administrator on 2016/7/19.
 */
public class MyEvaluate implements TypeEvaluator<PointF> {
     private PointF P1;
    private PointF P2;
    public MyEvaluate(PointF f, PointF pointF) {
        P1 =f;
        P2=pointF;
    }

    @Override
    public PointF evaluate(float v, PointF startValue, PointF endValue) {
        PointF pointF=new PointF();
        pointF.y=startValue.y*(1-v)*(1-v)*(1-v)+3*P1.y*v*(1-v)*(1-v)+3*P2.y*v*v*(1-v)+endValue.y*v*v*v;
        pointF.x=startValue.x*(1-v)*(1-v)*(1-v)+3*P1.x*v*(1-v)*(1-v)+3*P2.x*v*v*(1-v)+endValue.x*v*v*v;

        return pointF;
    }
}
