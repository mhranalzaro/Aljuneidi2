package com.example.aljuneidi

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.aljuneidi.ui.main.SectionsPagerAdapter
import com.google.zxing.integration.android.IntentIntegrator

class Customer_dataPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_data_page)
        var CustomerBarcode=intent.getStringExtra("CustomerBarcode")
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager,CustomerBarcode)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            val intent = Intent(this@Customer_dataPage, salesman_customers::class.java)
            startActivity(intent)
            finish()
       //     Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
              //  .setAction("Action", null).show()
        }






    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater = menuInflater
        menuInflater.inflate(R.menu.close_button, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        when (item!!.itemId) {
            R.id.close_AB -> {
                run {
                    val intent = Intent(this@Customer_dataPage, salesman_customers::class.java)
                    startActivity(intent)
                    finish()
                }

                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


    // handle back touch
    override fun onBackPressed() {
        val intent = Intent(this@Customer_dataPage, salesman_customers::class.java)
        startActivity(intent)
        finish()


    }

}