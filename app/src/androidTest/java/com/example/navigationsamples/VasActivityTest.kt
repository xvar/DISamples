package com.example.navigationsamples

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.agoda.kakao.text.KTextView
import com.example.navigationsamples.vas.R
import com.example.navigationsamples.vas.VasActivity
import com.example.navigationsamples.vas.di.VasComponent
import com.example.navigationsamples.vas.di.VasModule
import com.example.util.ToastTextProvider
import com.example.util.TweetPartProvider
import com.joom.lightsaber.Component
import com.joom.lightsaber.Import
import com.joom.lightsaber.Module
import com.joom.lightsaber.Provide
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider


//Скрин не объектом, а классом
class VasScreen : KScreen<VasScreen>() {

    override val layoutId: Int = R.layout.activity_vas
    override val viewClass = VasActivity::class.java

    val label = KTextView { withId(R.id.label_tv) }

}

private const val TEST_TOAST = "Some test toast string"

@Module
class VasTestModule {

    @Provide
    @Named("test")
    fun provideTestToastProvider(tweetPartProvider: TweetPartProvider) : ToastTextProvider {
        return object: ToastTextProvider {
            override fun makeMessage(screenName: String, scopeName: String): String {
                return TEST_TOAST + " ${tweetPartProvider.tweetPart}"
            }
        }
    }

}

@Component
class VasTestComponent {
    @Import
    fun importVasModule() : VasModule {
        return VasModule()
    }

    @Import
    fun importVasTestModule() : VasTestModule {
        return VasTestModule()
    }

}

class VasActivityTest : TestCase() {

    private val appInitScreen = VasScreen()
    lateinit var activityScenario : ActivityScenario<VasActivity>

    @field:[Inject Named("test")]
    lateinit var testToastProvider: ToastTextProvider

    fun setupToastTextProvider() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as Application
        appContext.lightsaber.createInjector(VasTestComponent())
            .injectMembers(this)

        appContext.lightsaber =  appContext.lightsaber.newBuilder()
            .addProviderInterceptor { chain, key ->
                if (key.type === ToastTextProvider::class.java) {
                    Provider<Any?> {
                        return@Provider testToastProvider
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
            activityScenario = ActivityScenario.launch(VasActivity::class.java)
        }.after {
            activityScenario.close()
        }.run {
            step("Open App Init") {
                appInitScreen {
                    label {
                        isDisplayed()
                         this.containsText(TEST_TOAST)
                    }
                }
            }
        }
    }

}