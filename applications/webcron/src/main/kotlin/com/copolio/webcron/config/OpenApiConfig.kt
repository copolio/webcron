package com.copolio.webcron.config

import com.copolio.clients.webcronclient.dto.DefaultErrorResponse
import io.swagger.v3.core.converter.ModelConverters
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.responses.ApiResponse
import org.springdoc.core.customizers.OpenApiCustomiser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@Configuration
class OpenApiConfig {
    @Bean
    fun openApiCustomiser(): OpenApiCustomiser {
        return OpenApiCustomiser { openApi ->
            openApi.components.schemas.putAll(ModelConverters.getInstance().read(DefaultErrorResponse::class.java))
            val errorSchema = Schema<DefaultErrorResponse>()
            errorSchema.name = "DefaultError"
            errorSchema.`$ref` = "#/components/schemas/DefaultError"
            openApi.paths.values.forEach { pathItem ->
                pathItem.readOperations().forEach { operation ->
                    val apiResponses = operation.responses
                    apiResponses.addApiResponse("400", createApiResponse("Bad Request", errorSchema))
                    apiResponses.addApiResponse("500", createApiResponse("Internal Server Error", errorSchema))
                }
            }
        }
    }

    private fun createApiResponse(message: String, schema: Schema<*>): ApiResponse {
        return ApiResponse().description(message).content(
            Content().addMediaType(
                APPLICATION_JSON_VALUE,
                MediaType().schema(schema)
            )
        )
    }
}
