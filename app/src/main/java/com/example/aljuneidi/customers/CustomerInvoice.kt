package com.example.aljuneidi.customers

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aljuneidi.Customer_Sale
import com.example.aljuneidi.R

import kotlinx.android.synthetic.main.freagment_invoice.view.*

class CustomerInvoice :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v= inflater.inflate(R.layout.freagment_invoice,container ,false)
        v.ly_StartCustomerSale.setOnClickListener(){
            val intent = Intent(context, Customer_Sale::class.java)
            startActivity(intent)

        }

        return v
    }

}