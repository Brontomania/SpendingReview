package com.thewoollizard.android.spendingreview.lib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.thewoollizard.android.spendingreview.lib.database.iface.IDBStructure;
import com.thewoollizard.android.spendingreview.lib.utilities.GlobalConst;
import com.thewoollizard.android.spendingreview.lib.utilities.Utilities;

/**
 * Created by @BrontoMania on 12/09/2014.
 * Define the Database Structure and Drop/Creation/Reset methods
 */
public class DBStructure extends SQLiteOpenHelper implements IDBStructure {

    private int mDBVersion= GlobalConst.DBVERSION;

    public DBStructure(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.createAllTables(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBStructure.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        dropAllTables(db);
        onCreate(db);
        mDBVersion++;
    }


    //Create All Tables
    public int createAllTables(SQLiteDatabase db){
        try{
            db.execSQL(TCODCURRENCY_TABLE_CREATE);
            db.execSQL(TTABMOVTYPE_TABLE_CREATE);
            db.execSQL(TTABFLOWTYPE_TABLE_CREATE);
            db.execSQL(TTABBDG_TABLE_CREATE);
            db.execSQL(TTABCATEGORY_TABLE_CREATE);
            db.execSQL(TTABFIELD_TABLE_CREATE);
            db.execSQL(TTABUSER_TABLE_CREATE);
            db.execSQL(TMOVITEMS_TABLE_CREATE);
            db.execSQL(TMOVCATBDG_TABLE_CREATE);
            db.execSQL(TMOVCATUSER_TABLE_CREATE);
            db.execSQL(TMOVFIELDBDG_TABLE_CREATE);
            db.execSQL(TMOVFIELDUSER_TABLE_CREATE);
            db.execSQL(TMOVFLOWTYPEBDG_TABLE_CREATE);
            db.execSQL(TMOVFLOWTYPEUSER_TABLE_CREATE);
            db.execSQL(TMOVITEMSBDG_TABLE_CREATE);
            db.execSQL(TMOVITEMSCATEGORIES_TABLE_CREATE);
            db.execSQL(TMOVITEMSFIELDS_TABLE_CREATE);
            db.execSQL(TMOVITEMSUSER_TABLE_CREATE);
            db.execSQL(TMOVMOVTYPEBDG_TABLE_CREATE);
            db.execSQL(TMOVMOVTYPEUSER_TABLE_CREATE);
            db.execSQL(TMOVUSERBDG_TABLE_CREATE);
        }
        catch(Exception E){
            return 1;
        }
        return 0;
    }

    //Drop All Tables
    public int dropAllTables(SQLiteDatabase db){
        try{
            db.execSQL(TCODCURRENCY_TABLE_DROP);
            db.execSQL(TTABMOVTYPE_TABLE_DROP);
            db.execSQL(TTABFLOWTYPE_TABLE_DROP);
            db.execSQL(TTABBDG_TABLE_DROP);
            db.execSQL(TTABCATEGORY_TABLE_DROP);
            db.execSQL(TTABFIELD_TABLE_DROP);
            db.execSQL(TTABUSER_TABLE_DROP);
            db.execSQL(TMOVITEMS_TABLE_DROP);
            db.execSQL(TMOVCATBDG_TABLE_DROP);
            db.execSQL(TMOVCATUSER_TABLE_DROP);
            db.execSQL(TMOVFIELDBDG_TABLE_DROP);
            db.execSQL(TMOVFIELDUSER_TABLE_DROP);
            db.execSQL(TMOVFLOWTYPEBDG_TABLE_DROP);
            db.execSQL(TMOVFLOWTYPEUSER_TABLE_DROP);
            db.execSQL(TMOVITEMSBDG_TABLE_DROP);
            db.execSQL(TMOVITEMSCATEGORIES_TABLE_DROP);
            db.execSQL(TMOVITEMSUSER_TABLE_DROP);
            db.execSQL(TMOVMOVTYPEBDG_TABLE_DROP);
            db.execSQL(TMOVMOVTYPEUSER_TABLE_DROP);
            db.execSQL(TMOVUSERBDG_TABLE_DROP);
        }
        catch(Exception E){
            return 1;
        }
        return 0;
    }

    //Reset Database: drop and create all tables
    @Override
    public void onResetAllTables(SQLiteDatabase db){
        int dropTablesCtrl=this.dropAllTables(db);
        int createTablesCtrl=this.createAllTables(db);

        if (dropTablesCtrl==1){
            Log.e("DBStructure","Errore di Drop All Tables");
            throw new SQLiteException("Impossibile droppare tutte le tabelle");
        }

        if (createTablesCtrl==1){
            Log.e("DBStructure","Errore di Create All Tables");
            throw new SQLiteException("Impossibile creare tutte le tabelle");
        }
    }

    //Table T_COD_CURRENCY definition: currencies list
    public static class TCODCurrency{
        public static final String TB_NAME="T_COD_CURRENCY";
        public static final String IDCURRENCY = "_id";
        public static final String IDCURRENCYSTATUS = "id_currencystatus";  // 0-off, 1-on
        public static final String DESCURRENCY="des_currency"; //currency description (ex. Euro)
        public static final String DESCURRENCYSYMBOL="des_currencysymbol"; //currency symbol (ex. €)
        public static final String TMS="tms";
        public static final String TCODCURRENCY_TABLE_INSERT="INSERT INTO "
                + TCODCurrency.TB_NAME+" ("
                + TCODCurrency.IDCURRENCYSTATUS + ", "
                + TCODCurrency.DESCURRENCY + ", "
                + TCODCurrency.DESCURRENCYSYMBOL + ", "
                + TCODCurrency.TMS + ") "
                + "VALUES (?,?,?,?)";
        public static final String TCODCURRENCY_TABLE_UPDATE_WH_STATUS="UPDATE "
                + TCODCurrency.TB_NAME
                + " SET " + TCODCurrency.IDCURRENCYSTATUS + "=?, "
                + " SET " + TCODCurrency.TMS + "=?, "
                + "WHERE "
                + TCODCurrency.IDCURRENCYSTATUS + "=?";
        public static final String TCODCURRENCY_TABLE_DELETE_ALL="DELETE FROM "+ TCODCurrency.TB_NAME;
    }

    //Table T_COD_CURRENCY creation query definition
    private static final String TCODCURRENCY_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TCODCurrency.TB_NAME + "("
            + TCODCurrency.IDCURRENCY + " integer primary key autoincrement, "
            + TCODCurrency.IDCURRENCYSTATUS + " integer not null, "
            + TCODCurrency.DESCURRENCY +" text not null,"
            + TCODCurrency.DESCURRENCYSYMBOL +" text not null,"
            + TCODCurrency.TMS+" text not null)";
    //Table T_COD_CURRENCY drop table query definition
    private static final String TCODCURRENCY_TABLE_DROP="DROP TABLE IF EXISTS " + TCODCurrency.TB_NAME;

    //Table T_TAB_MOVTYPE definition: movement types list. Tree structure (Root ID = 0)
    public static class TTABMovType{
        public static final String TB_NAME="T_TAB_MOVTYPE";
        public static final String IDMOVTYPE = "_id";
        public static final String IDMOVTYPESTATUS = "id_movtypestatus";  // 0-off, 1-on
        public static final String IDMOVTYPEFATHER = "id_movtypefather"; //movtype father id
        public static final String DESMOVTYPE = "des_movtype"; //movtype description (ex. bank)
        public static final String TMS="tms";
        public static final String TTABMOVTYPE_TABLE_INSERT="INSERT INTO "
                + TTABMovType.TB_NAME+" ("
                + TTABMovType.IDMOVTYPESTATUS + ", "
                + TTABMovType.IDMOVTYPEFATHER + ", "
                + TTABMovType.DESMOVTYPE + ", "
                + TTABMovType.TMS + ") "
                + "VALUES (?,?,?,?)";
        public static final String TTABMOVTYPE_TABLE_UPDATE_WH_STATUS="UPDATE "
                + TTABMovType.TB_NAME
                + " SET " + TTABMovType.IDMOVTYPESTATUS + "=?, "
                + " SET " + TTABMovType.TMS + "=?, "
                + "WHERE "
                + TTABMovType.IDMOVTYPESTATUS + "=?";
        public static final String TTABMOVTYPE_TABLE_DELETE_ALL="DELETE FROM "+ TTABMovType.TB_NAME;
    }

    //Table T_TAB_MOVTYPE creation query definition
    private static final String TTABMOVTYPE_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TTABMovType.TB_NAME + "("
            + TTABMovType.IDMOVTYPE + " integer primary key autoincrement, "
            + TTABMovType.IDMOVTYPESTATUS + " integer not null, "
            + TTABMovType.IDMOVTYPEFATHER + " integer not null, "
            + TTABMovType.DESMOVTYPE +" text not null,"
            + TTABMovType.TMS+" text not null)";
    //Table T_TAB_MOVTYPE drop table query definition
    private static final String TTABMOVTYPE_TABLE_DROP="DROP TABLE IF EXISTS " + TTABMovType.TB_NAME;

    //Table T_TAB_FLOWTYPE definition: flow types list
    public static class TTABFlowType{
        public static final String TB_NAME="T_TAB_FLOWTYPE";
        public static final String IDFLOWTYPE = "_id";
        public static final String IDFLOWTYPESTATUS = "id_flowtypestatus";  // 0-off, 1-on
        public static final String DESFLOWTYPE = "des_flowtype"; // flow types description (ex. in/out)
        public static final String TMS="tms";

        public static final String TTABFLOWTYPE_TABLE_INSERT="INSERT INTO "
                + TTABFlowType.TB_NAME+" ("
                + TTABFlowType.IDFLOWTYPESTATUS + ", "
                + TTABFlowType.DESFLOWTYPE + ", "
                + TTABFlowType.TMS + ") "
                + "VALUES (?,?,?)";
        public static final String TTABFLOWTYPE_TABLE_UPDATE_WH_STATUS="UPDATE "
                + TTABFlowType.TB_NAME
                + " SET " + TTABFlowType.IDFLOWTYPESTATUS + "=?, "
                + " SET " + TTABFlowType.TMS + "=?, "
                + "WHERE "
                + TTABFlowType.IDFLOWTYPESTATUS + "=?";
        public static final String TTABFLOWTYPE_TABLE_DELETE_ALL="DELETE FROM "+ TTABFlowType.TB_NAME;
    }

    //Table T_TAB_FLOWTYPE creation query definition
    private static final String TTABFLOWTYPE_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TTABFlowType.TB_NAME + "("
            + TTABFlowType.IDFLOWTYPE + " integer primary key autoincrement, "
            + TTABFlowType.IDFLOWTYPESTATUS + " integer not null, "
            + TTABFlowType.DESFLOWTYPE +" text not null,"
            + TTABFlowType.TMS+" text not null)";
    //Table T_TAB_FLOWTYPE drop table query definition
    private static final String TTABFLOWTYPE_TABLE_DROP="DROP TABLE IF EXISTS " + TTABFlowType.TB_NAME;

    //Table T_TAB_FIELD definition: fields list. Tree structure (Root ID = 0)
    public static class TTABField{
        public static final String TB_NAME="T_TAB_FIELD";
        public static final String IDFIELD = "_id";
        public static final String IDFIELDSTATUS = "id_fieldstatus";  // 0-off, 1-on
        public static final String IDFIELDFATHER = "id_fieldfather"; //field father id
        public static final String DESFIELD = "des_field"; //field description (ex. home, office)
        public static final String TMS="tms";

        public static final String TTABFIELD_TABLE_INSERT="INSERT INTO "
                + TTABField.TB_NAME+" ("
                + TTABField.IDFIELDSTATUS + ", "
                + TTABField.IDFIELDFATHER + ", "
                + TTABField.DESFIELD + ", "
                + TTABField.TMS + ") "
                + "VALUES (?,?,?,?)";
        public static final String TTABFIELD_TABLE_UPDATE_WH_STATUS="UPDATE "
                + TTABField.TB_NAME
                + " SET " + TTABField.IDFIELDSTATUS + "=?, "
                + " SET " + TTABField.TMS + "=?, "
                + "WHERE "
                + TTABField.IDFIELDSTATUS + "=?";
        public static final String TTABFIELD_TABLE_DELETE_ALL="DELETE FROM "+ TTABField.TB_NAME;
    }

    //Table T_TAB_FIELD creation query definition
    private static final String TTABFIELD_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TTABField.TB_NAME + "("
            + TTABField.IDFIELD + " integer primary key autoincrement, "
            + TTABField.IDFIELDSTATUS + " integer not null, "
            + TTABField.IDFIELDFATHER + " integer not null, "
            + TTABField.DESFIELD +" text not null,"
            + TTABField.TMS+" text not null)";
    //Table T_TAB_FIELD drop table query definition
    private static final String TTABFIELD_TABLE_DROP="DROP TABLE IF EXISTS " + TTABField.TB_NAME;

    //Table T_TAB_CATEGORY definition: categories list. Tree structure (Root ID = 0)
    public static class TTABCategory{
        public static final String TB_NAME="T_TAB_CATEGORY";
        public static final String IDCATEGORY = "_id";
        public static final String IDCATEGORYSTATUS = "id_categorystatus";  // 0-off, 1-on
        public static final String IDCATEGORYFATHER = "id_categoryfather"; //category father id
        public static final String DESCATEGORY = "des_category"; //category description (ex. car)
        public static final String TMS="tms";

        public static final String TTABCATEGORY_TABLE_INSERT="INSERT INTO "
                + TTABCategory.TB_NAME+" ("
                + TTABCategory.IDCATEGORYSTATUS + ", "
                + TTABCategory.IDCATEGORYFATHER + ", "
                + TTABCategory.DESCATEGORY + ", "
                + TTABCategory.TMS + ") "
                + "VALUES (?,?,?,?)";
        public static final String TTABCATEGORY_TABLE_UPDATE_WH_STATUS="UPDATE "
                + TTABCategory.TB_NAME
                + " SET " + TTABCategory.IDCATEGORYSTATUS + "=?, "
                + " SET " + TTABCategory.TMS + "=?, "
                + "WHERE "
                + TTABCategory.IDCATEGORYSTATUS + "=?";
        public static final String TTABCATEGORY_TABLE_DELETE_ALL="DELETE FROM "+ TTABCategory.TB_NAME;
    }

    //Table T_TAB_CATEGORY creation query definition
    private static final String TTABCATEGORY_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TTABCategory.TB_NAME + "("
            + TTABCategory.IDCATEGORY + " integer primary key autoincrement, "
            + TTABCategory.IDCATEGORYSTATUS + " integer not null, "
            + TTABCategory.IDCATEGORYFATHER + " integer not null, "
            + TTABCategory.DESCATEGORY +" text not null,"
            + TTABCategory.TMS+" text not null)";
    //Table T_TAB_CATEGORY drop table query definition
    private static final String TTABCATEGORY_TABLE_DROP="DROP TABLE IF EXISTS " + TTABCategory.TB_NAME;

    //Table T_TAB_USER definition: users list
    public static class TTABUser{
        static final String TB_NAME="T_TAB_USER";
        public static final String IDUSER = "_id";
        public static final String IDUSERSTATUS = "id_userstatus";  // 0-off, 1-on
        public static final String USER = "des_user"; //account
        public static final String DESUSERNAME = "des_username";
        public static final String DESUSERSURNAME = "des_usersurname";
        public static final String IDUSERPROFILE = "id_userprofile"; //user profile (0=user, 1=admin)
        public static final String TMS="tms";
    }

    //Table T_TAB_USER creation query definition
    private static final String TTABUSER_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TTABUser.TB_NAME + "("
            + TTABUser.IDUSER + " integer primary key autoincrement, "
            + TTABUser.IDUSERSTATUS +" integer not null,"
            + TTABUser.IDUSERPROFILE +" integer not null,"
            + TTABUser.USER +" text not null,"
            + TTABUser.DESUSERNAME +" text not null,"
            + TTABUser.DESUSERSURNAME +" text not null,"
            + TTABUser.TMS+" text not null)";
    //Table T_TAB_CATEGORY drop table query definition
    private static final String TTABUSER_TABLE_DROP="DROP TABLE IF EXISTS " + TTABUser.TB_NAME;

    //Table T_TAB_BDG definition: budgets list
    public static class TTABBdg{
        static final String TB_NAME="T_TAB_BDG";
        public static final String IDBDG = "_id";
        public static final String IDBDGTYPE = "id_bdgtype"; // 0-day, 1-week, 2-month, 3-year, 4-custom
        public static final String IDBDGSTATUS = "id_bdgstatus"; // 0-off, 1-on
        public static final String DESBDGNAME = "des_bdgname"; // budget name
        public static final String DESBDG = "des_bdg"; // budget description
        public static final String AMOUNT = "amount"; // budget amount in budget type (or in budget interval if type is custom)
        public static final String DT1="dt1"; // budget interval begin
        public static final String DT2="dt2"; // budget interval end
        public static final String TMS="tms";
    }

    //Table T_TAB_BDG creation query definition
    private static final String TTABBDG_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TTABBdg.TB_NAME + "("
            + TTABBdg.IDBDG + " integer primary key autoincrement, "
            + TTABBdg.IDBDGTYPE + " integer not null, "
            + TTABBdg.IDBDGSTATUS + " integer not null, "
            + TTABBdg.DESBDGNAME +" text not null, "
            + TTABBdg.DESBDG +" text not null, "
            + TTABBdg.AMOUNT +" real not null, "
            + TTABBdg.DT1 +" text, "
            + TTABBdg.DT2 +" text, "
            + TTABBdg.TMS+" text not null)";
    //Table T_TAB_BDG drop table query definition
    private static final String TTABBDG_TABLE_DROP="DROP TABLE IF EXISTS " + TTABBdg.TB_NAME;

    //FIXME: PIPPO
    //Table T_MOV_ITEMS definition: spending review items list
    public static class TMOVItems{
        public static final String TB_NAME="T_MOV_ITEMS";
        public static final String IDITEM = "_id";
        public static final String IDITEMSTATUS = "id_itemstatus"; // 0-off, 1-on
        public static final String IDCURRENCY = "id_currency"; // Item currency
        public static final String IDMOVTYPE="id_movtype"; // Item MovType
        public static final String IDFLOWTYPE="id_flowtype"; // Item FlowType
        public static final String DESITEM = "des_item"; // Item free description
        public static final String DTITEM = "dt_item"; // Item date
        public static final String TMITEM = "tm_item"; // Item time
        public static final String AMOUNT = "amount"; // Item amount
        public static final String TMS="tms";

        public static final String TMOVITEMS_TABLE_INSERT="INSERT INTO "
                + TMOVItems.TB_NAME+" ("
                + TMOVItems.IDITEMSTATUS + ", "
                + TMOVItems.IDCURRENCY + ", "
                + TMOVItems.IDMOVTYPE + ", "
                + TMOVItems.IDFLOWTYPE + ", "
                + TMOVItems.DESITEM + ", "
                + TMOVItems.DTITEM + ", "
                + TMOVItems.TMITEM + ", "
                + TMOVItems.AMOUNT + ", "
                + TMOVItems.TMS + ") "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        public static final String TMOVITEMS_TABLE_UPDATE_WH_STATUS="UPDATE "
                + TMOVItems.TB_NAME
                + " SET " + TMOVItems.IDITEMSTATUS + "=?, "
                + " SET " + TMOVItems.TMS + "=?, "
                + "WHERE "
                + TMOVItems.IDITEMSTATUS + "=?";
        public static final String TMOVITEMS_TABLE_DELETE_ALL="DELETE FROM "+ TMOVItems.TB_NAME;
    }

    //Table T_MOV_ITEMS creation query definition
    private static final String TMOVITEMS_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVItems.TB_NAME + "("
            + TMOVItems.IDITEM + " integer primary key autoincrement, "
            + TMOVItems.IDITEMSTATUS+" int not null,"
            + TMOVItems.IDCURRENCY +" int not null,"
            + TMOVItems.IDMOVTYPE +" int not null,"
            + TMOVItems.IDFLOWTYPE +" int not null,"
            + TMOVItems.DESITEM+" text not null,"
            + TMOVItems.DTITEM+" text not null,"
            + TMOVItems.TMITEM+" text not null,"
            + TMOVItems.AMOUNT+" int not null,"
            + TMOVItems.TMS+" text not null)";
    //Table T_MOV_ITEMS drop table query definition
    private static final String TMOVITEMS_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVItems.TB_NAME;

    //Table T_MOV_ITEMS_CATEGORIES definition: link between Items and Categories
    public static class TMOVItemsCategories{
        public static final String TB_NAME="T_MOV_ITEMS_CATEGORIES";
        public static final String IDITEMCATEGORY = "_id";
        public static final String IDCATEGORY = "id_category";
        public static final String IDITEM = "id_item";
        public static final String TMS="tms";

        public static final String TMOVITEMSCATEGORIES_TABLE_INSERT="INSERT INTO "
                + TMOVItemsCategories.TB_NAME+" ("
                + TMOVItemsCategories.IDCATEGORY + ", "
                + TMOVItemsCategories.IDITEM + ", "
                + TMOVItemsCategories.TMS + ") "
                + "VALUES (?,?,?)";
    }

    //Table T_MOV_ITEMS_CATEGORIES creation query definition
    private static final String TMOVITEMSCATEGORIES_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVItemsCategories.TB_NAME + "("
            + TMOVItemsCategories.IDITEMCATEGORY + " integer primary key autoincrement, "
            + TMOVItemsCategories.IDCATEGORY + " integer not null, "
            + TMOVItemsCategories.IDITEM + " integer not null, "
            + TMOVItemsCategories.TMS+" text not null)";
    //Table T_MOV_ITEMS_CATEGORIES drop table query definition
    private static final String TMOVITEMSCATEGORIES_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVItemsCategories.TB_NAME;

    //Table T_MOV_ITEMS_FIELDS definition: link between Items and Fields
    public static class TMOVItemsFields{
        public static final String TB_NAME="T_MOV_ITEMS_FIELDS";
        public static final String IDITEMFIELD = "_id";
        public static final String IDFIELD = "id_category";
        public static final String IDITEM = "id_item";
        public static final String TMS="tms";

        public static final String TMOVITEMSFIELDS_TABLE_INSERT="INSERT INTO "
                + TMOVItemsFields.TB_NAME+" ("
                + TMOVItemsFields.IDFIELD + ", "
                + TMOVItemsFields.IDITEM + ", "
                + TMOVItemsFields.TMS + ") "
                + "VALUES (?,?,?)";
    }

    //Table T_MOV_ITEMS_FIELDS creation query definition
    private static final String TMOVITEMSFIELDS_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVItemsFields.TB_NAME + "("
            + TMOVItemsFields.IDITEMFIELD + " integer primary key autoincrement, "
            + TMOVItemsFields.IDFIELD + " integer not null, "
            + TMOVItemsFields.IDITEM + " integer not null, "
            + TMOVItemsFields.TMS+" text not null)";
    //Table T_MOV_ITEMS_CATEGORIES drop table query definition
    private static final String TMOVITEMSFIELDS_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVItemsFields.TB_NAME;

    //Table T_MOV_ITEMS_USER definition: link between Items and Users
    public static class TMOVItemsUser{
        static final String TB_NAME="T_MOV_ITEMS_USER";
        public static final String IDITEMUSER = "_id";
        public static final String IDUSER = "id_user";
        public static final String IDITEM = "id_item";
        public static final String TMS="tms";
    }

    //Table T_MOV_ITEMS_USER creation query definition
    private static final String TMOVITEMSUSER_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVItemsUser.TB_NAME + "("
            + TMOVItemsUser.IDITEMUSER + " integer primary key autoincrement, "
            + TMOVItemsUser.IDUSER + " integer not null, "
            + TMOVItemsUser.IDITEM + " integer not null, "
            + TMOVItemsUser.TMS+" text not null)";
    //Table T_MOV_ITEMS_USER drop table query definition
    private static final String TMOVITEMSUSER_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVItemsUser.TB_NAME;

    //Table T_MOV_ITEMS_BDG definition: link between Items and Budgets
    public static class TMOVItemsBdg{
        static final String TB_NAME="T_MOV_ITEMS_BDG";
        public static final String IDITEMBDG = "_id";
        public static final String IDBDG = "id_bdg";
        public static final String IDITEM = "id_item";
        public static final String TMS="tms";
    }

    //Table T_MOV_ITEMS_BDG creation query definition
    private static final String TMOVITEMSBDG_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVItemsBdg.TB_NAME + "("
            + TMOVItemsBdg.IDITEMBDG + " integer primary key autoincrement, "
            + TMOVItemsBdg.IDBDG + " integer not null, "
            + TMOVItemsBdg.IDITEM + " integer not null, "
            + TMOVItemsBdg.TMS+" text not null)";
    //Table T_MOV_ITEMS_BDG drop table query definition
    private static final String TMOVITEMSBDG_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVItemsBdg.TB_NAME;

    //Table T_MOV_CAT_BDG definition: link between Categories and Budgets
    public static class TMOVCatBdg{
        static final String TB_NAME="T_MOV_CAT_BDG";
        public static final String IDCATBDG = "_id";
        public static final String IDCATEGORY = "id_category";
        public static final String IDBDG = "id_bdg";
        public static final String TMS="tms";
    }

    //Table T_MOV_CAT_BDG creation query definition
    private static final String TMOVCATBDG_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVCatBdg.TB_NAME + "("
            + TMOVCatBdg.IDCATBDG + " integer primary key autoincrement, "
            + TMOVCatBdg.IDCATEGORY + " integer not null, "
            + TMOVCatBdg.IDBDG + " integer not null, "
            + TMOVCatBdg.TMS+" text not null)";
    //Table T_MOV_CAT_BDG drop table query definition
    private static final String TMOVCATBDG_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVCatBdg.TB_NAME;

    //Table T_MOV_MOVTYPE_BDG definition: link between MovTypes and Budgets
    public static class TMOVMovTypeBdg{
        static final String TB_NAME="T_MOV_MOVTYPE_BDG";
        public static final String IDMOVTYPEBDG = "_id";
        public static final String IDMOVTYPE = "id_movtype";
        public static final String IDBDG = "id_bdg";
        public static final String TMS="tms";
    }

    //Table T_MOV_MOVTYPE_BDG creation query definition
    private static final String TMOVMOVTYPEBDG_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVMovTypeBdg.TB_NAME + "("
            + TMOVMovTypeBdg.IDMOVTYPEBDG + " integer primary key autoincrement, "
            + TMOVMovTypeBdg.IDMOVTYPE + " integer not null, "
            + TMOVMovTypeBdg.IDBDG + " integer not null, "
            + TMOVMovTypeBdg.TMS+" text not null)";
    //Table T_MOV_MOVTYPE_BDG drop table query definition
    private static final String TMOVMOVTYPEBDG_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVMovTypeBdg.TB_NAME;

    //Table T_MOV_FLOWTYPE_BDG definition: link between FlowTypes and Budgets
    public static class TMOVFlowTypeBdg{
        static final String TB_NAME="T_MOV_FLOWTYPE_BDG";
        public static final String IDFLOWTYPEBDG = "_id";
        public static final String IDFLOWTYPE = "id_flowtype";
        public static final String IDBDG = "id_bdg";
        public static final String TMS="tms";
    }

    //Table T_MOV_FLOWTYPE_BDG creation query definition
    private static final String TMOVFLOWTYPEBDG_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVFlowTypeBdg.TB_NAME + "("
            + TMOVFlowTypeBdg.IDFLOWTYPEBDG + " integer primary key autoincrement, "
            + TMOVFlowTypeBdg.IDFLOWTYPE + " integer not null, "
            + TMOVFlowTypeBdg.IDBDG + " integer not null, "
            + TMOVFlowTypeBdg.TMS+" text not null)";
    //Table T_MOV_FLOWTYPE_BDG drop table query definition
    private static final String TMOVFLOWTYPEBDG_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVFlowTypeBdg.TB_NAME;

    //Table T_MOV_FIELD_BDG definition: link between Fields and Budgets
    public static class TMOVFieldBdg{
        static final String TB_NAME="T_MOV_FIELD_BDG";
        public static final String IDFIELDBDG = "_id";
        public static final String IDFIELD = "id_field";
        public static final String IDBDG = "id_bdg";
        public static final String TMS="tms";
    }

    //Table T_MOV_FIELD_BDG creation query definition
    private static final String TMOVFIELDBDG_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVFieldBdg.TB_NAME + "("
            + TMOVFieldBdg.IDFIELDBDG + " integer primary key autoincrement, "
            + TMOVFieldBdg.IDFIELD + " integer not null, "
            + TMOVFieldBdg.IDBDG + " integer not null, "
            + TMOVFieldBdg.TMS+" text not null)";
    //Table T_MOV_FIELD_BDG drop table query definition
    private static final String TMOVFIELDBDG_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVFieldBdg.TB_NAME;

    //Table T_MOV_USER_BDG definition: link between Users and Budgets
    public static class TMOVUserBdg{
        static final String TB_NAME="T_MOV_USER_BDG";
        public static final String IDUSERBDG = "_id";
        public static final String IDUSER = "id_user";
        public static final String IDBDG = "id_bdg";
        public static final String TMS="tms";
    }

    //Table T_MOV_USER_BDG creation query definition
    private static final String TMOVUSERBDG_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVUserBdg.TB_NAME + "("
            + TMOVUserBdg.IDUSERBDG + " integer primary key autoincrement, "
            + TMOVUserBdg.IDUSER + " integer not null, "
            + TMOVUserBdg.IDBDG + " integer not null, "
            + TMOVUserBdg.TMS+" text not null)";
    //Table T_MOV_USER_BDG drop table query definition
    private static final String TMOVUSERBDG_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVUserBdg.TB_NAME;

    //Table T_MOV_CAT_USER definition: link between Users and Categories
    public static class TMOVCatUser{
        static final String TB_NAME="T_MOV_CAT_USER";
        public static final String IDCATUSER = "_id";
        public static final String IDCATEGORY = "id_category";
        public static final String IDUSER = "id_user";
        public static final String TMS="tms";
    }

    //Table T_MOV_CAT_USER creation query definition
    private static final String TMOVCATUSER_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVCatUser.TB_NAME + "("
            + TMOVCatUser.IDCATUSER + " integer primary key autoincrement, "
            + TMOVCatUser.IDCATEGORY + " integer not null, "
            + TMOVCatUser.IDUSER + " integer not null, "
            + TMOVCatUser.TMS+" text not null)";
    //Table T_MOV_CAT_USER drop table query definition
    private static final String TMOVCATUSER_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVCatUser.TB_NAME;

    //Table T_MOV_MOVTYPE_USER definition: link between Users and MovTypes
    public static class TMOVMovTypeUser{
        static final String TB_NAME="T_MOV_MOVTYPE_USER";
        public static final String IDMOVTYPEUSER = "_id";
        public static final String IDMOVTYPE = "id_movtype";
        public static final String IDUSER = "id_user";
        public static final String TMS="tms";
    }

    //Table T_MOV_MOVTYPE_USER creation query definition
    private static final String TMOVMOVTYPEUSER_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVMovTypeUser.TB_NAME + "("
            + TMOVMovTypeUser.IDMOVTYPEUSER + " integer primary key autoincrement, "
            + TMOVMovTypeUser.IDMOVTYPE + " integer not null, "
            + TMOVMovTypeUser.IDUSER + " integer not null, "
            + TMOVMovTypeUser.TMS+" text not null)";
    //Table T_MOV_MOVTYPE_USER drop table query definition
    private static final String TMOVMOVTYPEUSER_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVMovTypeUser.TB_NAME;

    //Table T_MOV_FLOWTYPE_USER definition: link between Users and FlowTypes
    public static class TMOVFlowTypeUser{
        static final String TB_NAME="T_MOV_FLOWTYPE_USER";
        public static final String IDFLOWTYPEUSER = "_id";
        public static final String IDFLOWTYPE = "id_flowtype";
        public static final String IDUSER = "id_user";
        public static final String TMS="tms";
    }

    //Table T_MOV_FLOWTYPE_USER creation query definition
    private static final String TMOVFLOWTYPEUSER_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVFlowTypeUser.TB_NAME + "("
            + TMOVFlowTypeUser.IDFLOWTYPEUSER + " integer primary key autoincrement, "
            + TMOVFlowTypeUser.IDFLOWTYPE + " integer not null, "
            + TMOVFlowTypeUser.IDUSER + " integer not null, "
            + TMOVFlowTypeUser.TMS+" text not null)";
    //Table T_MOV_FLOWTYPE_USER drop table query definition
    private static final String TMOVFLOWTYPEUSER_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVFlowTypeUser.TB_NAME;

    //Table T_MOV_FIELD_USER definition: link between Users and Fields
    public static class TMOVFieldUser{
        static final String TB_NAME="T_MOV_FIELD_USER";
        public static final String IDFIELDUSER = "_id";
        public static final String IDFIELD = "id_field";
        public static final String IDUSER = "id_user";
        public static final String TMS="tms";
    }

    //Table T_MOV_FIELD_USER creation query definition
    private static final String TMOVFIELDUSER_TABLE_CREATE="CREATE TABLE IF NOT EXISTS "
            + TMOVFieldUser.TB_NAME + "("
            + TMOVFieldUser.IDFIELDUSER + " integer primary key autoincrement, "
            + TMOVFieldUser.IDFIELD + " integer not null, "
            + TMOVFieldUser.IDUSER + " integer not null, "
            + TMOVFieldUser.TMS+" text not null)";
    //Table T_MOV_FIELD_USER drop table query definition
    private static final String TMOVFIELDUSER_TABLE_DROP="DROP TABLE IF EXISTS " + TMOVFieldUser.TB_NAME;


    @Override
    public int createTCODCurrency(SQLiteDatabase db) {
        try{
            db.execSQL(TCODCURRENCY_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTCODCurrency(SQLiteDatabase db){
        try{
            db.execSQL(TCODCURRENCY_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTCODCurrency(SQLiteDatabase db) {    }

    @Override
    public int createTTABMovType(SQLiteDatabase db){
        try{
            db.execSQL(TTABMOVTYPE_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTTABMovType(SQLiteDatabase db){
        try{
            db.execSQL(TTABMOVTYPE_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTTABMovType(SQLiteDatabase db) {  }

    @Override
    public int createTTABFlowType(SQLiteDatabase db){
        try{
            db.execSQL(TTABFLOWTYPE_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTTABFlowType(SQLiteDatabase db){
        try{
            db.execSQL(TTABFLOWTYPE_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTTABFlowType(SQLiteDatabase db) {    }

    @Override
    public int createTTABBdg(SQLiteDatabase db){
        try{
            db.execSQL(TTABBDG_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTTABBdg(SQLiteDatabase db){
        try{
            db.execSQL(TTABBDG_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTTABBdg(SQLiteDatabase db) {    }

    @Override
    public int createTTABCategory(SQLiteDatabase db){
        try{
            db.execSQL(TTABCATEGORY_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTTABCategory(SQLiteDatabase db){
        try{
            db.execSQL(TTABCATEGORY_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTTABCategory(SQLiteDatabase db) {    }

    @Override
    public int createTTABField(SQLiteDatabase db){
        try{
            db.execSQL(TTABFIELD_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }
    @Override
    public int dropTTABField(SQLiteDatabase db){
        try{
            db.execSQL(TTABFIELD_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTTABField(SQLiteDatabase db) {    }

    @Override
    public int createTTABUser(SQLiteDatabase db){
        try{
            db.execSQL(TTABUSER_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTTABUser(SQLiteDatabase db){
        try{
            db.execSQL(TTABUSER_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTTABUser(SQLiteDatabase db) {    }

    @Override
    public int createTMOVItems(SQLiteDatabase db){
        try{
            db.execSQL(TMOVITEMS_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVItems(SQLiteDatabase db){
        try{
            db.execSQL(TMOVITEMS_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVItems(SQLiteDatabase db) {   }

    @Override
    public int createTMOVCatBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVCATBDG_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVCatBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVCATBDG_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVCatBdg(SQLiteDatabase db) {   }

    @Override
    public int createTMOVCatUser(SQLiteDatabase db){
        try{
            db.execSQL(TMOVCATUSER_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVCatUser(SQLiteDatabase db){
        try{
            db.execSQL(TMOVCATUSER_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVCatUser(SQLiteDatabase db) {    }

    @Override
    public int createTMOVFieldBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVFIELDBDG_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVFieldBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVFIELDBDG_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVFieldBdg(SQLiteDatabase db) {    }

    @Override
    public int createTMOVFieldUser(SQLiteDatabase db){
        try{
            db.execSQL(TMOVFIELDUSER_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVFieldUser(SQLiteDatabase db){
        try{
            db.execSQL(TMOVFIELDUSER_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVFieldUser(SQLiteDatabase db) {    }

    @Override
    public int createTMOVFlowTypeBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVFLOWTYPEBDG_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVFlowTypeBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVFLOWTYPEBDG_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVFlowTypeBdg(SQLiteDatabase db) {    }

    @Override
    public int createTMOVFlowTypeUser(SQLiteDatabase db){
        try{
            db.execSQL(TMOVFLOWTYPEUSER_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVFlowTypeUser(SQLiteDatabase db){
        try{
            db.execSQL(TMOVFLOWTYPEUSER_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVFlowTypeUser(SQLiteDatabase db) {    }

    @Override
    public int createTMOVItemsBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVITEMSBDG_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVItemsBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVITEMSBDG_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVItemsBdg(SQLiteDatabase db) {    }

    @Override
    public int createTMOVItemsCategories(SQLiteDatabase db){
        try{
            db.execSQL(TMOVITEMSCATEGORIES_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVItemsCategories(SQLiteDatabase db){
        try{
            db.execSQL(TMOVITEMSCATEGORIES_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVItemsCategories(SQLiteDatabase db) {    }

    @Override
    public int createTMOVItemsFields(SQLiteDatabase db){
        try{
            db.execSQL(TMOVITEMSFIELDS_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVItemsFields(SQLiteDatabase db){
        try{
            db.execSQL(TMOVITEMSFIELDS_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVItemsFields(SQLiteDatabase db) {    }

    @Override
    public int createTMOVItemsUser(SQLiteDatabase db){
        try{
            db.execSQL(TMOVITEMSUSER_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVItemsUser(SQLiteDatabase db){
        try{
            db.execSQL(TMOVITEMSUSER_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVItemsUser(SQLiteDatabase db) {    }

    @Override
    public int createTMOVMovTypeBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVMOVTYPEBDG_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVMovTypeBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVMOVTYPEBDG_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVMovTypeBdg(SQLiteDatabase db) {    }

    @Override
    public int createTMOVMovTypeUser(SQLiteDatabase db){
        try{
            db.execSQL(TMOVMOVTYPEUSER_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVMovTypeUser(SQLiteDatabase db){
        try{
            db.execSQL(TMOVMOVTYPEUSER_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVMovTypeUser(SQLiteDatabase db) {    }

    @Override
    public int createTMOVUserBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVUSERBDG_TABLE_CREATE);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int dropTMOVUserBdg(SQLiteDatabase db){
        try{
            db.execSQL(TMOVUSERBDG_TABLE_DROP);
        }
        catch (SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public void onResetTMOVUserBdg(SQLiteDatabase db) {    }


}
