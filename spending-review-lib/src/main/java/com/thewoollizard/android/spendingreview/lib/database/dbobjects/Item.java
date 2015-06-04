package com.thewoollizard.android.spendingreview.lib.database.dbobjects;

import com.thewoollizard.android.spendingreview.lib.utilities.DateTimeObj;

import java.util.ArrayList;

/**
 * Created by @BrontoMania on 12/09/2014.
 */
public class Item {

    //TODO: Inserire il DateTimeObj nell'Item
    String mTbName;
    int mId;
    int mStatus;
    MovType mMovType;
    FlowType mFlowType;
    String mDesItem;
    Currency mCurrency;
    String mDTItem;
    String mTMItem;
    ArrayList<Field> mFields;
    ArrayList<Category> mCategories;
    Double mAmount;
    String mCurrencyAmount;

    public Item(String tbName, int id, MovType movType, FlowType flowType, String desItem, String dtItem, String tmItem
            , int status, Currency currency, Double amount, ArrayList<Field> fields, ArrayList<Category> categories){
        setTBName(tbName);
        setId(id);
        setMovtype(movType);
        setFlowType(flowType);
        setDesItem(desItem);
        setDTItem(dtItem);
        setTMItem(tmItem);
        setStatus(status);
        setCurrency(currency);
        setAmount(amount);
        setCurrencyAmount(amount);
        setFields(fields);
        setCategories(categories);
    }

    public void setId(int id){mId=id;}
    public void setMovtype(MovType movType){mMovType=movType;}
    public void setFlowType(FlowType flowType){mFlowType=flowType;}
    public void setDesItem(String desItem){mDesItem=desItem;}
    public void setDTItem(String dtItem){mDTItem=dtItem;}
    public void setTMItem(String tmItem){mTMItem=tmItem;}
    public void setStatus(int status){mStatus=status;}
    public void setTBName(String tbName){mTbName=tbName;}
    public void setCurrency(Currency currency){mCurrency=currency;}
    public void setAmount(double amount){mAmount=amount;}
    public void setCurrencyAmount(double amount){
        mCurrencyAmount="";
        mCurrencyAmount=mCurrencyAmount+mCurrency.getCurrency()+" ";
        mCurrencyAmount=mCurrencyAmount+String.format("%.2f", amount).replace(".",",");
    }

    public void setFields(ArrayList<Field> fields){mFields=fields;}
    public void setCategories(ArrayList<Category> categories){mCategories=categories;}

    public int getId(){return mId;}
    public int getStatus(){return mStatus;}
    public String getTBName(){return mTbName;}
    public Currency getCurrency(){return mCurrency;}
    public MovType getMovType(){return mMovType;}
    public FlowType getFlowType(){return mFlowType;}
    public String getDesItem(){return mDesItem;}
    public String getDTItem(){return mDTItem;}
    public String getTMItem(){return mTMItem;}
    public double getAmount(){
        return mAmount;
    }
    public String getCurrencyAmount(){
        return mCurrencyAmount;
    }

    public ArrayList<Category> getCategories(){return mCategories;}
    public ArrayList<Field> getFields(){return mFields;}

    public Category getCategory(int idCategory){
        if(idCategory<mCategories.size()) return mCategories.get(idCategory);
        return mCategories.get(0);
    }

    public Field getField(int idField){
        if(idField<mFields.size()) return mFields.get(idField);
        return mFields.get(0);
    }

}
