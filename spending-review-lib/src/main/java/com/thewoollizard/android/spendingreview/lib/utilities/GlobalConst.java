package com.thewoollizard.android.spendingreview.lib.utilities;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;

import com.thewoollizard.android.spendingreview.lib.database.DBStructure;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by @Brontomania on 04/03/2015.
 * Global constant
 */
public class GlobalConst {

    //DATE SEPARATOR
    public static final Character DATE_SEPARATOR_SLASH='/';
    public static final Character DATE_SEPARATOR_MINUS='-';

    //TIME SEPARATOR
    public static final Character TIME_SEPARATOR_COLON=':';
    public static final Character TIME_SEPARATOR_DOT='.';

    //LOG TAGS
    public static final String[] LOGTAGS={
            "DATABASE"
    };

    //MESSAGES
    public static final String[] MESSAGES={
            "DATABASE"
    };

    //MOVTYPES
    public static final String MOVTYPES_ALL="ALL";
    public static final String MOVTYPES_BANK="BANK";
    public static final String MOVTYPES_CASH="CASH";
    public static final String MOVTYPES_CARD="CARD";

    //FRAGMENT
    public static final String TAB_SECTION_NUMBER = "tab_section_number";

    //DATABASE
    public static int DBVERSION=1;
    public final static String DBNAME = "dbSPreview";
    public final static String DBPATH="data/data/com.thewoollizard.android.spendingreview.free/databases/dbSPreview";

    //URI
    public final static String AUTHORITY="com.thewoollizard.android.spendingreview.free.provider";
    public final static String T_MOV_ITEMS= DBStructure.TMOVItems.TB_NAME;
    public final static String T_MOV_ITEMS_CATEGORIES= DBStructure.TMOVItemsCategories.TB_NAME;
    public final static String T_COD_CURRENCY= DBStructure.TCODCurrency.TB_NAME;
    public final static String T_TAB_MOVTYPE= DBStructure.TTABMovType.TB_NAME;
    public final static String T_TAB_FLOWTYPE= DBStructure.TTABFlowType.TB_NAME;
    public final static String T_TAB_FIELD= DBStructure.TTABField.TB_NAME;
    public final static String T_TAB_CATEGORY= DBStructure.TTABCategory.TB_NAME;
    public final static Uri URI_T_MOV_ITEMS= Uri.parse("content://" + AUTHORITY + "/" + T_MOV_ITEMS);
    public final static Uri URI_T_MOV_ITEMS_CATEGORIES= Uri.parse("content://" + AUTHORITY + "/" + T_MOV_ITEMS_CATEGORIES);
    public final static Uri URI_T_COD_CURRENCY= Uri.parse("content://" + AUTHORITY + "/" + T_COD_CURRENCY);
    public final static Uri URI_T_TAB_MOVTYPE= Uri.parse("content://" + AUTHORITY + "/" + T_TAB_MOVTYPE);
    public final static Uri URI_T_TAB_FLOWTYPE= Uri.parse("content://" + AUTHORITY + "/" + T_TAB_FLOWTYPE);
    public final static Uri URI_T_TAB_FIELD= Uri.parse("content://" + AUTHORITY + "/" + T_TAB_FIELD);
    public final static Uri URI_T_TAB_CATEGORY= Uri.parse("content://" + AUTHORITY + "/" + T_TAB_CATEGORY);

    //INT
    public final static int ITEM=100;
    public final static int ITEM_CATEGORIES=101;
    public final static int CURRENCY=110;
    public final static int MOVTYPE=120;
    public final static int FLOWTYPE=130;
    public final static int FIELD=140;
    public final static int CATEGORY=150;

    //CONTENT RESOLVER
    public final static String ITEM_CONTENT_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.spendingreview.free.item";
    public final static String ITEM_CATEGORIES_CONTENT_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE+"/vnd.spendingreview.free.item_categories";
    public final static String CURRENCY_CONTENT_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.spendingreview.free.currency";
    public final static String MOVTYPE_CONTENT_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.spendingreview.free.movtype";
    public final static String FLOWTYPE_CONTENT_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.spendingreview.free.flowtype";
    public final static String FIELD_CONTENT_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.spendingreview.free.field";
    public final static String CATEGORY_CONTENT_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.spendingreview.free.category";

    //UM
    public static final int UNIT_BASE=0;
    public static final int UNIT_KILO=1;
    public static final int UNIT_MEGA=2;
    public static final int UNIT_GIGA=3;

    //Number Format
    public final static NumberFormat nForm= NumberFormat.getCurrencyInstance(Locale.ITALY);

    //Uri Matcher
    public final static UriMatcher mUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
    static {
        mUriMatcher.addURI(AUTHORITY, T_MOV_ITEMS, ITEM);
        mUriMatcher.addURI(AUTHORITY, T_MOV_ITEMS_CATEGORIES, ITEM_CATEGORIES);
        mUriMatcher.addURI(AUTHORITY, T_COD_CURRENCY, CURRENCY);
        mUriMatcher.addURI(AUTHORITY, T_TAB_MOVTYPE, MOVTYPE);
        mUriMatcher.addURI(AUTHORITY, T_TAB_FLOWTYPE, FLOWTYPE);
        mUriMatcher.addURI(AUTHORITY, T_TAB_FIELD, FIELD);
        mUriMatcher.addURI(AUTHORITY, T_TAB_CATEGORY, CATEGORY);
    }

    //ID del Loader
    public final static int LOADER_ID=1;
    public final static int LOADER_ID_MOVTYPE=2;

    //Composizione di query
    public final static String[] SQL_SELECT_ITEMS_CATEGORIES={
            DBStructure.TTABCategory.TB_NAME + "." + DBStructure.TTABCategory.IDCATEGORY
            , DBStructure.TTABCategory.TB_NAME + "." + DBStructure.TTABCategory.DESCATEGORY
    };

    public final static String SQL_FROM_ITEMS_CATEGORIES= DBStructure.TMOVItemsCategories.TB_NAME
            + " INNER JOIN " + DBStructure.TTABCategory.TB_NAME
            + " ON " + DBStructure.TMOVItemsCategories.TB_NAME
            + "." + DBStructure.TMOVItemsCategories.IDCATEGORY
            + " = " + DBStructure.TTABCategory.TB_NAME
            + "." + DBStructure.TTABCategory.IDCATEGORY;

    public final static String SQL_WHERE_ITEMS_CATEGORIES= DBStructure.TMOVItemsCategories.TB_NAME
            +"."+ DBStructure.TMOVItemsCategories.IDITEM+"=?";

}
