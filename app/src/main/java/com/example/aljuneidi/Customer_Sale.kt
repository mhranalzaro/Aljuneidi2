package com.example.aljuneidi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import com.example.aljuneidi.items.ItemCust_addpter
import kotlinx.android.synthetic.main.activity_customer__sale.*
import kotlin.collections.ArrayList
import android.widget.Toast
import com.example.aljuneidi.items.Items_class
import kotlinx.android.synthetic.main.item_customet.view.*
class Customer_Sale : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer__sale)
        var db = databaseHelper(this)
        var salesmanid = db.GetuserInfo()
        Log.v("@@@MMSalesman", salesmanid.salesman.toString())
        var userinfo = db.GetItems(222)
        var ItemList = lv_Items
//  val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mhran)

        val adapter = ItemCust_addpter(this, android.R.layout.simple_list_item_1, userinfo)
        val inflater = layoutInflater

        var Header = inflater.inflate(R.layout.item_list_header, ItemList, false)
        ItemList.addHeaderView(Header);
        ItemList.adapter = adapter

        ItemList.setOnItemClickListener { parent, view, position, id ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("حدث خطأ")
            builder.setMessage(""+adapter!!.getItem(position-1).Unit1Barcode)
            builder.setPositiveButton("خروج") { dialog, which ->
            }
            builder.show()
            Toast.makeText(this, "Clicked item :"+" "+position,Toast.LENGTH_SHORT).show()
        }


    }
}
