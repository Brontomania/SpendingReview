package com.thewoollizard.android.spendingreview.lib.utilities;

/**
 * Created by @Brontomania on 04/03/2015.
 */
public class DateTimeRes {

    DateTimeObj dtObj;
    String message;

    public DateTimeRes(DateTimeObj obj, String message){
        this.dtObj=obj;
        this.message=message;
    }

    public DateTimeObj getDtObj() {
        return dtObj;
    }

    public void setDtObj(DateTimeObj dtObj) {
        this.dtObj = dtObj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
