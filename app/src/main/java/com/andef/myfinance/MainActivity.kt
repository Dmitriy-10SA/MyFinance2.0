package com.andef.myfinance

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import com.andef.myfinance.data.network.currencyrub.api.CurrencyRubApiFactory
import com.andef.myfinance.data.network.currencyrub.dto.aud.AudRubDtoToAudRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.btc.BtcRubDtoToBtcRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.cad.CadRubDtoToCadRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.chf.ChfRubDtoToChfRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.cny.CnyRubDtoToCnyRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.eth.EthRubDtoToEthRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.eur.EurRubDtoToEurRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.gbp.GbpRubDtoToGbpRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.jpy.JpyRubDtoToJpyRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.usd.UsdRubDtoToUsdRubMapper
import com.andef.myfinance.data.network.currencyrub.repository.CurrencyRubRepositoryImpl
import com.andef.myfinance.ui.theme.MyFinanceTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinanceTheme {
                Scaffold {
                    Text("ASFASFFFFFFFFFFFFFFFFFFFFFFF", Modifier.padding(it).clickable {
                        GlobalScope.launch {
                            val api = CurrencyRubApiFactory.getInstance()
                            val it = CurrencyRubRepositoryImpl(
                                api,
                                AudRubDtoToAudRubMapper(),
                                BtcRubDtoToBtcRubMapper(),
                                CadRubDtoToCadRubMapper(),
                                ChfRubDtoToChfRubMapper(),
                                CnyRubDtoToCnyRubMapper(),
                                EthRubDtoToEthRubMapper(),
                                EurRubDtoToEurRubMapper(),
                                GbpRubDtoToGbpRubMapper(),
                                JpyRubDtoToJpyRubMapper(),
                                UsdRubDtoToUsdRubMapper()
                            ).getCurrencyRubList()
                            Log.d("MAIN_ACTIVITY", it.toString())
                        }
                    })
                }
            }
        }
    }
}