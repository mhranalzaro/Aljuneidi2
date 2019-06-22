package com.example.aljuneidi;

import android.R.layout;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.aljuneidi.adminClasses.userinformation;
import com.example.aljuneidi.getdataServer;
import com.example.aljuneidi.items.Items_class;
import com.example.aljuneidi.salesman.salesman;
import com.example.aljuneidi.customers.customers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class databaseHelper extends SQLiteOpenHelper {

    ArrayList<String> values = new ArrayList<String>();

    private static final String DB_NAME = "aljuneidi.db";
    private static final int DB_VERSIOM = 1;
    private static final String tbl_Info = "tbl_Info";


    public databaseHelper(Context context) {

        //super(context, DB_NAME, null, DB_VERSION);
        //   SQLiteDatabase.openOrCreateDatabase("/sdcard/"+DB_NAME,null);

        super(context, Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "AljuneidiDataBaseEk"
                + File.separator + DB_NAME, null, DB_VERSIOM);
        File d = Environment.getExternalStorageDirectory();
        String s = d.getAbsolutePath();

    }

    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE contacts (id INTEGER  PRIMARY KEY autoincrement ,name TEXT,phone TEXT,carnumber TEXT,distname TEXT)";
        //run the query
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tbl_Info);
        onCreate(db);
    }

    public userinformation GetuserInfo() {
        //take copy from db
        SQLiteDatabase db = this.getReadableDatabase();
        String Queryselect = "select * from tbl_Info where ID=" + 1;
        Cursor cursor = db.rawQuery(Queryselect, null);
        userinformation userinfo = null;
        if (cursor.moveToFirst()) {

            int ide = cursor.getInt(cursor.getColumnIndex(String.valueOf("ID")));

            String ServerIP = cursor.getString(cursor.getColumnIndex("ServerIP"));

            int Salesman = cursor.getInt(cursor.getColumnIndex(String.valueOf("Salesman")));

            String PrinterID = cursor.getString(cursor.getColumnIndex("PrinterID"));

            String PrinterPassword = cursor.getString(cursor.getColumnIndex("PrinterPassword"));

            userinfo = new userinformation(ide, ServerIP, Salesman, PrinterID, PrinterPassword);
        }
        return userinfo;
    }

    public void updateUserInfo(userinformation userinformation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ServerIP", userinformation.getServerIP());
        values.put("Salesman", userinformation.getSalesman());
        values.put("PrinterID", userinformation.getPrinterID());
        values.put("PrinterPassword", userinformation.getPrinterPassword());
        db.update(tbl_Info, values, "id=1", null);


    }

    //add data com from url to tbl_users
    public boolean URL_tbl_Users(Integer Num, String UserID, Integer GroupNum, Integer Department, Integer BoxNo, Integer SalmanNum, Integer SuperVisorNum, Integer ProductionDepNum, String Modification) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Num", Num);
        contentValues.put("UserID", UserID);
        contentValues.put("GroupNum", GroupNum);
        contentValues.put("Department", Department);
        contentValues.put("BoxNo", BoxNo);
        contentValues.put("SalmanNum", SalmanNum);
        contentValues.put("SuperVisorNum", SuperVisorNum);
        contentValues.put("ProductionDepNum", ProductionDepNum);
        contentValues.put("Modification", Modification);
        long result = db.insert("tbl_Users", null, contentValues);

        if (result == -1) {
            return false;
        } else {

            return true;
        }


    }

    public boolean Delete_tbl_Users() {

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("tbl_Users", null, null);
        if (result == -1) {
            return false;
        } else {


            return true;
        }


    }

    public boolean URL_tbl_Items(int SalesmanNumber, String Number, String NameEnglish, String NameArabic, String CategID, String Unit1Barcode, String Unit2Barcode, String Unit3Barcode, String Unit4Barcode, String SaleAvailableQty, String ReservedQty, String DamagedQty, String UsedForSales, String UsedForDistribution, String UsedForShelving, String UsedForPricing, String UsedForReturn, String UsedForOrder, String UsedForDeposit, String UsedForPrize, String Unit1DescID, String Unit2DescID, String Unit3DescID, String Unit4DescID, String Unit1DescEng, String Unit1DescArabic, String Unit2DescEng, String Unit2DescArabic, String Unit3DescEng, String Unit3DescArabic, String Unit4DescEng, String Unit4DescArabic, String PackSize12, String PackSize23, String PackSize34, String Tax1Perc, String Tax2Perc, String Tax3Perc, String NumberOfUnits, String DefLoadUnit, String NetSalesQty, String NetFreeQty, String CustomField1, String CustomField2, String HasPromotion, String MKeyIsUsed, String RequestedQty, String ReturnedQty, String ReturnedStatus, String RecordIsSelected, String PriceListRefNumber, String TargetQty, String TodayTarget, String AchievedQty, String IsFixtureItem, String WHQty, String DisplayOrder, String GlassItemNumber, String ShellItemNumber, String ItemDefaultPrice, String TaxType) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SalesmanNumber", SalesmanNumber);
        contentValues.put("Number", Number);
        contentValues.put("NameEnglish", NameEnglish);
        contentValues.put("NameArabic", NameArabic);
        contentValues.put("CategID", CategID);
        contentValues.put("Unit1Barcode", Unit1Barcode);
        contentValues.put("Unit2Barcode", Unit2Barcode);
        contentValues.put("Unit3Barcode", Unit3Barcode);
        contentValues.put("Unit4Barcode", Unit4Barcode);
        contentValues.put("SaleAvailableQty", SaleAvailableQty);
        contentValues.put("ReservedQty", ReservedQty);
        contentValues.put("DamagedQty", DamagedQty);
        contentValues.put("UsedForSales", UsedForSales);
        contentValues.put("UsedForDistribution", UsedForDistribution);
        contentValues.put("UsedForShelving", UsedForShelving);
        contentValues.put("UsedForPricing", UsedForPricing);
        contentValues.put("UsedForReturn", UsedForReturn);
        contentValues.put("UsedForOrder", UsedForOrder);
        contentValues.put("UsedForDeposit", UsedForDeposit);
        contentValues.put("UsedForPrize", UsedForPrize);
        contentValues.put("Unit1DescID", Unit1DescID);
        contentValues.put("Unit2DescID", Unit2DescID);
        contentValues.put("Unit3DescID", Unit3DescID);
        contentValues.put("Unit4DescID", Unit4DescID);
        contentValues.put("Unit1DescEng", Unit1DescEng);
        contentValues.put("Unit1DescArabic", Unit1DescArabic);
        contentValues.put("Unit2DescArabic", Unit2DescArabic);
        contentValues.put("Unit2DescEng", Unit2DescEng);
        contentValues.put("Unit3DescEng", Unit3DescEng);
        contentValues.put("Unit3DescArabic", Unit3DescArabic);
        contentValues.put("Unit4DescEng", Unit4DescEng);
        contentValues.put("Unit4DescArabic", Unit4DescArabic);
        contentValues.put("PackSize12", PackSize12);
        contentValues.put("PackSize23", PackSize23);
        contentValues.put("PackSize34", PackSize34);
        contentValues.put("Tax1Perc", Tax1Perc);
        contentValues.put("Tax2Perc", Tax2Perc);
        contentValues.put("Tax3Perc", Tax3Perc);
        contentValues.put("NumberOfUnits", NumberOfUnits);
        contentValues.put("DefLoadUnit", DefLoadUnit);
        contentValues.put("NetSalesQty", NetSalesQty);
        contentValues.put("NetFreeQty", NetFreeQty);
        contentValues.put("CustomField1", CustomField1);
        contentValues.put("CustomField2", CustomField2);
        contentValues.put("HasPromotion", HasPromotion);
        contentValues.put("MKeyIsUsed", MKeyIsUsed);
        contentValues.put("RequestedQty", RequestedQty);
        contentValues.put("ReturnedQty", ReturnedQty);
        contentValues.put("ReturnedStatus", ReturnedStatus);
        contentValues.put("RecordIsSelected", RecordIsSelected);
        contentValues.put("PriceListRefNumber", PriceListRefNumber);
        contentValues.put("TargetQty", TargetQty);
        contentValues.put("TodayTarget", TodayTarget);
        contentValues.put("AchievedQty", AchievedQty);
        contentValues.put("IsFixtureItem", IsFixtureItem);
        contentValues.put("WHQty", WHQty);
        contentValues.put("DisplayOrder", DisplayOrder);
        contentValues.put("GlassItemNumber", GlassItemNumber);
        contentValues.put("ShellItemNumber", ShellItemNumber);
        contentValues.put("ItemDefaultPrice", ItemDefaultPrice);
        contentValues.put("TaxType", TaxType);

        long resultT = db.insert("tbl_Items", null, contentValues);

        if (resultT == -1) {
            return false;
        } else {

            return true;
        }


    }

    public boolean Delete_tbl_Items() {

        SQLiteDatabase db = this.getWritableDatabase();
        long resultT = db.delete("tbl_Items", null, null);
        if (resultT == -1) {
            return false;
        } else {


            return true;
        }


    }
    public ArrayList<Items_class> GetItems(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Queryselect = "select * from tbl_Items";
        Cursor cursor = db.rawQuery(Queryselect, null);
        ArrayList<Items_class> Items = new ArrayList<Items_class>();
      while(cursor.moveToNext() && cursor != null) {
            Integer  SalesmanNumber=cursor.getInt(cursor.getColumnIndex(String.valueOf("SalesmanNumber")));
            String  Number=cursor.getString(cursor.getColumnIndex("Number"));
            String  NameEnglish=cursor.getString(cursor.getColumnIndex("NameEnglish"));
            String  NameArabic=cursor.getString(cursor.getColumnIndex("NameArabic"));
            String  CategID=cursor.getString(cursor.getColumnIndex("CategID"));
            String  Unit1Barcode=cursor.getString(cursor.getColumnIndex("Unit1Barcode"));
            String  Unit2Barcode=cursor.getString(cursor.getColumnIndex("Unit2Barcode"));
            String  Unit3Barcode=cursor.getString(cursor.getColumnIndex("Unit3Barcode"));
            String  Unit4Barcode=cursor.getString(cursor.getColumnIndex("Unit4Barcode"));
            String  SaleAvailableQty=cursor.getString(cursor.getColumnIndex("SaleAvailableQty"));
            String  ReservedQty=cursor.getString(cursor.getColumnIndex("ReservedQty"));
            String  DamagedQty=cursor.getString(cursor.getColumnIndex("DamagedQty"));
            String  UsedForSales=cursor.getString(cursor.getColumnIndex("UsedForSales"));
            String  UsedForDistribution=cursor.getString(cursor.getColumnIndex("UsedForDistribution"));
            String  UsedForShelving=cursor.getString(cursor.getColumnIndex("UsedForShelving"));
            String  UsedForPricing=cursor.getString(cursor.getColumnIndex("UsedForPricing"));
            String  UsedForReturn=cursor.getString(cursor.getColumnIndex("UsedForReturn"));
            String  UsedForOrder=cursor.getString(cursor.getColumnIndex("UsedForOrder"));
            String  UsedForDeposit=cursor.getString(cursor.getColumnIndex("UsedForDeposit"));
            String  UsedForPrize=cursor.getString(cursor.getColumnIndex("UsedForPrize"));
            String  Unit1DescID=cursor.getString(cursor.getColumnIndex("Unit1DescID"));
            String  Unit2DescID=cursor.getString(cursor.getColumnIndex("Unit2DescID"));
            String  Unit3DescID=cursor.getString(cursor.getColumnIndex("Unit3DescID"));
            String  Unit4DescID=cursor.getString(cursor.getColumnIndex("Unit4DescID"));
            String  Unit1DescEng=cursor.getString(cursor.getColumnIndex("Unit1DescEng"));
            String  Unit1DescArabic=cursor.getString(cursor.getColumnIndex("Unit1DescArabic"));
            String  Unit2DescEng=cursor.getString(cursor.getColumnIndex("Unit2DescEng"));
            String  Unit2DescArabic=cursor.getString(cursor.getColumnIndex("Unit2DescArabic"));
            String  Unit3DescEng=cursor.getString(cursor.getColumnIndex("Unit3DescEng"));
            String  Unit3DescArabic=cursor.getString(cursor.getColumnIndex("Unit3DescArabic"));
            String  Unit4DescEng=cursor.getString(cursor.getColumnIndex("Unit4DescEng"));
            String  Unit4DescArabic=cursor.getString(cursor.getColumnIndex("Unit4DescArabic"));
            String  PackSize12=cursor.getString(cursor.getColumnIndex("PackSize12"));
            String  PackSize23=cursor.getString(cursor.getColumnIndex("PackSize23"));
            String  PackSize34=cursor.getString(cursor.getColumnIndex("PackSize34"));
            String  Tax1Perc=cursor.getString(cursor.getColumnIndex("Tax1Perc"));
            String  Tax2Perc=cursor.getString(cursor.getColumnIndex("Tax2Perc"));
            String  Tax3Perc=cursor.getString(cursor.getColumnIndex("Tax3Perc"));
            String  NumberOfUnits=cursor.getString(cursor.getColumnIndex("NumberOfUnits"));
            String  DefLoadUnit=cursor.getString(cursor.getColumnIndex("DefLoadUnit"));
            String  NetSalesQty=cursor.getString(cursor.getColumnIndex("NetSalesQty"));
            String  NetFreeQty=cursor.getString(cursor.getColumnIndex("NetFreeQty"));
            String  CustomField1=cursor.getString(cursor.getColumnIndex("CustomField1"));
            String  CustomField2=cursor.getString(cursor.getColumnIndex("CustomField2"));
            String  HasPromotion=cursor.getString(cursor.getColumnIndex("HasPromotion"));
            String  MKeyIsUsed=cursor.getString(cursor.getColumnIndex("MKeyIsUsed"));
            String  RequestedQty=cursor.getString(cursor.getColumnIndex("RequestedQty"));
            String  ReturnedQty=cursor.getString(cursor.getColumnIndex("ReturnedQty"));
            String  ReturnedStatus=cursor.getString(cursor.getColumnIndex("ReturnedStatus"));
            String  RecordIsSelected=cursor.getString(cursor.getColumnIndex("RecordIsSelected"));
            String  PriceListRefNumber=cursor.getString(cursor.getColumnIndex("PriceListRefNumber"));
            String  TargetQty=cursor.getString(cursor.getColumnIndex("TargetQty"));
            String  TodayTarget=cursor.getString(cursor.getColumnIndex("TodayTarget"));
            String  AchievedQty=cursor.getString(cursor.getColumnIndex("AchievedQty"));
            String  IsFixtureItem=cursor.getString(cursor.getColumnIndex("IsFixtureItem"));
            String  WHQty=cursor.getString(cursor.getColumnIndex("WHQty"));
            String  DisplayOrder=cursor.getString(cursor.getColumnIndex("DisplayOrder"));
            String  GlassItemNumber=cursor.getString(cursor.getColumnIndex("GlassItemNumber"));
            String  ShellItemNumber=cursor.getString(cursor.getColumnIndex("ShellItemNumber"));
            String  ItemDefaultPrice=cursor.getString(cursor.getColumnIndex("ItemDefaultPrice"));
            String  TaxType=cursor.getString(cursor.getColumnIndex("TaxType"));
           Items.add(new Items_class(
                    SalesmanNumber,
                    Number,
                    NameEnglish,
                    NameArabic,
                    CategID,
                    Unit1Barcode,
                    Unit2Barcode,
                    Unit3Barcode,
                    Unit4Barcode,
                    SaleAvailableQty,
                    ReservedQty,
                    DamagedQty,
                    UsedForSales,
                    UsedForDistribution,
                    UsedForShelving,
                    UsedForPricing,
                    UsedForReturn,
                    UsedForOrder,
                    UsedForDeposit,
                    UsedForPrize,
                    Unit1DescID,
                    Unit2DescID,
                    Unit3DescID,
                    Unit4DescID,
                    Unit1DescEng,
                    Unit1DescArabic,
                    Unit2DescEng,
                    Unit2DescArabic,
                    Unit3DescEng,
                    Unit3DescArabic,
                    Unit4DescEng,
                    Unit4DescArabic,
                    PackSize12,
                    PackSize23,
                    PackSize34,
                    Tax1Perc,
                    Tax2Perc,
                    Tax3Perc,
                    NumberOfUnits,
                    DefLoadUnit,
                    NetSalesQty,
                    NetFreeQty,
                    CustomField1,
                    CustomField2,
                    HasPromotion,
                    MKeyIsUsed,
                    RequestedQty,
                    ReturnedQty,
                    ReturnedStatus,
                    RecordIsSelected,
                    PriceListRefNumber,
                    TargetQty,
                    TodayTarget,
                    AchievedQty,
                    IsFixtureItem,
                    WHQty,
                    DisplayOrder,
                    GlassItemNumber,
                    ShellItemNumber,
                    ItemDefaultPrice,
                    TaxType
            ));

        }
        return  Items;
    }

    //======================================== Salesman ====================================
    public salesman getSalesman(Integer id) {
        //take copy from db
        SQLiteDatabase db = this.getReadableDatabase();
        String Queryselect = "select * from tbl_Salesman where SalesmanNumber=" + id;
        Cursor cursor = db.rawQuery(Queryselect, null);
        salesman salesman = null;
        if (cursor.moveToFirst() && cursor != null) {
            int SalesmanNumber = cursor.getInt(cursor.getColumnIndex(String.valueOf("SalesmanNumber")));
            String RouteNameEnglish = cursor.getString(cursor.getColumnIndex("RouteNameEnglish"));
            String RouteNameArabic = cursor.getString(cursor.getColumnIndex("RouteNameArabic"));
            String SalesmanNameEng = cursor.getString(cursor.getColumnIndex("SalesmanNameEng"));
            String SalesmanNameArabic = cursor.getString(cursor.getColumnIndex("SalesmanNameArabic"));
            int SalesmanUserID = cursor.getInt(cursor.getColumnIndex(String.valueOf("SalesmanUserID")));
            String SalesmanPassword = cursor.getString(cursor.getColumnIndex("SalesmanPassword"));
            String EnableSalesOperations = cursor.getString(cursor.getColumnIndex("EnableSalesOperations"));
            String EnableReturns = cursor.getString(cursor.getColumnIndex("EnableReturns"));
            String EnableDueInvCollection = cursor.getString(cursor.getColumnIndex("EnableDueInvCollection"));
            String EnableDistribution = cursor.getString(cursor.getColumnIndex("EnableDistribution"));
            String EnableShelving = cursor.getString(cursor.getColumnIndex("EnableShelving"));
            String EnablePricing = cursor.getString(cursor.getColumnIndex("EnablePricing"));
            String EnableFixtures = cursor.getString(cursor.getColumnIndex("EnableFixtures"));
            String MasterKeyBarcode = cursor.getString(cursor.getColumnIndex("MasterKeyBarcode"));
            String NextCashDocNumber = cursor.getString(cursor.getColumnIndex("NextCashDocNumber"));
            String NextCreditDocNumber = cursor.getString(cursor.getColumnIndex("NextCreditDocNumber"));
            String NextReturnDocNumber = cursor.getString(cursor.getColumnIndex("NextReturnDocNumber"));
            String NextReceiptDocNumber = cursor.getString(cursor.getColumnIndex("NextReceiptDocNumber"));
            String NextFixtureDocNumber = cursor.getString(cursor.getColumnIndex("NextFixtureDocNumber"));
            String NextLoadDocNumber = cursor.getString(cursor.getColumnIndex("NextLoadDocNumber"));
            String NextFreeVoucherDocNumber = cursor.getString(cursor.getColumnIndex("NextFreeVoucherDocNumber"));
            String NextDepositDocNumber = cursor.getString(cursor.getColumnIndex("NextDepositDocNumber"));
            String GroupID = cursor.getString(cursor.getColumnIndex("GroupID"));
            String TodayVisitsTarget = cursor.getString(cursor.getColumnIndex("TodayVisitsTarget"));
            String MonthVisitsTarget = cursor.getString(cursor.getColumnIndex("MonthVisitsTarget"));
            String MonthSalesTargetValue = cursor.getString(cursor.getColumnIndex("MonthSalesTargetValue"));
            String MTDSalesValue = cursor.getString(cursor.getColumnIndex("MTDSalesValue"));
            String VanStoreBarcode = cursor.getString(cursor.getColumnIndex("VanStoreBarcode"));
            String StoreID = cursor.getString(cursor.getColumnIndex("StoreID"));
            String NumOfItemsPerClass = cursor.getString(cursor.getColumnIndex("NumOfItemsPerClass"));
            String TodayNumberOfVisits = cursor.getString(cursor.getColumnIndex("TodayNumberOfVisits"));
            String TodayNumberOfEffectiveVisits = cursor.getString(cursor.getColumnIndex("TodayNumberOfEffectiveVisits"));
            String NumberOfCashSales = cursor.getString(cursor.getColumnIndex("NumberOfCashSales"));
            String NumberOfCreditSales = cursor.getString(cursor.getColumnIndex("NumberOfCreditSales"));
            String NumberOfReceipts = cursor.getString(cursor.getColumnIndex("NumberOfReceipts"));
            String NumberOfCollections = cursor.getString(cursor.getColumnIndex("NumberOfCollections"));
            String NumberOfReturns = cursor.getString(cursor.getColumnIndex("NumberOfReturns"));
            String NumberOfCashReturns = cursor.getString(cursor.getColumnIndex("NumberOfCashReturns"));
            String NumberOfCheques = cursor.getString(cursor.getColumnIndex("NumberOfCheques"));
            String CashSalesValue = cursor.getString(cursor.getColumnIndex("CashSalesValue"));
            String CreditSalesValue = cursor.getString(cursor.getColumnIndex("CreditSalesValue"));
            String CashReturnsValue = cursor.getString(cursor.getColumnIndex("CashReturnsValue"));
            String CreditCollectionValue = cursor.getString(cursor.getColumnIndex("CreditCollectionValue"));
            String TotPocketCashMoney = cursor.getString(cursor.getColumnIndex("TotPocketCashMoney"));
            String TotPocketChequeMoney = cursor.getString(cursor.getColumnIndex("TotPocketChequeMoney"));
            String EditingALoadOrder = cursor.getString(cursor.getColumnIndex("EditingALoadOrder"));
            String ScannedStartOfJourney = cursor.getString(cursor.getColumnIndex("ScannedStartOfJourney"));
            String StartMovTimeInSec = cursor.getString(cursor.getColumnIndex("StartMovTimeInSec"));
            String EndMovTimeInSec = cursor.getString(cursor.getColumnIndex("EndMovTimeInSec"));
            String CustTimeInSec = cursor.getString(cursor.getColumnIndex("CustTimeInSec"));
            String BreakTimeInSec = cursor.getString(cursor.getColumnIndex("BreakTimeInSec"));
            String EnablePrinting = cursor.getString(cursor.getColumnIndex("EnablePrinting"));
            String SupervisorID = cursor.getString(cursor.getColumnIndex("SupervisorID"));
            String SalesAreaManagerID = cursor.getString(cursor.getColumnIndex("SalesAreaManagerID"));
            String SalesManagerID = cursor.getString(cursor.getColumnIndex("SalesManagerID"));
            int OrderAllowed = cursor.getInt(cursor.getColumnIndex(String.valueOf("OrderAllowed")));
            String NextOrderNumber = cursor.getString(cursor.getColumnIndex("NextOrderNumber"));
            String NumberOfOrderTaking = cursor.getString(cursor.getColumnIndex("NumberOfOrderTaking"));
            String OrderTakingValue = cursor.getString(cursor.getColumnIndex("OrderTakingValue"));
            String NextFactoryReturnsSerial = cursor.getString(cursor.getColumnIndex("NextFactoryReturnsSerial"));
            String NextReturnsCheckSerial = cursor.getString(cursor.getColumnIndex("NextReturnsCheckSerial"));
            String NextSuggestedOrderSerial = cursor.getString(cursor.getColumnIndex("NextSuggestedOrderSerial"));
            String NextFOCSerialNumber = cursor.getString(cursor.getColumnIndex("NextFOCSerialNumber"));
            String NextPaymentVoucherSerial = cursor.getString(cursor.getColumnIndex("NextPaymentVoucherSerial"));
            String WHTransactionNumber = cursor.getString(cursor.getColumnIndex("WHTransactionNumber"));
            salesman = new salesman(SalesmanNumber,
                    RouteNameEnglish,
                    RouteNameArabic,
                    SalesmanNameEng,
                    SalesmanNameArabic,
                    SalesmanUserID,
                    SalesmanPassword,
                    EnableSalesOperations,
                    EnableReturns,
                    EnableDueInvCollection,
                    EnableDistribution,
                    EnableShelving,
                    EnablePricing,
                    EnableFixtures,
                    MasterKeyBarcode,
                    NextCashDocNumber,
                    NextCreditDocNumber,
                    NextReturnDocNumber,
                    NextReceiptDocNumber,
                    NextFixtureDocNumber,
                    NextLoadDocNumber,
                    NextFreeVoucherDocNumber,
                    NextDepositDocNumber,
                    GroupID,
                    TodayVisitsTarget,
                    MonthVisitsTarget,
                    MonthSalesTargetValue,
                    MTDSalesValue,
                    VanStoreBarcode,
                    StoreID,
                    NumOfItemsPerClass,
                    TodayNumberOfVisits,
                    TodayNumberOfEffectiveVisits,
                    NumberOfCashSales,
                    NumberOfCreditSales,
                    NumberOfReceipts,
                    NumberOfCollections,
                    NumberOfReturns,
                    NumberOfCashReturns,
                    NumberOfCheques,
                    CashSalesValue,
                    CreditSalesValue,
                    CashReturnsValue,
                    CreditCollectionValue,
                    TotPocketCashMoney,
                    TotPocketChequeMoney,
                    EditingALoadOrder,
                    ScannedStartOfJourney,
                    StartMovTimeInSec,
                    EndMovTimeInSec,
                    CustTimeInSec,
                    BreakTimeInSec,
                    EnablePrinting,
                    SupervisorID,
                    SalesAreaManagerID,
                    SalesManagerID,
                    OrderAllowed,
                    NextOrderNumber,
                    NumberOfOrderTaking,
                    OrderTakingValue,
                    NextFactoryReturnsSerial,
                    NextReturnsCheckSerial,
                    NextSuggestedOrderSerial,
                    NextFOCSerialNumber,
                    NextPaymentVoucherSerial,
                    WHTransactionNumber);
        }
        return salesman;
    }

    public boolean URL_tbl_Salesman(int SalesmanNumber,
                                    String RouteNameEnglish,
                                    String RouteNameArabic,
                                    String SalesmanNameEng,
                                    String SalesmanNameArabic,
                                    int SalesmanUserID,
                                    String SalesmanPassword,
                                    String EnableSalesOperations,
                                    String EnableReturns,
                                    String EnableDueInvCollection,
                                    String EnableDistribution,
                                    String EnableShelving,
                                    String EnablePricing,
                                    String EnableFixtures,
                                    String MasterKeyBarcode,
                                    String NextCashDocNumber,
                                    String NextCreditDocNumber,
                                    String NextReturnDocNumber,
                                    String NextReceiptDocNumber,
                                    String NextFixtureDocNumber,
                                    String NextLoadDocNumber,
                                    String NextFreeVoucherDocNumber,
                                    String NextDepositDocNumber,
                                    String GroupID,
                                    String TodayVisitsTarget,
                                    String MonthVisitsTarget,
                                    String MonthSalesTargetValue,
                                    String MTDSalesValue,
                                    String VanStoreBarcode,
                                    String StoreID,
                                    String NumOfItemsPerClass,
                                    String TodayNumberOfVisits,
                                    String TodayNumberOfEffectiveVisits,
                                    String NumberOfCashSales,
                                    String NumberOfCreditSales,
                                    String NumberOfReceipts,
                                    String NumberOfCollections,
                                    String NumberOfReturns,
                                    String NumberOfCashReturns,
                                    String NumberOfCheques,
                                    String CashSalesValue,
                                    String CreditSalesValue,
                                    String CashReturnsValue,
                                    String CreditCollectionValue,
                                    String TotPocketCashMoney,
                                    String TotPocketChequeMoney,
                                    String EditingALoadOrder,
                                    String ScannedStartOfJourney,
                                    String StartMovTimeInSec,
                                    String EndMovTimeInSec,
                                    String CustTimeInSec,
                                    String BreakTimeInSec,
                                    String EnablePrinting,
                                    String SupervisorID,
                                    String SalesAreaManagerID,
                                    String SalesManagerID,
                                    int OrderAllowed,
                                    String NextOrderNumber,
                                    String NumberOfOrderTaking,
                                    String OrderTakingValue,
                                    String NextFactoryReturnsSerial,
                                    String NextReturnsCheckSerial,
                                    String NextSuggestedOrderSerial,
                                    String NextFOCSerialNumber,
                                    String NextPaymentVoucherSerial,
                                    String WHTransactionNumber) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SalesmanNumber", SalesmanNumber);
        contentValues.put("RouteNameEnglish", RouteNameEnglish);
        contentValues.put("RouteNameArabic", RouteNameArabic);
        contentValues.put("SalesmanNameEng", SalesmanNameEng);
        contentValues.put("SalesmanNameArabic", SalesmanNameArabic);
        contentValues.put("SalesmanUserID", SalesmanUserID);
        contentValues.put("SalesmanPassword", SalesmanPassword);
        contentValues.put("EnableSalesOperations", EnableSalesOperations);
        contentValues.put("EnableReturns", EnableReturns);
        contentValues.put("EnableDueInvCollection", EnableDueInvCollection);
        contentValues.put("EnableDistribution", EnableDistribution);
        contentValues.put("EnableShelving", EnableShelving);
        contentValues.put("EnablePricing", EnablePricing);
        contentValues.put("EnableFixtures", EnableFixtures);
        contentValues.put("MasterKeyBarcode", MasterKeyBarcode);
        contentValues.put("NextCashDocNumber", NextCashDocNumber);
        contentValues.put("NextCreditDocNumber", NextCreditDocNumber);
        contentValues.put("NextReturnDocNumber", NextReturnDocNumber);
        contentValues.put("NextReceiptDocNumber", NextReceiptDocNumber);
        contentValues.put("NextFixtureDocNumber", NextFixtureDocNumber);
        contentValues.put("NextLoadDocNumber", NextLoadDocNumber);
        contentValues.put("NextFreeVoucherDocNumber", NextFreeVoucherDocNumber);
        contentValues.put("NextDepositDocNumber", NextDepositDocNumber);
        contentValues.put("GroupID", GroupID);
        contentValues.put("TodayVisitsTarget", TodayVisitsTarget);
        contentValues.put("MonthVisitsTarget", MonthVisitsTarget);
        contentValues.put("MonthSalesTargetValue", MonthSalesTargetValue);
        contentValues.put("MTDSalesValue", MTDSalesValue);
        contentValues.put("VanStoreBarcode", VanStoreBarcode);
        contentValues.put("StoreID", StoreID);
        contentValues.put("NumOfItemsPerClass", NumOfItemsPerClass);
        contentValues.put("TodayNumberOfVisits", TodayNumberOfVisits);
        contentValues.put("TodayNumberOfEffectiveVisits", TodayNumberOfEffectiveVisits);
        contentValues.put("NumberOfCashSales", NumberOfCashSales);
        contentValues.put("NumberOfCreditSales", NumberOfCreditSales);
        contentValues.put("NumberOfReceipts", NumberOfReceipts);
        contentValues.put("NumberOfCollections", NumberOfCollections);
        contentValues.put("NumberOfReturns", NumberOfReturns);
        contentValues.put("NumberOfCashReturns", NumberOfCashReturns);
        contentValues.put("NumberOfCheques", NumberOfCheques);
        contentValues.put("CashSalesValue", CashSalesValue);
        contentValues.put("CreditSalesValue", CreditSalesValue);
        contentValues.put("CashReturnsValue", CashReturnsValue);
        contentValues.put("CreditCollectionValue", CreditCollectionValue);
        contentValues.put("TotPocketCashMoney", TotPocketCashMoney);
        contentValues.put("TotPocketChequeMoney", TotPocketChequeMoney);
        contentValues.put("EditingALoadOrder", EditingALoadOrder);
        contentValues.put("ScannedStartOfJourney", ScannedStartOfJourney);
        contentValues.put("StartMovTimeInSec", StartMovTimeInSec);
        contentValues.put("EndMovTimeInSec", EndMovTimeInSec);
        contentValues.put("CustTimeInSec", CustTimeInSec);
        contentValues.put("BreakTimeInSec", BreakTimeInSec);
        contentValues.put("EnablePrinting", EnablePrinting);
        contentValues.put("SupervisorID", SupervisorID);
        contentValues.put("SalesAreaManagerID", SalesAreaManagerID);
        contentValues.put("SalesManagerID", SalesManagerID);
        contentValues.put("OrderAllowed", OrderAllowed);
        contentValues.put("NextOrderNumber", NextOrderNumber);
        contentValues.put("NumberOfOrderTaking", NumberOfOrderTaking);
        contentValues.put("OrderTakingValue", OrderTakingValue);
        contentValues.put("NextFactoryReturnsSerial", NextFactoryReturnsSerial);
        contentValues.put("NextReturnsCheckSerial", NextReturnsCheckSerial);
        contentValues.put("NextSuggestedOrderSerial", NextSuggestedOrderSerial);
        contentValues.put("NextFOCSerialNumber", NextFOCSerialNumber);
        contentValues.put("NextPaymentVoucherSerial", NextPaymentVoucherSerial);
        contentValues.put("WHTransactionNumber", WHTransactionNumber);
        long resultT = db.insert("tbl_Salesman", null, contentValues);

        if (resultT == -1) {
            return false;
        } else {

            return true;
        }

    }

    public boolean Delete_tbl_Salesman() {

        SQLiteDatabase db = this.getWritableDatabase();
        long resultT = db.delete("tbl_Salesman", null, null);
        if (resultT == -1) {
            return false;
        } else {


            return true;
        }


    }

    public int CheckSalesmanLogin(int username, String password) {
        //take copy from db
        SQLiteDatabase db = this.getReadableDatabase();
        String Queryselect = "select * from tbl_Salesman where SalesmanUserID=" + username + " and SalesmanPassword=" + password;
        Cursor LoginQuery = db.rawQuery(Queryselect, null);


        if (LoginQuery.getCount() > 0 && LoginQuery != null) {

            LoginQuery.moveToFirst();
            //   String LoginResult = LoginQuery.getString(4);
            LoginQuery.close();
            return 1;
        } else {
            LoginQuery.close();
            //String LoginResult = "Wrong User Name and Password";
            return 0;
        }


    }

