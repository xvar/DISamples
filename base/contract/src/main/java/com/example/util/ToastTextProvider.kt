package com.example.util

import io.michaelrocks.lightsaber.ProvidedAs
import javax.inject.Inject

interface ToastTextProvider {
    fun makeMessage(screenName: String, scopeName: String = "") : String
}

@ProvidedAs(ToastTextProvider::class)
class ToastTextProviderImpl @Inject constructor() : ToastTextProvider {

    override fun makeMessage(screenName: String, scopeName: String) : String {
        return "we are inside $scopeName, $screenName"
    }

}