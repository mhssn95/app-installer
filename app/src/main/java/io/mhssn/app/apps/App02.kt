package io.mhssn.app.apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.mhssn.app.R
import io.mhssn.app03.App03

class App02 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app02)
        startActivity(Intent(this, App03::class.java))
    }
}