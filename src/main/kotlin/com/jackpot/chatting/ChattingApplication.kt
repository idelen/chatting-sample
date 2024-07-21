package com.jackpot.chatting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class ChattingApplication

fun main(args: Array<String>) {
	runApplication<ChattingApplication>(*args)
}
