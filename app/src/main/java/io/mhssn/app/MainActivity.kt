package io.mhssn.app

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val installApp01 = findViewById<Button>(R.id.install_app01)
        val installApp02 = findViewById<Button>(R.id.install_app02)

        installApp01.setOnClickListener {
            val app01 = resources.openRawResourceFd(R.raw.app01)
            viewModel.install(app01)
        }
        installApp02.setOnClickListener {
            val app02 = resources.openRawResourceFd(R.raw.app02)
            viewModel.install(app02)
        }
    }
}