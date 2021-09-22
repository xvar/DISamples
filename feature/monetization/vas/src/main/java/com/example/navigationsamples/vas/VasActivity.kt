package com.example.navigationsamples.vas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.util.ToastTextMaker
//import io.michaelrocks.lightsaber.Lightsaber
//import javax.inject.Inject

class VasActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vas)

        /*//todo make wrapper
        val injector = Lightsaber.Builder().build().createInjector(Unit)
        injector.injectMembers(this)

        val message = toastTextMaker.makeMessage(this.toString())
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        */
    }
}