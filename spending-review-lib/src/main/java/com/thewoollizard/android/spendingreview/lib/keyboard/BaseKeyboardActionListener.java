package com.thewoollizard.android.spendingreview.lib.keyboard;

import android.app.Activity;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.view.KeyEvent;

/**
 * Created by @BrontoMania on 28/09/2014.
 */
public class BaseKeyboardActionListener implements OnKeyboardActionListener {

    private Activity mCallerActivity;

    public BaseKeyboardActionListener(Activity callerActivity) {
        mCallerActivity = callerActivity;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        long eventTime = System.currentTimeMillis();
        KeyEvent event = new KeyEvent(eventTime, eventTime,
                KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0,
                KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);

        mCallerActivity.dispatchKeyEvent(event);
    }

    @Override
    public void swipeUp() {	}

    @Override
    public void swipeRight() {	}

    @Override
    public void swipeLeft() {	}

    @Override
    public void swipeDown() {	}

    @Override
    public void onText(CharSequence text) {	}

    @Override
    public void onRelease(int primaryCode) { }

    @Override
    public void onPress(int primaryCode) {	}


}
