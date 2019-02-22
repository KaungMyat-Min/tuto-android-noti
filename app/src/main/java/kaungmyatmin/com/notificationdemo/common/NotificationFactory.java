package kaungmyatmin.com.notificationdemo.common;

import android.app.Activity;
import android.app.PendingIntent;


import android.content.Intent;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;


import kaungmyatmin.com.notificationdemo.R;
import kaungmyatmin.com.notificationdemo.broadcastReceiver.MyBroadCastReceiver;
import kaungmyatmin.com.notificationdemo.broadcastReceiver.QuickReplyReceiver;
import kaungmyatmin.com.notificationdemo.presentor.SubActivity;
import kaungmyatmin.com.notificationdemo.presentor.common.BaseActivity;

public class NotificationFactory {
    private Activity mActivity;

    public NotificationFactory(BaseActivity baseActivity) {
        this.mActivity = baseActivity;
    }

    public NotificationCompat.Builder getTypicalNotification(String textTitle, String textContent) {
        return new NotificationCompat.Builder(getActivity().getApplicationContext(), Constant.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }


    private Activity getActivity() {
        return this.mActivity;
    }


    public NotificationCompat.Builder getNotiBuilderWithTapEnable(String title, String text) {
        Intent intent = new Intent(getActivity(), SubActivity.class);
        /*
        just fresh starting an activity
        pressing back on the screen will exit applicaion
        */
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

        NotificationCompat.Builder builder = getTypicalNotification(title, text);
        builder.setContentIntent(pendingIntent)
                //to close noti after user's tap
                .setAutoCancel(true);
        return builder;
    }

    public NotificationCompat.Builder getNotiBuilderWithActionButton(String title, String text) {
        Intent intent = new Intent(getActivity(), MyBroadCastReceiver.class);
        intent.setAction(Constant.MY_BROADCAST_ACTION);
        intent.putExtra("data", "Notice me senpai!");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        NotificationCompat.Builder builder = getTypicalNotification(title, text);
        builder.addAction(R.drawable.ic_launcher_foreground, "Action1", pendingIntent);
        return builder;
    }

    public NotificationCompat.Builder getNotiBuilderWithQuickReply(String title, String text) {
        NotificationCompat.Builder builder = getTypicalNotification(title, text);
        builder.addAction(getActionWithRemoteInput());
        return builder;
    }

    private NotificationCompat.Action getActionWithRemoteInput() {
        NotificationCompat.Action.Builder builder = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_foreground, "Reply", getQuickReplyPendingIntent());
        builder.addRemoteInput(getRemoteInput());
        return builder.build();
    }

    private RemoteInput getRemoteInput() {
        return new RemoteInput.Builder(Constant.REMOTE_INPUT_KEY)
                .setLabel("Quick Reply")
                .build();
    }

    private PendingIntent getQuickReplyPendingIntent() {

        /*If you re-use a PendingIntent, a user may reply to a different conversation than the one
        they thought they did. You must either provide a request code that is different for each
        conversation or provide an intent that doesn't return true when you call equals() on the reply
        intent of any other conversation. The conversation ID is frequently passed as part of the
        intent's extras bundle, but is ignored when you call equals().
        Change Math.random() to some unique id (like conservation.id)
        */

        Intent intent = new Intent(getActivity(), QuickReplyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), (int) Math.random(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

}
