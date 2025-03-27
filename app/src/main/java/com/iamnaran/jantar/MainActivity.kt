package com.iamnaran.jantar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.iamnaran.common.data.PrefDataStoreHelper
import com.iamnaran.common.dispatcher.DispatcherType
import com.iamnaran.designsystem.theme.JantarTheme
import com.iamnaran.main.presentation.MainScreen
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {

    private val dataStoreHelper: PrefDataStoreHelper by inject()

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()

        lifecycleScope.launch {
            val isLoggedIn = dataStoreHelper.isLoggedIn()
            setContent {
                JantarTheme {
                    KoinAndroidContext {
                        MainScreen(isLoggedIn)
                    }
                }
            }

        }


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JantarTheme {

    }
}