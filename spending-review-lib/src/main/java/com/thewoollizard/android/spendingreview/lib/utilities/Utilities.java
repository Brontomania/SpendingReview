package com.thewoollizard.android.spendingreview.lib.utilities;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by @BrontoMania on 04/09/2014.
 * Generics functions for common uses
 */
public class Utilities {

    //TODO: fare il refactoring di tutto il progetto in base alle mie nuove conoscenze di JAVA

    public static String[] fromInt2String(int[] intArray){
        String[] stringArray= new String[intArray.length];
        for(int i=0;i<intArray.length;i++) stringArray[i]= Integer.toString(intArray[i]);
        return stringArray;
    }


    public static int amountItem2DB(Double amount){
        return ((Double)(amount*100)).intValue();
    }

    public static Double amountDB2Item(int amount){
        Double d=((Integer)amount).doubleValue();
        return d/100;
    }

    public static boolean checkDBExistance(){
        SQLiteDatabase mDb;
        try{
            mDb= SQLiteDatabase.openDatabase(GlobalConst.DBPATH, null, SQLiteDatabase.OPEN_READONLY);
            mDb.close();
        }
        catch(SQLiteException E){
            return false;
        }
        return true;
    }

}
