package com.example.lijun.livewallpaperbycustom.service.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class AutomaticCircleView {
    private float mPositionX;
    private float mPositionY;
    private Paint mPaint;
    private int mWindowWidth;
    private int mWindowHeight;
    private boolean mExceedX;
    private boolean mExceedY;
    private int mCircleRadius;
    private int mSpendX;
    private int mSpendY;

    public AutomaticCircleView(Context context, Paint mPaint) {
        this.mPaint = mPaint;
        mWindowWidth = context.getResources().getDisplayMetrics().widthPixels;
        mWindowHeight = context.getResources().getDisplayMetrics().heightPixels;
        mCircleRadius = (int) (Math.random() * 40 + 20);
        mPositionX = mPositionY = (float) (Math.random() * (mWindowWidth + (mCircleRadius * 2)) + mCircleRadius);
        mSpendX = (int) (Math.random() * 7 + 3); //[5,8]
        mSpendY = (int) (Math.random() * 7 + 3);
        mExceedX = Math.random() > 0.5? true : false;
        mExceedY = Math.random() > 0.5? true : false;

    }

    public void drawAutomaticCircle(Canvas canvas) {
        if (mPositionX <= mCircleRadius) {
            mExceedX = false;
        } else if (mPositionX > mWindowWidth - mCircleRadius) {
            mExceedX = true;
        }
        if (mPositionY <= mCircleRadius) {
            mExceedY = false;
        } else if (mPositionY > mWindowHeight - mCircleRadius) {
            mExceedY = true;
        }
        if (mExceedX) {
            mPositionX -= mSpendX;
        } else {
            mPositionX += mSpendX;
        }
        if (mExceedY) {
            mPositionY -= mSpendY;
        } else {
            mPositionY += mSpendY;
        }
        canvas.drawCircle(mPositionX, mPositionY, mCircleRadius, mPaint);
    }
}
