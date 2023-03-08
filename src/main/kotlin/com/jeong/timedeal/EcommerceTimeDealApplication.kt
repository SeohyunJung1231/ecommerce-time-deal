package com.jeong.timedeal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class EcommerceTimeDealApplication

fun main(args: Array<String>) {
	runApplication<EcommerceTimeDealApplication>(*args)
}
