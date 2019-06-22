package com.example.aljuneidi.syncClass

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.aljuneidi.adminpage
import com.example.aljuneidi.databaseHelper
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONException
import java.io.UnsupportedEncodingException
import com.android.volley.VolleyLog
import kotlinx.android.synthetic.main.activity_adminpage.*



class syncdata:AppCompatActivity() {

    // Request a string response from the provided URL.



    fun get_tbl_Users( ctx: Context) {
        var db= databaseHelper(ctx)
        var userinfo = db.GetuserInfo()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(ctx)
        val url: String = "http://movizhouse.com/juneidiapp/getusers.php?id="+userinfo.salesman
        // Request a string response from the provided URL.

        var pDialog = ProgressDialog(ctx)
        pDialog.setMessage("الرجاء الانتظار")
        pDialog.setCanceledOnTouchOutside(false)
        pDialog.show()


        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                pDialog.dismiss()

                var strResp = response.toString()

                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("items")
                val count = jsonArray.length()
                for (i in 0 until jsonArray.length()) {
                    try {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)

                        var Num = jsonInner.get("Num").toString().toInt()
                        var UserID = jsonInner.get("UserID").toString()
                        var GroupNum = jsonInner.get("GroupNum").toString().toInt()
                        var Department = jsonInner.get("Department").toString().toInt()
                        var BoxNo = jsonInner.get("BoxNo").toString().toInt()
                        var SalmanNum = jsonInner.get("SalmanNum").toString().toInt()
                        var SuperVisorNum = jsonInner.get("SuperVisorNum").toString().toInt()
                        var ProductionDepNum = jsonInner.get("ProductionDepNum").toString().toInt()
                        var Modification = jsonInner.get("Modification").toString()


                        Log.v("@@@@@", Modification)
                        Log.v("@@@@@", UserID)
                        Log.v("@@@@@Count Rows", count.toString())
                        //empty table
                        db.Delete_tbl_Users()
                        //insertData
                        db.URL_tbl_Users(
                            Num,
                            UserID,
                            GroupNum,
                            Department,
                            BoxNo,
                            SalmanNum,
                            SuperVisorNum,
                            ProductionDepNum,
                            Modification
                        )

                    }catch (e :Exception){
                        Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()

                    }

                }

                Toast.makeText(ctx, "تمت المزامنة بنجاح..", Toast.LENGTH_SHORT).show()

            },

            Response.ErrorListener(){


                Toast.makeText(ctx, "حدث خطأ ما ...", Toast.LENGTH_SHORT).show()
                pDialog.dismiss()
            }


        )

        queue.add(stringReq)


    }


}





