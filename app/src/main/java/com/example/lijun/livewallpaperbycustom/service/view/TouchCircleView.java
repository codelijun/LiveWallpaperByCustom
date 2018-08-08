package com.example.lijun.livewallpaperbycustom.service.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class TouchCircleView {
    private float mTouchX = -1;
    private float mTouchY = -1;
    private Paint mPaint;
    private boolean mExceedX;
    private boolean mExceedY;
    private int mWindowWidth;
    private int mWindowHeight;
    private int mCircleRadius;
    private int mSpendX;
    private int mSpendY;
    private boolean isDestroy = false;

    public TouchCircleView(Context context, float touchX, float touchY, Paint paint) {
        this.mTouchX = touchX;
        this.mTouchY = touchY;
        this.mPaint = paint;
        mWindowWidth = context.getResources().getDisplayMetrics().widthPixels;
        mWindowHeight = context.getResources().getDisplayMetrics().heightPixels;
        mCircleRadius = (int) (Math.random() * 40 + 20);
        mSpendX = (int) (Math.random() * 7 + 3); //[5,8]
        mSpendY = (int) (Math.random() * 7 + 3);
        mExceedX = Math.random() > 0.5? true : false;
        mExceedY = Math.random() > 0.5? true : false;
    }

    public void drawAutomaticCircle(Canvas canvas) {
        if(mTouchX < 0 || mTouchY < 0 || isDestroy){
            return;
        }
        if (mTouchX <= mCircleRadius) {
            mExceedX = false;
        } else if (mTouchX > mWindowWidth - mCircleRadius) {
            mExceedX = true;
        }
        if (mTouchY <= mCircleRadius) {
            mExceedY = false;
        } else if (mTouchY > mWindowHeight - mCircleRadius) {
            mExceedY = true;
        }
        if (mExceedX) {
            mTouchX -= mSpendX;
        } else {
            mTouchX += mSpendX;
        }
        if (mExceedY) {
            mTouchY -= mSpendY;
        } else {
            mTouchY += mSpendY;
        }
        canvas.drawCircle(mTouchX, mTouchY, mCircleRadius, mPaint);
    }

    public void onDeatroyView(){
        isDestroy = true;
    }
}
