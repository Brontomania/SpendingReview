package com.thewoollizard.android.spendingreview.lib.database.dbobjects;

/**
 * Created by @BrontoMania on 05/09/2014.
 */
public class User {

    String mTbName;
    int mId;
    int mStatus;
    String mAccount;
    String mName;
    String mSurname;
    int mProfile;

    public User(String tbName, int id, String account, String name, String surname, int status, int profile){
        setId(id);
        setTBName(tbName);
        setStatus(status);
        setAccount(account);
        setName(name);
        setSurname(surname);
        setProfile(profile);
    }

    public void setId(int id){mId=id;}
    public void setStatus(int status){mStatus=status;}
    public void setTBName(String tbName){mTbName=tbName;}
    public void setAccount(String account){mAccount=account;}
    public void setName(String name){mName=name;}
    public void setSurname(String surname){mSurname=surname;}
    public void setProfile(int profile){mProfile=profile;}

    public int getId(){return mId;}
    public int getStatus(){return mStatus;}
    public String getTBName(){return mTbName;}
    public String getAccount(){return mAccount;}
    public String getName(){return mName;}
    public String getSurname(){return mSurname;}
    public int getProfile(){return mProfile;}

}
