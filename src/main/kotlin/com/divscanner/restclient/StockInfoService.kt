package com.divscanner.restclient

import com.divscanner.model.DivEvent
import com.divscanner.model.StockHistoryMap
import com.divscanner.model.toDivEventList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestOperations

@Service
class StockInfoService {

    @Qualifier("restTemplate")
    @Autowired
    private lateinit var restTemplate: RestOperations

    fun getDivEvent(ticker: String) : List<DivEvent> {
        val serverUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&outputsize={size}&symbol={ticker}&apikey={key}"
        val response: ResponseEntity<StockHistoryMap> =
                restTemplate.getForEntity(serverUrl, StockHistoryMap::class.java, "full", ticker, "XCV1XX716R7YTPJC")
        val httpBody = response.body ?: StockHistoryMap()
        return httpBody.data.toDivEventList()
    }

}