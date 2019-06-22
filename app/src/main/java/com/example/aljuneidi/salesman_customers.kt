package com.example.aljuneidi
import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.aljuneidi.customers.CustomerHomePage
import com.example.aljuneidi.ui.main.SectionsPagerAdapter

import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_salesman_customers.*

class salesman_customers : AppCompatActivity() {
    var scannedResult: String = ""

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salesman_customers)
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "مسار البيع"
        actionbar.setDisplayHomeAsUpEnabled(true)
        var db = databaseHelper(adminpage@ this)

        var TotalcustomersToday = db!!.getCustomerInRouteDay()
        if (TotalcustomersToday == null) {
        } else {
            tv_TotalWantedVisitToday.setText("" + TotalcustomersToday)
        }
        var CustomerVistedToday = db.getCustomerVistedToday()
        tv_VistedTodayDone.setText("" + CustomerVistedToday)
        var TotalRemainToVisted = db.GetRemainCustomerToviset()
        tv_RemainUnVisted.setText("" + TotalRemainToVisted)
        var progressBarPERcentage = db.GetProgressBarPercentage()
        Log.v("@@progressBarPERcentage", progressBarPERcentage.toString())
        prog_percantge.setProgress(progressBarPERcentage.toInt())

        var cursor = db!!.getCustomersNavigation()
        if (cursor.moveToFirst()) {
            val ArabicName = cursor.getString(cursor.getColumnIndex("ArabicName"))
            val CustomerBarcode = cursor.getString(cursor.getColumnIndex("CustomerBarcode"))
            tv_customersName.setText("" + ArabicName)
            tv_customersBarcode.setText("" + CustomerBarcode)
        }
        btn_NextCustomer.setOnClickListener() {
            if (cursor.moveToNext()) {
                val ArabicName = cursor.getString(cursor.getColumnIndex("ArabicName"))
                val CustomerBarcode = cursor.getString(cursor.getColumnIndex("CustomerBarcode"))
                tv_customersName.setText("" + ArabicName)
                tv_customersBarcode.setText("" + CustomerBarcode)
            } else {
                //handle end of rows here
            }

        }
        btn_BackCustomer.setOnClickListener() {
            if (cursor.moveToPrevious()) {
                val ArabicName = cursor.getString(cursor.getColumnIndex("ArabicName"))
                val CustomerBarcode = cursor.getString(cursor.getColumnIndex("CustomerBarcode"))
                tv_customersName.setText("" + ArabicName)
                tv_customersBarcode.setText("" + CustomerBarcode)
            } else {
                //handle end of rows here
            }
        }

        btn_Manualogin.setOnClickListener() {
            // just for test remember ===================
            var customer = db!!.getCustomers("11361010004")
            var IsVistedDon = db!!.MakeCustomerVistedStatus(customer.CustomerNumber.toString())
            val intent = Intent(this, Customer_dataPage::class.java)
            intent.putExtra("CustomerBarcode",customer.CustomerBarcode)
            startActivity(intent)


            /*
            val builder = AlertDialog.Builder(this@salesman_customers)
            builder.setTitle("حدث خطأ")
            builder.setMessage("الدخول اليدوي غير مفعل..")
            builder.setPositiveButton("خروج") { dialog, which ->
            }
            builder.show()

            */
        }

    }

    //go back by arrow
    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

        //   onBackPressed()
        return true
    }

    // handle back touch
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater = menuInflater
        menuInflater.inflate(R.menu.barcodecamera, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        when (item!!.itemId) {
            R.id.AB_camera -> {
                run {
                    IntentIntegrator(this@salesman_customers).initiateScan();
                }

                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {

            if (result.contents != null) {
                scannedResult = result.contents
                var db = databaseHelper(adminpage@ this)
                var customer = db!!.getCustomers(scannedResult)
                if (customer == null) {
                    val builder = AlertDialog.Builder(this@salesman_customers)
                    builder.setTitle("حدث خطأ ما")
                    builder.setMessage("قمت بمسح باركود خاطأ او غير مسجل")
                    builder.setPositiveButton("خروج") { dialog, which ->
                    }
                    builder.show()


                } else {

                    val intent = Intent(this, Customer_dataPage::class.java)
                    intent.putExtra("CustomerBarcode",customer.CustomerBarcode)
                    startActivity(intent)
                    var IsVistedDon = db!!.MakeCustomerVistedStatus(customer.CustomerNumber.toString())
                    if (IsVistedDon == null) {

                    } else {

                    }

                    tv_BarcodeResult.text = customer.ArabicName

                }

            } else {
                val builder = AlertDialog.Builder(this@salesman_customers)
                builder.setTitle("حدث خطأ ما")
                builder.setMessage("لم تقم بمسح باركود")
                builder.setPositiveButton("خروج") { dialog, which ->
                }
                builder.show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {

        outState?.putString("scannedResult", scannedResult)
        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.let {
            scannedResult = it.getString("scannedResult")
            tv_BarcodeResult.text = scannedResult
        }
    }


}

