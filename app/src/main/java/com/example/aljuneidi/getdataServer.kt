package com.example.aljuneidi

import android.R.attr.*
import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_getdata_server.*
import org.json.JSONArray
import org.json.JSONObject
import android.support.v4.os.HandlerCompat.postDelayed
import android.widget.ArrayAdapter

import android.content.Intent
import android.graphics.Color
import com.example.aljuneidi.getdataServer as getdataServer1
import android.text.Html
import android.text.Layout
import android.view.Gravity
import android.R.attr.gravity
import android.widget.LinearLayout




class getdataServer : AppCompatActivity() {
    var mProgressStatus = 0
    val values = ArrayList<String>()
    val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getdata_server)
        //reSyncDataFromServer
     /*   btn_retrysync.setOnClickListener(){
            finish()
            startActivity(getIntent())


        }*/

        //Exist and Go back button After succesful syncData
        btn_ExistAndGoBack.setOnClickListener(){
            val intent = Intent(this, adminpage::class.java)
            startActivity(intent)
            finish()
        }
        //back to login Page
        btn_LoginBageReturnBack.setOnClickListener(){
            val intent = Intent(this, loginpage::class.java)
            startActivity(intent)
            finish()
        }

        Thread(Runnable {
              run() {
                  values.add("Start Getting Data..")
                  lst_getdataserver.setAdapter(arrayAdapter)
                  progress()
                  mHandler.post {
                     get_tbl_Users(this)
                     get_tbl_Items(this)
                     get_tbl_Salesman(this)
                     get_tbl_customers(this)
                        //  btn_retrysync.visibility=View.VISIBLE
                      btn_ExistAndGoBack.visibility = View.VISIBLE
                      btn_LoginBageReturnBack.visibility=View.VISIBLE

                      }

                      }




        }).start()



    }

