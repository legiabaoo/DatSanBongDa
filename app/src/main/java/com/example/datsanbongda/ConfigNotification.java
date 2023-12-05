package com.example.datsanbongda;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class ConfigNotification extends Application {
    public static final String CHANNEL_ID ="FPTPOLYTECHNIC";

    @Override
    public void onCreate(){
        super.onCreate();
        config();
    }

    private void config(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //tên notification channel
            CharSequence name = getString(R.string.channel_name);
            //mô tả notification channel
            String description = getString(R.string.channel_description);
            //Độ ưu tiên của thông báo
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            //lấy uri âm thông báo mặc định
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            //tạo thêm audioAttributes
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION).build();
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.setSound(uri, audioAttributes);
            //đăng ký thông báo
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
