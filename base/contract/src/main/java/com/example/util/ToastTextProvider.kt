package com.example.util

import com.joom.lightsaber.ProvidedAs
import javax.inject.Inject
import kotlin.random.Random

interface TweetPartProvider {
    val tweetPart: String
}

@ProvidedAs(TweetPartProvider::class)
class TweetPartProviderImpl @Inject constructor() : TweetPartProvider {

    private val max = 30

    override val tweetPart: String
        get() {
            return "part [${Random.nextInt(max)}/$max]"
        }

}

interface ToastTextProvider {
    fun makeMessage(screenName: String, scopeName: String = "") : String
}

@ProvidedAs(ToastTextProvider::class)
class ToastTextProviderImpl @Inject constructor(
    private val tweetPartProvider: TweetPartProvider
) : ToastTextProvider {

    override fun makeMessage(screenName: String, scopeName: String) : String {
        return "${tweetPartProvider.tweetPart}, $this is inside $scopeName, $screenName"
    }

}