package com.thewoollizard.android.spendingreview.dbmng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.thewoollizard.android.spendingreview.lib.database.DBStructure;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TCODCurrency;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TMOVItems;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TMOVItemsCategories;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TMOVItemsFields;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TTABCategory;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TTABField;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TTABFlowType;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TTABMovType;
import com.thewoollizard.android.spendingreview.lib.database.iface.IDBDAO;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Category;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Currency;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Field;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.FlowType;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Item;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.MovType;
import com.thewoollizard.android.spendingreview.lib.settings.Settings;
import com.thewoollizard.android.spendingreview.lib.utilities.DateTimeObj;
import com.thewoollizard.android.spendingreview.lib.utilities.DateTimeRes;
import com.thewoollizard.android.spendingreview.lib.utilities.GlobalConst;
import com.thewoollizard.android.spendingreview.lib.utilities.Utilities;

import java.util.ArrayList;

/**
 * Created by @BrontoMania on 26/09/2014.
 * Define the Data Access Object with database access methods (select, update, delete ...)
 */

public class DBDAO implements IDBDAO {

    public SQLiteDatabase mDb; //Database
    public DBStructure mDbStructure; //Database Structure
    Context mCtx;
    Settings settings;

    private static final int DB_VERSION=1; //Database version number

    public DBDAO(Context ctx, String dbName, Settings settings){
        mCtx=ctx;
        mDbStructure =new DBStructure(mCtx, dbName, null, DB_VERSION);
        this.openDB();
        this.settings=settings;
    }

    //to open the database
    public void openDB(){ mDb = mDbStructure.getWritableDatabase();
    }

    //to close the database
    public void closeDB(){
        mDb.close();
    }

    public String getDBPath(){
        return mDb.getPath();
    }

    //to delete the database dbName
    public void deleteDB(Context ctx, String dbName){
        mCtx=ctx;
        mCtx.deleteDatabase(dbName);

    }

    //Data Tables Methods
    //T_COD_CURRENCY
    //Select Currency: select the currency with Id mIdCurrency
    public Currency selectCurrency(int idCurrency){
        if(idCurrency==0) return new Currency(TCODCurrency.TB_NAME, 0, "", "", 0);
        try{
            Cursor c= mDb.query(TCODCurrency.TB_NAME, null, TCODCurrency.IDCURRENCY+"=?", new String[]{Integer.toString(idCurrency)}, null, null, null);
            if(c.getCount()>0){
                c.moveToFirst();
                Currency currency=new Currency(TCODCurrency.TB_NAME
                        , c.getInt(c.getColumnIndexOrThrow(TCODCurrency.IDCURRENCY))
                        , c.getString(c.getColumnIndexOrThrow(TCODCurrency.DESCURRENCY))
                        , c.getString(c.getColumnIndexOrThrow(TCODCurrency.DESCURRENCYSYMBOL))
                        , c.getInt(c.getColumnIndexOrThrow(TCODCurrency.IDCURRENCYSTATUS)));
                c.close();
                return currency;
            }
        }
        catch(Exception E){
            return new Currency(TCODCurrency.TB_NAME, 0, E.getMessage(), "", 0);
        }
        return new Currency(TCODCurrency.TB_NAME, 0, "", "", 0);
    }

