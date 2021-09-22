package com.example.util

import javax.inject.Inject

class ToastTextMaker @Inject constructor() {

    fun makeMessage(screenName: String, scopeName: String = "") : String {
        return "we are inside $scopeName, $screenName"
    }

}