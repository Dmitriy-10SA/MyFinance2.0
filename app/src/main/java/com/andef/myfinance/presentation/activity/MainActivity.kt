package com.andef.myfinance.presentation.activity

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.andef.myfinance.R
import com.andef.myfinance.presentation.app.MyFinanceApplication
import com.andef.myfinance.presentation.ui.main.MainScreen
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import com.andef.myfinance.ui.theme.MyFinanceTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

//https://www.banki.ru/

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as MyFinanceApplication).component
    }

    private val settings by lazy {
        getSharedPreferences(PREFS_THEME_FILE, MODE_PRIVATE)
    }

    private val networkRequest by lazy {
        NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }

    private val connectivityManager by lazy {
        getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainActivityContent()
        }
    }

    @Composable
    private fun MainActivityContent() {
        val isFirstLaunch = remember { mutableStateOf(true) }
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        val networkCallback by lazy {
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    if (isFirstLaunch.value) {
                        isFirstLaunch.value = false
                    } else {
                        showNetworkSnackbar(
                            message = getString(R.string.find_network),
                            scope = scope,
                            snackbarHostState = snackbarHostState
                        )
                    }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    showNetworkSnackbar(
                        message = getString(R.string.search_network),
                        scope = scope,
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }
        LaunchedEffect(Unit) {
            connectivityManager.requestNetwork(networkRequest, networkCallback)
            checkNetwork(
                scope = scope,
                snackbarHostState = snackbarHostState,
                isFirstLaunch = isFirstLaunch
            )
        }


        val isDarkTheme = remember {
            mutableStateOf(settings.getBoolean(PREF_IS_DARK_THEME, false))
        }
        MyFinanceTheme(darkTheme = isDarkTheme.value, dynamicColor = false) {
            Scaffold(
                snackbarHost = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 32.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SnackbarHost(
                            hostState = snackbarHostState,
                            snackbar = {
                                Snackbar(
                                    snackbarData = it,
                                    containerColor = if (isDarkTheme.value) {
                                        colorResource(R.color.my_blue)
                                    } else {
                                        colorResource(R.color.my_orange)
                                    },
                                    contentColor = Color.White,
                                    actionColor = Color.White,
                                    actionContentColor = Color.White,
                                    dismissActionContentColor = Color.White,
                                    shape = RoundedCornerShape(10.dp)
                                )
                            }
                        )
                    }
                }
            ) { paddingValues ->
                MainScreen(
                    viewModelFactory = viewModelFactory,
                    isDarkTheme = isDarkTheme.value,
                    paddingValues = paddingValues,
                    onCheckedChangeClickListener = { isChecked ->
                        isDarkTheme.value = isChecked
                        val editor = settings.edit()
                        editor.putBoolean(PREF_IS_DARK_THEME, isDarkTheme.value)
                        editor.apply()
                        isFirstLaunch.value = true
                    }
                )
            }
        }
    }

    private fun checkNetwork(
        isFirstLaunch: MutableState<Boolean>,
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState
    ) {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        if (capabilities == null || !capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
            showNetworkSnackbar(
                message = getString(R.string.search_network),
                scope = scope,
                snackbarHostState = snackbarHostState
            )
            isFirstLaunch.value = false
        }
    }

    private fun showNetworkSnackbar(
        message: String,
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState
    ) {
        snackbarHostState.currentSnackbarData?.dismiss()
        scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short,
                withDismissAction = true
            )
        }
    }

    companion object {
        private const val PREFS_THEME_FILE = "theme"
        private const val PREF_IS_DARK_THEME = "is_dark"
    }
}