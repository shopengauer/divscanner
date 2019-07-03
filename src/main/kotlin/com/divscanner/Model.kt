package com.divscanner

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSetter
import java.math.BigDecimal
import java.util.*

data class Dto(@JsonSetter(value = "Time Series (Daily)") var data: Map<Date, InnerInfo> = mutableMapOf())

data class InnerInfo(@JsonProperty("1. open") var open: BigDecimal,
                     @JsonProperty("2. high") var high: BigDecimal,
                     @JsonProperty("3. low") var low: BigDecimal,
                     @JsonProperty("4. close") var close: BigDecimal,
                     @JsonProperty("6. volume") var volume: BigDecimal,
                     @JsonProperty("7. dividend amount") var divs: BigDecimal,
                     @JsonProperty("8. split coefficient") var split: BigDecimal
)

data class DivEvent(var date:Date, var amount: BigDecimal, var price: BigDecimal,  val percent: BigDecimal)

