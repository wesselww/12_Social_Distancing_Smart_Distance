package de.wirvsvirus.smart_distance_control;

import android.content.Context;
import android.media.MediaPlayer;
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

    MediaPlayer mPlayer;

    public AlarmManager(Context ctx, ImageView alarmImage) {
        this.ctx = ctx;
        this.notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        this.r = RingtoneManager.getRingtone(ctx, notification);
        this.alarmImage = alarmImage;
        this.mPlayer = MediaPlayer.create(ctx, R.raw.alarm);
    }

    public void checkDistance(int distance) {

        int severity = getSeverity(distance);
        int drawable = getDrawable(severity);

        alarmImage.setImageDrawable( ctx.getResources().getDrawable( drawable ) );

        if (severity == SEVERITY_MEDIUM) {
            vibrate();
        } else if (severity == SEVERITY_SEVERE) {
            startAlarm();
            vibrate();
        } else {
            stopAlarm();
        }
    }

    public void alarmbutton() {
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        } else {
            mPlayer = MediaPlayer.create(ctx, R.raw.alarm);
            mPlayer.start();
        }
    }

    private int getDrawable(int severity) {
        if (severity == SEVERITY_SEVERE) {
            return R.drawable.bell_red;
        } else if (severity == SEVERITY_MEDIUM) {
            return R.drawable.bell_yellow;
        } else {
            return R.drawable.bell_green;
        }
    }

    private int getSeverity(int distance) {
        int severity = SEVERITY_NO;

        if (distance < 200 && distance > 150) {
            severity = SEVERITY_MEDIUM;
        } else if (distance < 150) {
            severity = SEVERITY_SEVERE;
        }

        return severity;
    }

    private void vibrate() {
        Vibrator v = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }

    }

    private void startAlarm() {
        mPlayer = MediaPlayer.create(ctx, R.raw.alarm);
        mPlayer.start();
    }

    private void stopAlarm() {
        mPlayer.stop();
    }
}
