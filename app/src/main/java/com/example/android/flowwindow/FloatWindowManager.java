package com.example.android.flowwindow;

import android.app.AppOpsManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by admin on 2016/10/14.
 */

public class FloatWindowManager {
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mBigLayoutParams, mSmallLayoutParams;


    private FloatWindowBigView mBigWindow;
    private FlowWindowSmallView mSmallWindow;
    private FlowWindowSmallHalfView mSmallHalfWindow;
    private Context mContext;
    private static FloatWindowManager mInstance;

    FloatWindowManager(Context context) {
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.mContext = context;
    }


    public static FloatWindowManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FloatWindowManager(context);
        }
        return mInstance;
    }

    public void createSmallWindow() {
        int screenHeight = mWindowManager.getDefaultDisplay().getHeight();
        if (mSmallWindow == null) {
            mSmallWindow = new FlowWindowSmallView(mContext);
            if (mSmallLayoutParams == null) {
                mSmallLayoutParams = new WindowManager.LayoutParams();
                //设置window type
                mSmallLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
                //设置图片格式，效果为背景透明
                mSmallLayoutParams.format = PixelFormat.RGBA_8888;
                //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
                mSmallLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

                //调整悬浮窗显示的停靠位置为左侧置顶
                mSmallLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
                // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
                mSmallLayoutParams.x = 0;
                mSmallLayoutParams.y = screenHeight / 2;
                //设置悬浮窗口长宽数据
                Log.e("xsy", "width " + mSmallWindow.getViewWidth() + " height " + mSmallWindow.getViewHeight());
                mSmallLayoutParams.width = mSmallWindow.getViewWidth();
                mSmallLayoutParams.height = mSmallWindow.getViewHeight();
            }
            mSmallWindow.setLayoutParams(mSmallLayoutParams);
            mSmallWindow.setParams(mSmallLayoutParams);
            mSmallWindow.scrollTo(100, 0);
            mWindowManager.addView(mSmallWindow, mSmallLayoutParams);
        }


    }

    public void createSmallHalfWindow() {
        int screenHeight = mWindowManager.getDefaultDisplay().getHeight();
        if (mSmallHalfWindow == null) {
            mSmallHalfWindow = new FlowWindowSmallHalfView(mContext);
            if (mSmallLayoutParams == null) {
                mSmallLayoutParams = new WindowManager.LayoutParams();
                //设置window type
//                mSmallLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                mSmallLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
                //设置图片格式，效果为背景透明
                mSmallLayoutParams.format = PixelFormat.RGBA_8888;
                //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
                mSmallLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

                //调整悬浮窗显示的停靠位置为左侧置顶
                mSmallLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
                // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
                mSmallLayoutParams.x = 0;
                mSmallLayoutParams.y = screenHeight / 2;
                //设置悬浮窗口长宽数据
                Log.e("xsy", "width " + mSmallHalfWindow.getViewWidth() + " height " + mSmallHalfWindow.getViewHeight());
                mSmallLayoutParams.width = mSmallHalfWindow.getViewWidth();
                mSmallLayoutParams.height = mSmallHalfWindow.getViewHeight();
            }
            mSmallHalfWindow.setLayoutParams(mSmallLayoutParams);
            mSmallHalfWindow.setParams(mSmallLayoutParams);
            mWindowManager.addView(mSmallHalfWindow, mSmallLayoutParams);
        }


    }


    public void removeSmallWindow() {
        if (mSmallWindow != null) {
            //移除悬浮窗口
            mWindowManager.removeView(mSmallWindow);
            mSmallWindow = null;
        }
    }

    public void removeSmallHalfWindow() {
        if (mSmallHalfWindow != null) {
            //移除悬浮窗口
            mWindowManager.removeView(mSmallHalfWindow);
            mSmallHalfWindow = null;
        }
    }


    public void createBigWindow() {
        if (mBigWindow == null) {
            mBigWindow = new FloatWindowBigView(mContext);
            if (mBigLayoutParams == null) {

                mBigLayoutParams = new WindowManager.LayoutParams();
                //设置window type
                mBigLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
                //设置图片格式，效果为背景透明
                mBigLayoutParams.format = PixelFormat.RGBA_8888;
                //调整悬浮窗显示的停靠位置为左侧置顶
                mBigLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
                mSmallLayoutParams.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
                // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
                Log.e("xsy", "big window== ");
                mBigLayoutParams.x = -mBigWindow.getViewWidth();
                Log.e("xsy", "mBigLayoutParams.x " + mBigLayoutParams.x);
                mBigLayoutParams.y = mSmallWindow.getParams().y;
                mBigLayoutParams.width = mBigWindow.getViewWidth();
                mBigLayoutParams.height = mBigWindow.getViewHeight();
            }

            Log.e("xsy", "big window  mBigLayoutParams.y " + mBigLayoutParams.y);
            mBigWindow.setLayoutParams(mBigLayoutParams);
            mWindowManager.addView(mBigWindow, mBigLayoutParams);

            openBigWindowAnimation();
            mBigWindow.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Log.e("xsy", "onFocusChange");
                }
            });
            mBigWindow.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.e("xsy", "mBigWindow  onTouch");

                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    Rect rect = new Rect();
                    mBigWindow.getLocalVisibleRect(rect);
                    if (!rect.contains(x, y)) {
                        Log.e("xsy", "closeBigWindowAnimation  onTouch");
                        closeBigWindowAnimation();

                    }

                    Log.e("xsy", "onTouch : " + x + ", " + y + ", rect: "
                            + rect);
                    return false;
                }
            });
        }

    }


    public void openBigWindowAnimation() {
        removeSmallWindow();
    }

    public void closeBigWindowAnimation() {
        if (mBigWindow != null) {
            removeBigWindow();
            createSmallWindow();
        }
    }


    public void removeBigWindow() {
        if (mBigWindow != null) {
            mWindowManager.removeView(mBigWindow);
            mBigWindow = null;
        }
    }

    /**
     * 判断是否开启浮窗权限,api未公开，使用反射调用
     *
     * @return
     */
    private static boolean hasAuthorFloatWin(Context context) {

        if (Build.VERSION.SDK_INT < 19) {
            return false;
        }
        try {
            AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            Class c = appOps.getClass();
            Class[] cArg = new Class[3];
            cArg[0] = int.class;
            cArg[1] = int.class;
            cArg[2] = String.class;
            Method lMethod = c.getDeclaredMethod("checkOp", cArg);
            //24是浮窗权限的标记
            return (AppOpsManager.MODE_ALLOWED == (Integer) lMethod.invoke(appOps, 24, Binder.getCallingUid(), context.getPackageName()));
        } catch (Exception e) {
            return false;
        }
    }
}