fun progress(){

    while (mProgressStatus < 100) {
        mProgressStatus++

        android.os.SystemClock.sleep(50)
        mHandler.post {
            tv_ProgressPercentage.setText("$mProgressStatus/100");

            prog_GetDataServer.progress = mProgressStatus

        }


    }


}
    fun get_tbl_Users( ctx: Context) {

        var db= databaseHelper(ctx)
        var userinfo = db.GetuserInfo()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(ctx)
        val url: String = "http://movizhouse.com/juneidiapp/getusers.php?id="+userinfo.salesman
        // Request a string response from the provided URL.
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
        var Delete_TBL_USER_res    = db.Delete_tbl_Users()
        if(Delete_TBL_USER_res ==true){
            values.add("Empty tbl_Users successfully")
            lst_getdataserver.setAdapter(arrayAdapter)
        }else{
            values.add("Error While Empty tbl_Users ")
            lst_getdataserver.setAdapter(arrayAdapter)
        }
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                //button exist

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")
                val count = jsonArray.length()

                for (i in 0 until jsonArray.length()) {
                    try {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        var Num = jsonInner.get("Num").toString().toInt()
                        var UserID = jsonInner.get("UserID").toString()
                        var GroupNum = jsonInner.get("GroupNum").toString().toInt()
                        var Department = jsonInner.get("Department").toString().toInt()
                        var BoxNo = jsonInner.get("BoxNo").toString().toInt()
                        var SalmanNum = jsonInner.get("SalmanNum").toString().toInt()
                        var SuperVisorNum = jsonInner.get("SuperVisorNum").toString().toInt()
                        var ProductionDepNum = jsonInner.get("ProductionDepNum").toString().toInt()
                        var Modification = jsonInner.get("Modification").toString()
                        Log.v("@@@@@", Modification)
                        Log.v("@@@@@", UserID)
                        Log.v("@@@@@Count Rows", count.toString())
                        //empty table


                        //insertData
                    var InsertTotbl_Useres=    db.URL_tbl_Users(
                            Num,
                            UserID,
                            GroupNum,
                            Department,
                            BoxNo,
                            SalmanNum,
                            SuperVisorNum,
                            ProductionDepNum,
                            Modification
                        )

                        if(InsertTotbl_Useres ==true){
                            values.add("tbl_Users "+count +" rows added successfully")
                            lst_getdataserver.setAdapter(arrayAdapter)
                        }else{
                            values.add("Error While Insert Data To tbl_Users ")
                            lst_getdataserver.setAdapter(arrayAdapter)
                        }

                    }catch (e :Exception){
                        Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()
                    }

                }

               // Toast.makeText(ctx, "تمت المزامنة بنجاح..", Toast.LENGTH_SHORT).show()
            },

            Response.ErrorListener(){error ->

                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)


                values.add("Error tbl_Users: $error")
                lst_getdataserver.setBackgroundColor(Color.RED)
                lst_getdataserver.setAdapter(arrayAdapter)


                // Toast.makeText(ctx, error.toString(), Toast.LENGTH_SHORT).show()


            }


        )

        queue.add(stringReq)


    }
    fun get_tbl_Items( ctx: Context){

        var db= databaseHelper(ctx)
     //   var userinfo = db.GetuserInfo()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(ctx)
        val url: String = "http://movizhouse.com/juneidiapp/getitems.php"
        // Request a string response from the provided URL.
         var tbl_Items_ResDelete:Boolean=false
        var tbl_Items_ResInsert:Boolean=false
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
        tbl_Items_ResDelete = db.Delete_tbl_Items()
        if(tbl_Items_ResDelete ==true){
            values.add("Empty tbl_Items successfully")
            lst_getdataserver.setAdapter(arrayAdapter)
        }else{
            values.add("Error While Empty tbl_Items ")
            lst_getdataserver.setAdapter(arrayAdapter)
        }
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                //button exist
                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArrayT: JSONArray = jsonObj.getJSONArray("items")
                val count = jsonArrayT.length()

                for (i in 0 until count) {

                        var jsonInner: JSONObject = jsonArrayT.getJSONObject(i)
                        var SalesmanNumber= jsonInner.get("SalesmanNumber").toString().toInt()
                        var Number= jsonInner.get("Number").toString()
                        var NameEnglish= jsonInner.get("NameEnglish").toString()
                        var NameArabic= jsonInner.get("NameArabic").toString()
                        var CategID= jsonInner.get("CategID").toString()
                        var Unit1Barcode= jsonInner.get("Unit1Barcode").toString()
                        var Unit2Barcode= jsonInner.get("Unit2Barcode").toString()
                        var Unit3Barcode= jsonInner.get("Unit3Barcode").toString()
                        var Unit4Barcode= jsonInner.get("Unit4Barcode").toString()
                        var SaleAvailableQty= jsonInner.get("SaleAvailableQty").toString()
                        var ReservedQty= jsonInner.get("ReservedQty").toString()
                        var DamagedQty= jsonInner.get("DamagedQty").toString()
                        var UsedForSales= jsonInner.get("UsedForSales").toString()
                        var UsedForDistribution= jsonInner.get("UsedForDistribution").toString()
                        var UsedForShelving= jsonInner.get("UsedForShelving").toString()
                        var UsedForPricing= jsonInner.get("UsedForPricing").toString()
                        var UsedForReturn= jsonInner.get("UsedForReturn").toString()
                        var UsedForOrder= jsonInner.get("UsedForOrder").toString()
                        var UsedForDeposit= jsonInner.get("UsedForDeposit").toString()
                        var UsedForPrize= jsonInner.get("UsedForPrize").toString()
                        var Unit1DescID= jsonInner.get("Unit1DescID").toString()
                        var Unit2DescID= jsonInner.get("Unit2DescID").toString()
                        var Unit3DescID= jsonInner.get("Unit3DescID").toString()
                        var Unit4DescID= jsonInner.get("Unit4DescID").toString()
                        var Unit1DescEng= jsonInner.get("Unit1DescEng").toString()
                        var Unit1DescArabic= jsonInner.get("Unit1DescArabic").toString()
                        var Unit2DescArabic= jsonInner.get("Unit2DescArabic").toString()
                        var Unit2DescEng= jsonInner.get("Unit2DescEng").toString()
                        var Unit3DescEng= jsonInner.get("Unit3DescEng").toString()
                        var Unit3DescArabic= jsonInner.get("Unit3DescArabic").toString()
                        var Unit4DescEng= jsonInner.get("Unit4DescEng").toString()
                        var Unit4DescArabic= jsonInner.get("Unit4DescArabic").toString()
                        var PackSize12= jsonInner.get("PackSize12").toString()
                        var PackSize23= jsonInner.get("PackSize23").toString()
                        var PackSize34= jsonInner.get("PackSize34").toString()
                        var Tax1Perc= jsonInner.get("Tax1Perc").toString()
                        var Tax2Perc= jsonInner.get("Tax2Perc").toString()
                        var Tax3Perc= jsonInner.get("Tax3Perc").toString()
                        var NumberOfUnits= jsonInner.get("NumberOfUnits").toString()
                        var DefLoadUnit= jsonInner.get("DefLoadUnit").toString()
                        var NetSalesQty= jsonInner.get("NetSalesQty").toString()
                        var NetFreeQty= jsonInner.get("NetFreeQty").toString()
                        var CustomField1= jsonInner.get("CustomField1").toString()
                        var CustomField2= jsonInner.get("CustomField2").toString()
                        var HasPromotion= jsonInner.get("HasPromotion").toString()
                        var MKeyIsUsed= jsonInner.get("MKeyIsUsed").toString()
                        var RequestedQty= jsonInner.get("RequestedQty").toString()
                        var ReturnedQty= jsonInner.get("ReturnedQty").toString()
                        var ReturnedStatus= jsonInner.get("ReturnedStatus").toString()
                        var RecordIsSelected= jsonInner.get("RecordIsSelected").toString()
                        var PriceListRefNumber= jsonInner.get("PriceListRefNumber").toString()
                        var TargetQty= jsonInner.get("TargetQty").toString()
                        var TodayTarget= jsonInner.get("TodayTarget").toString()
                        var AchievedQty= jsonInner.get("AchievedQty").toString()
                        var IsFixtureItem= jsonInner.get("IsFixtureItem").toString()
                        var WHQty= jsonInner.get("WHQty").toString()
                        var DisplayOrder= jsonInner.get("DisplayOrder").toString()
                        var GlassItemNumber= jsonInner.get("GlassItemNumber").toString()
                        var ShellItemNumber= jsonInner.get("ShellItemNumber").toString()
                        var ItemDefaultPrice= jsonInner.get("ItemDefaultPrice").toString()
                        var TaxType= jsonInner.get("TaxType").toString()

                        Log.v("@@@@@", NameArabic)
                        Log.v("@@@@@Count Rows", count.toString())
                        //empty table
                    try {
                        //insertData
                        tbl_Items_ResInsert=    db.URL_tbl_Items(
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
                        )


                    }catch (e :Exception){
                        Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()
                    }

                }

                if(tbl_Items_ResInsert ==true){
                    values.add("tbl_Items "+count +" rows added successfully")
                    lst_getdataserver.setAdapter(arrayAdapter)
                }else{
                    values.add("Error While Insert Data To tbl_Items ")
                    lst_getdataserver.setAdapter(arrayAdapter)
                }

                // Toast.makeText(ctx, "تمت المزامنة بنجاح..", Toast.LENGTH_SHORT).show()
            },

            Response.ErrorListener(){error ->

                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
                values.add("Error tbl_Items: $error")
                lst_getdataserver.setBackgroundColor(Color.RED)
                lst_getdataserver.setAdapter(arrayAdapter)

                // Toast.makeText(ctx, error.toString(), Toast.LENGTH_SHORT).show()

            }

        )

        queue.add(stringReq)


    }

    fun get_tbl_Salesman( ctx: Context){

        var db= databaseHelper(ctx)
         var userinfo = db.GetuserInfo()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(ctx)
        val url: String = "http://movizhouse.com/juneidiapp/salesman.php?id="+userinfo.salesman
        // Request a string response from the provided URL.
        var tbl_Salesman_ResDelete:Boolean=false
        var tbl_Salesman_ResInsert:Boolean=false
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
        tbl_Salesman_ResDelete = db.Delete_tbl_Salesman()
        if(tbl_Salesman_ResDelete ==true){
            values.add("Empty tbl_Salesman successfully")
            lst_getdataserver.setAdapter(arrayAdapter)
        }else{
            values.add("Error While Empty tbl_Salesman")
            lst_getdataserver.setAdapter(arrayAdapter)
        }
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                //button exist

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArrayT: JSONArray = jsonObj.getJSONArray("salesman")
                val count = jsonArrayT.length()

                for (i in 0 until jsonArrayT.length()) {
                    try {
                        var jsonInner: JSONObject = jsonArrayT.getJSONObject(i)
                        var SalesmanNumber=jsonInner.get("SalesmanNumber").toString().toInt()
                        var RouteNameEnglish=jsonInner.get("RouteNameEnglish").toString()
                        var RouteNameArabic=jsonInner.get("RouteNameArabic").toString()
                        var SalesmanNameEng=jsonInner.get("SalesmanNameEng").toString()
                        var SalesmanNameArabic=jsonInner.get("SalesmanNameArabic").toString()
                        var SalesmanUserID=jsonInner.get("SalesmanUserID").toString().toInt()
                        var SalesmanPassword=jsonInner.get("SalesmanPassword").toString()
                        var EnableSalesOperations=jsonInner.get("EnableSalesOperations").toString()
                        var EnableReturns=jsonInner.get("EnableReturns").toString()
                        var EnableDueInvCollection=jsonInner.get("EnableDueInvCollection").toString()
                        var EnableDistribution=jsonInner.get("EnableDistribution").toString()
                        var EnableShelving=jsonInner.get("EnableShelving").toString()
                        var EnablePricing=jsonInner.get("EnablePricing").toString()
                        var EnableFixtures=jsonInner.get("EnableFixtures").toString()
                        var MasterKeyBarcode=jsonInner.get("MasterKeyBarcode").toString()
                        var NextCashDocNumber=jsonInner.get("NextCashDocNumber").toString()
                        var NextCreditDocNumber=jsonInner.get("NextCreditDocNumber").toString()
                        var NextReturnDocNumber=jsonInner.get("NextReturnDocNumber").toString()
                        var NextReceiptDocNumber=jsonInner.get("NextReceiptDocNumber").toString()
                        var NextFixtureDocNumber=jsonInner.get("NextFixtureDocNumber").toString()
                        var NextLoadDocNumber=jsonInner.get("NextLoadDocNumber").toString()
                        var NextFreeVoucherDocNumber=jsonInner.get("NextFreeVoucherDocNumber").toString()
                        var NextDepositDocNumber=jsonInner.get("NextDepositDocNumber").toString()
                        var GroupID=jsonInner.get("GroupID").toString()
                        var TodayVisitsTarget=jsonInner.get("TodayVisitsTarget").toString()
                        var MonthVisitsTarget=jsonInner.get("MonthVisitsTarget").toString()
                        var MonthSalesTargetValue=jsonInner.get("MonthSalesTargetValue").toString()
                        var MTDSalesValue=jsonInner.get("MTDSalesValue").toString()
                        var VanStoreBarcode=jsonInner.get("VanStoreBarcode").toString()
                        var StoreID=jsonInner.get("StoreID").toString()
                        var NumOfItemsPerClass=jsonInner.get("NumOfItemsPerClass").toString()
                        var TodayNumberOfVisits=jsonInner.get("TodayNumberOfVisits").toString()
                        var TodayNumberOfEffectiveVisits=jsonInner.get("TodayNumberOfEffectiveVisits").toString()
                        var NumberOfCashSales=jsonInner.get("NumberOfCashSales").toString()
                        var NumberOfCreditSales=jsonInner.get("NumberOfCreditSales").toString()
                        var NumberOfReceipts=jsonInner.get("NumberOfReceipts").toString()
                        var NumberOfCollections=jsonInner.get("NumberOfCollections").toString()
                        var NumberOfReturns=jsonInner.get("NumberOfReturns").toString()
                        var NumberOfCashReturns=jsonInner.get("NumberOfCashReturns").toString()
                        var NumberOfCheques=jsonInner.get("NumberOfCheques").toString()
                        var CashSalesValue=jsonInner.get("CashSalesValue").toString()
                        var CreditSalesValue=jsonInner.get("CreditSalesValue").toString()
                        var CashReturnsValue=jsonInner.get("CashReturnsValue").toString()
                        var CreditCollectionValue=jsonInner.get("CreditCollectionValue").toString()
                        var TotPocketCashMoney=jsonInner.get("TotPocketCashMoney").toString()
                        var TotPocketChequeMoney=jsonInner.get("TotPocketChequeMoney").toString()
                        var EditingALoadOrder=jsonInner.get("EditingALoadOrder").toString()
                        var ScannedStartOfJourney=jsonInner.get("ScannedStartOfJourney").toString()
                        var StartMovTimeInSec=jsonInner.get("StartMovTimeInSec").toString()
                        var EndMovTimeInSec=jsonInner.get("EndMovTimeInSec").toString()
                        var CustTimeInSec=jsonInner.get("CustTimeInSec").toString()
                        var BreakTimeInSec=jsonInner.get("BreakTimeInSec").toString()
                        var EnablePrinting=jsonInner.get("EnablePrinting").toString()
                        var SupervisorID=jsonInner.get("SupervisorID").toString()
                        var SalesAreaManagerID=jsonInner.get("SalesAreaManagerID").toString()
                        var SalesManagerID=jsonInner.get("SalesManagerID").toString()
                        var OrderAllowed=jsonInner.get("OrderAllowed").toString().toInt()
                        var NextOrderNumber=jsonInner.get("NextOrderNumber").toString()
                        var NumberOfOrderTaking=jsonInner.get("NumberOfOrderTaking").toString()
                        var OrderTakingValue=jsonInner.get("OrderTakingValue").toString()
                        var NextFactoryReturnsSerial=jsonInner.get("NextFactoryReturnsSerial").toString()
                        var NextReturnsCheckSerial=jsonInner.get("NextReturnsCheckSerial").toString()
                        var NextSuggestedOrderSerial=jsonInner.get("NextSuggestedOrderSerial").toString()
                        var NextFOCSerialNumber=jsonInner.get("NextFOCSerialNumber").toString()
                        var NextPaymentVoucherSerial=jsonInner.get("NextPaymentVoucherSerial").toString()
                        var WHTransactionNumber=jsonInner.get("WHTransactionNumber").toString()

                        Log.v("@@@@@Nameeee", SalesmanNameEng)
                        Log.v("@@@@@Count RowsSalesman", count.toString())
                        //empty table

                        //insertData
                        tbl_Salesman_ResInsert=    db.URL_tbl_Salesman(
                            SalesmanNumber,
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
                            WHTransactionNumber
                        )


                    }catch (e :Exception){
                        values.add(e.message.toString())
                        lst_getdataserver.setAdapter(arrayAdapter)
                        Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()
                    }

                }

                if(tbl_Salesman_ResInsert ==true){
                    values.add("tbl_Salesman "+count +" rows added successfully")
                    lst_getdataserver.setAdapter(arrayAdapter)
                }else{
                    values.add("Error While Insert Data To tbl_salesman ")
                    lst_getdataserver.setAdapter(arrayAdapter)
                }

                // Toast.makeText(ctx, "تمت المزامنة بنجاح..", Toast.LENGTH_SHORT).show()
            },

            Response.ErrorListener(){error ->

                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
                values.add("Error tbl_Salesman: $error")
                lst_getdataserver.setBackgroundColor(Color.RED)
                lst_getdataserver.setAdapter(arrayAdapter)

                // Toast.makeText(ctx, error.toString(), Toast.LENGTH_SHORT).show()

            }

        )

        queue.add(stringReq)


    }


    fun get_tbl_customers( ctx: Context){

        var db= databaseHelper(ctx)
        var userinfo = db.GetuserInfo()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(ctx)
        val url: String = "http://movizhouse.com/juneidiapp/getcustomers.php?id="+userinfo.salesman
        // Request a string response from the provided URL.
        var tbl_Customers_ResDelete:Boolean=false
        var tbl_Customers_ResInsert:Boolean=false
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
        tbl_Customers_ResDelete = db.Delete_tbl_Customers()
        if(tbl_Customers_ResDelete ==true){
            values.add("Empty tbl_Customers successfully")
            lst_getdataserver.setAdapter(arrayAdapter)
        }else{
            values.add("Error While Empty tbl_Customers")
            lst_getdataserver.setAdapter(arrayAdapter)
        }
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                //button exist

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArrayT: JSONArray = jsonObj.getJSONArray("customers")
                val count = jsonArrayT.length()


                for (i in 0 until jsonArrayT.length()) {
                    try {
                        var jsonInner: JSONObject = jsonArrayT.getJSONObject(i)
                        var SalesmanNumber=	jsonInner.get("SalesmanNumber").toString().toInt()
                        var CustomerNumber=jsonInner.get("CustomerNumber").toString().toInt()
                        var EngName=jsonInner.get("EngName").toString()
                        var ArabicName=jsonInner.get("ArabicName").toString()
                        var CustomerBarcode=jsonInner.get("CustomerBarcode").toString()
                        var IsNewCustomer=jsonInner.get("IsNewCustomer").toString()
                        var InTodayRoute=jsonInner.get("InTodayRoute").toString()
                        var Sequence=jsonInner.get("Sequence").toString()
                        var ContactPerson1=jsonInner.get("ContactPerson1").toString()
                        var ContactPerson2=jsonInner.get("ContactPerson2").toString()
                        var HasNotes=jsonInner.get("HasNotes").toString()
                        var LastVisit=jsonInner.get("LastVisit").toString()
                        var LastSalesDate=jsonInner.get("LastSalesDate").toString()
                        var LastInvoiceNumber=jsonInner.get("LastInvoiceNumber").toString()
                        var LastInvoiceType=jsonInner.get("LastInvoiceType").toString()
                        var TargetVisits=jsonInner.get("TargetVisits").toString()
                        var TargetVisitsType=jsonInner.get("TargetVisitsType").toString()
                        var ActualVisits=jsonInner.get("ActualVisits").toString()
                        var GeoCatagoryLevelOneID=jsonInner.get("GeoCatagoryLevelOneID").toString()
                        var GeoCatagoryLevelTwoID=jsonInner.get("GeoCatagoryLevelTwoID").toString()
                        var GeoCatagoryLevelThreeID=jsonInner.get("GeoCatagoryLevelThreeID").toString()
                        var GeoCatagoryLevelFourID=jsonInner.get("GeoCatagoryLevelFourID").toString()
                        var ClassID=jsonInner.get("ClassID").toString()
                        var Tax1Applicable=jsonInner.get("Tax1Applicable").toString()
                        var Tax2Applicable=jsonInner.get("Tax2Applicable").toString()
                        var Tax3Applicable=jsonInner.get("Tax3Applicable").toString()
                        var AllowChequePayment=jsonInner.get("AllowChequePayment").toString()
                        var AllowCreditSales=jsonInner.get("AllowCreditSales").toString()
                        var MaxChequeValue=jsonInner.get("MaxChequeValue").toString()
                        var MaxChequePeriod=jsonInner.get("MaxChequePeriod").toString()
                        var ChequeCreditLimit=jsonInner.get("ChequeCreditLimit").toString()
                        var ChequesReturned=jsonInner.get("ChequesReturned").toString()
                        var CreditBalance=jsonInner.get("CreditBalance").toString()
                        var ChequeBalance=jsonInner.get("ChequeBalance").toString()
                        var BalanceDue=jsonInner.get("BalanceDue").toString()
                        var CreditLimit=jsonInner.get("CreditLimit").toString()
                        var CreditPeriod=jsonInner.get("CreditPeriod").toString()
                        var AllowOverLimitSales=jsonInner.get("AllowOverLimitSales").toString()
                        var AllowDueSales=jsonInner.get("AllowDueSales").toString()
                        var InvoiceRunningAvg=jsonInner.get("InvoiceRunningAvg").toString()
                        var InvoiceMTDAvg=jsonInner.get("InvoiceMTDAvg").toString()
                        var InvoiceLastQuartAvg=jsonInner.get("InvoiceLastQuartAvg").toString()
                        var ImportantDate=jsonInner.get("ImportantDate").toString()
                        var RouteDate=jsonInner.get("RouteDate").toString()
                        var IsVisited=jsonInner.get("IsVisited").toString()
                        var OrderChequeCreditLimit=jsonInner.get("OrderChequeCreditLimit").toString()
                        var OrderChequeCreditDue=jsonInner.get("OrderChequeCreditDue").toString()
                        var OrderChequeCurrentLimit=jsonInner.get("OrderChequeCurrentLimit").toString()
                        var OrderCreditLimit=jsonInner.get("OrderCreditLimit").toString()
                        var OrderCreditDue=jsonInner.get("OrderCreditDue").toString()
                        var OrderCreditCurrentLimit=jsonInner.get("OrderCreditCurrentLimit").toString()
                        var AllDepositQty=jsonInner.get("AllDepositQty").toString()
                        var AllDepositValue=jsonInner.get("AllDepositValue").toString()
                        var IOUQty=jsonInner.get("IOUQty").toString()
                        var IOUValue=jsonInner.get("IOUValue").toString()
                        var X=jsonInner.get("X").toString()
                        var Y=jsonInner.get("Y").toString()
                        var UnderCollectionBalance=jsonInner.get("UnderCollectionBalance").toString()


                        Log.v("@@@@@Nameeee", ArabicName)
                        Log.v("@@CountRowscusto", count.toString())
                        //empty table
                        //insertData
                        tbl_Customers_ResInsert=    db.URL_tbl_Customers(
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
                            UnderCollectionBalance
                        )


                    }catch (e :Exception){
                        values.add(e.message.toString())
                        lst_getdataserver.setAdapter(arrayAdapter)
                        Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()
                    }

                }

                if(tbl_Customers_ResInsert ==true){
                    values.add("tbl_Customers "+count +" rows added successfully")
                    lst_getdataserver.setAdapter(arrayAdapter)
                }else{
                    values.add("Error While Insert Data To tbl_Customers ")
                    lst_getdataserver.setAdapter(arrayAdapter)
                }

                // Toast.makeText(ctx, "تمت المزامنة بنجاح..", Toast.LENGTH_SHORT).show()
            },

            Response.ErrorListener(){error ->

                val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
                values.add("Error tbl_Customers: $error")
                lst_getdataserver.setBackgroundColor(Color.RED)
                lst_getdataserver.setAdapter(arrayAdapter)

                // Toast.makeText(ctx, error.toString(), Toast.LENGTH_SHORT).show()

            }

        )

        queue.add(stringReq)


    }





}
