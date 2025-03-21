package com.andef.myfinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.presentation.MainViewModel
import com.andef.myfinance.ui.theme.MyFinanceTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyFinanceApplication).component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel(factory = viewModelFactory)
            val isDarkTheme = remember { mutableStateOf(viewModel.isDarkTheme()) }
            MyFinanceTheme(darkTheme = isDarkTheme.value, dynamicColor = false) {
                Scaffold {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(it),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                viewModel.changeThemeUseCase()
                                isDarkTheme.value = viewModel.isDarkTheme()
                            }
                        ) {
                            Text("AAAA")
                        }
                    }
                }

            }
        }
    }
}