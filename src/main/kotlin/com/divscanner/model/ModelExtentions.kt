package com.divscanner.model

import java.math.BigDecimal
import java.util.*

fun Map<Date, StockHistoryEntry>.toDivEventList(): List<DivEvent> {
    return this.filter {
        it.value.divs > BigDecimal.ZERO ||
                it.key.toInstant().toString().split('T')[0] == Date().toInstant().toString().split('T')[0]
    }.map { DivEvent(date = it.key, divAmount = it.value.divs, price = it.value.close) }
}


fun fillTodayDivAmount(divEventList: List<DivEvent>): List<DivEvent> {
    val filledDivEventList: List<DivEvent> = mutableListOf()
    filledDivEventList + divEventList
    filledDivEventList[0].divAmount = filledDivEventList[1].divAmount
    return filledDivEventList
}