    //Insert Currency: insert only one row from Currency
    public String insertCurrency(Currency currency){
        DateTimeObj dtObj=new DateTimeObj();
        ContentValues cv=new ContentValues();
        cv.put(TCODCurrency.DESCURRENCY, currency.getCurrency());
        cv.put(TCODCurrency.DESCURRENCYSYMBOL, currency.getCurrencySymbol());
        cv.put(TCODCurrency.IDCURRENCYSTATUS, currency.getStatus());
        cv.put(TCODCurrency.TMS, dtObj.getDateTime(settings));
        try{
            mDb.insert(TCODCurrency.TB_NAME, null, cv);
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        return "";
    }

    //Insert Currencies: insert multiple currencies. DB Transaction is used for better insert perfomance
    public String insertCurrencies(Currency[] currencies){
        DateTimeObj dtObj=new DateTimeObj();
        try{
            mDb.beginTransaction();
            SQLiteStatement sqlStmt = mDb.compileStatement(TCODCurrency.TCODCURRENCY_TABLE_INSERT);
            for (int i = 0; i < currencies.length; i++) {
                sqlStmt.bindString(1, currencies[i].getCurrency());
                sqlStmt.bindString(2, currencies[i].getCurrencySymbol());
                sqlStmt.bindString(3, Integer.toString(currencies[i].getStatus()));
                sqlStmt.bindString(4, dtObj.getDateTime(settings));
                sqlStmt.execute();
                sqlStmt.clearBindings();
            }
            mDb.setTransactionSuccessful();
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        finally{
            mDb.endTransaction();
        }
        return "";
    }

    @Override
    public String insertCurrencies(ArrayList<Currency> currencies) {
        return null;
    }

    //Update Currency on _id
    public String updateCurrency(Currency currency){
        DateTimeObj dtObj=new DateTimeObj();
        ContentValues cv=new ContentValues();
        cv.put(TCODCurrency.DESCURRENCY, currency.getCurrency());
        cv.put(TCODCurrency.DESCURRENCYSYMBOL, currency.getCurrencySymbol());
        cv.put(TCODCurrency.IDCURRENCYSTATUS, currency.getStatus());
        cv.put(TCODCurrency.TMS, dtObj.getDateTime(settings));
        try{
            mDb.update(TCODCurrency.TB_NAME, cv, TCODCurrency.IDCURRENCY + "=?", new String[]{Integer.toString(currency.getId())});
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        return "";
    }

    //Insert Currencies: insert multiple currencies. DB Transaction is used for better insert performance
    public String updateCurrencies(int statusOld, int statusNew){
        DateTimeObj dtObj=new DateTimeObj();
        try{
            mDb.beginTransaction();
            SQLiteStatement sqlStmt = mDb.compileStatement(TCODCurrency.TCODCURRENCY_TABLE_UPDATE_WH_STATUS);
            sqlStmt.bindString(1, Integer.toString(statusNew));
            sqlStmt.bindString(2, dtObj.getDateTime(settings));
            sqlStmt.bindString(3, Integer.toString(statusOld));
            sqlStmt.execute();
            sqlStmt.clearBindings();
            mDb.setTransactionSuccessful();
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        finally{
            mDb.endTransaction();
        }
        return "";
    }

    //Delete Currency: delete on row on where condition --> switch status from ON to OFF
    public String deleteCurrency(Currency currency){
        return updateCurrency(currency);
    }

    //T_TAB_MOVTYPE
    //Select MovType: select the MovType with Id mIdMovType
    public MovType selectMovType(int idMovType){
        try{
            Cursor c= mDb.query(TTABMovType.TB_NAME, null, TTABMovType.IDMOVTYPE+"=?", new String[]{Integer.toString(idMovType)}, null, null, null);
            if(c.getCount()>0){
                c.moveToFirst();
                MovType movType=new MovType(TTABMovType.TB_NAME
                        , c.getInt(c.getColumnIndexOrThrow(TTABMovType.IDMOVTYPE))
                        , c.getString(c.getColumnIndexOrThrow(TTABMovType.DESMOVTYPE))
                        , c.getInt(c.getColumnIndexOrThrow(TTABMovType.IDMOVTYPEFATHER))
                        , c.getInt(c.getColumnIndexOrThrow(TTABMovType.IDMOVTYPESTATUS)));
                c.close();
                return movType;
            }
        }
        catch(Exception E){
            return new MovType(TTABMovType.TB_NAME, 0, E.getMessage(), 0, 0);
        }
        return new MovType(TTABMovType.TB_NAME, 0, "", 0, 0);
    }

    //Insert MovType: insert only one row from MovType
    public String insertMovType(MovType movType){
        DateTimeObj dtObj=new DateTimeObj();
        ContentValues cv=new ContentValues();
        cv.put(TTABMovType.IDMOVTYPESTATUS, movType.getStatus());
        cv.put(TTABMovType.IDMOVTYPEFATHER, movType.getFatherId());
        cv.put(TTABMovType.DESMOVTYPE, movType.getDescription());
        cv.put(TTABMovType.TMS, dtObj.getDateTime(settings));
        try{
            mDb.insert(TTABMovType.TB_NAME, null, cv);
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        return "";
    }

    //Insert MovType: insert multiple movTypes. DB Transaction is used for better insert performance
    public String insertMovTypes(MovType[] movTypes){
        DateTimeObj dtObj=new DateTimeObj();
        try{
            mDb.beginTransaction();
            SQLiteStatement sqlStmt = mDb.compileStatement(TTABMovType.TTABMOVTYPE_TABLE_INSERT);
            for (int i = 0; i < movTypes.length; i++) {
                sqlStmt.bindString(1, Integer.toString(movTypes[i].getStatus()));
                sqlStmt.bindString(2, Integer.toString(movTypes[i].getFatherId()));
                sqlStmt.bindString(3, movTypes[i].getDescription());
                sqlStmt.bindString(4, dtObj.getDateTime(settings));
                sqlStmt.execute();
                sqlStmt.clearBindings();
            }
            mDb.setTransactionSuccessful();
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        finally{
            mDb.endTransaction();
        }
        return "";
    }

    @Override
    public String insertMovTypes(ArrayList<MovType> movTypes) {
        return null;
    }

    //T_TAB_FLOWTYPE
    //Select FlowType: select the FlowType with Id mIdFlowType
    public FlowType selectFlowType(int idFlowType){
        try{
            Cursor c= mDb.query(TTABFlowType.TB_NAME, null, TTABFlowType.IDFLOWTYPE+"=?", new String[]{Integer.toString(idFlowType)}, null, null, null);
            if(c.getCount()>0){
                c.moveToFirst();
                FlowType flowType=new FlowType(TTABFlowType.TB_NAME
                        , c.getInt(c.getColumnIndexOrThrow(TTABFlowType.IDFLOWTYPE))
                        , c.getString(c.getColumnIndexOrThrow(TTABFlowType.DESFLOWTYPE))
                        , c.getInt(c.getColumnIndexOrThrow(TTABFlowType.IDFLOWTYPESTATUS)));
                c.close();
                return flowType;
            }
        }
        catch(Exception E){
            return new FlowType(TTABFlowType.TB_NAME, 0, E.getMessage(), 0);
        }
        return new FlowType(TTABFlowType.TB_NAME, 0, "", 0);
    }

    //Insert FlowType: insert only one row from FlowType
    public String insertFlowType(FlowType flowType){
        DateTimeObj dtObj=new DateTimeObj();
        ContentValues cv=new ContentValues();
        cv.put(TTABFlowType.IDFLOWTYPESTATUS, flowType.getStatus());
        cv.put(TTABFlowType.DESFLOWTYPE, flowType.getDescription());
        cv.put(TTABFlowType.TMS, dtObj.getDateTime(settings));
        try{
            mDb.insert(TTABFlowType.TB_NAME, null, cv);
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        return "";
    }

    //Insert FlowType: insert multiple flowTypes. DB Transaction is used for better insert performance
    public String insertFlowTypes(FlowType[] flowTypes){
        DateTimeObj dtObj=new DateTimeObj();
        try{
            mDb.beginTransaction();
            SQLiteStatement sqlStmt = mDb.compileStatement(TTABFlowType.TTABFLOWTYPE_TABLE_INSERT);
            for (int i = 0; i < flowTypes.length; i++) {
                sqlStmt.bindString(1, Integer.toString(flowTypes[i].getStatus()));
                sqlStmt.bindString(2, flowTypes[i].getDescription());
                sqlStmt.bindString(3, dtObj.getDateTime(settings));
                sqlStmt.execute();
                sqlStmt.clearBindings();
            }
            mDb.setTransactionSuccessful();
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        finally{
            mDb.endTransaction();
        }
        return "";
    }

    @Override
    public String insertFlowTypes(ArrayList<FlowType> flowTypes) {
        return null;
    }

    //T_TAB_FIELD
    //Select Fields: select the Field's ArrayList with IdItem mIdItem
    public ArrayList<Field> selectFields(int idItem){
        ArrayList<Field> mFields;
        if(idItem==0){
            mFields=new ArrayList<Field>();
            mFields.add(new Field(TTABField.TB_NAME, 0, "", 0, 0));
            return mFields;
        }

        try{
            Cursor c= mDb.query(TMOVItemsFields.TB_NAME, null, TMOVItemsFields.IDITEM+"=?", new String[]{Integer.toString(idItem)}, null, null, null);
            if(c.getCount()>0){
                //da generare una lista di Fields in uscita
                mFields=new ArrayList<Field>();
                for(int i=0;i<c.getCount();i++){
                    mFields.add(new Field(TTABField.TB_NAME
                            , c.getInt(c.getColumnIndexOrThrow(TTABField.IDFIELD))
                            , c.getString(c.getColumnIndexOrThrow(TTABField.DESFIELD))
                            , c.getInt(c.getColumnIndexOrThrow(TTABField.IDFIELDFATHER))
                            , c.getInt(c.getColumnIndexOrThrow(TTABField.IDFIELDSTATUS))));
                }
                c.close();
                return mFields;
            }
        }
        catch(Exception E){
            mFields=new ArrayList<Field>();
            mFields.add(new Field(TTABField.TB_NAME, 0, E.getMessage(), 0, 0));
            return mFields;
        }
        mFields=new ArrayList<Field>();
        mFields.add(new Field(TTABField.TB_NAME, 0, "", 0, 0));
        return mFields;
    }
    //Insert Field: insert only one row from Field
    public String insertField(Field field){
        DateTimeObj dtObj=new DateTimeObj();
        ContentValues cv=new ContentValues();
        cv.put(TTABField.IDFIELDSTATUS, field.getStatus());
        cv.put(TTABField.IDFIELDFATHER, field.getFatherId());
        cv.put(TTABField.DESFIELD, field.getDescription());
        cv.put(TTABField.TMS, dtObj.getDateTime(settings));
        try{
            mDb.insert(TTABField.TB_NAME, null, cv);
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        return "";
    }

    //Insert Field: insert multiple fields. DB Transaction is used for better insert performance
    public String insertFields(Field[] fields){
        DateTimeObj dtObj=new DateTimeObj();
        try{
            mDb.beginTransaction();
            SQLiteStatement sqlStmt = mDb.compileStatement(TTABField.TTABFIELD_TABLE_INSERT);
            for (int i = 0; i < fields.length; i++) {
                sqlStmt.bindString(1, Integer.toString(fields[i].getStatus()));
                sqlStmt.bindString(2, Integer.toString(fields[i].getFatherId()));
                sqlStmt.bindString(3, fields[i].getDescription());
                sqlStmt.bindString(4, dtObj.getDateTime(settings));
                sqlStmt.execute();
                sqlStmt.clearBindings();
            }
            mDb.setTransactionSuccessful();
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        finally{
            mDb.endTransaction();
        }
        return "";
    }

    @Override
    public String insertFields(ArrayList<Field> fields) {
        return null;
    }

    //T_TAB_CATEGORY
    //Select Categories: select the Category's ArrayList with IdItem mIdItem
    public ArrayList<Category> selectCategories(int idItem){
        ArrayList<Category> mCategories;
        if(idItem==0){
            mCategories=new ArrayList<>();
            mCategories.add(new Category(TTABCategory.TB_NAME, 0, "", 0, 0));
            return mCategories;
        }

        try{
            Cursor c= mDb.query(TMOVItemsCategories.TB_NAME, null, TMOVItemsCategories.IDITEM+"=?", new String[]{Integer.toString(idItem)}, null, null, null);
            if(c.getCount()>0){
                c.moveToFirst();
                mCategories=new ArrayList<>();
                for(int i=0;i<c.getCount();i++){
                    int idCategory=c.getInt(c.getColumnIndexOrThrow(TMOVItemsCategories.IDCATEGORY));
                    Cursor cCat= mDb.query(TTABCategory.TB_NAME, null, TTABCategory.IDCATEGORY+"=?", new String[]{Integer.toString(idCategory)}, null, null, null);
                    cCat.moveToFirst();
                    mCategories.add(new Category(TTABCategory.TB_NAME
                            , cCat.getInt(cCat.getColumnIndexOrThrow(TTABCategory.IDCATEGORY))
                            , cCat.getString(cCat.getColumnIndexOrThrow(TTABCategory.DESCATEGORY))
                            , cCat.getInt(cCat.getColumnIndexOrThrow(TTABCategory.IDCATEGORYFATHER))
                            , cCat.getInt(cCat.getColumnIndexOrThrow(TTABCategory.IDCATEGORYSTATUS))));
                    cCat.close();
                    c.moveToNext();
                }
                c.close();
                return mCategories;
            }
        }
        catch(Exception E){
            mCategories=new ArrayList<Category>();
            mCategories.add(new Category(TTABCategory.TB_NAME, 0, E.getMessage(), 0, 0));
            return mCategories;
        }
        mCategories=new ArrayList<Category>();
        mCategories.add(new Category(TTABCategory.TB_NAME, 0, "", 0, 0));
        return mCategories;
    }

    //Select Categorie: Estrae le Categorie per l'Item idItem
    @Override
    public ArrayList<CharSequence> selectCategorie(int idItem){
        ArrayList<Category> mCategories=this.selectCategories(idItem);
        ArrayList<CharSequence> mCategorie=new ArrayList<>();

        try {
            for (int i = 0; i < mCategories.size(); i++) {
                CharSequence categoria = mCategories.get(i).getDescription();
                mCategorie.add(categoria);
            }
            return mCategorie;
        }
        catch(Exception E){
            mCategorie.add("");
            return mCategorie;
        }

    }


    //Insert Category: insert only one row from Category
    public String insertCategory(Category category){
        DateTimeObj dtObj=new DateTimeObj();
        ContentValues cv=new ContentValues();
        cv.put(TTABCategory.IDCATEGORYSTATUS, category.getStatus());
        cv.put(TTABCategory.IDCATEGORYFATHER, category.getFatherId());
        cv.put(TTABCategory.DESCATEGORY, category.getDescription());
        cv.put(TTABCategory.TMS, dtObj.getDateTime(settings));
        try{
            mDb.insert(TTABCategory.TB_NAME, null, cv);
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        return "";
    }

    //Insert Category: insert multiple categories. DB Transaction is used for better insert performance
    public String insertCategories(Category[] categories){
        DateTimeObj dtObj=new DateTimeObj();
        try{
            mDb.beginTransaction();
            SQLiteStatement sqlStmt = mDb.compileStatement(TTABCategory.TTABCATEGORY_TABLE_INSERT);
            for (int i = 0; i < categories.length; i++) {
                sqlStmt.bindString(1, Integer.toString(categories[i].getStatus()));
                sqlStmt.bindString(2, Integer.toString(categories[i].getFatherId()));
                sqlStmt.bindString(3, categories[i].getDescription());
                sqlStmt.bindString(4, dtObj.getDateTime(settings));
                sqlStmt.execute();
                sqlStmt.clearBindings();
            }
            mDb.setTransactionSuccessful();
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        finally{
            mDb.endTransaction();
        }
        return "";
    }

    @Override
    public String insertCategories(ArrayList<Category> categories) {
        return null;
    }

    //T_MOV_ITEMS
    //Insert Item: insert only one row from Item
    public String insertItem(Item item){
        DateTimeObj dtObj=new DateTimeObj();
        ContentValues cv=new ContentValues();
        cv.put(TMOVItems.IDITEMSTATUS, item.getStatus());
        cv.put(TMOVItems.IDCURRENCY, item.getCurrency().getId());
        cv.put(TMOVItems.IDMOVTYPE, item.getMovType().getId());
        cv.put(TMOVItems.IDFLOWTYPE, item.getFlowType().getId());
        cv.put(TMOVItems.DESITEM, item.getDesItem());
        cv.put(TMOVItems.DTITEM, item.getDTItem());
        cv.put(TMOVItems.TMITEM, item.getTMItem());
        cv.put(TMOVItems.AMOUNT, Utilities.amountItem2DB(item.getAmount()));
        cv.put(TMOVItems.TMS, dtObj.getDateTime(settings));
        try{
            mDb.insert(TMOVItems.TB_NAME, null, cv);
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        return "";
    }

    //Insert Items: insert multiple items. DB Transaction is used for better insert performance
    public String insertItems(Item[] items){
        DateTimeObj dtObj=new DateTimeObj();
        try{
            mDb.beginTransaction();
            SQLiteStatement sqlStmt = mDb.compileStatement(TMOVItems.TMOVITEMS_TABLE_INSERT);
            for (int i = 0; i < items.length; i++) {
                sqlStmt.bindString(1, Integer.toString(items[i].getStatus()));
                sqlStmt.bindString(2, "1"); //Currency
                sqlStmt.bindString(3, Integer.toString(items[i].getMovType().getId()));
                sqlStmt.bindString(4, Integer.toString(items[i].getFlowType().getId()));
                sqlStmt.bindString(5, items[i].getDesItem());
                sqlStmt.bindString(6, items[i].getDTItem());
                sqlStmt.bindString(7, items[i].getTMItem());
                sqlStmt.bindString(8, Integer.toString(Utilities.amountItem2DB(items[i].getAmount())));
                sqlStmt.bindString(9, dtObj.getDateTime(settings));
                sqlStmt.execute();
                sqlStmt.clearBindings();
            }
            mDb.setTransactionSuccessful();
        }
        catch(SQLiteException E){
            Log.e(GlobalConst.LOGTAGS[0], E.getMessage());
            return E.getMessage();
        }
        finally{
            mDb.endTransaction();
        }
        return "";
    }

    @Override
    public String insertItems(ArrayList<Item> items) {
        return null;
    }

    public int selectMaxItemId(){
        Cursor c= mDb.rawQuery("select max(_id) from T_MOV_ITEMS", null);
        c.moveToFirst();
        int j=c.getInt(0);
        return j;
    }

    public Item selectItem(int itemId){
        try{
            Cursor c= mDb.query(TMOVItems.TB_NAME, null, TMOVItems.IDITEM+"=?", new String[]{Integer.toString(itemId)}, null, null, null);
            if(c.getCount()>0){
                c.moveToFirst();
                Item item= new Item(TMOVItems.TB_NAME
                        , c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDITEM))
                        , this.selectMovType(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDMOVTYPE)))
                        , this.selectFlowType(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDFLOWTYPE)))
                        , c.getString(c.getColumnIndexOrThrow(TMOVItems.DESITEM))
                        , c.getString(c.getColumnIndexOrThrow(TMOVItems.DTITEM))
                        , c.getString(c.getColumnIndexOrThrow(TMOVItems.TMITEM))
                        , c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDITEMSTATUS))
                        , this.selectCurrency(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDCURRENCY)))
                        , Utilities.amountDB2Item(c.getInt(c.getColumnIndexOrThrow(TMOVItems.AMOUNT)))
                        , this.selectFields(itemId)
                        , this.selectCategories(itemId));
                c.close();
                return item;
            }
        }
        catch(Exception E){
            return new Item(TMOVItems.TB_NAME
                    , 0
                    , this.selectMovType(0)
                    , this.selectFlowType(0)
                    , E.getMessage()
                    , ""
                    , ""
                    , 0
                    , this.selectCurrency(0)
                    , 0.00
                    , this.selectFields(0)
                    , this.selectCategories(0));
        }
        return new Item(TMOVItems.TB_NAME
                , 0
                , this.selectMovType(0)
                , this.selectFlowType(0)
                , ""
                , ""
                , ""
                , 0
                , this.selectCurrency(0)
                , 0.00
                , this.selectFields(0)
                , this.selectCategories(0));
    }


    public ArrayList<Item> selectItems(int filter){
        ArrayList<Item> items=new ArrayList<Item>();
        try{
            Cursor c;
            if(filter==0) c= mDb.query(TMOVItems.TB_NAME, null, null, null, null, null, null);
            else c= mDb.query(TMOVItems.TB_NAME, null, TMOVItems.IDMOVTYPE+"=?", new String[]{Integer.toString(filter)}, null, null, null);
            if(c.getCount()>0){
                c.moveToFirst();
                for(int i=0;i<c.getCount();i++){
                    Item item=new Item(TMOVItems.TB_NAME
                            , c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDITEM))
                            , this.selectMovType(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDMOVTYPE)))
                            , this.selectFlowType(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDFLOWTYPE)))
                            , c.getString(c.getColumnIndexOrThrow(TMOVItems.DESITEM))
                            , c.getString(c.getColumnIndexOrThrow(TMOVItems.DTITEM))
                            , c.getString(c.getColumnIndexOrThrow(TMOVItems.TMITEM))
                            , c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDITEMSTATUS))
                            , this.selectCurrency(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDCURRENCY)))
                            , Utilities.amountDB2Item(c.getInt(c.getColumnIndexOrThrow(TMOVItems.AMOUNT)))
                            , this.selectFields(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDITEM)))
                            , this.selectCategories(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDITEM)))
                    );
                    items.add(item);
                    c.moveToNext();
                }
                return items;
            }
        }
        catch(Exception E){
            Item item= new Item(TMOVItems.TB_NAME
                    , 0
                    , this.selectMovType(0)
                    , this.selectFlowType(0)
                    , E.getMessage()
                    , ""
                    , ""
                    , 0
                    , this.selectCurrency(0)
                    , 0.00
                    , this.selectFields(0)
                    , this.selectCategories(0));
            items.add(item);
            return items;
        }
        Item item=new Item(TMOVItems.TB_NAME
                , 0
                , this.selectMovType(0)
                , this.selectFlowType(0)
                , ""
                , ""
                , ""
                , 0
                , this.selectCurrency(0)
                , 0.00
                , this.selectFields(0)
                , this.selectCategories(0));
        items.add(item);
        return items;
    }

    public int countItems(int filter){
        int i=0;
        try{
            Cursor c;
            if(filter==0) c= mDb.query(TMOVItems.TB_NAME, null, null, null, null, null, null);
            else c= mDb.query(TMOVItems.TB_NAME, null, TMOVItems.IDMOVTYPE+"=?", new String[]{Integer.toString(filter)}, null, null, null);
            i=c.getCount();
        }
        catch(Exception E){
            i=0;
        }

        return i;
    }

    //T_MOV_ITEMS_CATEGORIES
    //Insert ItemCategories: insert only one row from ItemCategories
    public String insertItemCategory(Item item, Category category){
        DateTimeObj dtObj=new DateTimeObj();
        ContentValues cv=new ContentValues();
        cv.put(TMOVItemsCategories.IDCATEGORY, category.getId());
        cv.put(TMOVItemsCategories.IDITEM, item.getId());
        cv.put(TMOVItemsCategories.TMS, dtObj.getDateTime(settings));
        try{
            mDb.insert(TMOVItemsCategories.TB_NAME, null, cv);
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        return "";
    }

    //Insert ItemsCategories: insert multiple itemsCategory. DB Transaction is used for better insert performance
    public String insertItemsCategories(Item item, Category[] categories){
        DateTimeObj dtObj=new DateTimeObj();
        try{
            mDb.beginTransaction();
            SQLiteStatement sqlStmt = mDb.compileStatement(TMOVItemsCategories.TMOVITEMSCATEGORIES_TABLE_INSERT);
            for (int i = 0; i < categories.length; i++) {
                sqlStmt.bindString(1, Integer.toString(categories[i].getId()));
                sqlStmt.bindString(2, Integer.toString(item.getId()));
                sqlStmt.bindString(3, dtObj.getDateTime(settings));
                sqlStmt.execute();
                sqlStmt.clearBindings();
            }
            mDb.setTransactionSuccessful();
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        finally{
            mDb.endTransaction();
        }
        return "";
    }

    @Override
    public String insertItemsCategories(Item item, ArrayList<Category> categories) {
        return null;
    }

    //T_MOV_ITEMS_FIELDS
    //Insert ItemFields: insert only one row from ItemFields
    public String insertItemField(Item item, Field field){
        DateTimeObj dtObj=new DateTimeObj();
        ContentValues cv=new ContentValues();
        cv.put(TMOVItemsFields.IDFIELD, field.getId());
        cv.put(TMOVItemsFields.IDITEM, item.getId());
        cv.put(TMOVItemsFields.TMS, dtObj.getDateTime(settings));
        try{
            mDb.insert(TMOVItemsFields.TB_NAME, null, cv);
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        return "";
    }

    //Insert ItemsFields: insert multiple itemsField. DB Transaction is used for better insert performance
    public String insertItemsFields(Item item, Field[] fields){
        DateTimeObj dtObj=new DateTimeObj();
        try{
            mDb.beginTransaction();
            SQLiteStatement sqlStmt = mDb.compileStatement(TMOVItemsFields.TMOVITEMSFIELDS_TABLE_INSERT);
            for (int i = 0; i < fields.length; i++) {
                sqlStmt.bindString(1, Integer.toString(fields[i].getId()));
                sqlStmt.bindString(2, Integer.toString(item.getId()));
                sqlStmt.bindString(3, dtObj.getDateTime(settings));
                sqlStmt.execute();
                sqlStmt.clearBindings();
            }
            mDb.setTransactionSuccessful();
        }
        catch(SQLiteException E){
            return E.getMessage();
        }
        finally{
            mDb.endTransaction();
        }
        return "";
    }

    @Override
    public String insertItemsFields(Item item, ArrayList<Field> fields) {
        return null;
    }

}
