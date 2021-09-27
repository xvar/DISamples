package com.example.navigationsamples.vas.di

import com.example.util.ToastTextProvider
import com.example.util.ToastTextProviderImpl
import com.example.util.TweetPartProvider
import com.example.util.TweetPartProviderImpl
import com.joom.lightsaber.ImportedBy
import com.joom.lightsaber.Module
import com.joom.lightsaber.Provide
import javax.inject.Singleton

@Module
@ImportedBy(VasComponent::class)
class VasModule {

    @Provide
    fun provideTweetProvider() : TweetPartProvider {
        return TweetPartProviderImpl()
    }

    @Provide
    @Singleton
    fun provideToastTextProvider(tweetPartProvider: TweetPartProvider) : ToastTextProvider {
        return ToastTextProviderImpl(tweetPartProvider)
    }

}