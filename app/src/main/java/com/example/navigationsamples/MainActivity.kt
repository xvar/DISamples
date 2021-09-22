package com.example.navigationsamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.navigationsamples.di.LightsaberComponent
import com.example.navigationsamples.vas.VasActivity
import com.example.util.ToastTextMaker
import io.michaelrocks.lightsaber.Lightsaber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    private lateinit var toastTextMaker: ToastTextMaker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val injector = Lightsaber.Builder().build().createInjector(LightsaberComponent())
        injector.injectMembers(this)

        val message = toastTextMaker.makeMessage(this.toString())
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        //val intent = Intent(this, VasActivity::class.java)
        //startActivity(intent)
    }
}