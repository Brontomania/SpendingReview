package com.thewoollizard.android.spendingreview.lib.database.dbobjects;

/**
 * Created by @BrontoMania on 05/09/2014.
 */
public class FlowType {

    String mTbName;
    int mId;
    int mStatus;
    String mDescription;

    public FlowType(String tbName, int id, String description, int status){
        setId(id);
        setTBName(tbName);
        setStatus(status);
        setDescription(description);
    }

    public void setId(int id){mId=id;}
    public void setStatus(int status){mStatus=status;}
    public void setTBName(String tbName){mTbName=tbName;}
    public void setDescription(String description){mDescription=description;}

    public int getId(){return mId;}
    public int getStatus(){return mStatus;}
    public String getTBName(){return mTbName;}
    public String getDescription(){return mDescription;}

}
