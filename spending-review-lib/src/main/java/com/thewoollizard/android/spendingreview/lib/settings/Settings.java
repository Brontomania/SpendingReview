package com.thewoollizard.android.spendingreview.lib.settings;


/**
 * Created by @Brontomania on 07/03/2015.
 */
public class Settings {

    NationalSetting nationalSetting;
    TimeSeparator timeSeparator;
    DateSeparator dateSeparator;

    public Settings(NationalSetting nationalSetting, TimeSeparator timeSeparator, DateSeparator dateSeparator) {
        this.nationalSetting = nationalSetting;
        this.timeSeparator = timeSeparator;
        this.dateSeparator = dateSeparator;
    }

    public NationalSetting getNationalSetting() {
        return nationalSetting;
    }

    public void setNationalSetting(NationalSetting nationalSetting) {
        this.nationalSetting = nationalSetting;
    }

    public TimeSeparator getTimeSeparator() {
        return timeSeparator;
    }

    public void setTimeSeparator(TimeSeparator timeSeparator) {
        this.timeSeparator = timeSeparator;
    }

    public DateSeparator getDateSeparator() {
        return dateSeparator;
    }

    public void setDateSeparator(DateSeparator dateSeparator) {
        this.dateSeparator = dateSeparator;
    }

}
