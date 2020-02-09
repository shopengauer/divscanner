package com.divscanner.restclient

object AlphaUrlResolver {

    private const val apiKey = "XCV1XX716R7YTPJC"

    private const val full = true

    val alphaUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED${if (full) "&outputsize=full" else ""}&symbol={ticker}&apikey=$apiKey"

}

enum class StockHistoryType{
    TIME_SERIES_DAILY_ADJUSTED
}