package com.example.android.flowwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * android 6.0 要申请开启浮窗权限
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Button mStartFlowWindow1;
    private Button mStartFlowWindow2;
    private Button mStopFlowWindow1;
    private Button mStopFlowWindow2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mStartFlowWindow1 = (Button) findViewById(R.id.id_start_flow_window_for_activity);
        mStartFlowWindow2 = (Button) findViewById(R.id.id_start_flow_window_for_application);
        mStopFlowWindow1 = (Button) findViewById(R.id.id_stop_flow_window_for_activity);
        mStopFlowWindow2 = (Button) findViewById(R.id.id_stop_flow_window_for_application);
        mStartFlowWindow1.setOnClickListener(this);
        mStartFlowWindow2.setOnClickListener(this);
        mStopFlowWindow1.setOnClickListener(this);
        mStopFlowWindow2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_start_flow_window_for_activity:
                FloatWindowManager.getInstance(MainActivity.this).createSmallWindow();
                break;
            case R.id.id_stop_flow_window_for_activity:
                onBackPressed();
                break;
            case R.id.id_start_flow_window_for_application:
                FlowWindowService.startService(this);
                finish();
                break;
            case R.id.id_stop_flow_window_for_application:
                FlowWindowService.stopService(this);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FloatWindowManager.getInstance(MainActivity.this).removeSmallWindow();
        FloatWindowManager.getInstance(MainActivity.this).removeBigWindow();
    }


}
