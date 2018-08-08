package com.example.lijun.livewallpaperbycustom.service.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.lijun.livewallpaperbycustom.R;

import java.util.ArrayList;
import java.util.List;

public class BaseWallpaperView {
    private static final boolean DEBUG = true;
    private static final String TAG = "BaseWallpaperView";

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint = new Paint();
    private boolean mIsVisible;
    private Handler mHandler = new Handler();
    private Bitmap mWallpaperBg;
    private List<AutomaticCircleView> mAutomaticCircleViews;
    private List<TouchCircleView> mTouchCircleViews;
    private Context mContext;

    public BaseWallpaperView(Context context, SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        this.mContext = context;
        mAutomaticCircleViews = new ArrayList<>();
        mTouchCircleViews = new ArrayList<>();
        mWallpaperBg = BitmapFactory.decodeResource(context.getResources(), R.drawable.wallpaper_bg);
        initPaint();
        for (int i = 0; i < 10; i++) {
            AutomaticCircleView automaticCircleView = new AutomaticCircleView(context, mPaint);
            mAutomaticCircleViews.add(automaticCircleView);
        }

    }

    private void initPaint() {
        this.mPaint.setColor(0xfff0ff00);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(2);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setStyle(Paint.Style.STROKE);
    }


    // 定义一个周期性执行的任务
    private final Runnable mDrawTarget = new Runnable() {
        public void run() {
            drawCircle();
        }
    };

    public void drawCircle() {
        if (DEBUG) {
            Log.d(TAG, " drawCircle() ");
        }
        Canvas c = mSurfaceHolder.lockCanvas();
        if (mIsVisible && c != null) {
            c.save();
            c.drawBitmap(mWallpaperBg, 0, 0, mPaint);
            for (AutomaticCircleView view : mAutomaticCircleViews) {
                view.drawAutomaticCircle(c);
            }
            for (TouchCircleView view : mTouchCircleViews) {
                view.drawAutomaticCircle(c);
            }
            c.restore();
            mSurfaceHolder.unlockCanvasAndPost(c);
            mHandler.removeCallbacks(mDrawTarget);
            mHandler.postDelayed(mDrawTarget, 100);
        }
    }

    public void onVisibleChanged(boolean isVisible) {
        mIsVisible = isVisible;
        if (isVisible) {
            mHandler.post(mDrawTarget);
        } else {
            mHandler.removeCallbacks(mDrawTarget);
        }
    }

    public void onDestroyView() {
        mHandler.removeCallbacks(mDrawTarget);
    }

    public void createCircleByTouch(float touchX, float touchY) {
        TouchCircleView touchCircleView = new TouchCircleView(mContext, touchX, touchY, mPaint);
        mTouchCircleViews.add(touchCircleView);
        if (mTouchCircleViews.size() > 15) {
            mTouchCircleViews.get(0).onDeatroyView();
            mTouchCircleViews.remove(0);
        }
    }
}
