package com.example.navigationsamples.vas.di

import io.michaelrocks.lightsaber.ImportedBy
import io.michaelrocks.lightsaber.Module

@Module(isDefault = true)
@ImportedBy(VasComponent::class)
internal class VasModule {
}