package com.jackpot.chatting.repository

import com.jackpot.chatting.dto.ChatRoom
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.LinkedHashMap

@Repository
class ChatRoomRepository {
    private lateinit var chatRoomMap: MutableMap<String, ChatRoom>

    @PostConstruct
    fun init() {
        chatRoomMap = LinkedHashMap()
    }

    fun findAllRoom() : List<ChatRoom> {
        val chatRooms = chatRoomMap.values.toList()
        chatRooms.reversed()
        return chatRooms
    }

    fun findRoomById(id: String): ChatRoom? {
        return chatRoomMap[id]
    }

    fun createChatRoom(name: String): ChatRoom {
        val chatRoom = ChatRoom.create(name)
        chatRoomMap[chatRoom.roomId] = chatRoom
        return chatRoom
    }
}