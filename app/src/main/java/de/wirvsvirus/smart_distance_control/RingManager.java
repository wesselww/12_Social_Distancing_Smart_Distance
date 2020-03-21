package de.wirvsvirus.smart_distance_control;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

public class RingManager {

    private Context ctx;

    public RingManager(Context ctx) {
        this.ctx = ctx;
    }

    public void ring() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(ctx, notification);

        if ( r.isPlaying()) {
            r.stop();
        } else {
            r.play();
        }
    }
}
