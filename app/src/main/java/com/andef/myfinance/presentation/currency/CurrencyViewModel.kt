package com.andef.myfinance.presentation.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.usecases.GetAudRubUseCase
import com.andef.myfinance.domain.currency.usecases.GetBtcRubUseCase
import com.andef.myfinance.domain.currency.usecases.GetCadRubUseCase
import com.andef.myfinance.domain.currency.usecases.GetChfRubUseCase
import com.andef.myfinance.domain.currency.usecases.GetCnyRubUseCase
import com.andef.myfinance.domain.currency.usecases.GetEthRubUseCase
import com.andef.myfinance.domain.currency.usecases.GetEurRubUseCase
import com.andef.myfinance.domain.currency.usecases.GetGbpRubUseCase
import com.andef.myfinance.domain.currency.usecases.GetHkdRubUseCase
import com.andef.myfinance.domain.currency.usecases.GetJpyRubUseCase
import com.andef.myfinance.domain.currency.usecases.GetUsdRubUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val getAudRubUseCase: GetAudRubUseCase,
    private val getBtcRubUseCase: GetBtcRubUseCase,
    private val getCadRubUseCase: GetCadRubUseCase,
    private val getChfRubUseCase: GetChfRubUseCase,
    private val getCnyRubUseCase: GetCnyRubUseCase,
    private val getEthRubUseCase: GetEthRubUseCase,
    private val getEurRubUseCase: GetEurRubUseCase,
    private val getGbpRubUseCase: GetGbpRubUseCase,
    private val getJpyRubUseCase: GetJpyRubUseCase,
    private val getUsdRubUseCase: GetUsdRubUseCase,
    private val getHkdRubUseCase: GetHkdRubUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CurrencyState.Initial as CurrencyState)
    val state: StateFlow<CurrencyState> = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.value = CurrencyState.Error
    }

    private fun calcPercent(now: Double, before: Double) = ((now - before) / before) * 100

    fun loadCurrency(date: Date) {
        viewModelScope.launch(exceptionHandler) {
            _state.value = CurrencyState.Loading
            val allCurrency = mutableListOf<Pair<CurrencyRub, Double>>()
            val bestCurrency = withContext(Dispatchers.IO) {
                val usdRubNow = async { getUsdRubUseCase.execute() }
                val eurRubNow = async { getEurRubUseCase.execute() }
                val cnyRubNow = async { getCnyRubUseCase.execute() }
                val usdRubBefore = async { getUsdRubUseCase.execute(date) }
                val eurRubBefore = async { getEurRubUseCase.execute(date) }
                val cnyRubBefore = async { getCnyRubUseCase.execute(date) }

                val usdNow = usdRubNow.await()
                val eurNow = eurRubNow.await()
                val cnyNow = cnyRubNow.await()
                val usdBefore = usdRubBefore.await()
                val eurBefore = eurRubBefore.await()
                val cnyBefore = cnyRubBefore.await()

                val outList = mutableListOf<Pair<CurrencyRub, Double>>().apply {
                    add(usdNow to calcPercent(usdNow.amount, usdBefore.amount))
                    add(eurNow to calcPercent(eurNow.amount, eurBefore.amount))
                    add(cnyNow to calcPercent(cnyNow.amount, cnyBefore.amount))
                }
                outList
            }
            allCurrency.addAll(bestCurrency)
            _state.value = CurrencyState.CurrencyFirstPartWithPercent(allCurrency)
            val secondBestCurrency = withContext(Dispatchers.IO) {
                val jpyRubNow = async { getJpyRubUseCase.execute() }
                val gbpRubNow = async { getGbpRubUseCase.execute() }
                val btcRubNow = async { getBtcRubUseCase.execute() }
                val jpyRubBefore = async { getJpyRubUseCase.execute(date) }
                val gbpRubBefore = async { getGbpRubUseCase.execute(date) }
                val btcRubBefore = async { getBtcRubUseCase.execute(date) }

                val jpyNow = jpyRubNow.await()
                val gbpNow = gbpRubNow.await()
                val btcNow = btcRubNow.await()
                val jpyBefore = jpyRubBefore.await()
                val gbpBefore = gbpRubBefore.await()
                val btcBefore = btcRubBefore.await()

                val outList = mutableListOf<Pair<CurrencyRub, Double>>().apply {
                    add(jpyNow to calcPercent(jpyNow.amount, jpyBefore.amount))
                    add(gbpNow to calcPercent(gbpNow.amount, gbpBefore.amount))
                    add(btcNow to calcPercent(btcNow.amount, btcBefore.amount))
                }
                outList
            }
            allCurrency.addAll(secondBestCurrency)
            _state.value = CurrencyState.CurrencySecondPartWithPercent(allCurrency)
            val thirdBestCurrency = withContext(Dispatchers.IO) {
                val ethRubNow = async { getEthRubUseCase.execute() }
                val chfRubNow = async { getChfRubUseCase.execute() }
                val audRubNow = async { getAudRubUseCase.execute() }
                val ethRubBefore = async { getEthRubUseCase.execute(date) }
                val chfRubBefore = async { getChfRubUseCase.execute(date) }
                val audRubBefore = async { getAudRubUseCase.execute(date) }

                val ethNow = ethRubNow.await()
                val chfNow = chfRubNow.await()
                val audNow = audRubNow.await()
                val ethBefore = ethRubBefore.await()
                val chfBefore = chfRubBefore.await()
                val audBefore = audRubBefore.await()

                val outList = mutableListOf<Pair<CurrencyRub, Double>>().apply {
                    add(ethNow to calcPercent(ethNow.amount, ethBefore.amount))
                    add(chfNow to calcPercent(chfNow.amount, chfBefore.amount))
                    add(audNow to calcPercent(audNow.amount, audBefore.amount))
                }
                outList
            }
            allCurrency.addAll(thirdBestCurrency)
            _state.value = CurrencyState.CurrencyThirdPartWithPercent(allCurrency)
            val otherCurrency = withContext(Dispatchers.IO) {
                val cadRubNow = async { getCadRubUseCase.execute() }
                val hkdRubNow = async { getHkdRubUseCase.execute() }
                val cadRubBefore = async { getCadRubUseCase.execute(date) }
                val hkdRubBefore = async { getHkdRubUseCase.execute(date) }

                val cadNow = cadRubNow.await()
                val hkdNow = hkdRubNow.await()
                val cadBefore = cadRubBefore.await()
                val hkdBefore = hkdRubBefore.await()

                val outList = mutableListOf<Pair<CurrencyRub, Double>>().apply {
                    add(cadNow to calcPercent(cadNow.amount, cadBefore.amount))
                    add(hkdNow to calcPercent(hkdNow.amount, hkdBefore.amount))
                }
                outList
            }
            allCurrency.addAll(otherCurrency)
            _state.value = CurrencyState.CurrencyFourthPartWithPercent(allCurrency)
        }
    }
}