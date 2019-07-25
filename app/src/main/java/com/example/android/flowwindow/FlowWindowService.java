package com.example.android.flowwindow;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class FlowWindowService extends Service {
    private FlowWindowSmallView mFlowWindow;

    public FlowWindowService() {
    }


    public static void startService(Context context) {
        Intent intent = new Intent(context, FlowWindowService.class);
        context.startService(intent);
    }

    public static void stopService(Context context) {
        Intent intent = new Intent(context, FlowWindowService.class);
        context.stopService(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFlowWindow = new FlowWindowSmallView(this);
    }


    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
//        mFlowWindow.removeFlowWindow();
    }
}
