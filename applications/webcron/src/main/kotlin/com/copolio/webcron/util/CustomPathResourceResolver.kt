package com.copolio.webcron.util

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.web.servlet.resource.PathResourceResolver

class CustomPathResourceResolver : PathResourceResolver() {
    override fun getResource(resourcePath: String, location: Resource): Resource {
        val requestedResource = location.createRelative(resourcePath)
        return if (requestedResource.exists() && requestedResource.isReadable) requestedResource else ClassPathResource(
            "/static/index.html"
        )
    }
}