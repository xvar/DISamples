package com.example.vas

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.agoda.kakao.text.KTextView
import com.example.navigationsamples.vas.R
import com.example.navigationsamples.vas.VasActivity
import com.example.navigationsamples.vas.di.VasComponent
import com.example.util.ToastTextProvider
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import io.michaelrocks.lightsaber.ProviderInterceptor

import io.michaelrocks.lightsaber.Lightsaber
import org.junit.Before
import javax.inject.Provider


//Скрин не объектом, а классом
class VasScreen : KScreen<VasScreen>() {

    override val layoutId: Int = R.layout.activity_vas
    override val viewClass = VasActivity::class.java

    val label = KTextView { withId(R.id.label_tv) }

}

class VasActivityTest : TestCase() {

    private val appInitScreen = VasScreen()
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(appInitScreen.viewClass)

    private val TEST_TOAST = "Some test toast string"

    fun setupToastTextProvider() {
            Lightsaber.get().newBuilder()
            .addProviderInterceptor { chain, key ->
                if (key.type === ToastTextProvider::class.java) {
                    Provider<Any?> {
                        object : ToastTextProvider {
                            override fun makeMessage(
                                screenName: String,
                                scopeName: String
                            ): String {
                                return TEST_TOAST
                            }
                        }
                    }
                } else {
                    chain.proceed(key)
                }
            }
            .build()
    }

    //Проверяем лого на стартовом скрине
    @Test
    fun checkLogoIsDisplayed() {
        before {
            setupToastTextProvider()
            activityScenarioRule.scenario.moveToState(Lifecycle.State.RESUMED)
        }.after {
            //do nothing
        }.run {
            step("Open App Init") {
                appInitScreen {
                    label {
                        isDisplayed()

                    }
                }
            }
        }
    }

}