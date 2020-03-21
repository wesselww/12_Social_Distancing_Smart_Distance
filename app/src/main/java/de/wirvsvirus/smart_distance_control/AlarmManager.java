package de.wirvsvirus.smart_distance_control;

import android.content.Context;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.ImageView;

public class AlarmManager {

    public static final int SEVERITY_SEVERE = 0;
    public static final int SEVERITY_MEDIUM = 1;
    public static final int SEVERITY_LIGHT = 2;

    private Context ctx;
    Uri notification;
    Ringtone r;
    ImageView alarmImage;

    public AlarmManager(Context ctx, ImageView alarmImage) {
        this.ctx = ctx;
        this.notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        this.r = RingtoneManager.getRingtone(ctx, notification);
        this.alarmImage = alarmImage;
    }

    public void alarm(int severity, int lenght) {
        ring();
        vibrate();

        if (severity == SEVERITY_SEVERE) {
            alarmImage.setColorFilter(Color.RED);
        } else if (severity == SEVERITY_MEDIUM) {
            alarmImage.setColorFilter(Color.YELLOW);
        } else if (severity == SEVERITY_LIGHT) {
            alarmImage.setColorFilter(Color.GREEN);
        }
    }

    public void vibrate() {
        Vibrator v = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }

    }

    public void ring() {
        if (r.isPlaying()) {
            r.stop();
        } else {
            r.play();
        }
    }
}
