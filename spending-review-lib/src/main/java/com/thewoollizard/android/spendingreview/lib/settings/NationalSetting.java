package com.thewoollizard.android.spendingreview.lib.settings;

/**
 * Created by @Brontomania on 07/03/2015.
 */
public enum NationalSetting {

    EN(1), IT(2);

    private int v;

    private NationalSetting(int value){
        this.v=value;
    }

    public int getV() {
        return v;
    }
}
