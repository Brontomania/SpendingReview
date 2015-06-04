package com.thewoollizard.android.spendingreview.lib.database.iface;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by @Brontomania on 18/02/2015.
 */
public interface IDBStructure {

    //All Tables
    public int createAllTables(SQLiteDatabase db);
    public int dropAllTables(SQLiteDatabase db);
    public void onResetAllTables(SQLiteDatabase db);

    //T_COD_CURRENCY
    public int createTCODCurrency(SQLiteDatabase db);
    public int dropTCODCurrency(SQLiteDatabase db);
    public void onResetTCODCurrency(SQLiteDatabase db);

    //T_TAB_MOVTYPE
    public int createTTABMovType(SQLiteDatabase db);
    public int dropTTABMovType(SQLiteDatabase db);
    public void onResetTTABMovType(SQLiteDatabase db);

    //T_TAB_FLOWTYPE
    public int createTTABFlowType(SQLiteDatabase db);
    public int dropTTABFlowType(SQLiteDatabase db);
    public void onResetTTABFlowType(SQLiteDatabase db);

    //T_TAB_BDG
    public int createTTABBdg(SQLiteDatabase db);
    public int dropTTABBdg(SQLiteDatabase db);
    public void onResetTTABBdg(SQLiteDatabase db);

    //T_TAB_CATEGORY
    public int createTTABCategory(SQLiteDatabase db);
    public int dropTTABCategory(SQLiteDatabase db);
    public void onResetTTABCategory(SQLiteDatabase db);

    //T_TAB_FIELD
    public int createTTABField(SQLiteDatabase db);
    public int dropTTABField(SQLiteDatabase db);
    public void onResetTTABField(SQLiteDatabase db);

    //T_TAB_USER
    public int createTTABUser(SQLiteDatabase db);
    public int dropTTABUser(SQLiteDatabase db);
    public void onResetTTABUser(SQLiteDatabase db);

    //T_MOV_ITEMS
    public int createTMOVItems(SQLiteDatabase db);
    public int dropTMOVItems(SQLiteDatabase db);
    public void onResetTMOVItems(SQLiteDatabase db);

    //T_MOV_CATBDG
    public int createTMOVCatBdg(SQLiteDatabase db);
    public int dropTMOVCatBdg(SQLiteDatabase db);
    public void onResetTMOVCatBdg(SQLiteDatabase db);

    //T_MOV_CATUSER
    public int createTMOVCatUser(SQLiteDatabase db);
    public int dropTMOVCatUser(SQLiteDatabase db);
    public void onResetTMOVCatUser(SQLiteDatabase db);

    //T_MOV_FIELDBDG
    public int createTMOVFieldBdg(SQLiteDatabase db);
    public int dropTMOVFieldBdg(SQLiteDatabase db);
    public void onResetTMOVFieldBdg(SQLiteDatabase db);

    //T_MOV_FIELDUSER
    public int createTMOVFieldUser(SQLiteDatabase db);
    public int dropTMOVFieldUser(SQLiteDatabase db);
    public void onResetTMOVFieldUser(SQLiteDatabase db);

    //T_MOV_FLOWTYPEBDG
    public int createTMOVFlowTypeBdg(SQLiteDatabase db);
    public int dropTMOVFlowTypeBdg(SQLiteDatabase db);
    public void onResetTMOVFlowTypeBdg(SQLiteDatabase db);

    //T_MOV_FLOWTYPEUSER
    public int createTMOVFlowTypeUser(SQLiteDatabase db);
    public int dropTMOVFlowTypeUser(SQLiteDatabase db);
    public void onResetTMOVFlowTypeUser(SQLiteDatabase db);

    //T_MOV_ITEMSBDG
    public int createTMOVItemsBdg(SQLiteDatabase db);
    public int dropTMOVItemsBdg(SQLiteDatabase db);
    public void onResetTMOVItemsBdg(SQLiteDatabase db);

    //T_MOV_ITEMSCATEGORIES
    public int createTMOVItemsCategories(SQLiteDatabase db);
    public int dropTMOVItemsCategories(SQLiteDatabase db);
    public void onResetTMOVItemsCategories(SQLiteDatabase db);

    //T_MOV_ITEMSFIELDS
    public int createTMOVItemsFields(SQLiteDatabase db);
    public int dropTMOVItemsFields(SQLiteDatabase db);
    public void onResetTMOVItemsFields(SQLiteDatabase db);

    //T_MOV_ITEMSUSER
    public int createTMOVItemsUser(SQLiteDatabase db);
    public int dropTMOVItemsUser(SQLiteDatabase db);
    public void onResetTMOVItemsUser(SQLiteDatabase db);

    //T_MOV_MOVTYPEBDG
    public int createTMOVMovTypeBdg(SQLiteDatabase db);
    public int dropTMOVMovTypeBdg(SQLiteDatabase db);
    public void onResetTMOVMovTypeBdg(SQLiteDatabase db);

    //T_MOV_MOVTYPEUSER
    public int createTMOVMovTypeUser(SQLiteDatabase db);
    public int dropTMOVMovTypeUser(SQLiteDatabase db);
    public void onResetTMOVMovTypeUser(SQLiteDatabase db);

    //T_MOV_USERBDG
    public int createTMOVUserBdg(SQLiteDatabase db);
    public int dropTMOVUserBdg(SQLiteDatabase db);
    public void onResetTMOVUserBdg(SQLiteDatabase db);

}
