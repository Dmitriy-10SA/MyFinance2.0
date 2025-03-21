package com.andef.myfinance.old_presentation.viewmodel.currency
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.andef.myfinance.domain.currency.CurrencyRub
//import com.andef.myfinance.domain.currency.usecases.GetAudRubUseCase
//import com.andef.myfinance.domain.currency.usecases.GetBtcRubUseCase
//import com.andef.myfinance.domain.currency.usecases.GetCadRubUseCase
//import com.andef.myfinance.domain.currency.usecases.GetChfRubUseCase
//import com.andef.myfinance.domain.currency.usecases.GetCnyRubUseCase
//import com.andef.myfinance.domain.currency.usecases.GetEthRubUseCase
//import com.andef.myfinance.domain.currency.usecases.GetEurRubUseCase
//import com.andef.myfinance.domain.currency.usecases.GetGbpRubUseCase
//import com.andef.myfinance.domain.currency.usecases.GetHkdRubUseCase
//import com.andef.myfinance.domain.currency.usecases.GetJpyRubUseCase
//import com.andef.myfinance.domain.currency.usecases.GetUsdRubUseCase
//import com.andef.myfinance.presentation.ui.currency.CurrencyState
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.async
//import kotlinx.coroutines.awaitAll
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.util.Date
//import javax.inject.Inject
//
//class CurrencyViewModel @Inject constructor(
//    private val getAudRubUseCase: com.andef.myfinance.domain.currency.usecases.GetAudRubUseCase,
//    private val getBtcRubUseCase: com.andef.myfinance.domain.currency.usecases.GetBtcRubUseCase,
//    private val getCadRubUseCase: com.andef.myfinance.domain.currency.usecases.GetCadRubUseCase,
//    private val getChfRubUseCase: com.andef.myfinance.domain.currency.usecases.GetChfRubUseCase,
//    private val getCnyRubUseCase: com.andef.myfinance.domain.currency.usecases.GetCnyRubUseCase,
//    private val getEthRubUseCase: com.andef.myfinance.domain.currency.usecases.GetEthRubUseCase,
//    private val getEurRubUseCase: com.andef.myfinance.domain.currency.usecases.GetEurRubUseCase,
//    private val getGbpRubUseCase: com.andef.myfinance.domain.currency.usecases.GetGbpRubUseCase,
//    private val getJpyRubUseCase: com.andef.myfinance.domain.currency.usecases.GetJpyRubUseCase,
//    private val getUsdRubUseCase: com.andef.myfinance.domain.currency.usecases.GetUsdRubUseCase,
//    private val getHkdRubUseCase: com.andef.myfinance.domain.currency.usecases.GetHkdRubUseCase
//) : ViewModel() {
//    private val _state = MutableLiveData<CurrencyState>()
//    val state: LiveData<CurrencyState>
//        get() = _state
//
//    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
//        _state.value = CurrencyState.Error
//    }
//
//    private fun calcPercent(now: Double, before: Double) = ((now - before) / before) * 100
//
//    fun loadCurrency(date: Date) {
//        viewModelScope.launch(exceptionHandler) {
//            _state.value = CurrencyState.Loading
//            val allCurrency = mutableListOf<Pair<com.andef.myfinance.domain.currency.CurrencyRub, Double>>()
//            val bestCurrency = withContext(Dispatchers.IO) {
//                val usdRubNow = async { getUsdRubUseCase.execute() }
//                val usdRubBefore = async { getUsdRubUseCase.execute(date) }
//
//                val eurRubNow = async { getEurRubUseCase.execute() }
//                val eurRubBefore = async { getEurRubUseCase.execute(date) }
//
//                val cnyRubNow = async { getCnyRubUseCase.execute() }
//                val cnyRubBefore = async { getCnyRubUseCase.execute(date) }
//
//                val now = listOf(usdRubNow, eurRubNow, cnyRubNow).awaitAll()
//                val before = listOf(usdRubBefore, eurRubBefore, cnyRubBefore).awaitAll()
//                val outList = mutableListOf<Pair<com.andef.myfinance.domain.currency.CurrencyRub, Double>>()
//                for (i in 0..2) {
//                    outList.add(now[i] to calcPercent(now[i].amount, before[i].amount))
//                }
//                outList
//            }
//            allCurrency.addAll(bestCurrency)
//            _state.value = CurrencyState.CurrencyFirstPartWithPercent(allCurrency)
//            val secondBestCurrency = withContext(Dispatchers.IO) {
//                val jpyRubNow = async { getJpyRubUseCase.execute() }
//                val jpyRubBefore = async { getJpyRubUseCase.execute(date) }
//
//                val gbpRubNow = async { getGbpRubUseCase.execute() }
//                val gbpRubBefore = async { getGbpRubUseCase.execute(date) }
//
//                val btcRubNow = async { getBtcRubUseCase.execute() }
//                val btcRubBefore = async { getBtcRubUseCase.execute(date) }
//
//                val now = listOf(jpyRubNow, gbpRubNow, btcRubNow).awaitAll()
//                val before = listOf(jpyRubBefore, gbpRubBefore, btcRubBefore).awaitAll()
//                val outList = mutableListOf<Pair<com.andef.myfinance.domain.currency.CurrencyRub, Double>>()
//                for (i in 0..2) {
//                    outList.add(now[i] to calcPercent(now[i].amount, before[i].amount))
//                }
//                outList
//            }
//            allCurrency.addAll(secondBestCurrency)
//            _state.value = CurrencyState.CurrencySecondPartWithPercent(allCurrency)
//            val thirdBestCurrency = withContext(Dispatchers.IO) {
//                val ethRubNow = async { getEthRubUseCase.execute() }
//                val ethRubBefore = async { getEthRubUseCase.execute(date) }
//
//                val chfRubNow = async { getChfRubUseCase.execute() }
//                val chfRubBefore = async { getChfRubUseCase.execute(date) }
//
//                val audRubNow = async { getAudRubUseCase.execute() }
//                val audRubBefore = async { getAudRubUseCase.execute(date) }
//
//                val now = listOf(ethRubNow, chfRubNow, audRubNow).awaitAll()
//                val before = listOf(ethRubBefore, chfRubBefore, audRubBefore).awaitAll()
//                val outList = mutableListOf<Pair<com.andef.myfinance.domain.currency.CurrencyRub, Double>>()
//                for (i in 0..2) {
//                    outList.add(now[i] to calcPercent(now[i].amount, before[i].amount))
//                }
//                outList
//            }
//            allCurrency.addAll(thirdBestCurrency)
//            _state.value = CurrencyState.CurrencyThirdPartWithPercent(allCurrency)
//            val otherCurrency = withContext(Dispatchers.IO) {
//                val cadRubNow = async { getCadRubUseCase.execute() }
//                val cadRubBefore = async { getCadRubUseCase.execute(date) }
//
//                val hkdRubNow = async { getHkdRubUseCase.execute() }
//                val hkdRubBefore = async { getHkdRubUseCase.execute(date) }
//
//                val now = listOf(cadRubNow, hkdRubNow).awaitAll()
//                val before = listOf(cadRubBefore, hkdRubBefore).awaitAll()
//                val outList = mutableListOf<Pair<com.andef.myfinance.domain.currency.CurrencyRub, Double>>()
//                for (i in 0..1) {
//                    outList.add(now[i] to calcPercent(now[i].amount, before[i].amount))
//                }
//                outList
//            }
//            allCurrency.addAll(otherCurrency)
//            _state.value = CurrencyState.CurrencyFourthPartWithPercent(allCurrency)
//        }
//    }
//}