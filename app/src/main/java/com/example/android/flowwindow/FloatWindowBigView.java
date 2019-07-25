package com.example.android.flowwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by admin on 2016/10/14.
 */

public class FloatWindowBigView extends RelativeLayout implements View.OnClickListener {


    private int viewWidth;
    private int viewHeight;

    public FloatWindowBigView(Context context) {
        super(context);
        init(context);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_image_view_game_cat_icon:
                FloatWindowManager.getInstance(getContext()).closeBigWindowAnimation();
                break;
            case R.id.id_text_view_game_cat_personal:
                break;
            case R.id.id_text_view_game_cat_gifts:
                break;

        }
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_flow_big_window, this);
        View view = rootView.findViewById(R.id.id_layout_big_window);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        findViewById(R.id.id_image_view_game_cat_icon).setOnClickListener(this);
        findViewById(R.id.id_text_view_game_cat_personal).setOnClickListener(this);
        findViewById(R.id.id_text_view_game_cat_gifts).setOnClickListener(this);
    }


    public int getViewWidth() {
        return this.viewWidth;
    }

    public int getViewHeight() {
        return this.viewHeight;
    }
}
