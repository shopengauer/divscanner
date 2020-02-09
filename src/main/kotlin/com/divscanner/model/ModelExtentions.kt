package com.divscanner.model

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

fun Map<Date, StockHistoryEntry>.toDivEventList(): List<DivEvent> {
    val filteredMap = this.filter {
        it.value.divs > BigDecimal.ZERO ||
                it.key.toInstant().toString().split('T')[0] == Date().toInstant().toString().split('T')[0]
    }

    return fillTodayDivAmount (filteredMap.map { DivEvent(date = it.key, divAmount = it.value.divs, price = it.value.close)})
            .map{it.setPercent()}
           //  percent = it.value.close.div(it.value.divs).setScale(4, RoundingMode.DOWN).multiply(BigDecimal.valueOf(4*100))) }
}


fun fillTodayDivAmount(divEventList: List<DivEvent>): List<DivEvent> {
    val filledArray = mutableListOf<DivEvent>().plus(divEventList)
    filledArray[0].divAmount = filledArray[1].divAmount
    return filledArray
}
