package com.example.aljuneidi.items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.aljuneidi.R
import kotlinx.android.synthetic.main.item_customet.view.*
import android.support.v7.widget.RecyclerView.ViewHolder
import android.widget.TextView
import android.widget.Toast
import android.widget.ListView


class ItemCust_addpter(context: Context, resource: Int, objects: ArrayList<Items_class>) :
    ArrayAdapter<Items_class>(context, resource, objects) {
    var Mresoure=resource

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var NameArabic = getItem(position).NameArabic
            var SaleAvailableQty = getItem(position).SaleAvailableQty
        var BarcodeItem = getItem(position).Unit1Barcode
            var RequstedQty: String = "0"
            var inflater = LayoutInflater.from(context)
            // var convertView=inflater.inflate(R.layout.activity_customer__sale,parent ,false)
            //   var convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_customer__sale, parent, false);
            var convertView = inflater.inflate(R.layout.item_customet, parent, false)


        convertView.tv_ItemName.setText("" + NameArabic)
            convertView.tv_AvialbeItem.setText("" + SaleAvailableQty)
            convertView.tv_RequstedItem.setText("" + RequstedQty)
        convertView.tv_ItemBarcode.setText("" + BarcodeItem)





        return convertView
    }



}