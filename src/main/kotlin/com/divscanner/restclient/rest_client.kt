package com.divscanner.restclient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestClientConfig {

    @Bean
    fun restTemplate() = RestTemplate()

}



