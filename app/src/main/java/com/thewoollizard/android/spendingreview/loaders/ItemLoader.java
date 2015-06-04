package com.thewoollizard.android.spendingreview.loaders;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;

import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TCODCurrency;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TMOVItems;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TTABCategory;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TTABFlowType;
import com.thewoollizard.android.spendingreview.lib.database.DBStructure.TTABMovType;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Currency;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.FlowType;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Item;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.MovType;
import com.thewoollizard.android.spendingreview.lib.utilities.GlobalConst;
import com.thewoollizard.android.spendingreview.objects.MovLineItem;
import com.thewoollizard.android.spendingreview.lib.observers.ContentWatch;
import com.thewoollizard.android.spendingreview.lib.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @BrontoMania on 03/10/2014.
 */
public class ItemLoader extends AsyncTaskLoader<ArrayList<MovLineItem>> {

    Uri mUri;
    String[] mProjection, mSelectionArgs;
    String mSelection, mSort;

    ArrayList<MovLineItem> mData;
    ContentWatch myWatch;

    public ItemLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context);
        this.mUri=uri;
        this.mProjection=projection;
        this.mSelection=selection;
        this.mSelectionArgs=selectionArgs;
        this.mSort=sortOrder;
    }

    @Override
    public ArrayList<MovLineItem> loadInBackground() {
        ArrayList<MovLineItem> data=new ArrayList<MovLineItem>();
        Cursor c=getContext().getContentResolver().query(mUri, mProjection, mSelection, mSelectionArgs, mSort);

        if(c.getCount()>0){
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++){
                int j=c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDCURRENCY));
                Currency mCurrency=this.selectCurrency(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDCURRENCY)));
                MovType mMovType=this.selectMovType(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDMOVTYPE)));
                FlowType mFlowType=this.selectFlowType(c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDFLOWTYPE)));
                Item item=new Item(TMOVItems.TB_NAME, c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDITEM)), mMovType, mFlowType, c.getString(c.getColumnIndexOrThrow(TMOVItems.DESITEM)), c.getString(c.getColumnIndexOrThrow(TMOVItems.DTITEM)), c.getString(c.getColumnIndexOrThrow(TMOVItems.TMITEM)), c.getInt(c.getColumnIndexOrThrow(TMOVItems.IDITEMSTATUS)), mCurrency, Utilities.amountDB2Item(c.getInt(c.getColumnIndexOrThrow(TMOVItems.AMOUNT))), null, null);
                Cursor cCategories=getContext().getContentResolver().query(GlobalConst.URI_T_MOV_ITEMS_CATEGORIES, GlobalConst.SQL_SELECT_ITEMS_CATEGORIES, GlobalConst.SQL_WHERE_ITEMS_CATEGORIES, new String[]{Integer.toString(item.getId())}, null);
                cCategories.moveToFirst();
                MovLineItem mLineItem=new MovLineItem(cCategories.getString(cCategories.getColumnIndexOrThrow(TTABCategory.DESCATEGORY)), item.getDesItem(), item.getAmount());
                data.add(mLineItem);
                c.moveToNext();
                cCategories.close();
            }
            return data;
        }

        return null;
    }

    @Override
    public void deliverResult(ArrayList<MovLineItem> data){
        if(isReset()){
            releaseResources(data);
            return;
        }

        ArrayList<MovLineItem> oldData=mData;
        mData=data;

        if(isStarted()) super.deliverResult(data);

        if(oldData!=null && oldData!=data) releaseResources(oldData);

    }

    @Override
    protected void onStartLoading(){
        if(mData!=null) deliverResult(mData);
        if(myWatch==null){
            myWatch=new ContentWatch(null);
            getContext().getContentResolver().registerContentObserver(mUri, true, myWatch);
        }

        if(takeContentChanged() || mData==null) forceLoad();
    }

    @Override
    protected void onStopLoading(){
        cancelLoad();
    }

    @Override
    protected void onReset(){
        onStopLoading();
        if(mData!=null){
            releaseResources(mData);
            mData=null;
        }

        if(myWatch!=null){
            getContext().getContentResolver().unregisterContentObserver(myWatch);
            myWatch=null;
        }
    }

    @Override
    public void onCanceled(ArrayList<MovLineItem> data){
        super.onCanceled(data);
        releaseResources(data);
    }

    private void releaseResources(List<MovLineItem> data){
        //if data was a Cursor I must to close it here
    }

    private Currency selectCurrency(int mIdCurrency){
        if(mIdCurrency==0) return new Currency(TCODCurrency.TB_NAME, 0, "", "", 0);
        try{
            String[] selectionArgs=new String[]{Integer.toString(mIdCurrency)};
            Cursor c=getContext().getContentResolver().query(GlobalConst.URI_T_COD_CURRENCY, null, TCODCurrency.IDCURRENCY+"=?", selectionArgs, null);
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

    private MovType selectMovType(int mIdMovType){
        try{
            String[] selectionArgs=new String[]{Integer.toString(mIdMovType)};
            Cursor c=getContext().getContentResolver().query(GlobalConst.URI_T_TAB_MOVTYPE, null, TTABMovType.IDMOVTYPE+"=?", selectionArgs, null);
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

    private FlowType selectFlowType(int mIdFlowType){
        try{
            String[] selectionArgs=new String[]{Integer.toString(mIdFlowType)};
            Cursor c=getContext().getContentResolver().query(GlobalConst.URI_T_TAB_FLOWTYPE, null, TTABFlowType.IDFLOWTYPE+"=?", selectionArgs, null);
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

}
