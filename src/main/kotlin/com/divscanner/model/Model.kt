package com.divscanner.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSetter
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

data class StockHistoryStructure(@JsonSetter(value = "Time Series (Daily)") var data: Map<Date, StockHistoryEntry> = mutableMapOf())

data class StockHistoryEntry(@JsonProperty("1. open") var open: BigDecimal = BigDecimal.ZERO,
                             @JsonProperty("2. high") var high: BigDecimal = BigDecimal.ZERO,
                             @JsonProperty("3. low") var low: BigDecimal = BigDecimal.ZERO,
                             @JsonProperty("4. close") var close: BigDecimal = BigDecimal.ZERO,
                             @JsonProperty("6. volume") var volume: BigDecimal = BigDecimal.ZERO,
                             @JsonProperty("7. dividend amount") var divs: BigDecimal = BigDecimal.ZERO,
                             @JsonProperty("8. split coefficient") var split: BigDecimal = BigDecimal.ZERO
)




data class DivEvent(var date:Date,
                    var divAmount: BigDecimal,
                    var price: BigDecimal
                    /*val percent: BigDecimal*/) {
   var divYearPercent: BigDecimal = BigDecimal.valueOf(0)
       get()  = price.div(divAmount).setScale(4, RoundingMode.DOWN).multiply(BigDecimal.valueOf(4*100))
}

