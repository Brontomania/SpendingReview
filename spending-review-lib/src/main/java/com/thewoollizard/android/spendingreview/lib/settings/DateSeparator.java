package com.thewoollizard.android.spendingreview.lib.settings;

import com.thewoollizard.android.spendingreview.lib.utilities.GlobalConst;

/**
 * Created by @Brontomania on 07/03/2015.
 */
public enum DateSeparator {

    SLASH(GlobalConst.DATE_SEPARATOR_SLASH), MINUS(GlobalConst.DATE_SEPARATOR_MINUS);

    private Character c;

    private DateSeparator(Character c){
        this.c=c;
    }

    public Character getC() {
        return c;
    }
}
