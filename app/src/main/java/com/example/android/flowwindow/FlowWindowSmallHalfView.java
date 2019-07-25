package com.example.android.flowwindow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by admin on 2016/10/26.
 */

public class FlowWindowSmallHalfView extends LinearLayout {
    private View mFlowLayout;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private Context mContext;
    private int viewWidth;
    private int viewHeight;
    private ImageView mImageView;

    private Boolean mIsMove;

    public FlowWindowSmallHalfView(Context context) {
        super(context);
        this.mContext = context;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        init();
    }


    /**
     *
     */


    float firstRawX = 0, firstRawY = 0, lastRawX = 0, lastRawY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstRawX = event.getRawX();
                firstRawY = event.getRawY();
                Log.e("xsy", "firstRawX" + firstRawX);
                Log.e("xsy", "firstRawY" + firstRawY);
                break;
            case MotionEvent.ACTION_MOVE:
                lastRawX = event.getRawX();
                lastRawY = event.getRawY();
                Log.e("xsy", "lastRawX" + lastRawX);
                Log.e("xsy", "lastRawY" + lastRawY);
                mLayoutParams.x = (int) event.getRawX() - mImageView.getMeasuredWidth() / 2;
                mLayoutParams.y = (int) event.getRawY() - mImageView.getMeasuredHeight() / 2;
                //刷新
                mWindowManager.updateViewLayout(mFlowLayout, mLayoutParams);

                break;
            case MotionEvent.ACTION_UP:
                lastRawX = event.getRawX();
                lastRawY = event.getRawY();
                Log.e("xsy", "lastRawX" + lastRawX);
                Log.e("xsy", "lastRawY" + lastRawY);
                mImageView.setImageResource(R.mipmap.flow_game_cat_activated);
                if ((firstRawX == lastRawX) && (firstRawY == lastRawY)) {
                    openBigWindow();
                } else {
                    FloatWindowManager.getInstance(mContext).removeSmallHalfWindow();
                    FloatWindowManager.getInstance(mContext).createSmallWindow();
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    private void init() {
        mFlowLayout = LayoutInflater.from(mContext).inflate(R.layout.layout_flow_small_half_window, this);
        mImageView = (ImageView) findViewById(R.id.id_image_view_game_cat);
        Log.e("xsy", mFlowLayout == null ? "mFlowLayout is null" : "layout params " + mFlowLayout.getLayoutParams());
        viewWidth = mImageView.getLayoutParams().width;
        viewHeight = mImageView.getLayoutParams().height;

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        mImageView.measure(widthMeasureSpec, heightMeasureSpec);
        Log.e("xsy", "mImageView:" + mImageView.getId());
        Log.e("xsy", "mFlowLayout:" + mFlowLayout.getId());
    }

    public int getViewWidth() {
        return this.viewWidth;
    }

    public int getViewHeight() {
        return this.viewHeight;
    }

    public void setParams(WindowManager.LayoutParams mLayoutParams) {
        this.mLayoutParams = mLayoutParams;
    }

    public WindowManager.LayoutParams getParams() {
        return this.mLayoutParams;
    }

    private void openBigWindow() {
        FloatWindowManager.getInstance(mContext).createBigWindow();
    }
}
