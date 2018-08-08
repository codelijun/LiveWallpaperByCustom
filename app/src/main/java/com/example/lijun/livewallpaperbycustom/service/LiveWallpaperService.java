package com.example.lijun.livewallpaperbycustom.service;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.lijun.livewallpaperbycustom.service.view.BaseWallpaperView;

import java.util.List;

public class LiveWallpaperService extends WallpaperService {
    private static final boolean DEBUG = true;
    private static final String TAG = "LiveWallpaperService";

    @Override
    public WallpaperService.Engine onCreateEngine() {
        return new LiveWallpaperEngine();
    }

    private class LiveWallpaperEngine extends WallpaperService.Engine {
        private BaseWallpaperView mCircleView;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            mCircleView = new BaseWallpaperView(getApplicationContext(), surfaceHolder);

            setTouchEventsEnabled(true);

        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mCircleView.onDestroyView();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            mCircleView.onVisibleChanged(visible);
        }

        public void onOffsetsChanged(float xOffset, float yOffset, float xStep,
                                     float yStep, int xPixels, int yPixels) {

        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCircleView.createCircleByTouch(event.getX(), event.getY());
                    break;
                default:
                    break;
            }
        }
    }
}
