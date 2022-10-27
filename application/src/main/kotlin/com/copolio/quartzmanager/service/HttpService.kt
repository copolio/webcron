package com.copolio.quartzmanager.service

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

interface HttpService {
    fun send(url: String, httpMethod: HttpMethod, body: String?): ResponseEntity<String>
}

@Service
class HttpServiceImpl(
    restTemplateBuilder: RestTemplateBuilder
) : HttpService {
    private val restTemplate: RestTemplate

    init {
        restTemplate = restTemplateBuilder.build()
    }

    override fun send(url: String, httpMethod: HttpMethod, body: String?): ResponseEntity<String> {
        val entity = if (body.isNullOrBlank()) null else HttpEntity(body)
        return restTemplate.exchange(URI(url), httpMethod, entity, String::class.java)
    }
}