package kaungmyatmin.com.notificationdemo.presentor.common;

import android.support.v7.app.AppCompatActivity;

import kaungmyatmin.com.notificationdemo.common.NotificationFactory;

public class BaseActivity extends AppCompatActivity {

    private NotificationFactory mNotificationFactory;

    public NotificationFactory getNotificationFactory(){

        if(mNotificationFactory == null) {
            mNotificationFactory= new NotificationFactory(this);
        }
        return mNotificationFactory;
    }


}
