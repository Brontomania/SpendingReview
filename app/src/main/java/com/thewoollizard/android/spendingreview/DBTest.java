package com.thewoollizard.android.spendingreview;

import com.thewoollizard.android.spendingreview.dbmng.DBDAO;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Category;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Currency;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Field;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.FlowType;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.Item;
import com.thewoollizard.android.spendingreview.lib.database.dbobjects.MovType;

import java.util.ArrayList;


/**
 * Created by @BrontoMania on 26/09/2014.
 * Load dummy values into database
 */
public class DBTest {

    DBDAO dbDAO;

    public DBTest(DBDAO dbDAO){
        this.dbDAO=dbDAO;
        dbDAO.mDbStructure.onResetAllTables(dbDAO.mDb);
    }

    public void run(){
        insertCurrencies();
        insertMovTypes();
        insertFlowTypes();
        insertFields();
        insertCategories();
        insertItems();
        insertItemsCategories();
//		Da perfezionare, non funziona
//      for(int i=0;i<10;i++) {
//            insertItems();
//            insertItemsCategories();
//        }
    }

    private void insertCurrencies(){
        Currency euro=new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1);
        Currency dollar=new Currency("T_COD_CURRENCY", 2, "Dollar", "$", 1);
        Currency sterlin=new Currency("T_COD_CURRENCY", 3, "Sterlin", "£", 1);
        Currency[] currencies=new Currency[]{euro, dollar, sterlin};

