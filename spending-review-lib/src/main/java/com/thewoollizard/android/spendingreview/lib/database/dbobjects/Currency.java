package com.thewoollizard.android.spendingreview.lib.database.dbobjects;

/**
 * Created by @BrontoMania on 05/09/2014.
 */
public class Currency {

    String mTbName;
    int mId;
    int mStatus;
    String mCurrency;
    String mCurrencySymbol;

    public Currency(String tbName, int id, String currency, String currencySymbol, int status){
        setTBName(tbName);
        setId(id);
        setCurrency(currency);
        setCurrencySymbol(currencySymbol);
        setStatus(status);
    }

    public void setId(int id){mId=id;}
    public void setStatus(int status){mStatus=status;}
    public void setTBName(String tbName){mTbName=tbName;}
    public void setCurrency(String currency){mCurrency=currency;}
    public void setCurrencySymbol(String currencySymbol){mCurrencySymbol=currencySymbol;}

    public int getId(){return mId;}
    public int getStatus(){return mStatus;}
    public String getTBName(){return mTbName;}
    public String getCurrency(){return mCurrency;}
    public String getCurrencySymbol(){return mCurrencySymbol;}

}
