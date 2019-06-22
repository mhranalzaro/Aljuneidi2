package com.example.aljuneidi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_loginpage.*
import kotlinx.android.synthetic.main.activity_salesman_start_round.*

class salesman_start_round : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salesman_start_round)
        //hide action bar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "بداية المسار"
        //set back button

        btn_ExistRoundStart.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_StartRound.setOnClickListener(){
            if(tv_startRoundSymbol.text.toString().trim().isEmpty()) {

                tv_Errorstartround.setText("يجب ادخال رمز المسار الصحيح")
                tv_Errorstartround.visibility= View.VISIBLE


            }else if(tv_startRoundSymbol.text.toString()=="123"){
                val intent = Intent(this, salesman_customers::class.java)
                startActivity(intent)
                finish()

            }else{
                tv_Errorstartround.setText("يجب ادخال رمز المسار الصحيح")
                tv_Errorstartround.visibility= View.VISIBLE

            }

            }


        }
    }

