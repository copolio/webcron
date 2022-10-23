package com.copolio.quartzmanager.services

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

interface HttpService {
    fun sendRequest(url: String): String
    fun send(url: String, httpMethod: HttpMethod, body: String): ResponseEntity<String>
}

@Service
class HttpServiceImpl(
    restTemplateBuilder: RestTemplateBuilder
) : HttpService {
    private val restTemplate: RestTemplate

    init {
        restTemplate = restTemplateBuilder.build()
    }

    override fun sendRequest(url: String): String {
        return restTemplate.getForObject(url, String)
    }

    override fun send(url: String, httpMethod: HttpMethod, body: String): ResponseEntity<String> {
        val entity = HttpEntity(body)
        return restTemplate.exchange(url, httpMethod, entity, String::class.java)
    }
}