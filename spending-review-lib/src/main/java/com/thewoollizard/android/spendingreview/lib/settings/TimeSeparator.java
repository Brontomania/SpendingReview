package com.thewoollizard.android.spendingreview.lib.settings;

import com.thewoollizard.android.spendingreview.lib.utilities.GlobalConst;

/**
 * Created by @Brontomania on 07/03/2015.
 */
public enum TimeSeparator {

    COLON(GlobalConst.TIME_SEPARATOR_COLON), DOT(GlobalConst.TIME_SEPARATOR_DOT);

    private Character c;

    private TimeSeparator(Character c){
        this.c=c;
    }

    public Character getC() {
        return c;
    }
}
