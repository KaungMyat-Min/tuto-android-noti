package kaungmyatmin.com.notificationdemo.presentor;

import android.app.NotificationManager;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import kaungmyatmin.com.notificationdemo.R;
import kaungmyatmin.com.notificationdemo.asyncTask.DoSomeHeavyDutyAsync;
import kaungmyatmin.com.notificationdemo.common.Constant;
import kaungmyatmin.com.notificationdemo.databinding.ActivityMainBinding;
import kaungmyatmin.com.notificationdemo.presentor.common.BaseActivity;
import kaungmyatmin.com.notificationdemo.viewmodel.MainViewModel;


public class MainActivity extends BaseActivity {

    private Button normalNoti;

    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        binding.setViewModel(mainViewModel);


        notificationManager = NotificationManagerCompat.from(this);

        findViewById(R.id.noti_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Constant.LOG_KEY, "button Clicked");
                NotificationCompat.Builder builder = getNotificationFactory().getTypicalNotification("Title", "blah blah blah");
                notificationManager.notify(1, builder.build());
            }
        });


        findViewById(R.id.noti_tap_enable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = getNotificationFactory().getNotiBuilderWithTapEnable("hehehtitle", "Blah blah");
                notificationManager.notify(1, builder.build());
            }
        });

        findViewById(R.id.noti_tap_enable_back_stack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO implement backstacking
                Snackbar.make(binding.getRoot(), "Backstacking not implemented yet", Snackbar.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.noti_action_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = getNotificationFactory().getNotiBuilderWithActionButton("Noti", "blahdajskl;dfjakl;jdfkl;ajsdfklq basdjkl;jf");
                notificationManager.notify(1, builder.build());
            }
        });

        findViewById(R.id.noti_quick_reply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = getNotificationFactory().getNotiBuilderWithQuickReply("The Man", "The Man is greeting.");
                builder.setAutoCancel(true);
                notificationManager.notify(1, builder.build());

            }
        });

        final DoSomeHeavyDutyAsync async = new DoSomeHeavyDutyAsync(getNotificationFactory().getTypicalNotification("Noti", "Just demo noti with progress bar")
                , notificationManager);
        findViewById(R.id.noti_progress_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                async.execute("");


            }
        });

    }
}
