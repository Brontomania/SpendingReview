package com.thewoollizard.android.spendingreview.lib.utilities;

import com.thewoollizard.android.spendingreview.lib.settings.DateSeparator;
import com.thewoollizard.android.spendingreview.lib.settings.Settings;
import com.thewoollizard.android.spendingreview.lib.settings.TimeSeparator;
import com.thewoollizard.android.spendingreview.lib.utilities.iface.IDateTime;

import java.util.Calendar;

/**
 * Created by @Brontomania on 04/03/2015.
 */
public class DateTimeObj implements IDateTime {

    int year;

    int month;
    int day;
    int hour;
    int minute;
    int sec;

    public DateTimeObj() {

        Calendar c= Calendar.getInstance();

        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH)+1;
        this.day = c.get(Calendar.DAY_OF_MONTH);
        this.hour = c.get(Calendar.HOUR_OF_DAY);
        this.minute = c.get(Calendar.MINUTE);
        this.sec = c.get(Calendar.SECOND);

    }

    public DateTimeObj(int year, int month, int day, int hour, int minute, int sec) {

        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.sec = sec;

    }

    public DateTimeObj(int year, int month, int day, int setting) {
        this.year = year;
        this.month = month;
        this.day = day;

        if (setting==2) {
            this.hour = 23;
            this.minute = 59;
            this.sec = 59;
        }
        else {
            this.hour = 0;
            this.minute = 0;
            this.sec = 0;
        }
    }

    @Override
    public String getDateTime(Settings settings) {
        String dt;
        switch (settings.getNationalSetting()){
            case EN: dt=getDateTimeEN();
                break;
            case IT: dt=getDateTimeIT();
                break;
            default:dt=getDateTimeIT();
                break;
        }
        return dt;
    }

    @Override
    public String getDate(Settings settings) {
        String dt;
        switch (settings.getNationalSetting()){
            case EN: dt=getDateEN();
                break;
            case IT: dt=getDateIT();
                break;
            default:dt=getDateIT();
                break;
        }
        return dt;
    }

    @Override
    public String getTime(Settings settings) {
        String dt;
        switch (settings.getNationalSetting()){
            case EN: dt=getTimeEN();
                break;
            case IT: dt=getTimeIT();
                break;
            default:dt=getTimeIT();
                break;
        }
        return dt;
    }

    @Override
    public String getDateTimeIT(){
        Character separatorDate= DateSeparator.SLASH.getC();
        Character separatorTime= TimeSeparator.COLON.getC();

        String dateTime=Integer.toString(day)
                +separatorDate+Integer.toString(month)
                +separatorDate+Integer.toString(year)
                +' '+Integer.toString(hour)
                +separatorTime+Integer.toString(minute)
                +separatorTime+Integer.toString(sec);

        return dateTime;
    }

    @Override
    public String getDateTimeEN(){
        Character separatorDate= DateSeparator.MINUS.getC();
        Character separatorTime= TimeSeparator.DOT.getC();

        String dateTime=Integer.toString(month)
                +separatorDate+Integer.toString(day)
                +separatorDate+Integer.toString(year)
                +' '+Integer.toString(hour)
                +separatorDate+Integer.toString(minute)
                +separatorTime+Integer.toString(sec);

        return dateTime;
    }

    @Override
    public String getDateIT() {
        Character separatorDate= DateSeparator.SLASH.getC();

        String date=Integer.toString(day)
                +separatorDate+Integer.toString(month)
                +separatorDate+Integer.toString(year);

        return date;
    }

    @Override
    public String getDateEN() {
        Character separatorDate= DateSeparator.MINUS.getC();

        String date=Integer.toString(month)
                +separatorDate+Integer.toString(day)
                +separatorDate+Integer.toString(year);

        return date;
    }

    @Override
    public String getTimeIT() {
        Character separatorTime=TimeSeparator.COLON.getC();

        String time=Integer.toString(hour)
                +separatorTime+Integer.toString(minute)
                +separatorTime+Integer.toString(sec);

        return time;
    }

    @Override
    public String getTimeEN() {
        Character separatorTime=TimeSeparator.DOT.getC();

        String time=Integer.toString(hour)
                +separatorTime+Integer.toString(minute)
                +separatorTime+Integer.toString(sec);

        return time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

}
