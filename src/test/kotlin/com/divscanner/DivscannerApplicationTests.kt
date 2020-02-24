package com.divscanner

import com.divscanner.model.StockHistoryEntry
import com.divscanner.model.StockHistoryMap
import com.divscanner.model.toDivEventList
import com.divscanner.restclient.StockHistoryType
import com.divscanner.services.StockHistoryRestClient
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class DivscannerApplicationTests {


    @Qualifier("restTemplate")
    @Autowired
    private lateinit var restTemplate : RestOperations
    @Autowired
    private lateinit var stockHistoryRestClient: StockHistoryRestClient

    val apiKey = "XCV1XX716R7YTPJC"//XCV1XX716R7YTPJC
    val dataKey = "Time Series (Daily)"
    val full = true
    val tickerList = listOf("AAPL", "VLO", "ET", "ABBV", "MMM", "ALB", "AGN", "MO", "ADM", "T")



    @Test
    fun contextLoads() {
        val outputSizeToken = if (full) "&outputsize=full" else ""
        val serverUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED${outputSizeToken}&symbol={ticker}&apikey=$apiKey"
        println(serverUrl)
       // val client = RestTemplate()
        val response: ResponseEntity<StockHistoryMap> = restTemplate.getForEntity(serverUrl, StockHistoryMap::class.java, "pfe")


        val httpBody = response.body ?: StockHistoryMap()
        val stockData: Map<Date, StockHistoryEntry> = httpBody.data

        val divList = stockData.toDivEventList()




        println("stop")

    }

    @Test
    fun stockHistoryClientTest() {
     val stockHistoryMap = stockHistoryRestClient.getStockHistoryMap("OXY", true, StockHistoryType.TIME_SERIES_DAILY_ADJUSTED.name, apiKey)
        println()
    }
}


