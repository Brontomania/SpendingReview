package com.thewoollizard.android.spendingreview.lib.keyboard;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;


/**
 * Created by @BrontoMania on 28/09/2014.
 */
public class CustomKeyboardView extends KeyboardView {

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //TODO: drop animation
    //TODO: add hideCustomKeyboard, showCustomKeyboard, isCustomKeyboardVisible

    public void showWithAnimation(Animation animation) {
        animation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {	}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(View.VISIBLE);
            }
        });

        setAnimation(animation);
    }

    public void hideWithAnimation() {
        setVisibility(View.INVISIBLE);
    }

}
