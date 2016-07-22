package com.socks.androiddemo.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;

import com.socks.androiddemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhaokaiqiang on 15/9/15.
 */
public class FllowerView extends View implements ValueAnimator.AnimatorUpdateListener {

    /**
     * 动画改变的属性值
     */
    private float phase1 = 0f;
    private float phase2 = 0f;
    private float phase3 = 0f;
    private int fllowerCount = 4;
    private int width = 0;
    private int height = 0;

    private List<Fllower> fllowers1 = new ArrayList<>();
    private List<Fllower> fllowers2 = new ArrayList<>();
    private List<Fllower> fllowers3 = new ArrayList<>();
    private Paint mPaint;
    private PathMeasure pathMeasure = null;
    private Bitmap mBitmap;

    private int range = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());

    private int top = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

    public FllowerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
//        height = wm.getDefaultDisplay().getHeight();
        height = (int) (wm.getDefaultDisplay().getHeight() * 3 / 2f);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);

        pathMeasure = new PathMeasure();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_heart);

        createFollower(fllowerCount, fllowers1);
        createFollower(fllowerCount, fllowers2);
        createFollower(fllowerCount, fllowers3);

    }
    private void createFollower(int count, List<Fllower> fllowers) {

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int s = random.nextInt(width/2)+ width/4;
            Path path = new Path();
            CPoint cPoint = new CPoint(s, 0);
            List<CPoint> points = createPath(cPoint);
            drawFllowerPath(path, points);
            Fllower fllower = new Fllower();
            fllower.setPath(path);
            fllowers.add(fllower);
        }

    }


    private void drawFllowerPath(Path path, List<CPoint> points) {
        if (points.size() > 1) {
            for (int j = 0; j < points.size(); j++) {

                CPoint point = points.get(j);

                if (j == 0) {
                    CPoint next = points.get(j + 1);
                    point.dx = ((next.x - point.x) * 0.2F);
                    point.dy = ((next.y - point.y) * 0.2F);
                } else if (j == points.size() - 1) {
                    CPoint prev = points.get(j - 1);
                    point.dx = ((point.x - prev.x) * 0.2F);
                    point.dy = ((point.y - prev.y) * 0.2F);
                } else {
                    CPoint next = points.get(j + 1);
                    CPoint prev = points.get(j - 1);
                    point.dx = ((next.x - prev.x) * 0.2F);
                    point.dy = ((next.y - prev.y) * 0.2F);
                }

                // create the cubic-spline path
                if (j == 0) {
                    path.moveTo(point.x, point.y);
                } else {
                    CPoint prev = points.get(j - 1);
                    path.cubicTo(prev.x + prev.dx, (prev.y + prev.dy),
                            point.x - point.dx, (point.y - point.dy),
                            point.x, point.y);
                }
            }
        }
    }


    private List<CPoint> createPath(CPoint point) {
        List<CPoint> points = new ArrayList<CPoint>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                points.add(point);
            } else {
                CPoint tmp = new CPoint(0, 0);
                if (random.nextInt(100) % 2 == 0) {
                    tmp.x = point.x + random.nextInt(range);
                } else {
                    tmp.x = point.x - random.nextInt(range);
                }
                tmp.y = (int) (height / (float) 10 * i);
                points.add(tmp);
            }
        }
        return points;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawFllower(canvas, fllowers1);
        drawFllower(canvas, fllowers2);
        drawFllower(canvas, fllowers3);

    }

    private void drawFllower(Canvas canvas, List<Fllower> fllowers) {
        for (Fllower fllower : fllowers) {
            float[] pos = new float[2];

//            canvas.drawPath(fllower.getPath(), mPaint);
            pathMeasure.setPath(fllower.getPath(), false);
            pathMeasure.getPosTan(height * fllower.getValue(), pos, null);
            canvas.drawBitmap(mBitmap, pos[0], pos[1]-top, null);
        }
    }

    public void startAnimation() {
        ObjectAnimator mAnimator1 = ObjectAnimator.ofFloat(this, "phase1", 0f,
                1f);
        mAnimator1.setDuration(4000);
        mAnimator1.addUpdateListener(this);
        mAnimator1.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator mAnimator2 = ObjectAnimator.ofFloat(this, "phase2", 0f,
                1f);
        mAnimator2.setDuration(4000);
        mAnimator2.addUpdateListener(this);
        mAnimator2.setInterpolator(new AccelerateInterpolator());
        mAnimator2.setStartDelay(500);

        ObjectAnimator mAnimator3 = ObjectAnimator.ofFloat(this, "phase3", 0f,
                1f);
        mAnimator3.setDuration(4000);
        mAnimator3.addUpdateListener(this);
        mAnimator3.setInterpolator(new AccelerateInterpolator());
        mAnimator3.setStartDelay(500 * 2);

        mAnimator2.start();
        mAnimator1.start();
        mAnimator3.start();
    }

    private void updateValue(float value, List<Fllower> fllowers) {
        for (Fllower fllower : fllowers) {
            fllower.setValue(value);
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator arg0) {

        updateValue(getPhase1(), fllowers1);
        updateValue(getPhase2(), fllowers2);
        updateValue(getPhase3(), fllowers3);
        invalidate();
    }

    public float getPhase1() {
        return phase1;
    }

    public void setPhase1(float phase1) {
        this.phase1 = phase1;
    }

    public float getPhase2() {
        return phase2;
    }

    public void setPhase2(float phase2) {
        this.phase2 = phase2;
    }

    public float getPhase3() {
        return phase3;
    }

    public void setPhase3(float phase3) {
        this.phase3 = phase3;

    }

    class CPoint {

        public float x = 0f;
        public float y = 0f;

        public float dx = 0f;
        public float dy = 0f;

        public CPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Fllower {

        private Path mPath;

        private float value;

        public Path getPath() {
            return mPath;
        }

        public void setPath(Path mPath) {
            this.mPath = mPath;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }


}