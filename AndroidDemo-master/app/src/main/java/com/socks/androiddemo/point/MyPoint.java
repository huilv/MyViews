package com.socks.androiddemo.point;

import java.util.Random;

/**
 * Created by Administrator on 2016/3/4.
 */
public class MyPoint {
    float x;
    float y;
    int flag;
    public MyPoint(int flag,int w,int h){
        this.flag=flag;
        init(w,h);
    }

    public void init(int w,int h){
        //0
        if (flag==0){
             x=0;
             y=random.nextInt(h);
        }
        //1
        if (flag==1){
             x=random.nextInt(w);
             y=0;
        }
        //2
        if (flag==2){
             x=w*7f/8+random.nextFloat()*w*7f/8;
             y=random.nextInt(h);
        }
        //3
        if (flag==3){
             x=random.nextInt(w);
             y=h*7f/8+random.nextFloat()*h*7f/8;
        }
    }
     Random random=new Random();
}
