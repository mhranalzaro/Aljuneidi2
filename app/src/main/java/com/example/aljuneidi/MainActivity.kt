package com.example.aljuneidi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        var db= databaseHelper(adminpage@this)
        var userinfo = db.GetuserInfo()
        var salesman = db.getSalesman(userinfo.salesman)
        tv_SalesmanNameDashboard.setText(salesman.SalesmanNameArabic)

        tv_SalesmanNumberDashboard.setText(""+salesman.SalesmanNumber)

        ly_SalesmanTrip.setOnClickListener(){
            val intent = Intent(this, salesman_start_round::class.java)
            startActivity(intent)
            finish()
        }
        ly_ExistSalesman.setOnClickListener(){
            val intent = Intent(this, loginpage::class.java)
            startActivity(intent)
            finish()
        }
    }
}
