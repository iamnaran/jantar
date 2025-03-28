package com.iamnaran.jantar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.iamnaran.common.data.PrefDataStoreHelper
import com.iamnaran.designsystem.theme.JantarTheme
import com.iamnaran.main.presentation.MainScreen
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {

    private val dataStoreHelper: PrefDataStoreHelper by inject()

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashScreen = installSplashScreen()
        setContent {
            JantarTheme {
                KoinAndroidContext {
                    val isUserLoggedIn by dataStoreHelper.getLoggedInStatus().collectAsState(initial = null)
                    // Keep splash screen visible until isUserLoggedIn is loaded
                    splashScreen.setKeepOnScreenCondition {
                        isUserLoggedIn == null
                    }
                    if (isUserLoggedIn == true){
                        MainScreen(true)
                    }else{
                        MainScreen(false)
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