package com.example.navigationsamples.di

import io.michaelrocks.lightsaber.ImportedBy
import io.michaelrocks.lightsaber.Module

@Module(isDefault = true)
@ImportedBy(LightsaberComponent::class)
internal class LightsaberModule {
}