//==================================================== End SalesMan ==============================================================

    //====================================================Customers=====================================================================
    public boolean URL_tbl_Customers(Integer SalesmanNumber,
                                     Integer CustomerNumber,
                                     String EngName,
                                     String ArabicName,
                                     String CustomerBarcode,
                                     String IsNewCustomer,
                                     String InTodayRoute,
                                     String Sequence,
                                     String ContactPerson1,
                                     String ContactPerson2,
                                     String HasNotes,
                                     String LastVisit,
                                     String LastSalesDate,
                                     String LastInvoiceNumber,
                                     String LastInvoiceType,
                                     String TargetVisits,
                                     String TargetVisitsType,
                                     String ActualVisits,
                                     String GeoCatagoryLevelOneID,
                                     String GeoCatagoryLevelTwoID,
                                     String GeoCatagoryLevelThreeID,
                                     String GeoCatagoryLevelFourID,
                                     String ClassID,
                                     String Tax1Applicable,
                                     String Tax2Applicable,
                                     String Tax3Applicable,
                                     String AllowChequePayment,
                                     String AllowCreditSales,
                                     String MaxChequeValue,
                                     String MaxChequePeriod,
                                     String ChequeCreditLimit,
                                     String ChequesReturned,
                                     String CreditBalance,
                                     String ChequeBalance,
                                     String BalanceDue,
                                     String CreditLimit,
                                     String CreditPeriod,
                                     String AllowOverLimitSales,
                                     String AllowDueSales,
                                     String InvoiceRunningAvg,
                                     String InvoiceMTDAvg,
                                     String InvoiceLastQuartAvg,
                                     String ImportantDate,
                                     String RouteDate,
                                     String IsVisited,
                                     String OrderChequeCreditLimit,
                                     String OrderChequeCreditDue,
                                     String OrderChequeCurrentLimit,
                                     String OrderCreditLimit,
                                     String OrderCreditDue,
                                     String OrderCreditCurrentLimit,
                                     String AllDepositQty,
                                     String AllDepositValue,
                                     String IOUQty,
                                     String IOUValue,
                                     String X,
                                     String Y,
                                     String UnderCollectionBalance) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SalesmanNumber", SalesmanNumber);
        contentValues.put("CustomerNumber", CustomerNumber);
        contentValues.put("EngName", EngName);
        contentValues.put("ArabicName", ArabicName);
        contentValues.put("CustomerBarcode", CustomerBarcode);
        contentValues.put("IsNewCustomer", IsNewCustomer);
        contentValues.put("InTodayRoute", InTodayRoute);
        contentValues.put("Sequence", Sequence);
        contentValues.put("ContactPerson1", ContactPerson1);
        contentValues.put("ContactPerson2", ContactPerson2);
        contentValues.put("HasNotes", HasNotes);
        contentValues.put("LastVisit", LastVisit);
        contentValues.put("LastSalesDate", LastSalesDate);
        contentValues.put("LastInvoiceNumber", LastInvoiceNumber);
        contentValues.put("LastInvoiceType", LastInvoiceType);
        contentValues.put("TargetVisits", TargetVisits);
        contentValues.put("TargetVisitsType", TargetVisitsType);
        contentValues.put("ActualVisits", ActualVisits);
        contentValues.put("GeoCatagoryLevelOneID", GeoCatagoryLevelOneID);
        contentValues.put("GeoCatagoryLevelTwoID", GeoCatagoryLevelTwoID);
        contentValues.put("GeoCatagoryLevelThreeID", GeoCatagoryLevelThreeID);
        contentValues.put("GeoCatagoryLevelFourID", GeoCatagoryLevelFourID);
        contentValues.put("ClassID", ClassID);
        contentValues.put("Tax1Applicable", Tax1Applicable);
        contentValues.put("Tax2Applicable", Tax2Applicable);
        contentValues.put("Tax3Applicable", Tax3Applicable);
        contentValues.put("AllowChequePayment", AllowChequePayment);
        contentValues.put("AllowCreditSales", AllowCreditSales);
        contentValues.put("MaxChequeValue", MaxChequeValue);
        contentValues.put("MaxChequePeriod", MaxChequePeriod);
        contentValues.put("ChequeCreditLimit", ChequeCreditLimit);
        contentValues.put("ChequesReturned", ChequesReturned);
        contentValues.put("CreditBalance", CreditBalance);
        contentValues.put("ChequeBalance", ChequeBalance);
        contentValues.put("BalanceDue", BalanceDue);
        contentValues.put("CreditLimit", CreditLimit);
        contentValues.put("CreditPeriod", CreditPeriod);
        contentValues.put("AllowOverLimitSales", AllowOverLimitSales);
        contentValues.put("AllowDueSales", AllowDueSales);
        contentValues.put("InvoiceRunningAvg", InvoiceRunningAvg);
        contentValues.put("InvoiceMTDAvg", InvoiceMTDAvg);
        contentValues.put("InvoiceLastQuartAvg", InvoiceLastQuartAvg);
        contentValues.put("ImportantDate", ImportantDate);
        contentValues.put("RouteDate", RouteDate);
        contentValues.put("IsVisited", IsVisited);
        contentValues.put("OrderChequeCreditLimit", OrderChequeCreditLimit);
        contentValues.put("OrderChequeCreditDue", OrderChequeCreditDue);
        contentValues.put("OrderChequeCurrentLimit", OrderChequeCurrentLimit);
        contentValues.put("OrderCreditLimit", OrderCreditLimit);
        contentValues.put("OrderCreditDue", OrderCreditDue);
        contentValues.put("OrderCreditCurrentLimit", OrderCreditCurrentLimit);
        contentValues.put("AllDepositQty", AllDepositQty);
        contentValues.put("AllDepositValue", AllDepositValue);
        contentValues.put("IOUQty", IOUQty);
        contentValues.put("IOUValue", IOUValue);
        contentValues.put("X", X);
        contentValues.put("Y", Y);
        contentValues.put("UnderCollectionBalance", UnderCollectionBalance);

        long resultT = db.insert("tbl_Customers", null, contentValues);

        if (resultT == -1) {
            return false;
        } else {

            return true;
        }
    }

    public boolean Delete_tbl_Customers() {

        SQLiteDatabase db = this.getWritableDatabase();
        long resultT = db.delete("tbl_Customers", null, null);
        if (resultT == -1) {
            return false;
        } else {


            return true;
        }


    }

    // get customers using barcodenumber
    public customers getCustomers(String customersBarcode) {
        //take copy from db
        SQLiteDatabase db = this.getReadableDatabase();
        String Queryselect = "select * from tbl_Customers where CustomerBarcode=" + customersBarcode;
        Log.v("@@@CustomersBarcode", "customersBarcode");
        Cursor cursor = db.rawQuery(Queryselect, null);
        customers customers = null;
        if (cursor.moveToFirst() && cursor != null) {
            int SalesmanNumber = cursor.getInt(cursor.getColumnIndex(String.valueOf("SalesmanNumber")));
            int CustomerNumber = cursor.getInt(cursor.getColumnIndex(String.valueOf("CustomerNumber")));
            String EngName = cursor.getString(cursor.getColumnIndex("EngName"));
            String ArabicName = cursor.getString(cursor.getColumnIndex("ArabicName"));
            String CustomerBarcode = cursor.getString(cursor.getColumnIndex("CustomerBarcode"));
            String IsNewCustomer = cursor.getString(cursor.getColumnIndex("IsNewCustomer"));
            String InTodayRoute = cursor.getString(cursor.getColumnIndex("InTodayRoute"));
            String Sequence = cursor.getString(cursor.getColumnIndex("Sequence"));
            String ContactPerson1 = cursor.getString(cursor.getColumnIndex("ContactPerson1"));
            String ContactPerson2 = cursor.getString(cursor.getColumnIndex("ContactPerson2"));
            String HasNotes = cursor.getString(cursor.getColumnIndex("HasNotes"));
            String LastVisit = cursor.getString(cursor.getColumnIndex("LastVisit"));
            String LastSalesDate = cursor.getString(cursor.getColumnIndex("LastSalesDate"));
            String LastInvoiceNumber = cursor.getString(cursor.getColumnIndex("LastInvoiceNumber"));
            String LastInvoiceType = cursor.getString(cursor.getColumnIndex("LastInvoiceType"));
            String TargetVisits = cursor.getString(cursor.getColumnIndex("TargetVisits"));
            String TargetVisitsType = cursor.getString(cursor.getColumnIndex("TargetVisitsType"));
            String ActualVisits = cursor.getString(cursor.getColumnIndex("ActualVisits"));
            String GeoCatagoryLevelOneID = cursor.getString(cursor.getColumnIndex("GeoCatagoryLevelOneID"));
            String GeoCatagoryLevelTwoID = cursor.getString(cursor.getColumnIndex("GeoCatagoryLevelTwoID"));
            String GeoCatagoryLevelThreeID = cursor.getString(cursor.getColumnIndex("GeoCatagoryLevelThreeID"));
            String GeoCatagoryLevelFourID = cursor.getString(cursor.getColumnIndex("GeoCatagoryLevelFourID"));
            String ClassID = cursor.getString(cursor.getColumnIndex("ClassID"));
            String Tax1Applicable = cursor.getString(cursor.getColumnIndex("Tax1Applicable"));
            String Tax2Applicable = cursor.getString(cursor.getColumnIndex("Tax2Applicable"));
            String Tax3Applicable = cursor.getString(cursor.getColumnIndex("Tax3Applicable"));
            String AllowChequePayment = cursor.getString(cursor.getColumnIndex("AllowChequePayment"));
            String AllowCreditSales = cursor.getString(cursor.getColumnIndex("AllowCreditSales"));
            String MaxChequeValue = cursor.getString(cursor.getColumnIndex("MaxChequeValue"));
            String MaxChequePeriod = cursor.getString(cursor.getColumnIndex("MaxChequePeriod"));
            String ChequeCreditLimit = cursor.getString(cursor.getColumnIndex("ChequeCreditLimit"));
            String ChequesReturned = cursor.getString(cursor.getColumnIndex("ChequesReturned"));
            String CreditBalance = cursor.getString(cursor.getColumnIndex("CreditBalance"));
            String ChequeBalance = cursor.getString(cursor.getColumnIndex("ChequeBalance"));
            String BalanceDue = cursor.getString(cursor.getColumnIndex("BalanceDue"));
            String CreditLimit = cursor.getString(cursor.getColumnIndex("CreditLimit"));
            String CreditPeriod = cursor.getString(cursor.getColumnIndex("CreditPeriod"));
            String AllowOverLimitSales = cursor.getString(cursor.getColumnIndex("AllowOverLimitSales"));
            String AllowDueSales = cursor.getString(cursor.getColumnIndex("AllowDueSales"));
            String InvoiceRunningAvg = cursor.getString(cursor.getColumnIndex("InvoiceRunningAvg"));
            String InvoiceMTDAvg = cursor.getString(cursor.getColumnIndex("InvoiceMTDAvg"));
            String InvoiceLastQuartAvg = cursor.getString(cursor.getColumnIndex("InvoiceLastQuartAvg"));
            String ImportantDate = cursor.getString(cursor.getColumnIndex("ImportantDate"));
            String RouteDate = cursor.getString(cursor.getColumnIndex("RouteDate"));
            String IsVisited = cursor.getString(cursor.getColumnIndex("IsVisited"));
            String OrderChequeCreditLimit = cursor.getString(cursor.getColumnIndex("OrderChequeCreditLimit"));
            String OrderChequeCreditDue = cursor.getString(cursor.getColumnIndex("OrderChequeCreditDue"));
            String OrderChequeCurrentLimit = cursor.getString(cursor.getColumnIndex("OrderChequeCurrentLimit"));
            String OrderCreditLimit = cursor.getString(cursor.getColumnIndex("OrderCreditLimit"));
            String OrderCreditDue = cursor.getString(cursor.getColumnIndex("OrderCreditDue"));
            String OrderCreditCurrentLimit = cursor.getString(cursor.getColumnIndex("OrderCreditCurrentLimit"));
            String AllDepositQty = cursor.getString(cursor.getColumnIndex("AllDepositQty"));
            String AllDepositValue = cursor.getString(cursor.getColumnIndex("AllDepositValue"));
            String IOUQty = cursor.getString(cursor.getColumnIndex("IOUQty"));
            String IOUValue = cursor.getString(cursor.getColumnIndex("IOUValue"));
            String X = cursor.getString(cursor.getColumnIndex("X"));
            String Y = cursor.getString(cursor.getColumnIndex("Y"));
            String UnderCollectionBalance = cursor.getString(cursor.getColumnIndex("UnderCollectionBalance"));

            customers = new customers(
                    SalesmanNumber,
                    CustomerNumber,
                    EngName,
                    ArabicName,
                    CustomerBarcode,
                    IsNewCustomer,
                    InTodayRoute,
                    Sequence,
                    ContactPerson1,
                    ContactPerson2,
                    HasNotes,
                    LastVisit,
                    LastSalesDate,
                    LastInvoiceNumber,
                    LastInvoiceType,
                    TargetVisits,
                    TargetVisitsType,
                    ActualVisits,
                    GeoCatagoryLevelOneID,
                    GeoCatagoryLevelTwoID,
                    GeoCatagoryLevelThreeID,
                    GeoCatagoryLevelFourID,
                    ClassID,
                    Tax1Applicable,
                    Tax2Applicable,
                    Tax3Applicable,
                    AllowChequePayment,
                    AllowCreditSales,
                    MaxChequeValue,
                    MaxChequePeriod,
                    ChequeCreditLimit,
                    ChequesReturned,
                    CreditBalance,
                    ChequeBalance,
                    BalanceDue,
                    CreditLimit,
                    CreditPeriod,
                    AllowOverLimitSales,
                    AllowDueSales,
                    InvoiceRunningAvg,
                    InvoiceMTDAvg,
                    InvoiceLastQuartAvg,
                    ImportantDate,
                    RouteDate,
                    IsVisited,
                    OrderChequeCreditLimit,
                    OrderChequeCreditDue,
                    OrderChequeCurrentLimit,
                    OrderCreditLimit,
                    OrderCreditDue,
                    OrderCreditCurrentLimit,
                    AllDepositQty,
                    AllDepositValue,
                    IOUQty,
                    IOUValue,
                    X,
                    Y,
                    UnderCollectionBalance);
        }
        return customers;
    }

    //navigate customers
    public Cursor getCustomersNavigation() {
        //take copy from db
        SQLiteDatabase db = this.getReadableDatabase();
        String Queryselect = "select * from tbl_Customers";
        Log.v("@@@CustomersBarcode", "customersBarcode");
        Cursor cursor = db.rawQuery(Queryselect, null);
        return cursor;
    }



    public int getCustomerInRouteDay() {
        //take copy from db
        SQLiteDatabase db = this.getReadableDatabase();
        String Queryselect = "select count(*) from tbl_Customers  where InTodayRoute=1";
        Cursor cursor = db.rawQuery(Queryselect, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
Log.v("@@@CountCustomers",""+count);
        return count;
    }


    public int getCustomerVistedToday() {
        //take copy from db
        SQLiteDatabase db = this.getReadableDatabase();
        String Queryselect = "select count(*) from tbl_Customers  where IsVisited=1";
        Cursor cursor = db.rawQuery(Queryselect, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        return count;
    }
    public void MakeCustomerVistedStatus(String id) {
        //take copy from db
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("IsVisited","1");
       db.update("tbl_Customers", cv, "CustomerNumber="+id, null);


    }
    public int GetRemainCustomerToviset() {
        int countTotalCust=this.getCustomerVistedToday();
        int VistedTobe=this.getCustomerInRouteDay();
        int totalUnvisted=VistedTobe-countTotalCust;
        return totalUnvisted;
    }
    public float GetProgressBarPercentage() {
        float countTotalCust=this.getCustomerVistedToday();
        float VistedTobe=this.getCustomerInRouteDay();
        float totalProgress=(VistedTobe-countTotalCust);
        if(countTotalCust > 0){
            float GetPercentage=(countTotalCust/VistedTobe)*100;
            Log.v("@@countTotalCust",""+countTotalCust);
            Log.v("@@VistedTobe",""+VistedTobe);
            Log.v("@@infunctionPer8888",""+GetPercentage);
            return GetPercentage;

        }else {
            float GetPercentage=0;
            Log.v("@@infunctionPer",""+GetPercentage);
            return GetPercentage;
        }


    }

//================================================================End customers ===============================================

}





