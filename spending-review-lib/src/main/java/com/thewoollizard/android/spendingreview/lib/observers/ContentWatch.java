package com.thewoollizard.android.spendingreview.lib.observers;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

/**
 * Created by @BrontoMania on 03/10/2014.
 */
public class ContentWatch extends ContentObserver {

    public ContentWatch(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        this.onChange(selfChange, null);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        Log.i("Observer", "PIPPO");
    }

}
