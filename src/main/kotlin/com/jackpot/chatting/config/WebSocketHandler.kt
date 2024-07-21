package com.jackpot.chatting.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.jackpot.chatting.dto.ChatMessage
import com.jackpot.chatting.service.ChatService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketHandler(
    private val objectMapper: ObjectMapper,
    private val chatService: ChatService,
) : TextWebSocketHandler() {

    private val log = LoggerFactory.getLogger(WebSocketHandler::class.java)

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        log.info("payload : {}", payload)

        val chatMessage = objectMapper.readValue(payload, ChatMessage::class.java)
        val room = chatService.findRoomById(chatMessage.roomId)
        room?.handleActions(session, chatMessage, chatService)
    }
}