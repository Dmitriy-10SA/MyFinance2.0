package com.andef.myfinance.data.currency.mapper

import com.andef.myfinance.data.currency.dto.CurrencyRubDto
import com.andef.myfinance.domain.currency.entities.CurrencyRub

internal object CurrencyRubDtoToCurrencyRubMapper {
    fun map(currencyRubDto: CurrencyRubDto): CurrencyRub {
        return when (currencyRubDto) {
            is CurrencyRubDto.AudRubDto -> CurrencyRub.Aud(amount = currencyRubDto.audInRubDto.amount)
            is CurrencyRubDto.BtcRubDto -> CurrencyRub.Btc(amount = currencyRubDto.btcInRubDto.amount)
            is CurrencyRubDto.CadRubDto -> CurrencyRub.Cad(amount = currencyRubDto.cadInRubDto.amount)
            is CurrencyRubDto.ChfRubDto -> CurrencyRub.Chf(amount = currencyRubDto.chfInRubDto.amount)
            is CurrencyRubDto.CnyRubDto -> CurrencyRub.Cny(amount = currencyRubDto.cnyInRubDto.amount)
            is CurrencyRubDto.EthRubDto -> CurrencyRub.Eth(amount = currencyRubDto.ethInRubDto.amount)
            is CurrencyRubDto.EurRubDto -> CurrencyRub.Eur(amount = currencyRubDto.eurInRubDto.amount)
            is CurrencyRubDto.GbpRubDto -> CurrencyRub.Gbp(amount = currencyRubDto.gbpInRubDto.amount)
            is CurrencyRubDto.HkdRubDto -> CurrencyRub.Hkd(amount = currencyRubDto.hkdInRubDto.amount)
            is CurrencyRubDto.JpyRubDto -> CurrencyRub.Jpy(amount = currencyRubDto.jpyInRubDto.amount)
            is CurrencyRubDto.UsdRubDto -> CurrencyRub.Usd(amount = currencyRubDto.usdInRubDto.amount)
        }
    }
}