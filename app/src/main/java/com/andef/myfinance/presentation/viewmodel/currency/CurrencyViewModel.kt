package com.andef.myfinance.presentation.viewmodel.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub
import com.andef.myfinance.domain.network.currency.usecases.aud.GetAudRubUseCase
import com.andef.myfinance.domain.network.currency.usecases.btc.GetBtcRubUseCase
import com.andef.myfinance.domain.network.currency.usecases.cad.GetCadRubUseCase
import com.andef.myfinance.domain.network.currency.usecases.chf.GetChfRubUseCase
import com.andef.myfinance.domain.network.currency.usecases.cny.GetCnyRubUseCase
import com.andef.myfinance.domain.network.currency.usecases.eth.GetEthRubUseCase
import com.andef.myfinance.domain.network.currency.usecases.eur.GetEurRubUseCase
import com.andef.myfinance.domain.network.currency.usecases.gbp.GetGbpRubUseCase
import com.andef.myfinance.domain.network.currency.usecases.hkd.GetHkdRubUseCase
import com.andef.myfinance.domain.network.currency.usecases.jpy.GetJpyRubUseCase
import com.andef.myfinance.domain.network.currency.usecases.usd.GetUsdRubUseCase
import com.andef.myfinance.presentation.ui.currency.CurrencyState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val _state = MutableLiveData<CurrencyState>()
    val state: LiveData<CurrencyState>
        get() = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.value = CurrencyState.Error
    }

    init {
        viewModelScope.launch(exceptionHandler) {
            _state.value = CurrencyState.Loading
            val allCurrency = mutableListOf<CurrencyRub>()
            val bestCurrency: List<CurrencyRub> = withContext(Dispatchers.IO) {
                val usdRub = async { getUsdRubUseCase.execute() }
                val eurRub = async { getEurRubUseCase.execute() }
                val cnyRub = async { getCnyRubUseCase.execute() }
                listOf(usdRub, eurRub, cnyRub).awaitAll()
            }
            allCurrency.addAll(bestCurrency)
            _state.value = CurrencyState.CurrencyFirstPart(allCurrency)
            val secondBestCurrency: List<CurrencyRub> = withContext(Dispatchers.IO) {
                val jpyRub = async { getJpyRubUseCase.execute() }
                val gbpRub = async { getGbpRubUseCase.execute() }
                val btcRub = async { getBtcRubUseCase.execute() }
                listOf(jpyRub, gbpRub, btcRub).awaitAll()
            }
            allCurrency.addAll(secondBestCurrency)
            _state.value = CurrencyState.CurrencySecondPart(allCurrency)
            val thirdBestCurrency: List<CurrencyRub> = withContext(Dispatchers.IO) {
                val ethRub = async { getEthRubUseCase.execute() }
                val chfRub = async { getChfRubUseCase.execute() }
                val audRub = async { getAudRubUseCase.execute() }
                listOf(ethRub, chfRub, audRub).awaitAll()
            }
            allCurrency.addAll(thirdBestCurrency)
            _state.value = CurrencyState.CurrencyThirdPart(allCurrency)
            val otherCurrency: List<CurrencyRub> = withContext(Dispatchers.IO) {
                val cadRub = async { getCadRubUseCase.execute() }
                val hkdRub = async { getHkdRubUseCase.execute() }
                listOf(cadRub, hkdRub).awaitAll()
            }
            allCurrency.addAll(otherCurrency)
            _state.value = CurrencyState.CurrencyFourthPart(allCurrency)
        }
    }
}