        dbDAO.insertCurrencies(currencies);
    }

    private void insertMovTypes(){
        MovType bank=new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1);
        MovType cash=new MovType("T_TAB_MOVTYPE", 2, "Cash", 0, 1);
        MovType card=new MovType("T_TAB_MOVTYPE", 3, "Card", 0, 1);
        MovType[] movTypes=new MovType[]{bank, cash, card};

        dbDAO.insertMovTypes(movTypes);
    }

    private void insertFlowTypes(){
        FlowType in=new FlowType("T_TAB_FlowType", 1, "In", 1);
        FlowType out=new FlowType("T_TAB_FlowType", 2, "Out", 1);
        FlowType[] flowTypes=new FlowType[]{in, out};

        dbDAO.insertFlowTypes(flowTypes);
    }

    private void insertFields(){
        Field field1=new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1);
        Field field2=new Field("T_TAB_Field", 2, "Nonna", 0, 1);
        Field[] fields=new Field[]{field1, field2};

        dbDAO.insertFields(fields);
    }

    private void insertCategories(){
        Category cat1=new Category("T_TAB_Category", 1, "Veicoli", 0, 1);
        Category cat2=new Category("T_TAB_Category", 2, "Spese Condominiali", 0, 1);
        Category cat3=new Category("T_TAB_Category", 3, "Stipendio", 0, 1);
        Category cat4=new Category("T_TAB_Category", 4, "Spesa", 0, 1);
        Category cat5=new Category("T_TAB_Category", 5, "Auto", 1, 1);
        Category cat6=new Category("T_TAB_Category", 6, "Moto", 1, 1);
        Category cat7=new Category("T_TAB_Category", 7, "Bici", 1, 1);
        Category cat8=new Category("T_TAB_Category", 8, "Yaris", 5, 1);
        Category cat9=new Category("T_TAB_Category", 9, "Suzuki", 6, 1);
        Category cat10=new Category("T_TAB_Category", 10, "Bottecchia", 7, 1);
        Category cat11=new Category("T_TAB_Category", 11, "Ordinarie", 2, 1);
        Category cat12=new Category("T_TAB_Category", 12, "Straordinarie", 2, 1);

        Category[] cats=new Category[]{cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12};

        dbDAO.insertCategories(cats);
    }

    private ArrayList<Field> generaFields(Field field){
        ArrayList<Field> fields=new ArrayList<Field>();
        fields.add(field);
        return fields;
    }

    private ArrayList<Category> generaCategories(Category category){
        ArrayList<Category> categories=new ArrayList<Category>();
        categories.add(category);
        return categories;
    }

    private void insertItems(){
        Item item1=new Item("T_MOV_ITEMS", 1, new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 2, "Out", 1), "Assicurazione", "2012-09-20", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 276.48, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 8, "Yaris", 5, 1)));
        Item item2=new Item("T_MOV_ITEMS", 2, new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 2, "Out", 1), "Bollo", "2012-05-29", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 175.48, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 8, "Yaris", 5, 1)));
        Item item3=new Item("T_MOV_ITEMS", 3, new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 2, "Out", 1), "Bollo", "2012-07-18", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 65.00, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 9, "Suzuki", 6, 1)));
        Item item4=new Item("T_MOV_ITEMS", 4, new MovType("T_TAB_MOVTYPE", 2, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 2, "Out", 1), "Camera d'aria", "2013-03-13", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 3.20, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 10, "Bottecchia", 7, 1)));
        Item item5=new Item("T_MOV_ITEMS", 5, new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 2, "Out", 1), "Rata 1 - 2013", "2013-06-05", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 554.00, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 11, "Ordinarie", 2, 1)));
        Item item6=new Item("T_MOV_ITEMS", 6, new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 1, "Out", 1), "Maggio 2013", "2013-05-27", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 2114.00, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 3, "Stipendio", 0, 1)));

        Item[] items=new Item[]{item1, item2, item3, item4, item5, item6};
        dbDAO.insertItems(items);
    }

    private void insertItemsCategories(){
        Item item1=new Item("T_MOV_ITEMS", 1, new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 2, "Out", 1), "Assicurazione", "2012-09-20", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 276.48, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 8, "Yaris", 5, 1)));
        Item item2=new Item("T_MOV_ITEMS", 2, new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 2, "Out", 1), "Bollo", "2012-05-29", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 175.48, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 8, "Yaris", 5, 1)));
        Item item3=new Item("T_MOV_ITEMS", 3, new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 2, "Out", 1), "Bollo", "2012-07-18", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 65.00, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 9, "Suzuki", 6, 1)));
        Item item4=new Item("T_MOV_ITEMS", 4, new MovType("T_TAB_MOVTYPE", 2, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 2, "Out", 1), "Camera d'aria", "2013-03-13", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 3.20, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 10, "Bottecchia", 7, 1)));
        Item item5=new Item("T_MOV_ITEMS", 5, new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 2, "Out", 1), "Rata 1 - 2013", "2013-06-05", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 554.00, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 11, "Ordinarie", 2, 1)));
        Item item6=new Item("T_MOV_ITEMS", 6, new MovType("T_TAB_MOVTYPE", 1, "Bank", 0, 1), new FlowType("T_TAB_FlowType", 1, "Out", 1), "Maggio 2013", "2013-05-27", "19:50:00", 1, new Currency("T_COD_CURRENCY", 1, "Eur", "€", 1), 2114.00, generaFields(new Field("T_TAB_Field", 1, "Bilancio Familiare", 0, 1)), generaCategories(new Category("T_TAB_Category", 3, "Stipendio", 0, 1)));

        Item[] items=new Item[]{item1, item2, item3, item4, item5, item6};

        dbDAO.insertItemCategory(item1, new Category("T_TAB_Category", 8, "Yaris", 5, 1));
        dbDAO.insertItemCategory(item2, new Category("T_TAB_Category", 8, "Yaris", 5, 1));
        dbDAO.insertItemCategory(item3, new Category("T_TAB_Category", 9, "Suzuki", 6, 1));
        dbDAO.insertItemCategory(item4, new Category("T_TAB_Category", 10, "Bottecchia", 7, 1));
        dbDAO.insertItemCategory(item5, new Category("T_TAB_Category", 11, "Ordinarie", 2, 1));
        dbDAO.insertItemCategory(item6, new Category("T_TAB_Category", 3, "Stipendio", 0, 1));
        dbDAO.insertItemCategory(item6, new Category("T_TAB_Category", 8, "Yaris", 5, 1));
        dbDAO.insertItemCategory(item6, new Category("T_TAB_Category", 9, "Suzuki", 6, 1));

    }

}
