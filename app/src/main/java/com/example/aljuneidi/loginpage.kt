package com.example.aljuneidi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_loginpage.*
import kotlinx.android.synthetic.main.activity_adminpage.*

class loginpage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)
        supportActionBar?.hide()
       var db= databaseHelper(adminpage@this)
       var userinfo = db.GetuserInfo()
     Log.v("@@@Salesman",userinfo.salesman.toString())

     var salesman = db!!.getSalesman(userinfo.salesman)
        if(salesman==null){

        }else{
            //   Log.v("@@@SalesmanName",salesman!!.SalesmanNameArabic)
            tv_salesmanName.setText(""+salesman!!.SalesmanNameArabic)
        }


        btn_login.setOnClickListener(){

            if(username.text.toString() =="admin" && password.text.toString() =="admin@j"){
                val intent = Intent(this, adminpage::class.java)
                startActivity(intent)
                tv_ErrorLogin.visibility=View.INVISIBLE
             }else if(username.text.toString().trim().isEmpty() || password.text.toString().trim().isEmpty()){

                tv_ErrorLogin.setText("تأكد من اسم المستخدم وكلمة المرور")
                tv_ErrorLogin.visibility=View.VISIBLE

            }
            else{
                var loginResult=  db.CheckSalesmanLogin(username.text.toString().toInt(),password.text.toString())
                if(loginResult==1){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    tv_ErrorLogin.visibility=View.INVISIBLE
                }else {
                    tv_ErrorLogin.setText("تأكد من اسم المستخدم وكلمة المرور")
                    tv_ErrorLogin.visibility=View.VISIBLE

                }

            }
        }

    }
}
