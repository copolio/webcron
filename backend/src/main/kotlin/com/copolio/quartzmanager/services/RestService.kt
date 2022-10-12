package com.copolio.quartzmanager.services

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

interface RestService {
    fun sendRequest(url: String): String
}

@Service
class RestServiceImpl(
    restTemplateBuilder: RestTemplateBuilder
) : RestService {
    private val restTemplate: RestTemplate

    init {
        restTemplate = restTemplateBuilder.build()
    }

    override fun sendRequest(url: String): String {
        return restTemplate.getForObject(url, String)
    }
}