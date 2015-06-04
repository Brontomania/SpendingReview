package com.thewoollizard.android.spendingreview.lib.database.iface;

import android.content.Context;

import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Category;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Currency;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Field;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.FlowType;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Item;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.MovType;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by @Brontomania on 18/02/2015.
 */
public interface IDBDAO {

    public void openDB();
    public void closeDB();
    public String getDBPath();
    public void deleteDB(Context ctx, String dbName);

    //T_TAB_CURRENCY
    public Currency selectCurrency(int idCurrency);
    public String insertCurrency(Currency currency);
    public String insertCurrencies(Currency[] currencies);
    public String insertCurrencies(ArrayList<Currency> currencies);
    public String updateCurrency(Currency currency);
    public String updateCurrencies(int statusOld, int statusNew);
    public String deleteCurrency(Currency currency);

    //T_TAB_MOVTYPE
    public MovType selectMovType(int idMovType);
    public String insertMovType(MovType movType);
    public String insertMovTypes(MovType[] movTypes);
    public String insertMovTypes(ArrayList<MovType> movTypes);

    //T_TAB_FLOWTYPE
    public FlowType selectFlowType(int idFlowType);
    public String insertFlowType(FlowType flowType);
    public String insertFlowTypes(FlowType[] flowTypes);
    public String insertFlowTypes(ArrayList<FlowType> flowTypes);

    //T_TAB_FIELD
    public ArrayList<Field> selectFields(int idItem);
    public String insertField(Field field);
    public String insertFields(Field[] fields);
    public String insertFields(ArrayList<Field> fields);

    //T_TAB_CATEGORY
    public ArrayList<Category> selectCategories(int idItem);
    public ArrayList<CharSequence> selectCategorie(int idItem);
    public String insertCategory(Category category);
    public String insertCategories(Category[] categories);
    public String insertCategories(ArrayList<Category> categories);

    //T_MOV_ITEMS
    public String insertItem(Item item);
    public String insertItems(Item[] items);
    public String insertItems(ArrayList<Item> items);
    public int selectMaxItemId();
    public Item selectItem(int itemId);
    public ArrayList<Item> selectItems(int filter);
    public int countItems(int filter);

    //T_MOV_ITEMS_CATEGORIES
    public String insertItemCategory(Item item, Category category);
    public String insertItemsCategories(Item item, Category[] categories);
    public String insertItemsCategories(Item item, ArrayList<Category> categories);

    //T_MOV_ITEMS_FIELDS
    public String insertItemField(Item item, Field field);
    public String insertItemsFields(Item item, Field[] fields);
    public String insertItemsFields(Item item, ArrayList<Field> fields);

}
