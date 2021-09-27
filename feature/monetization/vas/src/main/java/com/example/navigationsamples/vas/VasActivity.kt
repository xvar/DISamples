package com.example.navigationsamples.vas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.util.ToastTextProvider
import javax.inject.Inject


class VasActivity : AppCompatActivity() {

    @Inject
    private lateinit var toastTextMaker: ToastTextProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vas)

        val message = toastTextMaker.makeMessage(this.toString())

        findViewById<TextView>(R.id.label_tv).text = message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}