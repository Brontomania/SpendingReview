package com.thewoollizard.android.spendingreview.lib.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.thewoollizard.android.spendingreview.lib.database.DBStructure;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TCODCurrency;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TMOVItems;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TTABFlowType;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TTABMovType;
import com.thewoollizard.android.spendingreview.lib.utilities.Utilities;
import com.thewoollizard.android.spendingreview.lib.utilities.GlobalConst;


/**
 * Created by @BrontoMania on 29/09/2014.
 */

public class SPReviewContentProvider extends ContentProvider {

    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        db=(new DBStructure(getContext(), GlobalConst.DBNAME, null, GlobalConst.DBVERSION)).getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        String tableName;
        switch (GlobalConst.mUriMatcher.match(uri)){
            case GlobalConst.ITEM:
                tableName= TMOVItems.TB_NAME;
                cursor = db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case GlobalConst.ITEM_CATEGORIES:
                SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
                qb.setTables(GlobalConst.SQL_FROM_ITEMS_CATEGORIES);
                cursor=qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case GlobalConst.MOVTYPE:
                tableName= TTABMovType.TB_NAME;
                cursor=db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case GlobalConst.FLOWTYPE:
                tableName= TTABFlowType.TB_NAME;
                cursor=db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case GlobalConst.CURRENCY:
                tableName= TCODCurrency.TB_NAME;
                cursor=db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                cursor=null;
                break;
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (GlobalConst.mUriMatcher.match(uri)) {
            case GlobalConst.ITEM:
                return GlobalConst.ITEM_CONTENT_TYPE;
            case GlobalConst.ITEM_CATEGORIES:
                return GlobalConst.ITEM_CATEGORIES_CONTENT_TYPE;
            case GlobalConst.CURRENCY:
                return GlobalConst.CURRENCY_CONTENT_TYPE;
            case GlobalConst.MOVTYPE:
                return GlobalConst.MOVTYPE_CONTENT_TYPE;
            case GlobalConst.FLOWTYPE:
                return GlobalConst.FLOWTYPE_CONTENT_TYPE;
            case GlobalConst.FIELD:
                return GlobalConst.FIELD_CONTENT_TYPE;
            case GlobalConst.CATEGORY:
                return GlobalConst.CATEGORY_CONTENT_TYPE;
            default:
                return null;
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues cv) {
        long id=0;
        switch (GlobalConst.mUriMatcher.match(uri)){
            case GlobalConst.ITEM:
                id=db.insert(TMOVItems.TB_NAME, null, cv);
                break;
            case GlobalConst.ITEM_CATEGORIES:
                id=db.insert(DBStructure.TMOVItemsCategories.TB_NAME, null, cv);
                break;
            default:
                return null;
        }
        return Uri.parse(uri + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db.delete(TMOVItems.TB_NAME, TMOVItems.IDITEM+"<?", new String[]{"3"});
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }


}
