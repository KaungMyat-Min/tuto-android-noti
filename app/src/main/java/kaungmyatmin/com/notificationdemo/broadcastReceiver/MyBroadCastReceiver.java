package kaungmyatmin.com.notificationdemo.broadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import kaungmyatmin.com.notificationdemo.broadcastReceiver.common.BaseBroadCastReceiver;
import kaungmyatmin.com.notificationdemo.common.Constant;

public class MyBroadCastReceiver extends BaseBroadCastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(Constant.LOG_KEY,"Data received");
    }
}
