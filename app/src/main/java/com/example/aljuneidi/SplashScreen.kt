package com.example.aljuneidi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    internal var TimeOut = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, loginpage::class.java)
            startActivity(intent)
            finish()
        }, TimeOut.toLong())

        supportActionBar?.hide()

    }
}
