package kaungmyatmin.com.notificationdemo.asyncTask;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import kaungmyatmin.com.notificationdemo.asyncTask.common.BaseAsync;
import kaungmyatmin.com.notificationdemo.common.Constant;


public class DoSomeHeavyDutyAsync extends BaseAsync<String, Integer, Integer> {
    private final NotificationCompat.Builder builder;
    private final NotificationManagerCompat notificationManagerCompat;

    public DoSomeHeavyDutyAsync(NotificationCompat.Builder builder, NotificationManagerCompat notificationManagerCompat) {
        this.builder = builder;
        this.notificationManagerCompat = notificationManagerCompat;
    }

    @Override
    protected void onPreExecute() {
        builder.setProgress(100, 0, false);
        notificationManagerCompat.notify(1, builder.build());

    }

    @Override
    protected Integer doInBackground(String... params) {
        int i = 0;
        for (i = 0; i < 100; i++) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i);
            if (isCancelled()) break;
        }

        return i;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        builder.setProgress(100, progress[0], false);
        notificationManagerCompat.notify(1, builder.build());
    }

    @Override
    protected void onPostExecute(Integer value) {

        builder.setProgress(0, 0, false)
                .setContentText("Progress finished, Progress bar should be gone now.");
        notificationManagerCompat.notify(1, builder.build());

        Log.d(Constant.LOG_KEY, "progress finished");
    }
}
