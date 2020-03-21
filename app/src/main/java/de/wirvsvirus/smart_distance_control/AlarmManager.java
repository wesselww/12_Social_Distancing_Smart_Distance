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

    public static final int SEVERITY_NO = 0;
    public static final int SEVERITY_MEDIUM = 2;
    public static final int SEVERITY_SEVERE = 3;

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

    public void alarm(int distance, int lenght) {
        ring();
        vibrate();

        setSeverity(distance);
    }

    private void setSeverity(int distance) {
        int severity = SEVERITY_NO;

        if (distance < 200 && distance > 150) {
            severity = SEVERITY_MEDIUM;
        } else if (distance < 150) {
            severity = SEVERITY_SEVERE;
        }

        if (severity == SEVERITY_SEVERE) {
            alarmImage.setColorFilter(Color.RED);
        } else if (severity == SEVERITY_MEDIUM) {
            alarmImage.setColorFilter(Color.YELLOW);
        } else if (severity == SEVERITY_NO) {
            alarmImage.setColorFilter(Color.GREEN);
        }
    }

    private void vibrate() {
        Vibrator v = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }

    }

    private void ring() {
        if (r.isPlaying()) {
            r.stop();
        } else {
            r.play();
        }
    }
}
