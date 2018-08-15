package com.gaurav.beerlelo.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.gaurav.beerlelo.R
import com.gaurav.beerlelo.ui.activity.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(createIntent())
            finish()
        }, 3000)
    }

    private fun createIntent() = Intent(this, HomeActivity::class.java)

}
