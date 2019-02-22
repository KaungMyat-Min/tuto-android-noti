package kaungmyatmin.com.notificationdemo.broadcastReceiver;

import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import kaungmyatmin.com.notificationdemo.broadcastReceiver.common.BaseBroadCastReceiver;
import kaungmyatmin.com.notificationdemo.common.Constant;

public class QuickReplyReceiver extends BaseBroadCastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(Constant.LOG_KEY,getReplyMessage(intent)+"");

    }

    private CharSequence getReplyMessage(Intent intent) {
        Bundle bundle = RemoteInput.getResultsFromIntent(intent);
        CharSequence reply = null;
        if(bundle!=null){
            reply = bundle.getCharSequence(Constant.REMOTE_INPUT_KEY);

        }
        return reply;

    }

}
