package com.thewoollizard.android.spendingreview.lib.database.dbobjects;

/**
 * Created by @BrontoMania on 05/09/2014.
 */
public class MovType {

    String mTbName;
    int mId;
    int mStatus;
    String mDescription;
    int mFatherId;

    public MovType(String tbName, int id, String description, int fatherId, int status){
        setId(id);
        setTBName(tbName);
        setStatus(status);
        setDescription(description);
        setFatherId(fatherId);
    }

    public void setId(int id){mId=id;}
    public void setStatus(int status){mStatus=status;}
    public void setTBName(String tbName){mTbName=tbName;}
    public void setDescription(String description){mDescription=description;}
    public void setFatherId(int fatherId){mFatherId=fatherId;}

    public int getId(){return mId;}
    public int getStatus(){return mStatus;}
    public String getTBName(){return mTbName;}
    public String getDescription(){return mDescription;}
    public int getFatherId(){return mFatherId;}

}
