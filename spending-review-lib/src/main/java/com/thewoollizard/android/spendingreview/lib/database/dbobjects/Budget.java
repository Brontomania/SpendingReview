package com.thewoollizard.android.spendingreview.lib.database.dbobjects;

/**
 * Created by @BrontoMania on 05/09/2014.
 */
public class Budget {

    String mTbName;
    int mId;
    int mStatus;
    String mType;
    String mName;
    String mDescription;
    String mDt1;
    String mDt2;
    int mAmount;

    public Budget(String tbName, int id, String type, String name, String description, String dt1, String dt2, int amount){
        setId(id);
        setTBName(tbName);
        setType(type);
        setName(name);
        setDescription(description);
        setDt1(dt1);
        setDt2(dt2);
        setAmount(amount);
    }

    public void setId(int id){mId=id;}
    public void setStatus(int status){mStatus=status;}
    public void setTBName(String tbName){mTbName=tbName;}
    public void setType(String type){mType=type;}
    public void setName(String name){mName=name;}
    public void setDescription(String description){mDescription=description;}
    public void setDt1(String dt1){mDt1=dt1;}
    public void setDt2(String dt2){mDt2=dt2;}
    public void setAmount(int amount){mAmount=amount;}

    public int getId(){return mId;}
    public int getStatus(){return mStatus;}
    public String getTBName(){return mTbName;}
    public String getType(){return mType;}
    public String getName(){return mName;}
    public String getDescription(){return mDescription;}
    public String getDt1(){return mDt1;}
    public String getDt2(){return mDt2;}
    public int getAmount(){return mAmount;}

}
