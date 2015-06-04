package com.thewoollizard.android.spendingreview.lib.keyboard;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;


/**
 * Created by @BrontoMania on 29/09/2014.
 */
public class MyNumericKbdActionListener implements KeyboardView.OnKeyboardActionListener {

    private Activity mActivity;
    EditText edTxtCurrent;
    //Editable edTblCurrent;

    public MyNumericKbdActionListener(Activity callerActivity){
        mActivity=callerActivity;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

        View currentFocus=mActivity.getCurrentFocus();

        if(currentFocus==null || currentFocus.getClass()!=EditText.class) return;

        edTxtCurrent=(EditText)currentFocus;
        long eventTime = System.currentTimeMillis();
        KeyEvent event = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0, KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);

        if (primaryCode==160){
            //TODO: verificare il FOCUS_DOWN (prima era FOCUS_FORWARD)
            View nextFocus=edTxtCurrent.focusSearch(View.FOCUS_DOWN);
            if(nextFocus!=null) nextFocus.requestFocus();
            return;
        }

        if(primaryCode==159){

            if (commaExistence() || (((EditText) currentFocus).getText().toString().length())==0 ) return;
            edTxtCurrent.setText(redefAmount(edTxtCurrent.getText().toString()));
            edTxtCurrent.setSelection(edTxtCurrent.getText().length());
        }

        if(primaryCode==67 && edTxtCurrent.getText().length()>0){
            mActivity.dispatchKeyEvent(event);
            edTxtCurrent.setText(redefAmount(edTxtCurrent.getText().toString()));
            edTxtCurrent.setSelection(edTxtCurrent.getText().length());
            return;
        }

        if(primaryCode>=144 && primaryCode<=153 ){
            if(maxDecimalLimit()) return;
            if(!commaExistence() && needDot()){
                KeyEvent eventDot = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_NUMPAD_DOT, 0, 0, 0, 0, KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);
                mActivity.dispatchKeyEvent(event);
                mActivity.dispatchKeyEvent(eventDot);
                return;
            }
            mActivity.dispatchKeyEvent(event);
            return;
        }

        mActivity.dispatchKeyEvent(event);
    }

    private String redefAmount(String s){
        String s1;
        String s2="";
        if (commaExistence()){
            s2=s.substring(s.lastIndexOf(","), s.length());
            s=s.substring(0, s.lastIndexOf(",")-1);
        }
        s=s.replace(".","");
        int amountLen=s.length();

        if (amountLen>3){

            s1="";
            int i;

            for(i=amountLen-1;i>=0;i--){
                s1=s.charAt(i)+s1;

                if(s1.replace(".","").length()%3==0 && i>0) s1="."+s1;

            };

        }
        else return s+s2;

        return s1+s2;
    }

    private boolean commaExistence(){

        String s=edTxtCurrent.getText().toString();

        if(s.lastIndexOf(",")==-1)
            return false;

        return true;
    }

    private boolean maxDecimalLimit(){

        String s=edTxtCurrent.getText().toString();
        int afterCommaSpace=0;
        int commaIndex=s.lastIndexOf(",");

        if(commaIndex!=-1)  afterCommaSpace=s.length()-commaIndex;

        if(afterCommaSpace<=2)
            return false;
        else return true;
    }

    private boolean needDot(){

        String s1=edTxtCurrent.getText().toString();

        if(s1.length()>0){
            String s2=s1.replace(".","");

            if(s1.lastIndexOf(".")==-1 && s2.length()==2) return true;

            if(s1.lastIndexOf(".")!=-1 && (s2.length()%3)==2) return true;
        }
        return false;
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

}
