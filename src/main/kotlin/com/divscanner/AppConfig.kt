package com.divscanner

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate

@Configuration
class AppConfig {

    @Bean
    internal fun restTemplate(): RestOperations = RestTemplate()


    @Bean
    internal fun objectReader(): ObjectReader
    {
        val objectMapper = ObjectMapper()
        return objectMapper.reader()
    }
}