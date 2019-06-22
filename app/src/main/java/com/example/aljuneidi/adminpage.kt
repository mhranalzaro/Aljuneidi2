package com.example.aljuneidi
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.widget.Toast
import com.example.aljuneidi.adminClasses.userinformation
import kotlinx.android.synthetic.main.activity_adminpage.*
import com.example.aljuneidi.syncClass.syncdata
import android.os.AsyncTask.execute
import android.os.AsyncTask
import android.R.string.cancel
import android.content.Intent
import android.os.AsyncTask.execute
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import android.R.attr.x




public open class adminpage : AppCompatActivity() {
    lateinit var pDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminpage)
  
        //hide action bar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "تسجيل الدخول"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

//check intered data and update userinformation
        btn_layout_sync.setOnClickListener(){

            val intent = Intent(this, getdataServer::class.java)
            startActivity(intent)
        }

        fun checkDataentired() {
            var db=databaseHelper(this)

            var userinfo = db.GetuserInfo()
            val Salesman = tv_salesman.getText().toString().toInt()
            val ServerIP = tv_serverip.getText().toString()
            val PrinterID = tv_printername.getText().toString()
            val PrinterPassword = tv_printerpassword.getText().toString()

             if (isEmpty(ServerIP)) {

                Toast.makeText(this@adminpage, "يجب ادخال عنوان السيرفر", Toast.LENGTH_SHORT).show()

            } else if(isEmpty(PrinterID) ){
                Toast.makeText(this@adminpage, "يجب ادخال اسم الطابعة", Toast.LENGTH_SHORT).show()
            }else{
            try{
                userinfo = userinformation(1, ServerIP, Salesman, PrinterID, PrinterPassword)
                db.updateUserInfo(userinfo)
                Toast.makeText(this@adminpage, "تم حفظ التعديلات بنجاح", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(this@adminpage, e.toString(), Toast.LENGTH_SHORT).show()

            }



            }
        }

//end userinformation update



        btn_syncData.setOnClickListener(){
            //go to syncdata class and parse json data
            var syncdata=syncdata()
            syncdata.get_tbl_Users(this@adminpage)

        }



        //update userinformation
        btn_UserInfoUpdate.setOnClickListener(){
            checkDataentired()

        }


    }






    //go back by arrow
    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this@adminpage, loginpage::class.java)
        startActivity(intent)
        finish()

     //   onBackPressed()
        return true
    }



    //desplay userinformation
    override fun onStart() {

        //get userinfo and display it in editboxes
        var db= databaseHelper(this)
        var userinfo = db.GetuserInfo()
        tv_salesman.setText(""+ userinfo.salesman)
        tv_serverip.setText(userinfo.serverIP)
        tv_printername.setText(userinfo.printerID)
        tv_printerpassword.setText(userinfo.printerPassword)

        super.onStart()
    }



}


