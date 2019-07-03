package com.divscanner

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class DivscannerApplicationTests {

    val apiKey = "XCV1XX716R7YTPJC"
    val dataKey = "Time Series (Daily)"
    val full = true

    @Test
    fun contextLoads() {
        val outputSizeToken = if (full) "&outputsize=full" else ""
        val serverUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED${outputSizeToken}&symbol={ticker}&apikey=$apiKey"
        println(serverUrl)
        val client = RestTemplate()
        val response: ResponseEntity<Dto> = client.getForEntity(serverUrl, Dto::class.java, "BMY")

        val stockData: Map<Date, InnerInfo>? = response.body?.data

        val divList: List<DivEvent>? = stockData?.filter { it.value.divs > BigDecimal.ZERO }
                ?.map { DivEvent(date = it.key, amount = it.value.divs, price = it.value.close, percent = it.value.divs.div(it.value.close).setScale(4, RoundingMode.DOWN).multiply(BigDecimal.valueOf(400))) }

        println("stop")

    }

}


