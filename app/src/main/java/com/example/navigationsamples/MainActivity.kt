package com.example.navigationsamples

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.navigationsamples.vas.VasActivity
import com.example.util.ToastTextProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var toastTextProvider: ToastTextProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val message = toastTextProvider.makeMessage(this.toString())
        Toast.makeText(this,
            message,
            Toast.LENGTH_SHORT
        ).show()

        findViewById<TextView>(R.id.main_label).text = message

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, VasActivity::class.java)
            startActivity(intent)
        }, 3000)

    }
}