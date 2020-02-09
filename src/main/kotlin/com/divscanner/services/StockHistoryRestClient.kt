package com.divscanner.services

import com.divscanner.model.DivEvent
import com.divscanner.model.StockHistoryEntry
import com.divscanner.model.StockHistoryMap
import com.divscanner.model.toDivEventList
import com.divscanner.restclient.AlphaUrlResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

const val alphaUrl = "https://www.alphavantage.co/query?function={function}&outputsize={full}&symbol={ticker}&apikey={apiKey}"

@Service
class StockHistoryRestClient {

    @Autowired
    lateinit var restTemplate: RestTemplate

    fun getStockHistoryMap(ticker: String, isFullData: Boolean, function: String, apiKey: String): StockHistoryMap? {
        val full = if (isFullData) "full" else ""
        return restTemplate.getForEntity(alphaUrl, StockHistoryMap::class.java, function, full, ticker, apiKey).body
    }

    fun getDivEventList(ticker: String): List<DivEvent> {
        val response: ResponseEntity<StockHistoryMap> = restTemplate.getForEntity(AlphaUrlResolver.alphaUrl, StockHistoryMap::class.java, ticker)
        val httpBody = response.body ?: StockHistoryMap()
        val stockData: Map<Date, StockHistoryEntry> = httpBody.data
        return stockData.toDivEventList()
    }


}