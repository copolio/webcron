package com.copolio.apiquartz.controllers

import org.quartz.SchedulerException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(SchedulerException::class)
    fun handleSchedulerException(
        ex: SchedulerException
    ): ResponseEntity<String> {
        return ResponseEntity.badRequest()
            .body(ex.message)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(
        ex: NoSuchElementException
    ): ResponseEntity<String> {
        return ResponseEntity.notFound().build()
    }
}