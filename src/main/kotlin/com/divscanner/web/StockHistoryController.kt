package com.divscanner.web

import com.divscanner.model.DivEvent
import com.divscanner.restclient.StockInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class StockHistoryController {

    @Autowired
    private lateinit var stockInfoService: StockInfoService

    @GetMapping("/info")
    fun  getDivEvent(): List<DivEvent> {
        return stockInfoService.getDivEvent("cfg")
    }

}