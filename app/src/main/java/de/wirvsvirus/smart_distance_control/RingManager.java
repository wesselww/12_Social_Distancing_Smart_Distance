package de.wirvsvirus.smart_distance_control;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

public class RingManager {

    Uri notification;
    Ringtone r;

    public RingManager(Context ctx) {
        this.notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        this.r = RingtoneManager.getRingtone(ctx, notification);
    }

    public void ring() {
        if ( r.isPlaying()) {
            r.stop();
        } else {
            r.play();
        }
    }
}
