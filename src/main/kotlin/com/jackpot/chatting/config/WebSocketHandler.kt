package com.jackpot.chatting.config

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketHandler : TextWebSocketHandler() {

    private val log = LoggerFactory.getLogger(WebSocketHandler::class.java)

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        log.info("payload : {}", payload)

        val textMessage = TextMessage("Welcome!")
        session.sendMessage(textMessage)
    }
}