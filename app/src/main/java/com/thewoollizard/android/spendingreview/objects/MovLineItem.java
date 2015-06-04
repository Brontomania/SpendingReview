package com.thewoollizard.android.spendingreview.objects;

import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Category;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Item;


/**
 * Created by @BrontoMania on 26/09/2014.
 */
public class MovLineItem {

    String mMovCategory, mMovDetail, mMovType, mMovFlowType;
    Double mMovValue;
    Item mItem;

    public MovLineItem(String category, String detail, Double value){
        this.setMovCategory(category);
        this.setMovDetail(detail);
        this.setMovValue(value);
    }

    public MovLineItem(Item item){
        this.mItem=item;
//        this.setMovCategory(category);
//        this.setMovDetail(detail);
//        this.setMovValue(value);
    }

    public Category getMovItemCategory(int mIdCategory){
        return mItem.getCategory(0);
    }

    public String getMovItemDetail(){
        return mItem.getDesItem();
    }

    public double getMovItemValue(){
        return  mItem.getAmount();

    }

    public String getMovCategory(){
        return this.mMovCategory;
    }

    public String getMovDetail(){
        return this.mMovDetail;
    }

    public Double getMovValue(){
        return this.mMovValue;
    }

    public void setMovCategory(String mCategory){
        this.mMovCategory=mCategory;
    }

    public void setMovDetail(String mDetail){
        this.mMovDetail=mDetail;
    }

    public void setMovValue(Double mValue){
        this.mMovValue=mValue;

    }

}
