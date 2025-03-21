package com.andef.myfinance.data.currency.mapper

import com.andef.myfinance.data.currency.dto.CurrencyRubDto
import com.andef.myfinance.domain.currency.entities.CurrencyRub

object CurrencyRubDtoToCurrencyRubMapper {
    fun map(currencyRubDto: CurrencyRubDto): CurrencyRub {
        return when (currencyRubDto) {
            is CurrencyRubDto.AudRubDto -> CurrencyRub.Aud(currencyRubDto.audInRubDto.amount)
            is CurrencyRubDto.BtcRubDto -> CurrencyRub.Btc(currencyRubDto.btcInRubDto.amount)
            is CurrencyRubDto.CadRubDto -> CurrencyRub.Cad(currencyRubDto.cadInRubDto.amount)
            is CurrencyRubDto.ChfRubDto -> CurrencyRub.Chf(currencyRubDto.chfInRubDto.amount)
            is CurrencyRubDto.CnyRubDto -> CurrencyRub.Cny(currencyRubDto.cnyInRubDto.amount)
            is CurrencyRubDto.EthRubDto -> CurrencyRub.Eth(currencyRubDto.ethInRubDto.amount)
            is CurrencyRubDto.EurRubDto -> CurrencyRub.Eur(currencyRubDto.eurInRubDto.amount)
            is CurrencyRubDto.GbpRubDto -> CurrencyRub.Gbp(currencyRubDto.gbpInRubDto.amount)
            is CurrencyRubDto.HkdRubDto -> CurrencyRub.Hkd(currencyRubDto.hkdInRubDto.amount)
            is CurrencyRubDto.JpyRubDto -> CurrencyRub.Jpy(currencyRubDto.jpyInRubDto.amount)
            is CurrencyRubDto.UsdRubDto -> CurrencyRub.Usd(currencyRubDto.usdInRubDto.amount)
        }
    }
}