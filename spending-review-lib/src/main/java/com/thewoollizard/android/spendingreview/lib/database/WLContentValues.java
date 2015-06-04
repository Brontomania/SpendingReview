package com.thewoollizard.android.spendingreview.lib.database;

import android.content.ContentValues;
import android.content.Entity;
import android.net.Uri;

import com.thewoollizard.android.spendingreview.lib.settings.Settings;
import com.thewoollizard.android.spendingreview.lib.utilities.DateTimeObj;
import com.thewoollizard.android.spendingreview.lib.utilities.Utilities;
import com.thewoollizard.android.spendingreview.lib.utilities.GlobalConst;

/**
 * Created by @BrontoMania on 26/09/2014.
 * Replace old CustomCV class. It's a custom Content Value
 */
public class WLContentValues {

    ContentValues mCV;
    Uri mUri;
    DateTimeObj dtObj;

    public WLContentValues(Uri uri){
        mCV=new ContentValues();
        mUri=uri;
        Entity.NamedContentValues namedCV=new Entity.NamedContentValues(uri, mCV);
    }

    public ContentValues getWLContentValue(){
        return mCV;
    }

    public void setWLContentValues(int itemStatus, int idCurrency, int idMovType, int idFlowType, String desItem, float amount, int idCategory, int idItem, Settings settings){

        dtObj=new DateTimeObj();

        switch (GlobalConst.mUriMatcher.match(mUri)){
            case GlobalConst.ITEM:
                mCV.put(DBStructure.TMOVItems.IDITEMSTATUS, itemStatus);
                mCV.put(DBStructure.TMOVItems.IDCURRENCY, idCurrency);
                mCV.put(DBStructure.TMOVItems.IDMOVTYPE, idMovType);
                mCV.put(DBStructure.TMOVItems.IDFLOWTYPE, idFlowType);
                mCV.put(DBStructure.TMOVItems.DESITEM, desItem);
                mCV.put(DBStructure.TMOVItems.DTITEM, dtObj.getDate(settings));
                mCV.put(DBStructure.TMOVItems.AMOUNT, amount);
                mCV.put(DBStructure.TMOVItems.TMS, dtObj.getDateTime(settings));
                break;
            case GlobalConst.ITEM_CATEGORIES:
                mCV.put(DBStructure.TMOVItemsCategories.IDCATEGORY, idCategory);
                mCV.put(DBStructure.TMOVItemsCategories.IDITEM, idItem);
                mCV.put(DBStructure.TMOVItemsCategories.TMS, dtObj.getDateTime(settings));
                break;
            default:
                break;
        }
    }

}
