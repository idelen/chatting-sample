package com.jackpot.chatting.dto

import com.jackpot.chatting.service.ChatService
import org.springframework.web.socket.WebSocketSession
import java.util.concurrent.ConcurrentHashMap

data class ChatRoom(
    var roomId: String,
    var name: String,
    val sessions: MutableSet<WebSocketSession> = ConcurrentHashMap.newKeySet()
) {
    fun handleActions(session: WebSocketSession, chatMessage: ChatMessage, chatService: ChatService) {
        if (chatMessage.type == ChatMessage.MessageType.ENTER) {
            sessions.add(session)
            chatMessage.message = "${chatMessage.sender}님이 입장했습니다."
        }
        sendMessage(chatMessage, chatService)
    }

    fun <T> sendMessage(message: T, chatService: ChatService) {
        sessions.parallelStream().forEach { session -> chatService.sendMessage(session, message) }
    }
}
