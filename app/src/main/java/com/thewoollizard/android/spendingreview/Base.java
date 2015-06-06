package com.thewoollizard.android.spendingreview;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.thewoollizard.android.spendingreview.dbmng.DBDAO;
import com.thewoollizard.android.spendingreview.lib.settings.DateSeparator;
import com.thewoollizard.android.spendingreview.lib.settings.NationalSetting;
import com.thewoollizard.android.spendingreview.lib.settings.Settings;
import com.thewoollizard.android.spendingreview.lib.settings.TimeSeparator;
import com.thewoollizard.android.spendingreview.lib.utilities.GlobalConst;

/**
 * Created by @BrontoMania on 19/01/2015.
 */

public class Base extends FragmentActivity {

    protected DBDAO dbDAO;
    private DBTest dbTest;
    Settings settings;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        settings=new Settings(NationalSetting.IT, TimeSeparator.DOT, DateSeparator.MINUS);
        dbDAO=new DBDAO(this, GlobalConst.DBNAME, settings);
        /*
        if (Utilities.checkDBExistance()==false) {
            dbTest=new DBTest(dbDAO);
            dbTest.run();
        }
        */
        dbTest=new DBTest(dbDAO);
        dbTest.run();
    }


}
