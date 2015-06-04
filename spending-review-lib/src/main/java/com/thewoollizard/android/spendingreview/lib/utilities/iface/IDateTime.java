package com.thewoollizard.android.spendingreview.lib.utilities.iface;

import com.thewoollizard.android.spendingreview.lib.settings.Settings;

/**
 * Created by @Brontomania on 04/03/2015.
 */
public interface IDateTime {

    public String getDateTimeIT();
    public String getDateTimeEN();
    public String getDateTime(Settings settings);
    public String getDate(Settings settings);
    public String getDateIT();
    public String getDateEN();
    public String getTime(Settings settings);
    public String getTimeIT();
    public String getTimeEN();

}
