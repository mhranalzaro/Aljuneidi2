package com.example.aljuneidi.customers

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aljuneidi.R
import com.example.aljuneidi.databaseHelper
import kotlinx.android.synthetic.main.activity_customer_data_page.*
import kotlinx.android.synthetic.main.activity_customer_data_page.view.*
import kotlinx.android.synthetic.main.fragment_customer_information.view.*

class CustomerHomePage :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var CustomerBarcode = arguments!!.getString("CustomerBarcode")
        var v=inflater.inflate(R.layout.fragment_customer_information,container ,false)
        var db= databaseHelper(context)

   var CustInfo=     db.getCustomers(CustomerBarcode)
        v.tv_customerNameFragment.setText(""+CustInfo.ArabicName)
        v.tv_LastVisitFrag.setText(""+CustInfo.LastVisit)

        if(CustInfo.LastSalesDate=="null"){
            v.tv_LastSalesDate.setText(""+"0")
        }else {
            v.tv_LastSalesDate.setText(""+CustInfo.LastSalesDate)
        }

        v.tv_ContactPerson1.setText(""+CustInfo.ContactPerson1)

        // show payment method For customer
        if(CustInfo.AllowCreditSales=="1"){
            v.tv_AllowCreditSales.setText(""+"نقدي,شيكات")
        }else{
            v.tv_AllowCreditSales.setText(""+"نقدي")
        }
        v.tv_CreditBalanceFrage.setText(""+CustInfo.CreditBalance)
        v.tv_CreditLimitFrag.setText(""+CustInfo.CreditLimit)
        v.tv_CreditPeriodFrag.setText(""+CustInfo.CreditPeriod)
        v.tv_MaxChequePeriodFrag.setText(""+CustInfo.MaxChequePeriod)
        v.tv_XFrag.setText(""+CustInfo.X)
        v.tv_YFrag.setText(""+CustInfo.Y)

        return v
    }
}