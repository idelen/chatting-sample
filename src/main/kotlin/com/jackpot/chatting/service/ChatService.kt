package com.jackpot.chatting.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.jackpot.chatting.dto.ChatRoom
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

@Service
class ChatService(
    private val objectMapper: ObjectMapper,
) {

    private val log = LoggerFactory.getLogger(ChatService::class.java)
    private lateinit var chatRooms: MutableMap<String, ChatRoom>

    @PostConstruct
    fun init() {
        chatRooms = LinkedHashMap()
    }

    fun findAllRoom(): List<ChatRoom> {
        return ArrayList(chatRooms.values)
    }

    fun findRoomById(roomId: String): ChatRoom? {
        return chatRooms[roomId]
    }

    fun createRoom(name: String): ChatRoom {
        val randomId = UUID.randomUUID().toString()
        val chatRoom = ChatRoom(
            roomId = randomId,
            name = name
        )
        chatRooms[randomId] = chatRoom
        return chatRoom
    }

    fun <T> sendMessage(session: WebSocketSession, message: T) {
        try {
            session.sendMessage(TextMessage(objectMapper.writeValueAsString(message)))
        } catch (e: IOException) {
            log.error(e.message, e)
        }
    }
}