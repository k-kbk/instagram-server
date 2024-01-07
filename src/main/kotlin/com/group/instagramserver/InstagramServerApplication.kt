package com.group.instagramserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InstagramServerApplication

fun main(args: Array<String>) {
    runApplication<InstagramServerApplication>(*args)
}